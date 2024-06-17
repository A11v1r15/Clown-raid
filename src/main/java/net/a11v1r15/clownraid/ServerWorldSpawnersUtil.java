package net.a11v1r15.clownraid;

import net.a11v1r15.clownraid.mixin.ServerWorldAccessor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.SpecialSpawner;

import java.util.ArrayList;
import java.util.List;

public final class ServerWorldSpawnersUtil
{
    public static void register(ServerWorld world, SpecialSpawner spawner) {
        List<SpecialSpawner> spawnerList = new ArrayList<>(((ServerWorldAccessor) world).getSpawners());
        spawnerList.add(spawner);
        ((ServerWorldAccessor) world).setSpawners(spawnerList);
        ClownRaid.LOGGER.info(((ServerWorldAccessor) world).getSpawners().toString());
    }

    private ServerWorldSpawnersUtil() {
    }
}