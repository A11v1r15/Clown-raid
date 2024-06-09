package net.a11v1r15.clownraid;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.util.Identifier;

public class SellerEntityRenderer extends MobEntityRenderer<WanderingTraderEntity, VillagerResemblingModel<WanderingTraderEntity>> {
    private static final Identifier TEXTURE = ClownRaid.id("textures/entity/seller.png");

    public SellerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SellerEntityModel(context.getPart(ClownRaidClient.MODEL_SELLER_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(WanderingTraderEntity entity) {
        return TEXTURE;
    }
}