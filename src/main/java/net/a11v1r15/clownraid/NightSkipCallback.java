package net.a11v1r15.clownraid;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface NightSkipCallback {
    Event<NightSkipCallback> EVENT = EventFactory.createArrayBacked(NightSkipCallback.class,
            (listeners) -> () -> {
                for (NightSkipCallback listener : listeners) {
                    ActionResult result = listener.NightSkipped();

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult NightSkipped();
}
