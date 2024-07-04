package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.NightSkipListenner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
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
                    if (hasNoTrades) this.sayNo();
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
        TradeOffers.Factory[] factoryPoolTickets = ClownRaidTrades.PRESENTER_TRADES.get("Tickets");
        TradeOffers.Factory[] factoryPoolTokens = ClownRaidTrades.PRESENTER_TRADES.get("Tokens");
        TradeOffers.Factory[] factoryPoolPassCard = ClownRaidTrades.PRESENTER_TRADES.get("Passcard");
        if (factoryPoolTickets != null && factoryPoolTokens != null && factoryPoolPassCard != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            int i = this.random.nextInt(factoryPoolTickets.length);
            int j = this.random.nextInt(factoryPoolTokens.length);
            int k = this.random.nextInt(factoryPoolPassCard.length);
            TradeOffers.Factory ticketsFactory = factoryPoolTickets[i];
            TradeOffers.Factory tokensFactory = factoryPoolTokens[j];
            TradeOffers.Factory passCardFactory = factoryPoolPassCard[k];
            TradeOffer ticketsTradeOffer = ticketsFactory.create(this, this.random);
            TradeOffer tokensTradeOffer = tokensFactory.create(this, this.random);
            TradeOffer passCardTradeOffer = passCardFactory.create(this, this.random);
            if (ticketsTradeOffer != null) tradeOfferList.add(ticketsTradeOffer);
            if (tokensTradeOffer != null) tradeOfferList.add(tokensTradeOffer);
            if (passCardTradeOffer != null) tradeOfferList.add(passCardTradeOffer);
        }
        hasNoTrades = this.getOffers().isEmpty();
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

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? ClownRaid.ENTITY_PRESENTER_TRADE : ClownRaid.ENTITY_PRESENTER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ClownRaid.ENTITY_PRESENTER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ClownRaid.ENTITY_PRESENTER_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.isOf(Items.MILK_BUCKET) ? ClownRaid.ENTITY_PRESENTER_DRINK_MILK : ClownRaid.ENTITY_PRESENTER_DRINK_POTION;
    }

    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ClownRaid.ENTITY_PRESENTER_YES : ClownRaid.ENTITY_PRESENTER_NO;
    }

    public SoundEvent getYesSound() {
        return ClownRaid.ENTITY_PRESENTER_YES;
    }

    public SoundEvent getNoSound() {
        return ClownRaid.ENTITY_PRESENTER_NO;
    }
}