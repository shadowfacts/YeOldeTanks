package net.shadowfacts.yeoldetanks;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.shadowmc.config.Config;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.config.ConfigProperty;

/**
 * @author shadowfacts
 */
@Config(name = "YeOldeTanks")
public class YOTConfig {

	@ConfigProperty(comment = "The capacity of the barrel in milibuckets")
	public static int barrelCapacity = 55000;

	@ConfigProperty(comment = "Can the barrel be filled from any side.\nBy default, it can only be filled from the top if the lid is off.")
	public static boolean fillFromAnySide = false;

	@ConfigProperty(comment = "Can the barrel be drained from any side.\nBy default, it can only be drained from the bottom")
	public static boolean drainFromAnySide = false;

	@ConfigProperty(comment = "Render the fluid contained in the barrel")
	public static boolean renderFluid = true;

	@ConfigProperty(comment = "Automatically output to the bottom")
	public static boolean autoOutputBottom = true;

	@ConfigProperty(comment = "Mystical, magical stuff and things", category = "misc", name = "#BlameEllpeck")
	public static boolean blameEllpeck = false;

	public static void init(FMLPreInitializationEvent event) {
		YeOldeTanks.log.info("Loading config");
		ConfigManager.instance.configDirPath = event.getModConfigurationDirectory().getAbsolutePath();
		ConfigManager.instance.register(YeOldeTanks.modId, YOTConfig.class);
		ConfigManager.instance.load(YeOldeTanks.modId);
	}

}
