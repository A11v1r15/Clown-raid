package net.a11v1r15.clownraid.entity;

import dev.doublekekse.confetti.Confetti;
import dev.doublekekse.confetti.math.Vec3Dist;
import dev.doublekekse.confetti.packet.ExtendedParticlePacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ParaderEntity extends WanderingTraderEntity {
    protected @Nullable ParaderEntity follower = null;
    protected @Nullable ParaderEntity following = null;
    protected boolean hasNoTrades = true;

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

    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience() && this.getWorld() instanceof ServerWorld serverWorld) {
            Vec3Dist posDist = new Vec3Dist(this.getPos(), 1.0);
            Vec3Dist velDist = new Vec3Dist(new Vec3d(0.0, 1.0, 0.0), 0.2);
            serverWorld.getPlayers().forEach((player) -> ServerPlayNetworking.send(player, new ExtendedParticlePacket(posDist, velDist, offer.getMerchantExperience() * 100, true, Confetti.CONFETTI)));
            if(this.getOffers().isEmpty()) hasNoTrades = true;
        }
        super.afterUsing(offer);
    }

    protected void sayNo() {
        this.setHeadRollingTimeLeft(40);
        if (!this.getWorld().isClient()) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_NO);
        }
    }
}