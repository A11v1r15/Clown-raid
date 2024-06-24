package net.a11v1r15.clownraid.entity;

import dev.callmeecho.bombastic.main.registry.BombasticItemRegistrar;
import dev.doublekekse.confetti.Confetti;
import dev.doublekekse.confetti.math.Vec3Dist;
import dev.doublekekse.confetti.packet.ExtendedParticlePacket;
import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import symbolics.division.honque.Honque;

public class ClownEntity extends ParaderEntity {
    public ClownEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        ItemStack headItem = switch (this.random.nextInt(4)){
            case 0 -> new ItemStack(BombasticItemRegistrar.CLOWN_HAIR);
            case 1 -> new ItemStack(Honque.THE_GREEN_FUNNY);
            case 2 -> new ItemStack(Honque.THE_BLUE_FUNNY);
            default -> new ItemStack(Honque.THE_FUNNY);
        };
        this.equipStack(EquipmentSlot.HEAD, headItem);
        if (this.random.nextInt(4) == 0)
            this.equipStack(EquipmentSlot.FEET, new ItemStack(BombasticItemRegistrar.CLOWN_BOOTS));
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
        TradeOffers.Factory[] factories3 = ClownRaidTrades.CLOWN_TRADES.get(3);
        if (factories != null && factories2 != null && factories3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factories, 3);
            int i = this.random.nextInt(factories2.length);
            int j = this.random.nextInt(factories3.length);
            TradeOffers.Factory factory1 = factories2[i];
            TradeOffers.Factory factory2 = factories3[j];
            TradeOffer tradeOffer1 = factory1.create(this, this.random);
            TradeOffer tradeOffer2 = factory2.create(this, this.random);
            if (tradeOffer1 != null) {tradeOfferList.add(tradeOffer1);}
            if (tradeOffer2 != null) {tradeOfferList.add(tradeOffer2);}
        }
    }

    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience() && this.getWorld() instanceof ServerWorld serverWorld) {
            Vec3Dist posDist = new Vec3Dist(this.getPos(), 1.0);
            Vec3Dist velDist = new Vec3Dist(new Vec3d(0.0, 1.0, 0.0), 0.2);
            serverWorld.getPlayers().forEach((player) -> ServerPlayNetworking.send(player, new ExtendedParticlePacket(posDist, velDist, offer.getMerchantExperience() * 100, true, Confetti.CONFETTI)));
        }
        super.afterUsing(offer);
    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? ClownRaid.ENTITY_CLOWN_TRADE : ClownRaid.ENTITY_CLOWN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ClownRaid.ENTITY_CLOWN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ClownRaid.ENTITY_CLOWN_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.isOf(Items.MILK_BUCKET) ? ClownRaid.ENTITY_CLOWN_DRINK_MILK : ClownRaid.ENTITY_CLOWN_DRINK_POTION;
    }

    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ClownRaid.ENTITY_CLOWN_YES : ClownRaid.ENTITY_CLOWN_NO;
    }

    public SoundEvent getYesSound() {
        return ClownRaid.ENTITY_CLOWN_YES;
    }
}
