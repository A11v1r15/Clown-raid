package net.a11v1r15.clownraid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class MarcherEntityModel<T extends MarcherEntity> extends VillagerResemblingModel<T> {
    public MarcherEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData root  = modelData.getRoot();
        ModelPartData head  = root.getChild(EntityModelPartNames.HEAD);
        ModelPartData hat = head.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, new Dilation(0.51f)).uv(28, 54).cuboid(-4.0F, -19.0F, -4.0F, 8.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.NONE);
        ModelPartData hat_rim = hat.addChild(EntityModelPartNames.HAT_RIM, ModelPartBuilder.create().uv(38, 51).cuboid(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        
        return TexturedModelData.of(modelData, 64, 64);
    }
}
