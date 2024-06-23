package net.a11v1r15.clownraid.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class MagicianEntityModel<T extends MagicianEntity> extends VillagerResemblingModel<T> {
    public MagicianEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData root  = modelData.getRoot();
        ModelPartData head  = root.getChild(EntityModelPartNames.HEAD);
        ModelPartData hat = head.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).mirrored().cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.5F)).mirrored(false).uv(28, 54).cuboid(-4.0F, -19.0F, -4.0F, 8.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData hat_rim = hat.addChild(EntityModelPartNames.HAT_RIM, ModelPartBuilder.create().uv(48, 59).cuboid(-2.0F, 4.0F, -11.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }
}
