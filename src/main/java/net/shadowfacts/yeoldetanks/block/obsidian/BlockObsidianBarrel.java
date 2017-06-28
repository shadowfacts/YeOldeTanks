package net.shadowfacts.yeoldetanks.block.obsidian;

import net.shadowfacts.yeoldetanks.block.base.BlockBarrelBase;

/**
 * @author shadowfacts
 */
public class BlockObsidianBarrel extends BlockBarrelBase<TileEntityObsidianBarrel> {

	public BlockObsidianBarrel() {
		super("obsidian_barrel");
	}

	@Override
	public boolean isCreative() {
		return false;
	}

	@Override
	public Class<TileEntityObsidianBarrel> getTileEntityClass() {
		return TileEntityObsidianBarrel.class;
	}

}
