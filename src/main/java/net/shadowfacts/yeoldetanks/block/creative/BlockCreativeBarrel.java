package net.shadowfacts.yeoldetanks.block.creative;

import net.shadowfacts.yeoldetanks.block.base.BlockBarrelBase;

/**
 * @author shadowfacts
 */
public class BlockCreativeBarrel extends BlockBarrelBase<TileEntityCreativeBarrel> {

	public BlockCreativeBarrel() {
		super("creative_barrel");
	}

	@Override
	public boolean isCreative() {
		return true;
	}

	@Override
	public Class<TileEntityCreativeBarrel> getTileEntityClass() {
		return TileEntityCreativeBarrel.class;
	}

}
