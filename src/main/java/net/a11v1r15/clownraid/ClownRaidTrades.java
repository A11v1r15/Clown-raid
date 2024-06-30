package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.a11v1r15.clownraid.util.ClownRaidTrading;
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

import java.util.HashMap;
import java.util.List;

public class ClownRaidTrades {
    public static final HashMap<String, TradeOffers.Factory[]> PRESENTER_TRADES =
            new ClownRaidTrading.Builder()
                    .addPool("Tickets")
                    .sell( 32, "terrifictickets:ticket").inExchangeFor( 6, "minecraft:emerald").times(8).rewarding( 6)
                    .sell( 64, "terrifictickets:ticket").inExchangeFor(12, "minecraft:emerald").times(4).rewarding(12)
                    .sell(128, "terrifictickets:ticket").inExchangeFor(24, "minecraft:emerald").times(2).rewarding(24)
                    .sell(256, "terrifictickets:ticket").inExchangeFor(48, "minecraft:emerald").times(1).rewarding(48)
                    .addPool("Tokens")
                    .sell( 16, "terrifictickets:token").inExchangeFor( 6, "minecraft:emerald").times(8).rewarding( 6)
                    .sell( 32, "terrifictickets:token").inExchangeFor(12, "minecraft:emerald").times(4).rewarding(12)
                    .sell( 64, "terrifictickets:token").inExchangeFor(24, "minecraft:emerald").times(2).rewarding(24)
                    .sell(128, "terrifictickets:token").inExchangeFor(48, "minecraft:emerald").times(1).rewarding(48)
                    .addPool("Passcard")
                    .sell( 1, "terrifictickets:passcard").inExchangeFor(32, "minecraft:emerald").times(1).rewarding(32)
                    .build();
        /*copyToFastUtilMap(ImmutableMap.of(
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
                        new SellForCurrencyItemFactory(getItem("carnivalconfections:candied_apple"), getItem("terrifictickets:token"),  12,  1,  3,  12),
                        new SellForCurrencyItemFactory(getItem("carnivalconfections:popcorn"),  16,  1,  4, 12),
                        new SellForCurrencyItemFactory(getItem("carnival-foods:hotdog_in_bun"),  5,  6,  4, 8),
                        new SellForCurrencyItemFactory(getItem("carnival-foods:vegan_hotdog"),  15,  6,  4, 32),
                        new SellForCurrencyItemFactory(getItem("extravaganza:hot_dog"), getItem("extravaganza:common_festive_coin"),  5,  6,  4, 8),
                        new SellForCurrencyItemFactory(getItem("extravaganza:hot_dog_with_mayonnaise"), getItem("extravaganza:common_festive_coin"),  15,  6,  4, 32),
                        new SellForCurrencyItemFactory(getItem("extravaganza:popcorn"), getItem("extravaganza:uncommon_festive_coin"),  3,  1,  4, 12),
                        new SellForCurrencyItemFactory(getItem("sinister-circus:churros"),  12,  16,  3,  12),
                        new SellForCurrencyItemFactory(getItem("sinister-circus:sugar_frosted_churros"),  18,  16,  3,  24),
                        new SellForCurrencyItemFactory(getItem("sinister-circus:choco_frosted_churros"),  18,  16,  3,  24),
                        new SellForCurrencyItemFactory(getItem("duck:popcorn"),  3,  5,  4, 12),
                        new SellForCurrencyItemFactory(getItem("duck:duck_pop"),  4,  5,  4, 12),
                        new SellForCurrencyItemFactory(getItem("duck:duck_cornduck"),  8,  5,  4, 12),
                        new SellForCurrencyItemFactory(getItem("minecraft:milk_bucket"), 10, 1,  16, 13),
                        new SellForCurrencyItemFactory(getItem("minecraft:wooden_sword"), 3, 1,  16, 10),
                },
            2, //Candy Items
                new TradeOffers.Factory[]{
                        new SellForCurrencyItemFactory(getItem("carnival-foods:cotton_candy"), getItem("terrifictickets:token"),  12,  1,  3,  12),
                        new SellForCurrencyItemFactory(getItem("sinister-circus:cotton_candy"), getItem("terrifictickets:token"),  12,  1,  3,  12),
                        new SellForCurrencyItemFactory(getItem("carnivalconfections:strong_candy"),  12,  5,  4, 6),
                        new SellForCurrencyItemFactory(getItem("carnivalconfections:energetic_candy"),  12,  5,  4, 6),
                        new SellForCurrencyItemFactory(getItem("carnivalconfections:special_candy"),  12,  5,  4, 6),
                        new SellForCurrencyItemFactory(getItem("extravaganza:golden_candy_cane"), getItem("extravaganza:common_festive_coin"),  3,  4,  4, 8),
                        new SellForCurrencyItemFactory(getItem("extravaganza:green_candy_cane"), getItem("extravaganza:common_festive_coin"),  3,  4,  4, 8),
                        new SellForCurrencyItemFactory(getItem("extravaganza:red_candy_cane"), getItem("extravaganza:common_festive_coin"),  3,  4,  4, 8),
                        new SellForCurrencyItemFactory(getItem("ducktor:lozenge"),  5,  7,  7,  16),
                        new SellForCurrencyItemFactory(getItem("minecraft:cookie"), 4, 16,  16, 6),
                },
            3, //Flowers
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
            4, //Ductor
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
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 128, 1, 1, 3),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 64, 1, 1, 32),
                        new SellForCurrencyItemFactory(getItem("honque:the_funny"), getItem("terrifictickets:token"), 16, 1, 1, 320),
                }
            )
        );*/
}
