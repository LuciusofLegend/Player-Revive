package lucius.revive.mixin;

import lucius.revive.accessor.PlayerAccessor;
import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
        ((PlayerAccessor) player).makeConscious();
        this.stopReviving();
        return player.getEntityName();
    }

    @Unique
    public MutableText convertDeathCountdown() {
        int countdownInSeconds = (int) Math.ceil((double) this.reviveHandler.deathCountdown / 20);
        int minutes = (int) Math.floor((double) countdownInSeconds / 60);
        int seconds = countdownInSeconds % 60;

        Formatting color = null;
        if(minutes <= 5) { color = Formatting.GREEN; }
        if(minutes <= 3) { color = Formatting.YELLOW; }
        if(minutes <= 1) { color = Formatting.RED; }

        if(seconds < 10) {
            return Text.literal(minutes + ":" + "0" + seconds).setStyle(Style.EMPTY.withColor(color));
        }
        return Text.literal(minutes + ":" + seconds).setStyle(Style.EMPTY.withColor(color));
    }

    @Unique
    public void updateReviveProgress() {

    }
}
