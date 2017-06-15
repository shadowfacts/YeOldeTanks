package net.shadowfacts.yeoldetanks.block.creative;

import net.minecraft.item.ItemStack;
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
	public Class<TileEntityCreativeBarrel> getTileEntityClass() {
		return TileEntityCreativeBarrel.class;
	}

//	@Override
//	public Achievement getAchievement(ItemStack stack) {
//		return ModAchievements.craftCreativeBarrel;
//	}

}
