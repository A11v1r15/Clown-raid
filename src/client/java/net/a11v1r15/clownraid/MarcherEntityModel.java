package net.a11v1r15.clownraid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;

@Environment(EnvType.CLIENT)
public class MarcherEntityModel<T extends MarcherEntity> extends VillagerResemblingModel<T> {
    public MarcherEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        return TexturedModelData.of(modelData, 64, 64);
    }
}