package net.shadowfacts.yeoldetanks.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.proxy.ClientProxy;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemInfiniteWaterBucket extends ItemFluidContainer implements ItemModelProvider, AchievementProvider {

	public ItemInfiniteWaterBucket() {
		super(-1, 1000);
		setUnlocalizedName("infiniteWaterBucket");
		setRegistryName("infiniteWaterBucket");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(this), new ItemStack(this));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);
			if (mop == null) return stack;

			int placeX = mop.getBlockPos().getX() + mop.sideHit.getFrontOffsetX();
			int placeY = mop.getBlockPos().getY() + mop.sideHit.getFrontOffsetY();
			int placeZ = mop.getBlockPos().getZ() + mop.sideHit.getFrontOffsetZ();
			BlockPos placePos = new BlockPos(placeX, placeY, placeZ);
			if (world.getBlockState(placePos).getBlock().getMaterial().isReplaceable()) {
				world.setBlockState(placePos, Blocks.flowing_water.getStateFromMeta(0), 2);
			}
		}

		player.swingItem();
		return stack;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(this);
		stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound fluid = new NBTTagCompound();
		new FluidStack(FluidRegistry.WATER, 1000).writeToNBT(fluid);
		stack.getTagCompound().setTag("Fluid", fluid);
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
	public void initModel() {
		ClientProxy.registerInvModel(this, 0, "infiniteWaterBucket");
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftInfiniteWaterBucket;
	}
}
