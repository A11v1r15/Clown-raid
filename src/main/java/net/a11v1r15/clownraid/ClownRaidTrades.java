package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableMap;
import gay.lemmaeof.ducktor.Ducktor;
import gay.lemmaeof.terrifictickets.TerrificTickets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Pair;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import symbolics.division.honque.Honque;

import java.util.List;

public class ClownRaidTrades {
    public static final Int2ObjectMap<TradeOffers.Factory[]> PRESENTER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //Tickets
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.EMERALD,  6,  32,  8,  6),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.EMERALD, 12,  64,  4, 12),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.EMERALD, 24, 128,  2, 24),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.EMERALD, 48, 256,  1, 48),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.DIAMOND,  3,  32,  8,  6),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.DIAMOND,  6,  64,  4, 12),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.DIAMOND, 12, 128,  2, 24),
                        new SellForCurrencyItemFactory(TerrificTickets.TICKET, Items.DIAMOND, 24, 256,  1, 48)
                },
            2, //Tokens
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.EMERALD,  6,  16,  8,  6),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.EMERALD, 12,  64,  4, 12),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.EMERALD, 24,  64,  2, 24),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.EMERALD, 48, 128,  1, 48),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.DIAMOND,  3,  16,  8,  6),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.DIAMOND,  6,  64,  4, 12),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.DIAMOND, 12,  64,  2, 24),
                        new SellForCurrencyItemFactory(TerrificTickets.TOKEN, Items.DIAMOND, 24, 128,  1, 48)
                },
            3, //Pass card
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(TerrificTickets.PASSCARD, Items.EMERALD, 32, 1, 1, 32),
                        new SellForCurrencyItemFactory(TerrificTickets.PASSCARD, Items.DIAMOND, 16, 1, 1, 32)
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> SELLER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //Food Items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(Items.GOLDEN_APPLE, TerrificTickets.TOKEN,  16,  1,  3,  1),
                        new SellForCurrencyItemFactory(Items.GOLDEN_CARROT,  6,  5,  4, 1),
                        new SellForCurrencyItemFactory(Items.PUMPKIN_PIE, 12, 3,  2, 1),
                        new SellForCurrencyItemFactory(Items.COOKIE, 4, 16,  16, 1),
                        new SellForCurrencyItemFactory(Items.BAKED_POTATO, 4, 16,  16, 1),
                        new SellForCurrencyItemFactory(Items.MILK_BUCKET, 10, 1,  16, 1)
                },
            2, //Flowers
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(Items.ALLIUM,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.AZURE_BLUET,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.BLUE_ORCHID,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.CORNFLOWER,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.DANDELION,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.LILY_OF_THE_VALLEY,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.OXEYE_DAISY,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.POPPY,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.ORANGE_TULIP,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.PINK_TULIP,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.RED_TULIP,  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.WHITE_TULIP,  1,  7,  7,  1)
                },
            3, //Ductor
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(Items.WARPED_FUNGUS,  3,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.SWEET_BERRIES,  3,  7,  7,  1),
                        new SellForCurrencyItemFactory(Ducktor.LOZENGE,  5,  7,  7,  1),
                        new SellForCurrencyItemFactory(Ducktor.SCENTED_CANDLE,  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(Ducktor.LOZENGE,  5,  7,  7,  1),
                        new SellForCurrencyItemFactory(Ducktor.SCENTED_CANDLE,  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(Ducktor.WARDING_CANDLE, TerrificTickets.TOKEN,  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.POTION,  3,  1,  7,  1, new Pair<>(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Ducktor.MAGNIFICENT_POTION))),
                        new SellForCurrencyItemFactory(Items.POTION,  6,  1,  7,  1, new Pair<>(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Ducktor.REJUVENATION_POTION))),
                        new SellForCurrencyItemFactory(Items.POTION,  12,  1,  7,  1, new Pair<>(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Ducktor.LONG_REJUVENATION_POTION))),
                        new SellForCurrencyItemFactory(Items.POTION,  12,  1,  7,  1, new Pair<>(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Ducktor.STRONG_REJUVENATION_POTION)))
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> CLOWN_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //All items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(Items.PUMPKIN_PIE, 3,  3,  8,  6),
                        new SellForCurrencyItemFactory(Items.EGG,  6,  16,  4, 6),
                        new SellForCurrencyItemFactory(Items.SNOWBALL,  6,  16,  4, 6),
                        new SellForCurrencyItemFactory(Items.CAKE, 12, 1,  8, 6),
                        new SellForCurrencyItemFactory(Items.SUNFLOWER, 1, 6,  16, 3),
                        new SellForCurrencyItemFactory(Items.TNT, 16, 1,  6, 12),
                        new SellForCurrencyItemFactory(Ducktor.WARDING_CANDLE, TerrificTickets.TOKEN,  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(Items.CARVED_PUMPKIN, Enchantments.BINDING_CURSE, 1,3,  1, 8, 1),
                        new SellForCurrencyItemFactory(Items.SUSPICIOUS_STEW, 3,  1, 8, 1, new Pair<>(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.BLINDNESS, 99999)))))
                },
            2, //The Funny, multiple of them for chance
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 3),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 3),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(Honque.THE_FUNNY, TerrificTickets.TOKEN, 16, 1, 1, 320)
                }
            )
        );

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap(map);
    }

    public static class SellForCurrencyItemFactory implements TradeOffers.Factory {
        private final TradedItem currency;
        private final Item stack;
        private final RegistryKey<Enchantment> enchantment;
        private final int enchantmentLevel;
        private final int maxUses;
        private final int experience;
        private final int count;
        private final float multiplier;
        private final Pair<ComponentType, Object>[] components;

        public SellForCurrencyItemFactory(ItemConvertible item, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(TerrificTickets.TICKET.asItem(), price), item.asItem(), count, maxUses, experience, null, 0, components);
        }

        public SellForCurrencyItemFactory(ItemConvertible item, ItemConvertible currency, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(currency.asItem(), price), item.asItem(), count, maxUses, experience, null, 0, components);
        }

        public SellForCurrencyItemFactory(ItemConvertible item, RegistryKey<Enchantment> enchantment, int enchantmentLevel, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(TerrificTickets.TICKET.asItem(), price), item.asItem(), count, maxUses, experience, enchantment, enchantmentLevel, components);
        }

        public SellForCurrencyItemFactory(ItemConvertible item, ItemConvertible currency, RegistryKey<Enchantment> enchantment, int enchantmentLevel, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(currency.asItem(), price), item.asItem(), count, maxUses, experience, enchantment, enchantmentLevel, components);
        }

        public SellForCurrencyItemFactory(TradedItem currency, Item stack, int count, int maxUses, int experience, RegistryKey<Enchantment> enchantment, int enchantmentLevel, Pair<ComponentType, Object>... components) {
            this.stack = stack;
            this.enchantment = enchantment;
            this.enchantmentLevel = enchantmentLevel;
            this.currency = currency;
            this.maxUses = maxUses;
            this.experience = experience;
            this.count = count;
            this.multiplier = 0.05F;
            this.components = components;
        }

        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack =  new ItemStack(stack, this.count);
            if(enchantment != null) {
                RegistryEntry<Enchantment> enchantmentEntry = entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue()).get();
                itemStack.addEnchantment(enchantmentEntry, this.enchantmentLevel);
            }
            for (Pair<ComponentType, Object> component : this.components){
                ComponentType componentType = component.getLeft();
                Object value = component.getRight();
                itemStack.set(componentType, component.getRight());
            }
            return new TradeOffer(this.currency, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}
