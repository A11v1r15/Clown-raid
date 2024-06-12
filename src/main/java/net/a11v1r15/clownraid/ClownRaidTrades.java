package net.a11v1r15.clownraid;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.TradeOffers;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ClownRaidTrades {
    public static final Int2ObjectMap<TradeOffers.Factory[]> PRESENTER_TRADES =
        copyToFastUtilMap(ImmutableMap.of(
            1,
                new TradeOffers.Factory[]{
                    new TradeOffers.SellItemFactory(Items.PAPER, 2, 1, 5, 1)
                },
            2,
                new TradeOffers.Factory[]{
                }
            )
        );

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap(map);
    }
}
