package net.shadowfacts.yeoldetanks.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.recipe.ModRecipes;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.compat.ModCompat;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		YeOldeTanks.blocks.preInit(event);

		YeOldeTanks.items.preInit(event);

		registerTileEntities();

		ModEntities.preInit();

		ModAchievements.registerAchievements();

		ModCompat.registerModules();
		registerClientModules();
		ModCompat.preInit(event);

		registerRenderers();
	}

	public void init(FMLInitializationEvent event) {
		ModCompat.init(event);
		ModRecipes.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModCompat.postInit(event);
		ModRecipes.postInit();
	}

	private void registerTileEntities() {
		YeOldeTanks.log.info("Registering tile entities");

		GameRegistry.registerTileEntity(TileEntityBarrel.class, YeOldeTanks.modId + ".tileentity.barrel");
		GameRegistry.registerTileEntity(TileEntityCreativeBarrel.class, YeOldeTanks.modId + ".tileentity.creativebarrel");
	}

	protected void registerClientModules() {}

	public void registerRenderers() {}

}
