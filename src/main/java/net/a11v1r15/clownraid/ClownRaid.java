package net.a11v1r15.clownraid;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClownRaid implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("clown-raid");

	public static final EntityType<PresenterEntity> PRESENTER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("presenter"),
			EntityType.Builder.create(PresenterEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final Item PRESENTER_SPAWN_EGG = new SpawnEggItem(PRESENTER, 13112340, 0, new Item.Settings());
	
	
	public static final EntityType<MarcherEntity> MARCHER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("marcher"),
			EntityType.Builder.create(MarcherEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final Item MARCHER_SPAWN_EGG = new SpawnEggItem(MARCHER, 13112340, 15908921, new Item.Settings());
	
	
	public static final EntityType<SellerEntity> SELLER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("seller"),
			EntityType.Builder.create(SellerEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final Item SELLER_SPAWN_EGG = new SpawnEggItem(SELLER, 13112340, 16777215, new Item.Settings());

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(PRESENTER, PresenterEntity.createMobAttributes());
		Registry.register(Registries.ITEM, ClownRaid.id("presenter_spawn_egg"), PRESENTER_SPAWN_EGG);

		FabricDefaultAttributeRegistry.register(MARCHER, MarcherEntity.createMobAttributes());
		Registry.register(Registries.ITEM, ClownRaid.id("marcher_spawn_egg"), MARCHER_SPAWN_EGG);

		FabricDefaultAttributeRegistry.register(SELLER, SellerEntity.createMobAttributes());
		Registry.register(Registries.ITEM, ClownRaid.id("seller_spawn_egg"), SELLER_SPAWN_EGG);

		LOGGER.info("Coming to a village near you!");
	}

	public static Identifier id(String path) {
		return Identifier.of("clown-raid", path);
	}
}
