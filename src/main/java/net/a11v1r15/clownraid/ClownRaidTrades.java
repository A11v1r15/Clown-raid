package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableMap;
import gay.lemmaeof.terrifictickets.TerrificTickets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.village.TradeOffers;

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
    public static final Int2ObjectMap<TradeOffers.Factory[]> CLOWN_TRADES =
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

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap(map);
    }
}
