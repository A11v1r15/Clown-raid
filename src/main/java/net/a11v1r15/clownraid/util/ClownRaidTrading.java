package net.a11v1r15.clownraid.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
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

        public Builder sell(String item){
            return sell(1, item);
        }

        public Builder sellIfModIsPresent(int count, String item, String modID){
            if(FabricLoader.getInstance().isModLoaded(item.split(":")[0]) &&
                    FabricLoader.getInstance().isModLoaded(modID)){
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

        public Builder sellIfModIsPresent(String item, String modID){
            return sellIfModIsPresent(1, item, modID);
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

        public Builder inExchangeForDefault(int price){
            if (validTrade){
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

        public Builder enchantedWith(String enchant, int level){
            if (validTrade){
                lastFactory.enchantment = enchant;
                lastFactory.enchantmentLevel = level;
            }
            return this;
        }

        public Builder enchantedWith(String enchant){
            return enchantedWith(enchant, 1);
        }

        public Builder rewarding(int xp){
            if (validTrade){
                lastFactory.experience = xp;
            }
            return this;
        }

        public Builder withComponents(Pair<ComponentType, Object>... components){
            if (validTrade){
                lastFactory.components = components;
            }
            return this;
        }

        public HashMap<String, TradeOffers.Factory[]> build() {
            HashMap<String, TradeOffers.Factory[]> result = new HashMap<>();
            for (String key : output.keySet()){
                if(!output.get(key).isEmpty()){
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
                    TradeOffers.Factory[] workaround = new TradeOffers.Factory[list.size()];
                    result.put(key, list.toArray(workaround));
                }
            }
            return result;
        }
    }

    private static class OpenFactory{
        public Item currency = RegistryHelper.getItem("terrifictickets:ticket");
        public int price = 3;
        public Item stack = RegistryHelper.getItem("minecraft:beetroot");
        public int count = 1;
        public String enchantment = null;
        public int enchantmentLevel = 1;
        public int maxUses = 1;
        public int experience = 6;
        public Pair<ComponentType, Object>[] components = null;
    }

    public static class Factory implements TradeOffers.Factory {
        private final TradedItem currency;
        private final Item stack;
        private final String enchantment;
        private final int enchantmentLevel;
        private final int maxUses;
        private final int experience;
        private final int count;
        private final float multiplier;
        private final Pair<ComponentType, Object>[] components;

        public Factory(TradedItem currency, Item stack, int count, int maxUses, int experience, String enchantment, int enchantmentLevel, Pair<ComponentType, Object>... components) {
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
                RegistryEntry<Enchantment> enchantmentEntry = entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.of(enchantment)).orElse(null);
                if(enchantmentEntry != null)
                    itemStack.addEnchantment(enchantmentEntry, this.enchantmentLevel);
            }
            if(this.components != null)
                for (Pair<ComponentType, Object> component : this.components){
                    ComponentType componentType = component.getLeft();
                    Object value = component.getRight();
                    itemStack.set(componentType, value);
                }
            return new TradeOffer(this.currency, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}
