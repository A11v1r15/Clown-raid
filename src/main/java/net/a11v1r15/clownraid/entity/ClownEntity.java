package net.a11v1r15.clownraid.entity;

import dev.doublekekse.confetti.Confetti;
import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
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
import symbolics.division.honque.Honque;

public class ClownEntity extends ParaderEntity {
    public ClownEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        ItemStack theFunny = new ItemStack(Honque.THE_FUNNY);
        this.equipStack(EquipmentSlot.HEAD, theFunny);
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
        if (!itemStack.isOf(ClownRaid.CLOWN_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
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

    protected void fillRecipes() {
        TradeOffers.Factory[] factories =  ClownRaidTrades.CLOWN_TRADES.get(1);
        TradeOffers.Factory[] factories2 = ClownRaidTrades.CLOWN_TRADES.get(2);
        if (factories != null && factories2 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factories, 2);
            int i = this.random.nextInt(factories2.length);
            TradeOffers.Factory factory = factories2[i];
            TradeOffer tradeOffer = factory.create(this, this.random);
            if (tradeOffer != null) {
                tradeOfferList.add(tradeOffer);
            }
        }
    }

    protected void afterUsing(TradeOffer offer) {
        super.afterUsing(offer);
        if (offer.shouldRewardPlayerExperience()) {
            this.getWorld().addImportantParticle(Confetti.CONFETTI, this.getX(), this.getY(), this.getZ(), 0.0, 2.0, 0.0);
        }
    }
}
