package lucius.revive.mixin;

import lucius.revive.accessor.PlayerAccessor;
import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerAccessor {

    private PlayerReviveHandler reviveHandler = new PlayerReviveHandler();
    @Override
    public PlayerReviveHandler reviveHandler() {
        return this.reviveHandler;
    }
}
