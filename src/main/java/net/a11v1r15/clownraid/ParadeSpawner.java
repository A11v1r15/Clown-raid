package net.a11v1r15.clownraid;

import java.util.Optional;

import net.a11v1r15.clownraid.entity.ParaderEntity;
import net.a11v1r15.clownraid.entity.PresenterEntity;
import net.minecraft.entity.SpawnLocation;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldView;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.spawner.SpecialSpawner;
import org.jetbrains.annotations.Nullable;

public class ParadeSpawner implements SpecialSpawner {
    private static final int DEFAULT_SPAWN_TIMER = 1200;
    public static final int DEFAULT_SPAWN_DELAY = 24000;
    private static final int MIN_SPAWN_CHANCE = 25;
    private static final int MAX_SPAWN_CHANCE = 75;
    private static final int DEFAULT_SPAWN_CHANCE = 25;
    private static final int field_30635 = 10;
    private static final int field_30636 = 10;
    private final Random random = Random.create();
    private int spawnTimer;
    private int spawnDelay;
    private int spawnChance;

    public ParadeSpawner() {
        this.spawnTimer = DEFAULT_SPAWN_TIMER;
        this.spawnDelay = DEFAULT_SPAWN_DELAY;
        this.spawnChance = DEFAULT_SPAWN_CHANCE;
    }

    @Override
    public int spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
        if (!world.getGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING)) {
            return 0;
        } else if (--this.spawnTimer > 0) {
            return 0;
        } else {
            this.spawnTimer = DEFAULT_SPAWN_TIMER;
            this.spawnDelay -= DEFAULT_SPAWN_TIMER;
            if (this.spawnDelay > 0) {
                return 0;
            } else {
                this.spawnDelay = DEFAULT_SPAWN_DELAY;
                if (!world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                    return 0;
                } else {
                    int i = this.spawnChance;
                    this.spawnChance = MathHelper.clamp(this.spawnChance + DEFAULT_SPAWN_CHANCE, MIN_SPAWN_CHANCE, MAX_SPAWN_CHANCE);
                    if (this.random.nextInt(100) > i) {
                        return 0;
                    } else if (this.trySpawn(world)) {
                        this.spawnChance = DEFAULT_SPAWN_CHANCE;
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

    private boolean trySpawn(ServerWorld world) {
        PlayerEntity playerEntity = world.getRandomAlivePlayer();
        if (playerEntity == null) {
            return true;
        } else if (this.random.nextInt(15) != 0) {
            return false;
        } else {
            BlockPos blockPos = playerEntity.getBlockPos();
            int i = 48;
            PointOfInterestStorage pointOfInterestStorage = world.getPointOfInterestStorage();
            Optional<BlockPos> optional = pointOfInterestStorage.getPosition(
                    poiType -> poiType.matchesKey(PointOfInterestTypes.MEETING), pos -> true, blockPos, 48, PointOfInterestStorage.OccupationStatus.ANY
            );
            BlockPos blockPos2 = (BlockPos)optional.orElse(blockPos);
            BlockPos blockPos3 = this.getNearbySpawnPos(world, blockPos2, 48);
            if (blockPos3 != null && this.doesNotSuffocateAt(world, blockPos3)) {
                PresenterEntity presenterEntity = ClownRaid.PRESENTER.spawn(world, blockPos3, SpawnReason.EVENT);
                if (presenterEntity != null) {
                    for (int j = 0; j < 7; j++) {
                        ParaderEntity parader = this.spawnParader(world, presenterEntity);
                        if (parader != null) {
                            parader.setDespawnDelay(DEFAULT_SPAWN_DELAY);
                            parader.setWanderTarget(blockPos2);
                            parader.setPositionTarget(blockPos2, 16);
                        }
                    }

                    presenterEntity.setDespawnDelay(DEFAULT_SPAWN_DELAY);
                    presenterEntity.setWanderTarget(blockPos2);
                    presenterEntity.setPositionTarget(blockPos2, 16);
                    return true;
                }
            }
            return false;
        }
    }

    private ParaderEntity spawnParader(ServerWorld world, PresenterEntity presenter) {
        BlockPos blockPos = this.getNearbySpawnPos(world, presenter.getBlockPos(), 4);
        if (blockPos != null) {
            return switch (this.random.nextInt(3)) {
                case 0 -> ClownRaid.MARCHER.spawn(world, blockPos, SpawnReason.EVENT);
                case 1 -> ClownRaid.SELLER.spawn(world, blockPos, SpawnReason.EVENT);
                case 2 -> ClownRaid.CLOWN.spawn(world, blockPos, SpawnReason.EVENT);
                default -> null;
            };
        }
        return null;
    }

    @Nullable
    private BlockPos getNearbySpawnPos(WorldView world, BlockPos pos, int range) {
        BlockPos blockPos = null;
        SpawnLocation spawnLocation = SpawnRestriction.getLocation(ClownRaid.PRESENTER);

        for (int i = 0; i < 10; i++) {
            int j = pos.getX() + this.random.nextInt(range * 2) - range;
            int k = pos.getZ() + this.random.nextInt(range * 2) - range;
            int l = world.getTopY(Heightmap.Type.WORLD_SURFACE, j, k);
            BlockPos blockPos2 = new BlockPos(j, l, k);
            if (spawnLocation.isSpawnPositionOk(world, blockPos2, ClownRaid.PRESENTER)) {
                blockPos = blockPos2;
                break;
            }
        }
        return blockPos;
    }

    private boolean doesNotSuffocateAt(BlockView world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.iterate(pos, pos.add(1, 2, 1))) {
            if (!world.getBlockState(blockPos).getCollisionShape(world, blockPos).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}