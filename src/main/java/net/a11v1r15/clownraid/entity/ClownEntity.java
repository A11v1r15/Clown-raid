package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidTrades;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.a11v1r15.clownraid.util.ClownRaidTrading;
import net.a11v1r15.clownraid.util.RegistryHelper;
import net.fabricmc.loader.api.FabricLoader;
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

public class ClownEntity extends ParaderEntity {
    public ClownEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        if(FabricLoader.getInstance().isModLoaded("honque")){
            ItemStack headItem = new ItemStack(RegistryHelper.getItem(switch (this.random.nextInt(5)){
                case 0 -> "honque:the_orange_funny";
                case 1 -> "honque:the_green_funny";
                case 2 -> "honque:the_blue_funny";
                case 3 -> "honque:the_gay_funny";
                default -> "honque:the_funny";
            }));
            this.equipStack(EquipmentSlot.HEAD, headItem);
        } else if (FabricLoader.getInstance().isModLoaded("bombastic")) {
            this.equipStack(EquipmentSlot.HEAD, new ItemStack(RegistryHelper.getItem("bombastic:clown_hair")));
        }
        if (FabricLoader.getInstance().isModLoaded("bombastic"))
            this.equipStack(EquipmentSlot.FEET, new ItemStack(RegistryHelper.getItem("bombastic:clown_boots")));
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
                if (this.getOffers().isEmpty()) {
                    this.sayNo();
                }

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
        TradeOffers.Factory[] factories =  ClownRaidTrades.CLOWN_TRADES.get("Common");
        TradeOffers.Factory[] factories2 = ClownRaidTrades.CLOWN_TRADES.get("Premium");
        TradeOffers.Factory[] factories3 = ClownRaidTrades.CLOWN_TRADES.get("The Funny");
        if (factories != null){
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factories, 3);
        }
        if (factories2 != null){
            TradeOfferList tradeOfferList = this.getOffers();
            int i = this.random.nextInt(factories2.length);
            TradeOffers.Factory factory1 = factories2[i];
            TradeOffer tradeOffer1 = factory1.create(this, this.random);
            if (tradeOffer1 != null) {tradeOfferList.add(tradeOffer1);}
        }
        if (factories3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            int j = this.random.nextInt(factories3.length);
            TradeOffers.Factory factory2 = factories3[j];
            TradeOffer tradeOffer2 = factory2.create(this, this.random);
            if (tradeOffer2 != null) {
                TradeOffer honqueTrade = new ClownRaidTrading.Factory(
                        tradeOffer2.getFirstBuyItem(),
                        getEquippedStack(EquipmentSlot.HEAD).getItem(),
                        1, tradeOffer2.getMaxUses(), tradeOffer2.getMerchantExperience(),
                        null, 0
                ).create(this, this.random);
                tradeOfferList.add(honqueTrade);
            }
        }
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
