package net.a11v1r15.clownraid;

import net.a11v1r15.clownraid.entity.ClownEntity;
import net.a11v1r15.clownraid.entity.MarcherEntity;
import net.a11v1r15.clownraid.entity.PresenterEntity;
import net.a11v1r15.clownraid.entity.SellerEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
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
	public static final EntityType<MarcherEntity> MARCHER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("marcher"),
			EntityType.Builder.create(MarcherEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final EntityType<SellerEntity> SELLER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("seller"),
			EntityType.Builder.create(SellerEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final EntityType<ClownEntity> CLOWN = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("clown"),
			EntityType.Builder.create(ClownEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final Item PRESENTER_SPAWN_EGG = new SpawnEggItem(PRESENTER, 14765359, 0, new Item.Settings());
	public static final Item MARCHER_SPAWN_EGG = new SpawnEggItem(MARCHER, 14765359, 15449395, new Item.Settings());
	public static final Item SELLER_SPAWN_EGG = new SpawnEggItem(SELLER, 14765359, 16777215, new Item.Settings());
	public static final Item CLOWN_SPAWN_EGG = new SpawnEggItem(CLOWN, 14765359, 15741337, new Item.Settings());

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(PRESENTER, PresenterEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MARCHER, MarcherEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(SELLER, SellerEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(CLOWN, ClownEntity.createMobAttributes());
		Registry.register(Registries.ITEM, ClownRaid.id("presenter_spawn_egg"), PRESENTER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("marcher_spawn_egg"), MARCHER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("seller_spawn_egg"), SELLER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("clown_spawn_egg"), CLOWN_SPAWN_EGG);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(PRESENTER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(MARCHER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(SELLER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(CLOWN_SPAWN_EGG));

		LOGGER.info("Coming to a village near you!");
	}

	public static Identifier id(String path) {
		return Identifier.of("clown-raid", path);
	}
}
