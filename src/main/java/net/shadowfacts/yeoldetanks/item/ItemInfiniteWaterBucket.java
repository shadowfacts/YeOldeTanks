package net.shadowfacts.yeoldetanks.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemInfiniteWaterBucket extends ItemFluidContainer implements AchievementProvider {

	public ItemInfiniteWaterBucket() {
		super(-1, 1000);
		setUnlocalizedName("yot.infiniteWaterBucket");
		setTextureName("yeoldetanks:infinitewater");
		setMaxStackSize(1);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(this), new ItemStack(this));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);
			if (mop == null) return stack;

			ForgeDirection dir = ForgeDirection.getOrientation(mop.sideHit);
			int placeX = mop.blockX + dir.offsetX;
			int placeY = mop.blockY + dir.offsetY;
			int placeZ = mop.blockZ + dir.offsetZ;
			if (world.getBlock(placeX, placeY, placeZ).getMaterial().isReplaceable()) {
				world.setBlock(placeX, placeY, placeZ, Blocks.flowing_water, 0, 2);
			}
		}

		player.swingItem();
		return stack;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(this);
		stack.stackTagCompound = new NBTTagCompound();
		NBTTagCompound fluid = new NBTTagCompound();
		new FluidStack(FluidRegistry.WATER, 1000).writeToNBT(fluid);
		stack.stackTagCompound.setTag("Fluid", fluid);
		list.add(stack);
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return stack;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if (resource.isFluidEqual(getFluid(container))) {
			return resource.amount;
		}
		return 0;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		return new FluidStack(FluidRegistry.WATER, 1000);
	}

	@Override
	public Achievement getAchievement() {
		return ModAchievements.craftInfiniteWaterBucket;
	}
}
