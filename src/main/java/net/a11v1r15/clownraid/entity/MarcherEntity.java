package net.a11v1r15.clownraid.entity;

import com.chailotl.wowozela.Main;
import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.FormParadeGoal;
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

public class MarcherEntity extends ParaderEntity {
    public MarcherEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        ItemStack handItem;
        switch (this.random.nextInt(2)){
            case 0:
                handItem = new ItemStack(Items.GOAT_HORN);
                GoatHornItem.setRandomInstrumentFromTag(handItem, InstrumentTags.GOAT_HORNS, this.getWorld().getRandom());
                break;
            default:
                handItem = new ItemStack(Main.WOWOZELA);
        }
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