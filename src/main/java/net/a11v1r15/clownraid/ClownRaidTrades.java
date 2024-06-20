package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableMap;
import gay.lemmaeof.terrifictickets.TerrificTickets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import symbolics.division.honque.Honque;

public class ClownRaidTrades {
    public static final Int2ObjectMap<TradeOffers.Factory[]> PRESENTER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(TerrificTickets.TICKET,  3,  32,  8,  6),
                        new TradeOffers.SellItemFactory(TerrificTickets.TICKET,  6,  64,  4, 12),
                        new TradeOffers.SellItemFactory(TerrificTickets.TICKET, 12, 128,  2, 24),
                        new TradeOffers.SellItemFactory(TerrificTickets.TICKET, 24, 256,  1, 48)
                },
            2,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(TerrificTickets.TOKEN,  3,  16,  8,  6),
                        new TradeOffers.SellItemFactory(TerrificTickets.TOKEN,  6,  64,  4, 12),
                        new TradeOffers.SellItemFactory(TerrificTickets.TOKEN, 12,  64,  2, 24),
                        new TradeOffers.SellItemFactory(TerrificTickets.TOKEN, 24, 128,  1, 48)
                },
            3,
                new TradeOffers.Factory[]{
                    new TradeOffers.SellItemFactory(TerrificTickets.PASSCARD, 16, 1, 1, 32)
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> SELLER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.GOLDEN_APPLE,  16,  1,  3,  1),
                        new TradeOffers.SellItemFactory(Items.GOLDEN_CARROT,  6,  5,  4, 1),
                        new TradeOffers.SellItemFactory(Items.PUMPKIN_PIE, 12, 3,  2, 1),
                        new TradeOffers.SellItemFactory(Items.COOKIE, 4, 16,  16, 1),
                        new TradeOffers.SellItemFactory(Items.BAKED_POTATO, 4, 16,  16, 1),
                        new TradeOffers.SellItemFactory(Items.MILK_BUCKET, 10, 1,  16, 1)
                },
            2,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.ALLIUM,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.AZURE_BLUET,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.BLUE_ORCHID,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.CORNFLOWER,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.DANDELION,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.LILY_OF_THE_VALLEY,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.OXEYE_DAISY,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.POPPY,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.ORANGE_TULIP,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.PINK_TULIP,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.RED_TULIP,  1,  7,  7,  1),
                        new TradeOffers.SellItemFactory(Items.WHITE_TULIP,  1,  7,  7,  1)
                }
            )
        );
    public static final Int2ObjectMap<TradeOffers.Factory[]> CLOWN_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Items.PUMPKIN_PIE,  3,  3,  8,  6),
                        new TradeOffers.SellItemFactory(Items.EGG,  6,  16,  4, 6),
                        new TradeOffers.SellItemFactory(Items.SNOWBALL,  6,  16,  4, 6),
                        new TradeOffers.SellItemFactory(Items.CAKE, 12, 1,  8, 6),
                        new TradeOffers.SellItemFactory(Items.SUNFLOWER, 1, 6,  16, 3),
                        new TradeOffers.SellItemFactory(Items.TNT, 16, 1,  6, 12),
                        new TradeOffers.SellEnchantedToolFactory(Items.CARVED_PUMPKIN, 1, 8, 1),
                        new TradeOffers.SellSuspiciousStewFactory(StatusEffects.BLINDNESS, 99999, 10)
                },
            2,
                new TradeOffers.Factory[]{
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 3),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 3),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 3),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 64, 1, 1, 32),
                        new TradeOffers.SellItemFactory(Honque.THE_FUNNY, 16, 1, 1, 320)
                }
            )
        );

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap(map);
    }
}
