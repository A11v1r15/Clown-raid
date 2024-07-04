package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
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

public class MagicianEntity extends ParaderEntity {
    public MagicianEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
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
        this.goalSelector.add(2, new FormParadeGoal(this, 1.25));
        this.goalSelector.add(3, new WanderToTargetGoal(this, 2.0, 0.35));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 0.35));
        this.goalSelector.add(9, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(ClownRaid.MAGICIAN_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
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
            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    protected void fillRecipes() {
        TradeOffers.Factory[] factoryPoolCommon =  ClownRaidTrades.MAGICIAN_TRADES.get("Common");
        TradeOffers.Factory[] factoryPoolPremium = ClownRaidTrades.MAGICIAN_TRADES.get("Premium");
        TradeOffers.Factory[] factoryPoolHat = ClownRaidTrades.MAGICIAN_TRADES.get("Hat");
        if (factoryPoolCommon != null){
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factoryPoolCommon, 3);
        }
        if (factoryPoolPremium != null){
            TradeOfferList tradeOfferList = this.getOffers();
            int i = this.random.nextInt(factoryPoolPremium.length);
            TradeOffers.Factory premiumFactory = factoryPoolPremium[i];
            TradeOffer premiumTradeOffer = premiumFactory.create(this, this.random);
            if (premiumTradeOffer != null) {tradeOfferList.add(premiumTradeOffer);}
        }
        if (factoryPoolHat != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            int j = this.random.nextInt(factoryPoolHat.length);
            TradeOffers.Factory hatFactory = factoryPoolHat[j];
            TradeOffer hatTradeOffer = hatFactory.create(this, this.random);
            if (hatTradeOffer != null) {
                tradeOfferList.add(hatTradeOffer);
                this.equipStack(EquipmentSlot.HEAD, hatTradeOffer.copySellItem());
            }
        }
        hasNoTrades = this.getOffers().isEmpty();
    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? ClownRaid.ENTITY_MAGICIAN_TRADE : ClownRaid.ENTITY_MAGICIAN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ClownRaid.ENTITY_MAGICIAN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ClownRaid.ENTITY_MAGICIAN_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.isOf(Items.MILK_BUCKET) ? ClownRaid.ENTITY_MAGICIAN_DRINK_MILK : ClownRaid.ENTITY_MAGICIAN_DRINK_POTION;
    }

    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ClownRaid.ENTITY_MAGICIAN_YES : ClownRaid.ENTITY_MAGICIAN_NO;
    }

    public SoundEvent getYesSound() {
        return ClownRaid.ENTITY_MAGICIAN_YES;
    }

    public SoundEvent getNoSound() {
        return ClownRaid.ENTITY_MAGICIAN_NO;
    }
}
