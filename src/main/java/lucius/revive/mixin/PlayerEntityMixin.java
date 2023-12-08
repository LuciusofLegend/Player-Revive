package lucius.revive.mixin;

import lucius.revive.accessor.PlayerAccessor;
import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerAccessor {

    @Unique
    private final PlayerReviveHandler reviveHandler = new PlayerReviveHandler();
    @Override
    public PlayerReviveHandler reviveHandler() {
        return this.reviveHandler;
    }

    @Unique
    public void makeConscious() {
        this.reviveHandler.defaults();
    }

    @Unique
    public void makeUnconscious() {
        this.reviveHandler.isConscious = false;
    }

    @Unique
    public String startReviving() {
        return this.reviveHandler.playerToRevive.getEntityName();
    }

    @Unique
    public String stopReviving() {
        PlayerEntity player = this.reviveHandler.playerToRevive;
        this.reviveHandler.playerToRevive = null;
        this.reviveHandler.reviveProgress = 0;
        return player.getEntityName();
    }

    @Unique
    public String finishReviving() {
        PlayerEntity player = this.reviveHandler.playerToRevive;
        this.stopReviving();
        return player.getEntityName();
    }
}
