package net.shadowfacts.yeoldetanks.proxy;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.compat.computercraft.CompatComputerCraft;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.event.ForgeEventHandler;
import net.shadowfacts.yeoldetanks.recipe.ModRecipes;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());

		YOTConfig.init(event);

		YeOldeTanks.blocks.initBlocks();
		YeOldeTanks.items.initItems();

		registerTileEntities();

		ModEntities.preInit();

		ModAchievements.registerAchievements();

		YeOldeTanks.compat.registerModule(CompatComputerCraft.class);
		registerClientModules();

		YeOldeTanks.compat.preInit(event);

		registerRenderers();
	}

	public void init(FMLInitializationEvent event) {
		YeOldeTanks.compat.init(event);
		ModRecipes.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		YeOldeTanks.compat.postInit(event);
		ModRecipes.postInit();
	}

	private void registerTileEntities() {
		YeOldeTanks.log.info("Registering tile entities");

		GameRegistry.registerTileEntity(TileEntityBarrel.class, YeOldeTanks.modId + ".tileentity.barrel");
		GameRegistry.registerTileEntity(TileEntityCreativeBarrel.class, YeOldeTanks.modId + ".tileentity.creativebarrel");
	}

	protected void registerClientModules() {}

	public void registerRenderers() {}

	public World getClientWorld() {
		return null;
	}

}
