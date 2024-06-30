package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.FormParadeGoal;
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

public class SellerEntity extends ParaderEntity {
    public SellerEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
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
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35));
        this.goalSelector.add(5, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(ClownRaid.SELLER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
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

            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }
/*
    protected void fillRecipes() {
        TradeOffers.Factory[] factories = switch (this.random.nextInt(4)) {
            case 0 -> ClownRaidTrades.SELLER_TRADES.get(2);
            case 1 -> ClownRaidTrades.SELLER_TRADES.get(3);
            case 2 -> ClownRaidTrades.SELLER_TRADES.get(4);
            default -> ClownRaidTrades.SELLER_TRADES.get(1);
        };
        if (factories != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factories, 4);
        }
    }
 */
    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? ClownRaid.ENTITY_SELLER_TRADE : ClownRaid.ENTITY_SELLER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ClownRaid.ENTITY_SELLER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ClownRaid.ENTITY_SELLER_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.isOf(Items.MILK_BUCKET) ? ClownRaid.ENTITY_SELLER_DRINK_MILK : ClownRaid.ENTITY_SELLER_DRINK_POTION;
    }

    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ClownRaid.ENTITY_SELLER_YES : ClownRaid.ENTITY_SELLER_NO;
    }

    public SoundEvent getYesSound() {
        return ClownRaid.ENTITY_SELLER_YES;
    }
}
