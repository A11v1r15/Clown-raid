package net.a11v1r15.clownraid.util;

import net.a11v1r15.clownraid.mixin.ServerWorldAccessor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.SpecialSpawner;

import java.util.ArrayList;
import java.util.List;

public final class ServerWorldSpawners
{
    public static void register(ServerWorld world, SpecialSpawner spawner) {
        List<SpecialSpawner> spawnerList = new ArrayList<>(((ServerWorldAccessor) world).getSpawners());
        spawnerList.add(spawner);
        ((ServerWorldAccessor) world).setSpawners(spawnerList);
    }

    private ServerWorldSpawners() {
    }
}