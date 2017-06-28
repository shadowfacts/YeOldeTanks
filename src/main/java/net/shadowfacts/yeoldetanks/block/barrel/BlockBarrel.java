package net.shadowfacts.yeoldetanks.block.barrel;

import net.shadowfacts.yeoldetanks.block.base.BlockBarrelBase;

/**
 * @author shadowfacts
 */
public class BlockBarrel extends BlockBarrelBase<TileEntityBarrel> {

	public BlockBarrel() {
		super("barrel");
	}

	@Override
	public boolean isCreative() {
		return false;
	}

	@Override
	public Class<TileEntityBarrel> getTileEntityClass() {
		return TileEntityBarrel.class;
	}

}
