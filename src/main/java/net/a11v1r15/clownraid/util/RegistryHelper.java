package net.a11v1r15.clownraid.util;

import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class RegistryHelper {
    public static Item getItem (String identifier){
        return Registries.ITEM.get(Identifier.of(identifier));
    }

    public static ComponentType<?> getComponentType(String identifier){
        return Registries.DATA_COMPONENT_TYPE.get(Identifier.of(identifier));
    }

    public static Potion getPotion (String identifier){
        return Registries.POTION.get(Identifier.of(identifier));
    }

    public static RegistryEntry.Reference<Potion> getPotionEntry (String identifier){
        return Registries.POTION.getEntry(Identifier.of(identifier))
                .orElse(Registries.POTION.getEntry(Identifier.ofVanilla("awkward")).get());
    }

    public static RegistryEntry.Reference<Enchantment> getEnchantmentEntry (String identifier, World world){
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.of(identifier)).orElse(null);
    }

    public static StatusEffect getStatusEffect (String identifier){
        return Registries.STATUS_EFFECT.get(Identifier.of(identifier));
    }
}
