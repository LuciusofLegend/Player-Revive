package lucius.revive.mixin;

import lucius.revive.ReviveMain;
import lucius.revive.accessor.PlayerAccessor;
import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.commons.compress.harmony.pack200.NewAttributeBands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    private PlayerReviveHandler reviveHandler = new PlayerReviveHandler();

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void sharedHandlerTest(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.reviveHandler.deathCountdown = 10000;
        if(((PlayerAccessor) this).reviveHandler().deathCountdown == this.reviveHandler.deathCountdown) {
            ReviveMain.LOGGER.info("Success!");
        } else {
            ReviveMain.LOGGER.info("Failure");
        }
        this.reviveHandler.deathCountdown = 6000;
    }
}
