package lucius.revive;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviveMain implements ModInitializer {

	public static final String MODID = "modid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
	}
}