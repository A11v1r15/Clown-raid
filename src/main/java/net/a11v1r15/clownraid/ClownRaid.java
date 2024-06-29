package net.a11v1r15.clownraid;

import net.a11v1r15.clownraid.entity.PresenterEntity;
import net.a11v1r15.clownraid.entity.MagicianEntity;
import net.a11v1r15.clownraid.entity.MarcherEntity;
import net.a11v1r15.clownraid.entity.SellerEntity;
import net.a11v1r15.clownraid.entity.ClownEntity;
import net.a11v1r15.clownraid.util.DispensableSpawnEggItem;
import net.a11v1r15.clownraid.util.ServerWorldSpawners;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClownRaid implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Clown Raid");

	public static final EntityType<PresenterEntity> PRESENTER = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("presenter"),
			EntityType.Builder.create(PresenterEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
	);
	public static final EntityType<MagicianEntity> MAGICIAN = Registry.register(
			Registries.ENTITY_TYPE,
			ClownRaid.id("magician"),
			EntityType.Builder.create(MagicianEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 1.95F).eyeHeight(1.62F).maxTrackingRange(10).build()
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
	public static final Item PRESENTER_SPAWN_EGG = new DispensableSpawnEggItem(PRESENTER, 0xE14D2F, 0x000000, new Item.Settings());
	public static final Item MAGICIAN_SPAWN_EGG = new DispensableSpawnEggItem(MAGICIAN, 0xE68A25, 0xE14D2F, new Item.Settings());
	public static final Item MARCHER_SPAWN_EGG = new DispensableSpawnEggItem(MARCHER, 0xE14D2F, 0xEBBD33, new Item.Settings());
	public static final Item SELLER_SPAWN_EGG = new DispensableSpawnEggItem(SELLER, 0xE2E1DC, 0xE14D2F, new Item.Settings());
	public static final Item CLOWN_SPAWN_EGG = new DispensableSpawnEggItem(CLOWN, 0xEBBD33, 0xF03199, new Item.Settings());
	public static final Identifier ENTITY_PRESENTER_DRINK_POTION_ID = ClownRaid.id("entity.presenter.drink_potion");
	public static final Identifier ENTITY_PRESENTER_DISAPPEARED_ID = ClownRaid.id("entity.presenter.disappeared");
	public static final Identifier ENTITY_PRESENTER_DRINK_MILK_ID = ClownRaid.id("entity.presenter.drink_milk");
	public static final Identifier ENTITY_PRESENTER_REAPPEARED_ID = ClownRaid.id("entity.presenter.reappeared");
	public static final Identifier ENTITY_PRESENTER_AMBIENT_ID = ClownRaid.id("entity.presenter.ambient");
	public static final Identifier ENTITY_PRESENTER_DEATH_ID = ClownRaid.id("entity.presenter.death");
	public static final Identifier ENTITY_PRESENTER_TRADE_ID = ClownRaid.id("entity.presenter.trade");
	public static final Identifier ENTITY_PRESENTER_HURT_ID = ClownRaid.id("entity.presenter.hurt");
	public static final Identifier ENTITY_PRESENTER_YES_ID = ClownRaid.id("entity.presenter.yes");
	public static final Identifier ENTITY_PRESENTER_NO_ID = ClownRaid.id("entity.presenter.no");
	public static final Identifier ENTITY_MAGICIAN_DRINK_POTION_ID = ClownRaid.id("entity.magician.drink_potion");
	public static final Identifier ENTITY_MAGICIAN_DISAPPEARED_ID = ClownRaid.id("entity.magician.disappeared");
	public static final Identifier ENTITY_MAGICIAN_DRINK_MILK_ID = ClownRaid.id("entity.magician.drink_milk");
	public static final Identifier ENTITY_MAGICIAN_REAPPEARED_ID = ClownRaid.id("entity.magician.reappeared");
	public static final Identifier ENTITY_MAGICIAN_AMBIENT_ID = ClownRaid.id("entity.magician.ambient");
	public static final Identifier ENTITY_MAGICIAN_DEATH_ID = ClownRaid.id("entity.magician.death");
	public static final Identifier ENTITY_MAGICIAN_TRADE_ID = ClownRaid.id("entity.magician.trade");
	public static final Identifier ENTITY_MAGICIAN_HURT_ID = ClownRaid.id("entity.magician.hurt");
	public static final Identifier ENTITY_MAGICIAN_YES_ID = ClownRaid.id("entity.magician.yes");
	public static final Identifier ENTITY_MAGICIAN_NO_ID = ClownRaid.id("entity.magician.no");
	public static final Identifier ENTITY_MARCHER_DRINK_POTION_ID = ClownRaid.id("entity.marcher.drink_potion");
	public static final Identifier ENTITY_MARCHER_DISAPPEARED_ID = ClownRaid.id("entity.marcher.disappeared");
	public static final Identifier ENTITY_MARCHER_DRINK_MILK_ID = ClownRaid.id("entity.marcher.drink_milk");
	public static final Identifier ENTITY_MARCHER_REAPPEARED_ID = ClownRaid.id("entity.marcher.reappeared");
	public static final Identifier ENTITY_MARCHER_AMBIENT_ID = ClownRaid.id("entity.marcher.ambient");
	public static final Identifier ENTITY_MARCHER_DEATH_ID = ClownRaid.id("entity.marcher.death");
	public static final Identifier ENTITY_MARCHER_TRADE_ID = ClownRaid.id("entity.marcher.trade");
	public static final Identifier ENTITY_MARCHER_HURT_ID = ClownRaid.id("entity.marcher.hurt");
	public static final Identifier ENTITY_MARCHER_YES_ID = ClownRaid.id("entity.marcher.yes");
	public static final Identifier ENTITY_MARCHER_NO_ID = ClownRaid.id("entity.marcher.no");
	public static final Identifier ENTITY_SELLER_DRINK_POTION_ID = ClownRaid.id("entity.seller.drink_potion");
	public static final Identifier ENTITY_SELLER_DISAPPEARED_ID = ClownRaid.id("entity.seller.disappeared");
	public static final Identifier ENTITY_SELLER_DRINK_MILK_ID = ClownRaid.id("entity.seller.drink_milk");
	public static final Identifier ENTITY_SELLER_REAPPEARED_ID = ClownRaid.id("entity.seller.reappeared");
	public static final Identifier ENTITY_SELLER_AMBIENT_ID = ClownRaid.id("entity.seller.ambient");
	public static final Identifier ENTITY_SELLER_DEATH_ID = ClownRaid.id("entity.seller.death");
	public static final Identifier ENTITY_SELLER_TRADE_ID = ClownRaid.id("entity.seller.trade");
	public static final Identifier ENTITY_SELLER_HURT_ID = ClownRaid.id("entity.seller.hurt");
	public static final Identifier ENTITY_SELLER_YES_ID = ClownRaid.id("entity.seller.yes");
	public static final Identifier ENTITY_SELLER_NO_ID = ClownRaid.id("entity.seller.no");
	public static final Identifier ENTITY_CLOWN_DRINK_POTION_ID = ClownRaid.id("entity.clown.drink_potion");
	public static final Identifier ENTITY_CLOWN_DISAPPEARED_ID = ClownRaid.id("entity.clown.disappeared");
	public static final Identifier ENTITY_CLOWN_DRINK_MILK_ID = ClownRaid.id("entity.clown.drink_milk");
	public static final Identifier ENTITY_CLOWN_REAPPEARED_ID = ClownRaid.id("entity.clown.reappeared");
	public static final Identifier ENTITY_CLOWN_AMBIENT_ID = ClownRaid.id("entity.clown.ambient");
	public static final Identifier ENTITY_CLOWN_DEATH_ID = ClownRaid.id("entity.clown.death");
	public static final Identifier ENTITY_CLOWN_TRADE_ID = ClownRaid.id("entity.clown.trade");
	public static final Identifier ENTITY_CLOWN_HURT_ID = ClownRaid.id("entity.clown.hurt");
	public static final Identifier ENTITY_CLOWN_YES_ID = ClownRaid.id("entity.clown.yes");
	public static final Identifier ENTITY_CLOWN_NO_ID = ClownRaid.id("entity.clown.no");
	public static SoundEvent ENTITY_PRESENTER_DRINK_POTION = SoundEvent.of(ENTITY_PRESENTER_DRINK_POTION_ID);
	public static SoundEvent ENTITY_PRESENTER_DISAPPEARED = SoundEvent.of(ENTITY_PRESENTER_DISAPPEARED_ID);
	public static SoundEvent ENTITY_PRESENTER_DRINK_MILK = SoundEvent.of(ENTITY_PRESENTER_DRINK_MILK_ID);
	public static SoundEvent ENTITY_PRESENTER_REAPPEARED = SoundEvent.of(ENTITY_PRESENTER_REAPPEARED_ID);
	public static SoundEvent ENTITY_PRESENTER_AMBIENT = SoundEvent.of(ENTITY_PRESENTER_AMBIENT_ID);
	public static SoundEvent ENTITY_PRESENTER_DEATH = SoundEvent.of(ENTITY_PRESENTER_DEATH_ID);
	public static SoundEvent ENTITY_PRESENTER_TRADE = SoundEvent.of(ENTITY_PRESENTER_TRADE_ID);
	public static SoundEvent ENTITY_PRESENTER_HURT = SoundEvent.of(ENTITY_PRESENTER_HURT_ID);
	public static SoundEvent ENTITY_PRESENTER_YES = SoundEvent.of(ENTITY_PRESENTER_YES_ID);
	public static SoundEvent ENTITY_PRESENTER_NO = SoundEvent.of(ENTITY_PRESENTER_NO_ID);
	public static SoundEvent ENTITY_MAGICIAN_DRINK_POTION = SoundEvent.of(ENTITY_MAGICIAN_DRINK_POTION_ID);
	public static SoundEvent ENTITY_MAGICIAN_DISAPPEARED = SoundEvent.of(ENTITY_MAGICIAN_DISAPPEARED_ID);
	public static SoundEvent ENTITY_MAGICIAN_DRINK_MILK = SoundEvent.of(ENTITY_MAGICIAN_DRINK_MILK_ID);
	public static SoundEvent ENTITY_MAGICIAN_REAPPEARED = SoundEvent.of(ENTITY_MAGICIAN_REAPPEARED_ID);
	public static SoundEvent ENTITY_MAGICIAN_AMBIENT = SoundEvent.of(ENTITY_MAGICIAN_AMBIENT_ID);
	public static SoundEvent ENTITY_MAGICIAN_DEATH = SoundEvent.of(ENTITY_MAGICIAN_DEATH_ID);
	public static SoundEvent ENTITY_MAGICIAN_TRADE = SoundEvent.of(ENTITY_MAGICIAN_TRADE_ID);
	public static SoundEvent ENTITY_MAGICIAN_HURT = SoundEvent.of(ENTITY_MAGICIAN_HURT_ID);
	public static SoundEvent ENTITY_MAGICIAN_YES = SoundEvent.of(ENTITY_MAGICIAN_YES_ID);
	public static SoundEvent ENTITY_MAGICIAN_NO = SoundEvent.of(ENTITY_MAGICIAN_NO_ID);
	public static SoundEvent ENTITY_MARCHER_DRINK_POTION = SoundEvent.of(ENTITY_MARCHER_DRINK_POTION_ID);
	public static SoundEvent ENTITY_MARCHER_DISAPPEARED = SoundEvent.of(ENTITY_MARCHER_DISAPPEARED_ID);
	public static SoundEvent ENTITY_MARCHER_DRINK_MILK = SoundEvent.of(ENTITY_MARCHER_DRINK_MILK_ID);
	public static SoundEvent ENTITY_MARCHER_REAPPEARED = SoundEvent.of(ENTITY_MARCHER_REAPPEARED_ID);
	public static SoundEvent ENTITY_MARCHER_AMBIENT = SoundEvent.of(ENTITY_MARCHER_AMBIENT_ID);
	public static SoundEvent ENTITY_MARCHER_DEATH = SoundEvent.of(ENTITY_MARCHER_DEATH_ID);
	public static SoundEvent ENTITY_MARCHER_TRADE = SoundEvent.of(ENTITY_MARCHER_TRADE_ID);
	public static SoundEvent ENTITY_MARCHER_HURT = SoundEvent.of(ENTITY_MARCHER_HURT_ID);
	public static SoundEvent ENTITY_MARCHER_YES = SoundEvent.of(ENTITY_MARCHER_YES_ID);
	public static SoundEvent ENTITY_MARCHER_NO = SoundEvent.of(ENTITY_MARCHER_NO_ID);
	public static SoundEvent ENTITY_SELLER_DRINK_POTION = SoundEvent.of(ENTITY_SELLER_DRINK_POTION_ID);
	public static SoundEvent ENTITY_SELLER_DISAPPEARED = SoundEvent.of(ENTITY_SELLER_DISAPPEARED_ID);
	public static SoundEvent ENTITY_SELLER_DRINK_MILK = SoundEvent.of(ENTITY_SELLER_DRINK_MILK_ID);
	public static SoundEvent ENTITY_SELLER_REAPPEARED = SoundEvent.of(ENTITY_SELLER_REAPPEARED_ID);
	public static SoundEvent ENTITY_SELLER_AMBIENT = SoundEvent.of(ENTITY_SELLER_AMBIENT_ID);
	public static SoundEvent ENTITY_SELLER_DEATH = SoundEvent.of(ENTITY_SELLER_DEATH_ID);
	public static SoundEvent ENTITY_SELLER_TRADE = SoundEvent.of(ENTITY_SELLER_TRADE_ID);
	public static SoundEvent ENTITY_SELLER_HURT = SoundEvent.of(ENTITY_SELLER_HURT_ID);
	public static SoundEvent ENTITY_SELLER_YES = SoundEvent.of(ENTITY_SELLER_YES_ID);
	public static SoundEvent ENTITY_SELLER_NO = SoundEvent.of(ENTITY_SELLER_NO_ID);
	public static SoundEvent ENTITY_CLOWN_DRINK_POTION = SoundEvent.of(ENTITY_CLOWN_DRINK_POTION_ID);
	public static SoundEvent ENTITY_CLOWN_DISAPPEARED = SoundEvent.of(ENTITY_CLOWN_DISAPPEARED_ID);
	public static SoundEvent ENTITY_CLOWN_DRINK_MILK = SoundEvent.of(ENTITY_CLOWN_DRINK_MILK_ID);
	public static SoundEvent ENTITY_CLOWN_REAPPEARED = SoundEvent.of(ENTITY_CLOWN_REAPPEARED_ID);
	public static SoundEvent ENTITY_CLOWN_AMBIENT = SoundEvent.of(ENTITY_CLOWN_AMBIENT_ID);
	public static SoundEvent ENTITY_CLOWN_DEATH = SoundEvent.of(ENTITY_CLOWN_DEATH_ID);
	public static SoundEvent ENTITY_CLOWN_TRADE = SoundEvent.of(ENTITY_CLOWN_TRADE_ID);
	public static SoundEvent ENTITY_CLOWN_HURT = SoundEvent.of(ENTITY_CLOWN_HURT_ID);
	public static SoundEvent ENTITY_CLOWN_YES = SoundEvent.of(ENTITY_CLOWN_YES_ID);
	public static SoundEvent ENTITY_CLOWN_NO = SoundEvent.of(ENTITY_CLOWN_NO_ID);

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(PRESENTER, PresenterEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MAGICIAN, MagicianEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MARCHER, MarcherEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(SELLER, SellerEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(CLOWN, ClownEntity.createMobAttributes());
		Registry.register(Registries.ITEM, ClownRaid.id("presenter_spawn_egg"), PRESENTER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("magician_spawn_egg"), MAGICIAN_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("marcher_spawn_egg"), MARCHER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("seller_spawn_egg"), SELLER_SPAWN_EGG);
		Registry.register(Registries.ITEM, ClownRaid.id("clown_spawn_egg"), CLOWN_SPAWN_EGG);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(PRESENTER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(MAGICIAN_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(MARCHER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(SELLER_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(c -> c.add(CLOWN_SPAWN_EGG));
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_DRINK_POTION_ID, ENTITY_PRESENTER_DRINK_POTION);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_DISAPPEARED_ID, ENTITY_PRESENTER_DISAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_DRINK_MILK_ID, ENTITY_PRESENTER_DRINK_MILK);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_REAPPEARED_ID, ENTITY_PRESENTER_REAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_AMBIENT_ID, ENTITY_PRESENTER_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_DEATH_ID, ENTITY_PRESENTER_DEATH);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_TRADE_ID, ENTITY_PRESENTER_TRADE);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_HURT_ID, ENTITY_PRESENTER_HURT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_YES_ID, ENTITY_PRESENTER_YES);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_PRESENTER_NO_ID, ENTITY_PRESENTER_NO);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_DRINK_POTION_ID, ENTITY_MAGICIAN_DRINK_POTION);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_DISAPPEARED_ID, ENTITY_MAGICIAN_DISAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_DRINK_MILK_ID, ENTITY_MAGICIAN_DRINK_MILK);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_REAPPEARED_ID, ENTITY_MAGICIAN_REAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_AMBIENT_ID, ENTITY_MAGICIAN_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_DEATH_ID, ENTITY_MAGICIAN_DEATH);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_TRADE_ID, ENTITY_MAGICIAN_TRADE);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_HURT_ID, ENTITY_MAGICIAN_HURT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_YES_ID, ENTITY_MAGICIAN_YES);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MAGICIAN_NO_ID, ENTITY_MAGICIAN_NO);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_DRINK_POTION_ID, ENTITY_MARCHER_DRINK_POTION);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_DISAPPEARED_ID, ENTITY_MARCHER_DISAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_DRINK_MILK_ID, ENTITY_MARCHER_DRINK_MILK);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_REAPPEARED_ID, ENTITY_MARCHER_REAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_AMBIENT_ID, ENTITY_MARCHER_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_DEATH_ID, ENTITY_MARCHER_DEATH);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_TRADE_ID, ENTITY_MARCHER_TRADE);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_HURT_ID, ENTITY_MARCHER_HURT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_YES_ID, ENTITY_MARCHER_YES);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_MARCHER_NO_ID, ENTITY_MARCHER_NO);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_DRINK_POTION_ID, ENTITY_SELLER_DRINK_POTION);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_DISAPPEARED_ID, ENTITY_SELLER_DISAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_DRINK_MILK_ID, ENTITY_SELLER_DRINK_MILK);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_REAPPEARED_ID, ENTITY_SELLER_REAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_AMBIENT_ID, ENTITY_SELLER_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_DEATH_ID, ENTITY_SELLER_DEATH);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_TRADE_ID, ENTITY_SELLER_TRADE);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_HURT_ID, ENTITY_SELLER_HURT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_YES_ID, ENTITY_SELLER_YES);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_SELLER_NO_ID, ENTITY_SELLER_NO);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_DRINK_POTION_ID, ENTITY_CLOWN_DRINK_POTION);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_DISAPPEARED_ID, ENTITY_CLOWN_DISAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_DRINK_MILK_ID, ENTITY_CLOWN_DRINK_MILK);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_REAPPEARED_ID, ENTITY_CLOWN_REAPPEARED);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_AMBIENT_ID, ENTITY_CLOWN_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_DEATH_ID, ENTITY_CLOWN_DEATH);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_TRADE_ID, ENTITY_CLOWN_TRADE);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_HURT_ID, ENTITY_CLOWN_HURT);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_YES_ID, ENTITY_CLOWN_YES);
		Registry.register(Registries.SOUND_EVENT, ClownRaid.ENTITY_CLOWN_NO_ID, ENTITY_CLOWN_NO);

		ServerWorldEvents.LOAD.register(((server, world) -> {
			if (world.isClient()) return;
			ServerWorldSpawners.register(world, new ParadeSpawner());
		}));
		LOGGER.info("Coming to a village near you!");
	}

	public static Identifier id(String path) {
		return Identifier.of("clown-raid", path);
	}
}
