package net.a11v1r15.clownraid;

import net.a11v1r15.clownraid.entity.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ClownRaidClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_PRESENTER_LAYER = new EntityModelLayer(ClownRaid.id("presenter"), "main");
	public static final EntityModelLayer MODEL_MAGICIAN_LAYER = new EntityModelLayer(ClownRaid.id("magician"), "main");
	public static final EntityModelLayer MODEL_MARCHER_LAYER = new EntityModelLayer(ClownRaid.id("marcher"), "main");
	public static final EntityModelLayer MODEL_SELLER_LAYER = new EntityModelLayer(ClownRaid.id("seller"), "main");
	public static final EntityModelLayer MODEL_CLOWN_LAYER = new EntityModelLayer(ClownRaid.id("clown"), "main");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ClownRaid.PRESENTER, PresenterEntityRenderer::new);
		EntityRendererRegistry.register(ClownRaid.MAGICIAN, MagicianEntityRenderer::new);
		EntityRendererRegistry.register(ClownRaid.MARCHER, MarcherEntityRenderer::new);
		EntityRendererRegistry.register(ClownRaid.SELLER, SellerEntityRenderer::new);
		EntityRendererRegistry.register(ClownRaid.CLOWN, ClownEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_PRESENTER_LAYER, PresenterEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(MODEL_MAGICIAN_LAYER, MagicianEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(MODEL_MARCHER_LAYER, MarcherEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(MODEL_SELLER_LAYER, SellerEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(MODEL_CLOWN_LAYER, ClownEntityModel::getTexturedModelData);
	}
}
