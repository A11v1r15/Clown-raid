package net.a11v1r15.clownraid;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.world.World;

public class CubeEntity extends WanderingTraderEntity {

    public CubeEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
    }
}