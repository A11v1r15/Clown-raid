package net.a11v1r15.clownraid;

import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;

public interface Parader {
    default MobEntity getEntity() {
        if (this instanceof MobEntity mobEntity) return mobEntity;
        return null;
    }
    default boolean isLeader() {
        return false;
    }
    Parader getFollowing();
    boolean isFollowing();
    boolean hasFollower();
    void setFollower(@Nullable Parader parader);
    void follow(Parader parader);
    void stopFollowing();
}