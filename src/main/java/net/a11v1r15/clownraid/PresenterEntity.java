package net.a11v1r15.clownraid;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Set;

public class PresenterEntity extends WanderingTraderEntity {
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


    public void tickMovement() {
        super.tickMovement();
        if (!this.getWorld().isClient) {
            int i = this.getWorld().getGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE);
            /*if (this.getWorld().sleepManager.canSkipNight(i) && this.sleepManager.canResetTime(i, this.getWorld().getPlayers())) {
                //TODO: Place Circus
            }*/
        }

    }
}