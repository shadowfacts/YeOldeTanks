package net.shadowfacts.yeoldetanks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.BlockCreativeBarrel;
import net.shadowfacts.yeoldetanks.item.ItemBlockBarrel;
import net.shadowfacts.yeoldetanks.item.ItemBlockCreativeBarrel;
import net.shadowfacts.yeoldetanks.item.ItemModelProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ModBlocks {

	private List<ItemModelProvider> modelProviders = new ArrayList<>();

	public BlockBarrel barrel;
	public BlockCreativeBarrel creativeBarrel;

	public void initBlocks() {
		YeOldeTanks.log.info("Initializing blocks");

		barrel = register(new BlockBarrel(), ItemBlockBarrel.class);
		creativeBarrel = register(new BlockCreativeBarrel(), ItemBlockCreativeBarrel.class);
	}

	public void initModels() {
		modelProviders.forEach(ItemModelProvider::initModel);
	}

	private <T extends Block> T register(T block) {
		return register(block, ItemBlock.class);
	}

	private <T extends Block> T register(T block, Class<? extends ItemBlock> itemBlockClass) {
		GameRegistry.registerBlock(block, itemBlockClass);
		if (block instanceof ItemModelProvider) modelProviders.add((ItemModelProvider)block);
		return block;
	}

}
