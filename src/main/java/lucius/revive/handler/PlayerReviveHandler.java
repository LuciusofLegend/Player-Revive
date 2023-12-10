package lucius.revive.handler;

import lucius.revive.config.ReviveConfig;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerReviveHandler {

    public boolean isConscious;
    public int deathCountdown;
    public DamageSource KOSource;
    public int tickDisconnected;
    public ServerPlayerEntity playerToRevive;
    public int reviveProgress;

    public PlayerReviveHandler() {
        this.defaults();
    }

    public PlayerReviveHandler(boolean isConscious, int deathCountdown, int tickDisconnected) {
        this.defaults();
        this.isConscious = isConscious;
        this.deathCountdown = deathCountdown;
        this.tickDisconnected = tickDisconnected;
    }

    public void defaults() {
        this.isConscious = true;
        this.deathCountdown = ReviveConfig.deathCountdownLength;
        this.KOSource = null;
        this.tickDisconnected = 0;
        this.playerToRevive = null;
        this.reviveProgress = 0;
    }
}
