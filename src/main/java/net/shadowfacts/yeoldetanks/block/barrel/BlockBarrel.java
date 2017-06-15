package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.item.ItemStack;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
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

//	@Override
//	public Achievement getAchievement(ItemStack stack) {
//		return ModAchievements.craftBarrel;
//	}

}
