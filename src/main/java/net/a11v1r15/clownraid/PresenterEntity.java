package net.a11v1r15.clownraid;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Set;

public class PresenterEntity extends WanderingTraderEntity implements NightSkipCallback{
    public PresenterEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        super.initGoals();
        Set<PrioritizedGoal> goalsToDelete = new ObjectLinkedOpenHashSet();
        Set<PrioritizedGoal> goalSet = this.goalSelector.getGoals();
        for (PrioritizedGoal goal : goalSet) {
            if(goal.getGoal() instanceof HoldInHandsGoal) goalsToDelete.add(goal);
        }
        for (PrioritizedGoal goal : goalsToDelete) {
            this.goalSelector.remove(goal);
        }
    }

    public ActionResult NightSkipped() {
        if (!this.getWorld().isClient) {
            Objects.requireNonNull(this.getServer()).getCommandManager().executeWithPrefix(this.getCommandSource(), "place structure minecraft:village_plains");
            ClownRaid.LOGGER.info("I tried to spawn a Plains village!");
        }
        return ActionResult.SUCCESS;
    }
}