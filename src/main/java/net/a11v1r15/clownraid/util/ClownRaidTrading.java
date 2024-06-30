package net.a11v1r15.clownraid.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Pair;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;

import java.util.ArrayList;
import java.util.HashMap;

public class ClownRaidTrading {
    public static class Builder {
        private HashMap<String, ArrayList<OpenFactory>> output;
        private OpenFactory lastFactory;
        private String lastTag;
        private boolean validTrade = true;

        public Builder (){
            output = new HashMap<>();
        }

        public Builder addPool(String poolTag){
            lastTag = poolTag;
            output.putIfAbsent(lastTag, new ArrayList<>());
            return this;
        }

        public Builder sell(int count, String item){
            if(FabricLoader.getInstance().isModLoaded(item.split(":")[0])){
                lastFactory = new OpenFactory();
                output.get(lastTag).add(lastFactory);
                lastFactory.stack = RegistryHelper.getItem(item);
                lastFactory.count = count;
                validTrade = true;
            } else {
                validTrade = false;
            }
            return this;
        }

        public Builder inExchangeFor(int price, String... items){
            if (validTrade){
                for (String item : items){
                    if(FabricLoader.getInstance().isModLoaded(item.split(":")[0])){
                        lastFactory.currency = RegistryHelper.getItem(item);
                        break;
                    }
                }
                lastFactory.price = price;
            }
            return this;
        }

        public Builder times(int maxUses){
            if (validTrade){
                lastFactory.maxUses = maxUses;
            }
            return this;
        }

        public Builder enchantedWith(RegistryKey<Enchantment> enchant, int level){
            if (validTrade){
                lastFactory.enchantment = enchant;
                lastFactory.enchantmentLevel = level;
            }
            return this;
        }

        public Builder rewarding(int xp){
            if (validTrade){
                lastFactory.experience = xp;
            }
            return this;
        }

        public Builder ifModIsPresent(String modID){
            if(!FabricLoader.getInstance().isModLoaded(modID)){
                validTrade = false;
            }
            return this;
        }

        public HashMap<String, TradeOffers.Factory[]> build() {
            HashMap<String, TradeOffers.Factory[]> result = new HashMap<>();
            for (String key : output.keySet()){
                ArrayList<TradeOffers.Factory> list = new ArrayList<>();
                for(OpenFactory open : output.get(key)){
                    list.add(
                            new Factory(
                                    new TradedItem(open.currency, open.price),
                                    open.stack, open.count,
                                    open.maxUses, open.experience,
                                    open.enchantment, open.enchantmentLevel,
                                    open.components
                            )
                    );
                }
                result.put(key, (TradeOffers.Factory[]) list.toArray());
            }
            return result;
        }
    }

    private static class OpenFactory{
        public Item currency = FabricLoader.getInstance().isModLoaded("terrifictickets") ?
                RegistryHelper.getItem("terrifictickets:ticket") : (
                FabricLoader.getInstance().isModLoaded("extravaganza") ?
                        RegistryHelper.getItem("extravaganza:common_festive_coin") :
                        RegistryHelper.getItem("minecraft:emerald")
                );
        public int price = 3;
        public Item stack = RegistryHelper.getItem("minecraft:beetroot");
        public int count = 1;
        public RegistryKey<Enchantment> enchantment = null;
        public int enchantmentLevel = 1;
        public int maxUses = 1;
        public int experience = 6;
        public Pair<ComponentType, Object>[] components = null;
    }

    public static class Factory implements TradeOffers.Factory {
        private final TradedItem currency;
        private final Item stack;
        private final RegistryKey<Enchantment> enchantment;
        private final int enchantmentLevel;
        private final int maxUses;
        private final int experience;
        private final int count;
        private final float multiplier;
        private final Pair<ComponentType, Object>[] components;

        public Factory(TradedItem currency, Item stack, int count, int maxUses, int experience, RegistryKey<Enchantment> enchantment, int enchantmentLevel, Pair<ComponentType, Object>... components) {
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
