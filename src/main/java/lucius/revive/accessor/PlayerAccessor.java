package lucius.revive.accessor;

import lucius.revive.handler.PlayerReviveHandler;
import net.minecraft.text.MutableText;

public interface PlayerAccessor {

    PlayerReviveHandler reviveHandler();
    void makeConscious();
    void makeUnconscious();
    String startReviving();
    String stopReviving();
    String finishReviving();
    MutableText convertDeathCountdown();
}
