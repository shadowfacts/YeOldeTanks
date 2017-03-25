package net.shadowfacts.yeoldetanks.block;

import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.block.base.ItemBlockBarrelBase;
import net.shadowfacts.yeoldetanks.block.cosmetic.BlockCosmeticBarrel;
import net.shadowfacts.yeoldetanks.block.creative.BlockCreativeBarrel;

/**
 * @author shadowfacts
 */
public class ModBlocks extends net.shadowfacts.shadowmc.block.ModBlocks {

	public BlockBarrel barrel;
	public BlockCreativeBarrel creativeBarrel;
	public BlockCosmeticBarrel cosmeticBarrel;

	@Override
	public void init() {
		YeOldeTanks.log.info("Initializing blocks");

		register(barrel = new BlockBarrel(), new ItemBlockBarrelBase(barrel));
		register(creativeBarrel = new BlockCreativeBarrel(), new ItemBlockBarrelBase(creativeBarrel));
		register(cosmeticBarrel = new BlockCosmeticBarrel(), new ItemBlockBarrelBase(cosmeticBarrel));
	}

}
