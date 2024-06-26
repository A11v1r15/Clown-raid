package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;

import java.util.List;

public class ClownRaidTrades {
    public static final Int2ObjectMap<TradeOffers.Factory[]> PRESENTER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //Tickets
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:emerald"),  6,  32,  8,  6),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:emerald"), 12,  64,  4, 12),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:emerald"), 24, 128,  2, 24),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:emerald"), 48, 256,  1, 48),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:diamond"),  3,  32,  8,  6),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:diamond"),  6,  64,  4, 12),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:diamond"), 12, 128,  2, 24),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:ticket"), getItem("minecraft:diamond"), 24, 256,  1, 48),
                },
            2, //Tokens
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:emerald"),  6,  16,  8,  6),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:emerald"), 12,  32,  4, 12),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:emerald"), 24,  64,  2, 24),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:emerald"), 48, 128,  1, 48),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:diamond"),  3,  16,  8,  6),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:diamond"),  6,  32,  4, 12),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:diamond"), 12,  64,  2, 24),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:token"), getItem("minecraft:diamond"), 24, 128,  1, 48),
                },
            3, //Pass card
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("terrifictickets:passcard"), getItem("minecraft:emerald"), 32, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("terrifictickets:passcard"), getItem("minecraft:diamond"), 16, 1, 1, 32)
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> MAGICIAN_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //All items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("minecraft:rabbit_foot"), 3,  3,  8,  6),
                        new SellForCurrencyItemFactory(getItem("minecraft:rabbit_hide"),  6,  1,  6, 6),
                        new SellForCurrencyItemFactory(getItem("trickster:scroll_and_quill"),  6,  16,  4, 6),
                        new SellForCurrencyItemFactory(getItem("minecraft:suspicious_stew"), 3,  1, 8, 12, new Pair<>(getComponentType("minecraft:suspicious_stew_effects"), new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.INVISIBILITY, 666))))),
                },
            2, //Premium Items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("trickster:mirror_of_evaluation"), getItem("terrifictickets:token"), 16, 1,  3, 24),
                        new SellForCurrencyItemFactory(getItem("trickster:wand"), getItem("terrifictickets:token"), 16, 1,  3, 24),
                        new SellForCurrencyItemFactory(getItem("trickster:top_hat"), getItem("terrifictickets:token"), 16, 1,  3, 24),
                },
            3, //Magicians Hat, multiple of them for chance
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("magicians_hat:magicians_hat"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("magicians_hat:magicians_hat"), getItem("terrifictickets:token"), 64, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("magicians_hat:magicians_hat"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("magicians_hat:magicians_hat"), getItem("terrifictickets:token"), 16, 1, 1, 320)
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> SELLER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //Food Items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("minecraft:golden_apple"), getItem("terrifictickets:token"),  16,  1,  3,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:golden_carrot"),  6,  5,  4, 1),
                        new SellForCurrencyItemFactory(getItem("minecraft:pumpkin_pie"), 12, 3,  2, 1),
                        new SellForCurrencyItemFactory(getItem("minecraft:cookie"), 4, 16,  16, 1),
                        new SellForCurrencyItemFactory(getItem("minecraft:baked_potato"), 4, 16,  16, 1),
                        new SellForCurrencyItemFactory(getItem("minecraft:milk_bucket"), 10, 1,  16, 1),
                        new SellForCurrencyItemFactory(getItem("ducktor:lozenge"),  5,  7,  7,  1),
                },
            2, //Flowers
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("minecraft:allium"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:azure_bluet"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:blue_orchid"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:cornflower"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:dandelion"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:lily_of_the_valley"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:oxeye_daisy"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:poppy"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:orange_tulip"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:pink_tulip"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:red_tulip"),  1,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:white_tulip"),  1,  7,  7,  1),
                },
            3, //Ductor
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("minecraft:warped_fungus"),  3,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:sweet_berries"),  3,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("ducktor:lozenge"),  5,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("ducktor:scented_candle"),  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("ducktor:lozenge"),  5,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("ducktor:scented_candle"),  7,  7,  7,  1),
                        new SellForCurrencyItemFactory(getItem("minecraft:potion"),  3,  1,  7,  1, new Pair<>(getComponentType("minecraft:potion_contents"), getPotion("ducktor:magnificent"))),
                        new SellForCurrencyItemFactory(getItem("minecraft:potion"),  6,  1,  7,  1, new Pair<>(getComponentType("minecraft:potion_contents"), getPotion("ducktor:rejuvenation"))),
                        new SellForCurrencyItemFactory(getItem("minecraft:potion"),  12,  1,  7,  1, new Pair<>(getComponentType("minecraft:potion_contents"), getPotion("ducktor:long_rejuvenation"))),
                        new SellForCurrencyItemFactory(getItem("minecraft:potion"),  12,  1,  7,  1, new Pair<>(getComponentType("minecraft:potion_contents"), getPotion("ducktor:strong_rejuvenation"))),
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> CLOWN_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1, //All items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("bombastic:party_popper"), 3,  8,  8,  6),
                        new SellForCurrencyItemFactory(getItem("bombastic:juggling_ball"), 6,  1,  8,  6),
                        new SellForCurrencyItemFactory(getItem("minecraft:pumpkin_pie"), 3,  3,  8,  6),
                        new SellForCurrencyItemFactory(getItem("minecraft:egg"),  6,  16,  4, 6),
                        new SellForCurrencyItemFactory(getItem("minecraft:snowball"),  6,  16,  4, 6),
                        new SellForCurrencyItemFactory(getItem("minecraft:cake"), 12, 1,  8, 6),
                        new SellForCurrencyItemFactory(getItem("minecraft:sunflower"), 1, 6,  16, 3),
                        new SellForCurrencyItemFactory(getItem("minecraft:carved_pumpkin"), Enchantments.BINDING_CURSE, 1,3,  1, 8, 12),
                        new SellForCurrencyItemFactory(getItem("minecraft:suspicious_stew"), 3,  1, 8, 12, new Pair<>(getComponentType("minecraft:suspicious_stew_effects"), new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.BLINDNESS, 666))))),
                },
            2, //Premium Items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("minecraft:tnt"), getItem("terrifictickets:token"), 16, 1,  3, 24),
                        new SellForCurrencyItemFactory(getItem("magnificent_maw:curious_vial"), getItem("terrifictickets:token"), 16, 2,  12, 24),
                        new SellForCurrencyItemFactory(getItem("ducktor:warding_candle"), getItem("terrifictickets:token"),  16,  8,  7,  24),
                        new SellForCurrencyItemFactory(getItem("bombastic:pipe_bomb"), 64,  16,  2,  64, new Pair<>(getComponentType("bombastic:pinned"), false)),
                        new SellForCurrencyItemFactory(getItem("bombastic:clown_boots"), 16,  1,  2,  24),
                        new SellForCurrencyItemFactory(getItem("bombastic:clown_hair"), 16,  1,  2,  24),
                },
            3, //The Funny, multiple of them for chance
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_orange_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                        new SellForCurrencyItemFactory(getItem("honque:the_green_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                        new SellForCurrencyItemFactory(getItem("honque:the_blue_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                        new SellForCurrencyItemFactory(getItem("honque:the_gay_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                }
            )
        );

    public static Item getItem (String identifier){
        return Registries.ITEM.get(Identifier.of(identifier));
    }

    public static ComponentType<?> getComponentType(String identifier){
        return Registries.DATA_COMPONENT_TYPE.get(Identifier.of(identifier));
    }

    public static Potion getPotion (String identifier){
        return Registries.POTION.get(Identifier.of(identifier));
    }

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
            this(new TradedItem(getItem("terrifictickets:ticket").asItem(), price), item.asItem(), count, maxUses, experience, null, 0, components);
        }

        public SellForCurrencyItemFactory(ItemConvertible item, ItemConvertible currency, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(currency.asItem(), price), item.asItem(), count, maxUses, experience, null, 0, components);
        }

        public SellForCurrencyItemFactory(ItemConvertible item, RegistryKey<Enchantment> enchantment, int enchantmentLevel, int price, int count, int maxUses, int experience, Pair<ComponentType, Object>... components) {
            this(new TradedItem(getItem("terrifictickets:ticket").asItem(), price), item.asItem(), count, maxUses, experience, enchantment, enchantmentLevel, components);
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
                itemStack.set(componentType, value);
            }
            return new TradeOffer(this.currency, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}
