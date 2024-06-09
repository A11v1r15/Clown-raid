package net.a11v1r15.clownraid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ClownRaidClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_PRESENTER_LAYER = new EntityModelLayer(ClownRaid.id("presenter"), "main");
	public static final EntityModelLayer MODEL_MARCHER_LAYER = new EntityModelLayer(ClownRaid.id("marcher"), "main");
	public static final EntityModelLayer MODEL_SELLER_LAYER = new EntityModelLayer(ClownRaid.id("seller"), "main");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ClownRaid.PRESENTER, PresenterEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_PRESENTER_LAYER, PresenterEntityModel::getTexturedModelData);
		
		EntityRendererRegistry.register(ClownRaid.MARCHER, MarcherEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_MARCHER_LAYER, MarcherEntityModel::getTexturedModelData);
		
		EntityRendererRegistry.register(ClownRaid.SELLER, SellerrEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_SELLER_LAYER, SellerEntityModel::getTexturedModelData);
	}
}