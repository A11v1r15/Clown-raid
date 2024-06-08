package net.a11v1r15.clownraid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ClownRaidClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_PRESENTER_LAYER = new EntityModelLayer(ClownRaid.id("presenter"), "main");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ClownRaid.PRESENTER, PresenterEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_PRESENTER_LAYER, PresenterEntityModel::getTexturedModelData);
	}
}