package net.a11v1r15.clownraid.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ParaderEntity extends WanderingTraderEntity {
    protected @Nullable ParaderEntity follower = null;
    protected @Nullable ParaderEntity following = null;

    public ParaderEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean isLeader() { return false; }

    public ParaderEntity getFollowing() {
        return this.following;
    }

    public boolean isFollowing() {
        return this.following != null;
    }

    public boolean hasFollower() {
        return this.follower != null;
    }

    public void follow(ParaderEntity parader) {
        this.following = parader;
        this.following.follower = this;
    }

    public void stopFollowing() {
        if (this.following != null) {
            this.following.follower = null;
        }
        this.following = null;
    }
}