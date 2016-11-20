package net.shadowfacts.yeoldetanks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.proxy.ClientProxy;

/**
 * @author shadowfacts
 */
public class ItemDippingStick extends Item implements ItemModelProvider, AchievementProvider {

	public ItemDippingStick() {
		setUnlocalizedName("dipping_stick");
		setRegistryName("dipping_stick");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);

			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel) te;

				if (barrel.tank.getFluid() != null) {
					player.sendMessage(new TextComponentString("Fluid: " + barrel.tank.getFluid().getLocalizedName()));
					player.sendMessage(new TextComponentString(barrel.tank.getFluidAmount() + "mb / " + barrel.tank.getCapacity() + "mb"));
				} else {
					player.sendMessage(new TextComponentString("Empty"));
				}


				return EnumActionResult.SUCCESS;
			} else if (te instanceof TileEntityCreativeBarrel) {
				TileEntityCreativeBarrel barrel = (TileEntityCreativeBarrel) te;
				if (barrel.tank.getFluid() != null) {
					player.sendMessage(new TextComponentString("Fluid: " + barrel.tank.getFluid().getLocalizedName()));
					player.sendMessage(new TextComponentString(barrel.tank.getFluidAmount() + "mb / ∞ mb"));
				} else {
					player.sendMessage(new TextComponentString("Empty"));
				}

				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void initItemModel() {
		YeOldeTanks.proxy.registerInvModel(this, 0, "dipping_stick");
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftDippingStick;
	}
}
