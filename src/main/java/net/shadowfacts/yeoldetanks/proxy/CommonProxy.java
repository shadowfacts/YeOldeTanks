package net.shadowfacts.yeoldetanks.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.recipe.YOTRecipes;
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

		YeOldeTanks.log.info("Registering TileEntities");
		registerTileEntities();

		YeOldeTanks.log.info("Registering recipes");
		registerRecipes();

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

	private void registerRecipes() {
		YOTRecipes.register();
	}

	protected void registerClientModules() {}

	public void registerRenderers() {}

}
