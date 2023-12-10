package lucius.revive.mixin;

import lucius.revive.accessor.PlayerAccessor;
import lucius.revive.config.ReviveConfig;
import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Shadow public abstract void onDeath(DamageSource damageSource);
    @Shadow private ServerPlayNetworkHandler networkHandler;

    @Unique
    private final PlayerReviveHandler reviveHandler = ((PlayerAccessor) this).reviveHandler();

    @Inject(method = "onDeath", at = @At(value = "HEAD"), cancellable = true)
    private void onDeathMixin(DamageSource damageSource, CallbackInfo ci) {
        if(this.reviveHandler.isConscious) {
            ((PlayerAccessor) this).makeUnconscious();
            ci.cancel();
        } else {
            ((PlayerAccessor) this).makeConscious();
        }
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void tickMixin(CallbackInfo ci) {
        if(!this.reviveHandler.isConscious) {
            this.reviveHandler.deathCountdown --;

            if(this.reviveHandler.deathCountdown == 0) {
                this.onDeath(this.reviveHandler.KOSource);
            }

            this.networkHandler.sendPacket(new OverlayMessageS2CPacket(((PlayerAccessor) this).convertDeathCountdown()));
        }

        if(this.reviveHandler.playerToRevive != null) {
            if(!((PlayerAccessor) this.reviveHandler.playerToRevive).reviveHandler().isConscious) {
                this.reviveHandler.reviveProgress ++;
                if(this.reviveHandler.reviveProgress == ReviveConfig.reviveTimerLength) {
                    ((PlayerAccessor) this).finishReviving();
                }
            } else {
                ((PlayerAccessor) this).stopReviving();
            }
        }
    }
}
