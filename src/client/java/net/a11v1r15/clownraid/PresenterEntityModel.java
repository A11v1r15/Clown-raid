package net.a11v1r15.clownraid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;

@Environment(EnvType.CLIENT)
public class PresenterEntityModel<T extends PresenterEntity> extends VillagerResemblingModel<T> {
    public PresenterEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData root  = modelData.getRoot();
        ModelPartData head  = root.getChild(EntityModelPartNames.HEAD);
        ModelPartData hat = head.getChild(EntityModelPartNames.HAT);
        ModelPartData hat_rim = hat.addChild(EntityModelPartNames.HAT_RIM,  ModelPartBuilder.create().uv(44, 53).cuboid(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 1.0F, new Dilation(0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
    
        return TexturedModelData.of(modelData, 64, 64);
    }
}
