package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.NightSkipListenner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;

public class PresenterEntity extends ParaderEntity implements NightSkipListenner {
    protected boolean isOwner = false;
    protected boolean hasTraded = false;

    public PresenterEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new StopFollowingCustomerGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal(this, ZombieEntity.class, 8.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, EvokerEntity.class, 12.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, VindicatorEntity.class, 8.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, VexEntity.class, 8.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, PillagerEntity.class, 15.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, IllusionerEntity.class, 12.0F, 0.5, 0.5));
        this.goalSelector.add(1, new FleeEntityGoal(this, ZoglinEntity.class, 10.0F, 0.5, 0.5));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5));
        this.goalSelector.add(1, new LookAtCustomerGoal(this));
        this.goalSelector.add(2, new WanderToTargetGoal(this, 2.0, 0.35));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 0.35));
        this.goalSelector.add(9, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(ClownRaid.PRESENTER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
            if (!this.isOwner) {
                if (hand == Hand.MAIN_HAND) {
                    player.incrementStat(Stats.TALKED_TO_VILLAGER);
                }
                if (!this.getWorld().isClient) {
                    if (this.getOffers().isEmpty()) {
                        return ActionResult.CONSUME;
                    }
                    this.setCustomer(player);
                    this.sendOffers(player, this.getDisplayName(), 1);
                }
            }
            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    protected void fillRecipes() {
        TradeOffers.Factory[] factories1 = ClownRaidTrades.PRESENTER_TRADES.get(1);
        TradeOffers.Factory[] factories2 = ClownRaidTrades.PRESENTER_TRADES.get(2);
        TradeOffers.Factory[] factories3 = ClownRaidTrades.PRESENTER_TRADES.get(3);
        if (factories1 != null && factories2 != null && factories3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            int i = this.random.nextInt(factories1.length);
            int j = this.random.nextInt(factories2.length);
            int k = this.random.nextInt(factories3.length);
            TradeOffers.Factory factory1 = factories1[i];
            TradeOffers.Factory factory2 = factories2[j];
            TradeOffers.Factory factory3 = factories3[k];
            TradeOffer tradeOffer1 = factory1.create(this, this.random);
            TradeOffer tradeOffer2 = factory2.create(this, this.random);
            TradeOffer tradeOffer3 = factory3.create(this, this.random);
            if (tradeOffer1 != null) tradeOfferList.add(tradeOffer1);
            if (tradeOffer2 != null) tradeOfferList.add(tradeOffer2);
            if (tradeOffer3 != null) tradeOfferList.add(tradeOffer3);
        }
    }

    protected void afterUsing(TradeOffer offer) {
        super.afterUsing(offer);
        this.hasTraded = true;
    }

    public void nightSkipped() {
        if (!this.getWorld().isClient && !this.isOwner && this.hasTraded) {
            //TODO: Place the Circus
        }
    }

    @Override
    public boolean isLeader() {
        return true;
    }

    @Override
    public ParaderEntity getFollowing() {
        return null;
    }

    @Override
    public boolean isFollowing() {
        return false;
    }

    @Override
    public boolean hasFollower() {
        return this.follower != null;
    }

    @Override
    public void follow(ParaderEntity parader) {
    }

    @Override
    public void stopFollowing() {
    }
}