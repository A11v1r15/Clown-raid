package net.a11v1r15.clownraid;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class FormParadeGoal extends Goal {
    public final Parader parader;
    private double speed;
    private static final int MAX_PARADE_LENGTH = 8;
    private int counter;

    public FormParadeGoal(Parader parader, double speed) {
        this.parader = parader;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        if (!this.parader.isFollowing()) {
            List<Entity> list = this.parader.getEntity().getWorld().getOtherEntities(this.parader.getEntity(), this.parader.getEntity().getBoundingBox().expand(9.0, 4.0, 9.0), (entityx) -> {
                EntityType<?> entityType = entityx.getType();
                return entityType instanceof Parader;
            });
            Parader parader = null;
            double d = Double.MAX_VALUE;
            Iterator var5 = list.iterator();

            Entity entity;
            Parader parader2;
            double e;
            while(var5.hasNext()) {
                entity = (Entity)var5.next();
                parader2 = (Parader) entity;
                if (parader2.isFollowing() && !parader2.hasFollower()) {
                    e = this.parader.getEntity().squaredDistanceTo(parader2.getEntity());
                    if (!(e > d)) {
                        d = e;
                        parader = parader2;
                    }
                }
            }

            if (parader == null) {
                var5 = list.iterator();

                while(var5.hasNext()) {
                    entity = (Entity)var5.next();
                    parader2 = (Parader)entity;
                    if (parader2.isLeader() && !parader2.hasFollower()) {
                        e = this.parader.getEntity().squaredDistanceTo(parader2.getEntity());
                        if (!(e > d)) {
                            d = e;
                            parader = parader2;
                        }
                    }
                }
            }

            if (parader == null) {
                return false;
            } else if (d < 4.0) {
                return false;
            } else if (!parader.isLeader() && !this.canFollow(parader, 1)) {
                return false;
            } else {
                this.parader.follow(parader);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        if (this.parader.isFollowing() && this.parader.getFollowing().getEntity().isAlive() && this.canFollow(this.parader, 0)) {
            double d = this.parader.getEntity().squaredDistanceTo(this.parader.getFollowing().getEntity());
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
            Parader parader = this.parader.getFollowing();
            double d = (double)this.parader.getEntity().distanceTo(parader.getEntity());
            float f = 2.0F;
            Vec3d vec3d = (new Vec3d(parader.getEntity().getX() - this.parader.getEntity().getX(), parader.getEntity().getY() - this.parader.getEntity().getY(), parader.getEntity().getZ() - this.parader.getEntity().getZ())).normalize().multiply(Math.max(d - 2.0, 0.0));
            this.parader.getEntity().getNavigation().startMovingTo(this.parader.getEntity().getX() + vec3d.x, this.parader.getEntity().getY() + vec3d.y, this.parader.getEntity().getZ() + vec3d.z, this.speed);
        }
    }

    private boolean canFollow(Parader parader, int length) {
        if (length > 8) {
            return false;
        } else if (parader.isFollowing()) {
            if (parader.getFollowing().isLeader()) {
                return true;
            } else {
                Parader var10001 = parader.getFollowing();
                ++length;
                return this.canFollow(var10001, length);
            }
        } else {
            return false;
        }
    }
}
