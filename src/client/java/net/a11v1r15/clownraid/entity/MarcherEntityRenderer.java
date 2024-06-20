package net.a11v1r15.clownraid.entity;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.ClownRaidClient;
import net.a11v1r15.clownraid.MarcherHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.util.Identifier;

public class MarcherEntityRenderer extends MobEntityRenderer<WanderingTraderEntity, VillagerResemblingModel<WanderingTraderEntity>> {
    private static final Identifier TEXTURE = ClownRaid.id("textures/entity/marcher.png");

    public MarcherEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MarcherEntityModel(context.getPart(ClownRaidClient.MODEL_MARCHER_LAYER)), 0.5f);
        this.addFeature(new MarcherHeldItemFeatureRenderer(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(WanderingTraderEntity entity) {
        return TEXTURE;
    }
}