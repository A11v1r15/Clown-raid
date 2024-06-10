package net.a11v1r15.clownraid.mixin;

import net.a11v1r15.clownraid.ClownRaid;
import net.a11v1r15.clownraid.NightSkipCallback;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	@Inject(method = "tick(Ljava/util/function/BooleanSupplier;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;wakeSleepingPlayers()V"))
	private void clownRaid$addNightSkipEvent(CallbackInfo ci) {
		ClownRaid.LOGGER.info("Night Skipped!");
		ActionResult result = NightSkipCallback.EVENT.invoker().NightSkipped();
	}
}