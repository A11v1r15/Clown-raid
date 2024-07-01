package net.a11v1r15.clownraid;

import gay.lemmaeof.terrifictickets.component.PasscardComponent;
import net.a11v1r15.clownraid.util.ClownRaidTrading;
import net.a11v1r15.clownraid.util.RegistryHelper;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Pair;
import net.minecraft.village.TradeOffers;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class ClownRaidTrades {
    public static final HashMap<String, TradeOffers.Factory[]> PRESENTER_TRADES = new ClownRaidTrading.Builder()
            .addPool("Tickets")
            .sell( 32, "terrifictickets:ticket").inExchangeFor( 6, "minecraft:emerald").times(8).rewarding( 6)
            .sell( 64, "terrifictickets:ticket").inExchangeFor(12, "minecraft:emerald").times(4).rewarding(12)
            .sell(128, "terrifictickets:ticket").inExchangeFor(24, "minecraft:emerald").times(2).rewarding(24)
            .sell(256, "terrifictickets:ticket").inExchangeFor(48, "minecraft:emerald").times(1).rewarding(48)
            .sell( 32, "terrifictickets:ticket").inExchangeFor( 3, "minecraft:diamond").times(8).rewarding( 6)
            .sell( 64, "terrifictickets:ticket").inExchangeFor( 6, "minecraft:diamond").times(4).rewarding(12)
            .sell(128, "terrifictickets:ticket").inExchangeFor(12, "minecraft:diamond").times(2).rewarding(24)
            .sell(256, "terrifictickets:ticket").inExchangeFor(24, "minecraft:diamond").times(1).rewarding(48)
            .addPool("Tokens")
            .sell( 16, "terrifictickets:token").inExchangeFor( 6, "minecraft:emerald").times(8).rewarding( 6)
            .sell( 32, "terrifictickets:token").inExchangeFor(12, "minecraft:emerald").times(4).rewarding(12)
            .sell( 64, "terrifictickets:token").inExchangeFor(24, "minecraft:emerald").times(2).rewarding(24)
            .sell(128, "terrifictickets:token").inExchangeFor(48, "minecraft:emerald").times(1).rewarding(48)
            .sell( 16, "terrifictickets:token").inExchangeFor( 3, "minecraft:diamond").times(8).rewarding( 6)
            .sell( 32, "terrifictickets:token").inExchangeFor( 6, "minecraft:diamond").times(4).rewarding(12)
            .sell( 64, "terrifictickets:token").inExchangeFor(12, "minecraft:diamond").times(2).rewarding(24)
            .sell(128, "terrifictickets:token").inExchangeFor(24, "minecraft:diamond").times(1).rewarding(48)
            .addPool("Passcard")
            .sell("terrifictickets:passcard").inExchangeFor(32, "minecraft:emerald").times(1).rewarding(32).withComponents(new Pair<>(RegistryHelper.getComponentType("terrifictickets:passcard"), new PasscardComponent(16, 128)))
            .sell("terrifictickets:passcard").inExchangeFor(16, "minecraft:diamond").times(1).rewarding(32).withComponents(new Pair<>(RegistryHelper.getComponentType("terrifictickets:passcard"), new PasscardComponent(16, 128)))
            .sell("terrifictickets:passcard").inExchangeFor(32, "minecraft:emerald").times(1).rewarding(32).withComponents(new Pair<>(RegistryHelper.getComponentType("terrifictickets:passcard"), new PasscardComponent(32, 64)))
            .sell("terrifictickets:passcard").inExchangeFor(16, "minecraft:diamond").times(1).rewarding(32).withComponents(new Pair<>(RegistryHelper.getComponentType("terrifictickets:passcard"), new PasscardComponent(32, 64)))
            .build();

    public static final HashMap<String, TradeOffers.Factory[]> MAGICIAN_TRADES = new ClownRaidTrading.Builder()
            .addPool("Common")
            .sell("minecraft:suspicious_stew").inExchangeForDefault(3).times(8).rewarding(12).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:suspicious_stew_effects"), new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.INVISIBILITY, 666)))))
            .sell(16, "trickster:scroll_and_quill").inExchangeForDefault(6).times(4)
            .sell(3, "minecraft:rabbit_foot").inExchangeForDefault(3).times(8)
            .sell(1, "minecraft:rabbit_hide").inExchangeForDefault(6).times(6)
            .sell(1, "aji-maji:playing_card").inExchangeForDefault(3).times(6)
            .sell(1, "aji-maji:card_deck").inExchangeForDefault(3).times(6)
            .sell(1, "aji-maji:card_box").inExchangeForDefault(3).times(6)
            .addPool("Premium")
            .sell("trickster:mirror_of_evaluation").inExchangeForDefault(32).times(3).rewarding(24)
            .sell("aji-maji:magic_carpet").inExchangeForDefault(32).times(3).rewarding(24)
            .sell("trickster:wand").inExchangeForDefault(32).times(3).rewarding(24)
            .addPool("Hat")
            .sell("magicians_hat:magicians_hat").inExchangeForDefault(64).times(1).rewarding(320)
            .sell("trickster:top_hat").inExchangeForDefault(64).times(1).rewarding(320)
            .sell("aji-maji:top_hat").inExchangeForDefault(64).times(1).rewarding(320)
            .sell("aji-maji:bunny_ears").inExchangeForDefault(64).times(1).rewarding(320)
            .build();

    public static final HashMap<String, TradeOffers.Factory[]> SELLER_TRADES = new ClownRaidTrading.Builder()
            .addPool("Food")
            .sell("carnivalconfections:candied_apple").inExchangeForDefault(24).times(3).rewarding(12)
            .sell("carnivalconfections:popcorn").inExchangeForDefault(24).times(3).rewarding(12)
            .sell(6, "carnival-foods:hotdog_in_bun").inExchangeForDefault(5).times(4)
            .sell(6, "carnival-foods:vegan_hotdog").inExchangeForDefault(15).times(4).rewarding(24)
            .sell(6, "extravaganza:hot_dog").inExchangeFor(5, "extravaganza:common_festive_coin").times(4)
            .sell(6, "extravaganza:hot_dog_with_mayonnaise").inExchangeFor(15, "extravaganza:common_festive_coin").times(4).rewarding(32)
            .sell("extravaganza:popcorn").inExchangeFor(3, "extravaganza:uncommon_festive_coin").times(4).rewarding(12)
            .sell(16, "sinister-circus:churros").inExchangeForDefault(12).times(3).rewarding(24)
            .sell(16, "sinister-circus:sugar_frosted_churros").inExchangeForDefault(18).times(3).rewarding(24)
            .sell(16, "sinister-circus:choco_frosted_churros").inExchangeForDefault(18).times(3).rewarding(24)
            .sell(5, "duck:popcorn").inExchangeForDefault(3).times(4).rewarding(12)
            .sell(5, "duck:duck_pop").inExchangeForDefault(4).times(4).rewarding(12)
            .sell(5, "duck:duck_cornduck").inExchangeForDefault(8).times(4).rewarding(12)
            .sell("minecraft:milk_bucket").inExchangeForDefault(10).times(4).rewarding(12)
            .sell("minecraft:wooden_sword").inExchangeForDefault(3).times(4).rewarding(12)
            .addPool("Candy")
            .sell("carnival-foods:cotton_candy").inExchangeForDefault(24).times(3).rewarding(12)
            .sell("sinister-circus:cotton_candy").inExchangeForDefault(24).times(3).rewarding(12)
            .sell(5, "carnivalconfections:strong_candy").inExchangeForDefault(12).times(4)
            .sell(5, "carnivalconfections:energetic_candy").inExchangeForDefault(12).times(4)
            .sell(5, "carnivalconfections:special_candy").inExchangeForDefault(12).times(4)
            .sell(4, "extravaganza:golden_candy_cane").inExchangeFor(3, "extravaganza:common_festive_coin").times(4)
            .sell(4, "extravaganza:green_candy_cane").inExchangeFor(3, "extravaganza:common_festive_coin").times(4)
            .sell(4, "extravaganza:red_candy_cane").inExchangeFor(3, "extravaganza:common_festive_coin").times(4)
            .sell(7, "ducktor:lozenge").inExchangeForDefault(5).times(7).rewarding(16)
            .sell(16, "minecraft:cookie").inExchangeForDefault(4).times(16)
            .addPool("Flower")
            .sell(7, "minecraft:allium").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:azure_bluet").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:blue_orchid").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:cornflower").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:dandelion").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:lily_of_the_valley").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:oxeye_daisy").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:poppy").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:orange_tulip").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:pink_tulip").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:red_tulip").inExchangeForDefault(7).times(7)
            .sell(7, "minecraft:white_tulip").inExchangeForDefault(7).times(7)
            .addPool("Ductor")
            .sellIfModIsPresent(7, "minecraft:warped_fungus", "ducktor").inExchangeForDefault(3).times(7)
            .sellIfModIsPresent(7, "minecraft:sweet_berries", "ducktor").inExchangeForDefault(3).times(7)
            .sell(7, "ducktor:lozenge").inExchangeForDefault(3).times(7)
            .sell(7, "ducktor:scented_candle").inExchangeForDefault(7).times(7)
            .sell(7, "ducktor:lozenge").inExchangeForDefault(3).times(7)
            .sell(7, "ducktor:scented_candle").inExchangeForDefault(7).times(7)
            .sellIfModIsPresent("minecraft:potion", "ducktor").inExchangeForDefault(3).times(7).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:potion_contents"), new PotionContentsComponent(RegistryHelper.getPotionEntry("ducktor:magnificent"))))
            .sellIfModIsPresent("minecraft:potion", "ducktor").inExchangeForDefault(6).times(7).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:potion_contents"), new PotionContentsComponent(RegistryHelper.getPotionEntry("ducktor:rejuvenation"))))
            .sellIfModIsPresent("minecraft:potion", "ducktor").inExchangeForDefault(12).times(7).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:potion_contents"), new PotionContentsComponent(RegistryHelper.getPotionEntry("ducktor:long_rejuvenation"))))
            .sellIfModIsPresent("minecraft:potion", "ducktor").inExchangeForDefault(12).times(7).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:potion_contents"), new PotionContentsComponent(RegistryHelper.getPotionEntry("ducktor:strong_rejuvenation"))))
            .addPool("Balloon")
            .sell(2, "balloonsaway:camel_balloon").inExchangeForDefault(5).times(7)
            .sell(2, "balloonsaway:villager_balloon").inExchangeForDefault(5).times(7)
            .sell(2, "balloonsaway:white_balloon").inExchangeForDefault(5).times(7)
            .sell(2, "balloonsaway:wolf_balloon").inExchangeForDefault(5).times(7)
            .sell(2, "balloonsaway:sniffer_balloon").inExchangeForDefault(5).times(7)
            .sell(2, "extravaganza:cherry_balloon").inExchangeFor(3, "extravaganza:common_festive_coin").times(7)
            .sell(2, "extravaganza:creeper_balloon").inExchangeFor(3, "extravaganza:common_festive_coin").times(7)
            .sell(2, "sinister-circus:balloon").inExchangeForDefault(7).times(7)
            .sell(2, "sinister-circus:balloon_animal").inExchangeForDefault(7).times(7)
            .sell(2, "sinister-circus:balloon_bomb").inExchangeForDefault(7).times(7)
            .sell(1, "ballooning:magical_lead").inExchangeForDefault(64).times(1).rewarding(64)
            .build();

    public static final HashMap<String, TradeOffers.Factory[]> CLOWN_TRADES = new ClownRaidTrading.Builder()
            .addPool("Common")
            .sell(8, "bombastic:party_popper").inExchangeForDefault(3).times(8)
            .sell(8, "confetti_stuff:party_popper").inExchangeForDefault(5).times(8)
            .sell(1, "bombastic:juggling_ball").inExchangeForDefault(6).times(8)
            .sell(3, "minecraft:pumpkin_pie").inExchangeForDefault(3).times(8)
            .sell(16, "minecraft:egg").inExchangeForDefault(6).times(4)
            .sell(16, "minecraft:snowball").inExchangeForDefault(6).times(4)
            .sell("minecraft:cake").inExchangeForDefault(12).times(8)
            .sell(7, "minecraft:sunflower").inExchangeForDefault(1).times(16)
            .sell(3, "minecraft:carved_pumpkin").enchantedWith("minecraft:binding_curse").inExchangeForDefault(13).times(8).rewarding(120)
            .sell("minecraft:suspicious_stew").inExchangeForDefault(3).times(8).rewarding(12).withComponents(new Pair<>(RegistryHelper.getComponentType("minecraft:suspicious_stew_effects"), new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.BLINDNESS, 666)))))
            .addPool("Premium")
            .sell("unhingedcarnivalsupplies:fun_ball").inExchangeForDefault(32).times(3).rewarding(48)
            .sell(1, "minecraft:tnt").inExchangeForDefault(32).times(3).rewarding(24)
            .sell(2, "magnificent_maw:curious_vial").inExchangeForDefault(32).times(3).rewarding(24)
            .sell(8, "ducktor:warding_candle").inExchangeForDefault(32).times(3).rewarding(24)
            .sell(16, "bombastic:pipe_bomb").inExchangeForDefault(64).times(2).rewarding(64).withComponents(new Pair<>(RegistryHelper.getComponentType("bombastic:pinned"), false))
            .sell("bombastic:clown_boots").inExchangeForDefault(16).times(2).rewarding(24)
            .sell("bombastic:clown_hair").inExchangeForDefault(16).times(2).rewarding(24)
            .addPool("The Funny")
            .sell("honque:the_funny").inExchangeForDefault(256).rewarding(6)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(128).rewarding(64)
            .sell("honque:the_funny").inExchangeForDefault(32) .rewarding(640)
            .build();
}
