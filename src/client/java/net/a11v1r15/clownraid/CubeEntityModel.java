package net.a11v1r15.clownraid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WanderingTraderEntity;

@Environment(EnvType.CLIENT)
public class CubeEntityModel<T extends CubeEntity> extends VillagerResemblingModel<T> {
    public CubeEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        return TexturedModelData.of(modelData, 64, 128);
    }
}