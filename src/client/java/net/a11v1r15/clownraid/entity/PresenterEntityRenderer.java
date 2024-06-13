package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.util.Identifier;

public class PresenterEntityRenderer extends MobEntityRenderer<WanderingTraderEntity, VillagerResemblingModel<WanderingTraderEntity>> {
    private static final Identifier TEXTURE = ClownRaid.id("textures/entity/presenter.png");

    public PresenterEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PresenterEntityModel(context.getPart(ClownRaidClient.MODEL_PRESENTER_LAYER)), 0.5f);
        this.addFeature(new VillagerHeldItemFeatureRenderer(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(WanderingTraderEntity entity) {
        return TEXTURE;
    }
}