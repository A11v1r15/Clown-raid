package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.FormParadeGoal;
import net.a11v1r15.clownraid.Parader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MarcherEntity extends WanderingTraderEntity implements Parader {
    private @Nullable Parader follower = null;
    private @Nullable Parader followee = null;

    public MarcherEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOAT_HORN));
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
        this.goalSelector.add(2, new FormParadeGoal(this, 2.0999999046325684));
        this.goalSelector.add(3, new WanderToTargetGoal(this, 2.0, 0.35));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 0.35));
        this.goalSelector.add(9, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    protected void fillRecipes() {}

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(ClownRaid.MARCHER_SPAWN_EGG) && this.isAlive() && !this.hasCustomer() && !this.isBaby()) {
            if (!this.getWorld().isClient) {
                this.getEquippedStack(EquipmentSlot.MAINHAND).use(this.getWorld(), player, Hand.MAIN_HAND);
                return ActionResult.CONSUME;
            }
            return ActionResult.success(this.getWorld().isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public Parader getFollowing() {
        return this.followee;
    }

    @Override
    public boolean isFollowing() {
        return this.followee != null;
    }

    @Override
    public boolean hasFollower() {
        return this.follower != null;
    }

    @Override
    public void setFollower(@Nullable Parader parader) {
        this.follower = parader;
    }

    @Override
    public void follow(Parader parader) {
        this.followee = parader;
        this.followee.setFollower(this);
    }

    @Override
    public void stopFollowing() {
        if (this.followee != null) {
            this.followee.setFollower(this);
        }

        this.followee = null;
    }
}