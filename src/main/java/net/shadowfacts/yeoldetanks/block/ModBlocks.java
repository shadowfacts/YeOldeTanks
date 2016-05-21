package net.shadowfacts.yeoldetanks.block;

import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.BlockCreativeBarrel;
import net.shadowfacts.yeoldetanks.item.ItemBlockBarrel;
import net.shadowfacts.yeoldetanks.item.ItemBlockCreativeBarrel;

/**
 * @author shadowfacts
 */
public class ModBlocks extends net.shadowfacts.shadowmc.block.ModBlocks {

	public BlockBarrel barrel;
	public BlockCreativeBarrel creativeBarrel;

	@Override
	public void init() {
		YeOldeTanks.log.info("Initializing blocks");

		register(barrel = new BlockBarrel(), new ItemBlockBarrel(barrel));
		register(creativeBarrel = new BlockCreativeBarrel(), new ItemBlockCreativeBarrel(creativeBarrel));
	}

}
