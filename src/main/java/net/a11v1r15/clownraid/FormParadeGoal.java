package net.a11v1r15.clownraid;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.a11v1r15.clownraid.entity.ParaderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.util.math.Vec3d;

public class FormParadeGoal extends Goal {
    public final ParaderEntity parader;
    private double speed;
    private static final int MAX_PARADE_LENGTH = 8;
    private int counter;

    public FormParadeGoal(ParaderEntity parader, double speed) {
        this.parader = parader;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        if (!this.parader.isLeader() && !this.parader.isFollowing()) {
            List<Entity> list = this.parader.getWorld().getOtherEntities(this.parader, this.parader.getBoundingBox().expand(9.0, 4.0, 9.0), (entityx) -> entityx instanceof ParaderEntity);
            ParaderEntity paraderEntity = null;
            double d = Double.MAX_VALUE;
            Iterator var5 = list.iterator();

            Entity entity;
            ParaderEntity paraderEntity2;
            double e;
            while(var5.hasNext()) {
                entity = (Entity)var5.next();
                paraderEntity2 = (ParaderEntity)entity;
                if (paraderEntity2.isFollowing() && !paraderEntity2.hasFollower()) {
                    e = this.parader.squaredDistanceTo(paraderEntity2);
                    if (!(e > d)) {
                        d = e;
                        paraderEntity = paraderEntity2;
                    }
                }
            }

            if (paraderEntity == null) {
                var5 = list.iterator();

                while(var5.hasNext()) {
                    entity = (Entity)var5.next();
                    paraderEntity2 = (ParaderEntity)entity;
                    if (paraderEntity2.isLeader() && !paraderEntity2.hasFollower()) {
                        e = this.parader.squaredDistanceTo(paraderEntity2);
                        if (!(e > d)) {
                            d = e;
                            paraderEntity = paraderEntity2;
                        }
                    }
                }
            }

            if (paraderEntity == null) {
                return false;
            } else if (d < 4.0) {
                return false;
            } else if (!paraderEntity.isLeader() && !this.canFollow(paraderEntity, 1)) {
                return false;
            } else {
                this.parader.follow(paraderEntity);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        if (this.parader.isFollowing() && this.parader.getFollowing().isAlive() && this.canFollow(this.parader, 0)) {
            double d = this.parader.squaredDistanceTo(this.parader.getFollowing());
            if (d > 676.0) {
                if (this.speed <= 3.0) {
                    this.speed *= 1.2;
                    this.counter = toGoalTicks(40);
                    return true;
                }

                if (this.counter == 0) {
                    return false;
                }
            }

            if (this.counter > 0) {
                --this.counter;
            }

            return true;
        } else {
            return false;
        }
    }

    public void stop() {
        this.parader.stopFollowing();
        this.speed = 2.1;
    }

    public void tick() {
        if (this.parader.isFollowing()) {
            if (!(this.parader.getLeashHolder() instanceof LeashKnotEntity)) {
                ParaderEntity paraderEntity = this.parader.getFollowing();
                double d = (double)this.parader.distanceTo(paraderEntity);
                float f = 2.0F;
                Vec3d vec3d = (new Vec3d(paraderEntity.getX() - this.parader.getX(), paraderEntity.getY() - this.parader.getY(), paraderEntity.getZ() - this.parader.getZ())).normalize().multiply(Math.max(d - 2.0, 0.0));
                this.parader.getNavigation().startMovingTo(this.parader.getX() + vec3d.x, this.parader.getY() + vec3d.y, this.parader.getZ() + vec3d.z, this.speed);
            }
        }
    }

    private boolean canFollow(ParaderEntity parader, int length) {
        if (length > MAX_PARADE_LENGTH) {
            return false;
        } else if (parader.isFollowing()) {
            if (parader.getFollowing().isLeader()) {
                return true;
            } else {
                ParaderEntity following = parader.getFollowing();
                ++length;
                return this.canFollow(following, length);
            }
        } else {
            return false;
        }
    }
}
