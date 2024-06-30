package net.a11v1r15.clownraid.util;

import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

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
}
