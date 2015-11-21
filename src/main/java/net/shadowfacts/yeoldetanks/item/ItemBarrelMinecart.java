package net.shadowfacts.yeoldetanks.item;


import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemBarrelMinecart extends Item implements AchievementProvider {

	public ItemBarrelMinecart() {
		setMaxStackSize(1);
		setUnlocalizedName("yot.barrelminecart");
		setTextureName("yeoldetanks:barrelMinecart");
		setCreativeTab(YeOldeTanks.tab);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y, z) instanceof BlockRailBase) {
			if (!world.isRemote) {
				EntityBarrelMinecart cart = new EntityBarrelMinecart(world, x + .5, y + .5, z + .5);

				if (stack.hasDisplayName()) cart.setMinecartName(stack.getDisplayName());

				world.spawnEntityInWorld(cart);

				cart.tank.readFromNBT(stack.stackTagCompound);
			}

			stack.stackSize--;
			return true;
		}

		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(this);
		stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setString("Empty", "");

		list.add(stack);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		if (stack.stackTagCompound == null) return;
		if (stack.stackTagCompound.hasKey("Empty")) return;

		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.stackTagCompound);
		tooltip.add(fluid.getLocalizedName());
		tooltip.add(fluid.amount + "mb / " + YOTConfig.barrelCapacity + "mb");
	}

	@Override
	public Achievement getAchievement() {
		return ModAchievements.craftBarrelCart;
	}
}
