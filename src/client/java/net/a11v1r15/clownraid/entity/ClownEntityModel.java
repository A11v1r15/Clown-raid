package net.a11v1r15.clownraid.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;

@Environment(EnvType.CLIENT)
public class ClownEntityModel<T extends ClownEntity> extends VillagerResemblingModel<T> {
    public ClownEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData root  = modelData.getRoot();
        ModelPartData head  = root.getChild(EntityModelPartNames.HEAD);
        ModelPartData nose = head.addChild(EntityModelPartNames.NOSE, ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -1.0F, -6.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        ModelPartData hat = head.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData hat_rim = hat.addChild(EntityModelPartNames.HAT_RIM, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        ModelPartData body = root.getChild(EntityModelPartNames.BODY);
        ModelPartData jacket = 	body.addChild(EntityModelPartNames.JACKET, ModelPartBuilder.create().uv(0, 38).cuboid(-4.0F, -24.0F, -3.0F, 8.0F, 13.0F, 6.0F, new Dilation(0.75F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData right_leg = root.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 53).cuboid(-2.0F, 9.0F, -6.0F, 4.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
        ModelPartData left_leg = root.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(40, 53).mirrored().cuboid(-2.0F, 9.0F, -6.0F, 4.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }
}
