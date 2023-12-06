package lucius.revive;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviveMain implements DedicatedServerModInitializer {

	public static final String MODID = "revive";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitializeServer() {
		LOGGER.info("Hello, just a little revive mod here!");
	}
}