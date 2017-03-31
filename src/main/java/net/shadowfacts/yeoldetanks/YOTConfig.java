package net.shadowfacts.yeoldetanks;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.config.Config;
import net.shadowfacts.config.ConfigManager;

import java.io.File;

/**
 * @author shadowfacts
 */
@Config(name = "YeOldeTanks")
public class YOTConfig {

	public static Configuration config;

	@Config.Prop(description = "The capacity of the barrel in millibuckets")
	public static int barrelCapacity = 55_000;

	@Config.Prop(description = "The capacity of the Obsidian Barrel in millibuckets")
	public static int obsidianBarrelCapacity = 110_000;

	@Config.Prop(description = "Can the barrel be filled from any side.\nBy default, it can only be filled from the top if the lid is off.")
	public static boolean fillFromAnySide = false;

	@Config.Prop(description = "Can the barrel be drained from any side.\nBy default, it can only be drained from the bottom")
	public static boolean drainFromAnySide = false;

	@Config.Prop(description = "Render the fluid contained in the barrel")
	public static boolean renderFluid = true;

	@Config.Prop(description = "Automatically output to the bottom")
	public static boolean autoOutputBottom = true;

	@Config.Prop(description = "Should the barrel item and barrel minecart item store fluids")
	public static boolean itemsStoreFluids = true;

	@Config.Prop(description = "Mystical, magical stuff and things", category = "misc", name = "#BlameEllpeck")
	public static boolean blameEllpeck = false;

	public static void init(FMLPreInitializationEvent event) {
		config = new Configuration(new File(event.getModConfigurationDirectory(), "YeOldeTanks.cfg"));
		load();
	}

	public static void load() {
		YeOldeTanks.log.info("Loading config");

		config.load();

		ConfigManager.load(YOTConfig.class, Configuration.class, config);

		if (config.hasChanged()) config.save();
	}

}
