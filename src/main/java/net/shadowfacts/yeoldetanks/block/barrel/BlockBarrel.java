package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
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
	protected Item getBarrelItem() {
		return Item.getItemFromBlock(YeOldeTanks.blocks.barrel);
	}

	@Override
	public Class<TileEntityBarrel> getTileEntityClass() {
		return TileEntityBarrel.class;
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftBarrel;
	}

}
