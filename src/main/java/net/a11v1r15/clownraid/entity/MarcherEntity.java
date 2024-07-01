package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.a11v1r15.clownraid.util.RegistryHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;

public class MarcherEntity extends ParaderEntity {
    public MarcherEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        ArrayList<String> instruments = new ArrayList<>();
        instruments.add("minecraft:goat_horn");
        if(FabricLoader.getInstance().isModLoaded("mib")){
            instruments.add("mib:acoustic_guitar");
            instruments.add("mib:copper_goat_horn");
            instruments.add("mib:fantasy_trumpet");
            instruments.add("mib:flute");
            instruments.add("mib:harpsichord");
            instruments.add("mib:keyboard");
            instruments.add("mib:saxophone");
            instruments.add("mib:violin");
        }
        if(FabricLoader.getInstance().isModLoaded("powerchord")){
            instruments.add("powerchord:pan_flute");
            instruments.add("powerchord:harmonica");
            instruments.add("powerchord:wawa");
        }
        if(FabricLoader.getInstance().isModLoaded("wowozela")){
            instruments.add("wowozela:wowozela");
        }
        int chooseInt = this.random.nextInt(instruments.size());
        ItemStack handItem = new ItemStack(RegistryHelper.getItem(instruments.get(chooseInt)));
        if (handItem.isOf(RegistryHelper.getItem("minecraft:goat_horn")))
            GoatHornItem.setRandomInstrumentFromTag(handItem, InstrumentTags.GOAT_HORNS, this.getWorld().getRandom());
        this.equipStack(EquipmentSlot.MAINHAND, handItem);
        this.setCurrentHand(Hand.MAIN_HAND);
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

    @Override
    public boolean shouldDropLoot(){
        return false;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.dropStack(this.getEquippedStack(EquipmentSlot.MAINHAND));
    }

    protected void fillRecipes() {}

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        ItemStack myItemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        if (!itemStack.isOf(ClownRaid.MARCHER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
            if (!this.getWorld().isClient) {
                if (myItemStack.contains(DataComponentTypes.INSTRUMENT)){
                    Instrument instrument = myItemStack.getComponents().get(DataComponentTypes.INSTRUMENT).value();
                    SoundEvent soundEvent = instrument.soundEvent().value();
                    float f = instrument.range() / 16F;
                    this.getWorld().playSoundFromEntity(this, soundEvent, SoundCategory.RECORDS, f, 1.0F);
                    this.getWorld().emitGameEvent(GameEvent.INSTRUMENT_PLAY, this.getPos(), GameEvent.Emitter.of(this));
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? ClownRaid.ENTITY_MARCHER_TRADE : ClownRaid.ENTITY_MARCHER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ClownRaid.ENTITY_MARCHER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ClownRaid.ENTITY_MARCHER_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.isOf(Items.MILK_BUCKET) ? ClownRaid.ENTITY_MARCHER_DRINK_MILK : ClownRaid.ENTITY_MARCHER_DRINK_POTION;
    }

    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? ClownRaid.ENTITY_MARCHER_YES : ClownRaid.ENTITY_MARCHER_NO;
    }

    public SoundEvent getYesSound() {
        return ClownRaid.ENTITY_MARCHER_YES;
    }
}