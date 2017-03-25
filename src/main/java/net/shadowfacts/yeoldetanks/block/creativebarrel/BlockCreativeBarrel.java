package net.shadowfacts.yeoldetanks.block.creativebarrel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
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
	protected Item getBarrelItem() {
		return Item.getItemFromBlock(YeOldeTanks.blocks.creativeBarrel);
	}

	@Override
	public Class<TileEntityCreativeBarrel> getTileEntityClass() {
		return TileEntityCreativeBarrel.class;
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftCreativeBarrel;
	}

}
