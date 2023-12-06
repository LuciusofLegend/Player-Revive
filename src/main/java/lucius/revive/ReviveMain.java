package lucius.revive;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviveMain implements ModInitializer {

	public static final String MODID = "revive";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public void onInitialize() {
		LOGGER.info("Hello, just a little revive mod here!");
	}
}