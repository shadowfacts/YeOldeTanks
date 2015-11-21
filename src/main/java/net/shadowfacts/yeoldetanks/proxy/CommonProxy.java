package net.shadowfacts.yeoldetanks.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.recipe.ModRecipes;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.compat.YOTCompat;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		YeOldeTanks.log.info("Initializing blocks");
		YeOldeTanks.blocks.preInit(event);

		YeOldeTanks.log.info("Initializing items");
		YeOldeTanks.items.preInit(event);

		YeOldeTanks.log.info("Registering tile entities");
		registerTileEntities();

		YeOldeTanks.log.info("Registering recipes");
		ModRecipes.register();

		YeOldeTanks.log.info("Registering entities");
		ModEntities.init();

		YeOldeTanks.log.info("Registering achievements");
		ModAchievements.registerAchievements();

		YOTCompat.registerModules();
		registerClientModules();
		YOTCompat.preInit(event);

		registerRenderers();
	}

	public void init(FMLInitializationEvent event) {
		YOTCompat.init(event);
	}

	public void postInit(FMLPostInitializationEvent event) {
		YOTCompat.postInit(event);
	}

	private void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBarrel.class, YeOldeTanks.modId + ".tileentity.barrel");
	}

	protected void registerClientModules() {}

	public void registerRenderers() {}

}
