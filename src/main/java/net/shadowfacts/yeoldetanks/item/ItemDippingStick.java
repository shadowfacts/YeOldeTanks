package net.shadowfacts.yeoldetanks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;

/**
 * @author shadowfacts
 */
public class ItemDippingStick extends Item implements AchievementProvider {

	public ItemDippingStick() {
		setUnlocalizedName("yot.dippingstick");
		setTextureName("yeoldetanks:dippingstick");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(x, y, z);

			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel) te;

				if (barrel.tank.getFluid() != null) {
					player.addChatComponentMessage(new ChatComponentText("Fluid: " + barrel.tank.getFluid().getLocalizedName()));
					player.addChatComponentMessage(new ChatComponentText(barrel.tank.getFluidAmount() + "mb / " + barrel.tank.getCapacity() + "mb"));
				} else {
					player.addChatComponentMessage(new ChatComponentText("Empty"));
				}


				return true;
			} else if (te instanceof TileEntityCreativeBarrel) {
				TileEntityCreativeBarrel barrel = (TileEntityCreativeBarrel) te;
				if (barrel.tank.getFluid() != null) {
					player.addChatComponentMessage(new ChatComponentText("Fluid: " + barrel.tank.getFluid().getLocalizedName()));
					player.addChatComponentMessage(new ChatComponentText(barrel.tank.getFluidAmount() + "mb / ∞ mb"));
				} else {
					player.addChatComponentMessage(new ChatComponentText("Empty"));
				}
			}
		}

		return false;
	}

	@Override
	public Achievement getAchievement() {
		return ModAchievements.craftDippingStick;
	}
}
