package net.shadowfacts.yeoldetanks.block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.item.ItemBlockBarrel;

/**
 * @author shadowfacts
 */
public class ModBlocks {

	public BlockBarrel barrel;

	public void preInit(FMLPreInitializationEvent event) {
		YeOldeTanks.log.info("Initializing blocks");

		createBlocks();
		registerBlocks();
	}

	private void createBlocks() {
		barrel = new BlockBarrel();
	}

	private void registerBlocks() {
		GameRegistry.registerBlock(barrel, ItemBlockBarrel.class, "yot.barrel");
	}

}
