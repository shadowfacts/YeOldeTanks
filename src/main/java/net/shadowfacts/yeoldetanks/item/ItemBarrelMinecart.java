package net.shadowfacts.yeoldetanks.item;


import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;
import net.shadowfacts.yeoldetanks.proxy.ClientProxy;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemBarrelMinecart extends Item implements ItemModelProvider, AchievementProvider {

	public ItemBarrelMinecart() {
		setMaxStackSize(1);
		setUnlocalizedName("barrelMinecart");
		setRegistryName("barrelMinecart");
		setCreativeTab(YeOldeTanks.tab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.getBlockState(pos).getBlock() instanceof BlockRailBase) {
			if (!world.isRemote) {
				EntityBarrelMinecart cart = new EntityBarrelMinecart(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);

				if (stack.hasDisplayName()) {
					cart.setCustomNameTag(stack.getDisplayName());
				}

				world.spawnEntityInWorld(cart);

				cart.tank.readFromNBT(stack.getTagCompound());
			}

			stack.stackSize--;
			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
		ItemStack stack = new ItemStack(this);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("Empty", "");

		list.add(stack);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack.getTagCompound() == null) return;
		if (stack.getTagCompound().hasKey("Empty")) return;

		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTagCompound());
		tooltip.add(fluid.getLocalizedName());
		tooltip.add(fluid.amount + "mb / " + YOTConfig.barrelCapacity + "mb");
	}

	@Override
	public void initItemModel() {
		YeOldeTanks.proxy.registerInvModel(this, 0, "barrelMinecart");
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftBarrelCart;
	}
}
