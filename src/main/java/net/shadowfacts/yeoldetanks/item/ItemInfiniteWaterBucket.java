package net.shadowfacts.yeoldetanks.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.item.ItemBase;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;

import javax.annotation.Nonnull;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;

/**
 * @author shadowfacts
 */
public class ItemInfiniteWaterBucket extends ItemBase implements AchievementProvider {

	public ItemInfiniteWaterBucket() {
		super("infinite_water_bucket");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote) {
			RayTraceResult rayTrace = this.rayTrace(world, player, false);
			if (rayTrace == null) return new ActionResult<>(EnumActionResult.FAIL, stack);

			int placeX = rayTrace.getBlockPos().getX() + rayTrace.sideHit.getFrontOffsetX();
			int placeY = rayTrace.getBlockPos().getY() + rayTrace.sideHit.getFrontOffsetY();
			int placeZ = rayTrace.getBlockPos().getZ() + rayTrace.sideHit.getFrontOffsetZ();
			BlockPos placePos = new BlockPos(placeX, placeY, placeZ);
			IBlockState placeState = world.getBlockState(placePos);
			if (placeState.getMaterial().isReplaceable()) {
				world.setBlockState(placePos, Blocks.FLOWING_WATER.getStateFromMeta(0), 2);
			}
		}

		player.swingArm(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		ItemStack stack = new ItemStack(this);
		((InfiniteFluidHandler)stack.getCapability(FLUID_HANDLER_ITEM_CAPABILITY, null)).setFluid(new FluidStack(FluidRegistry.WATER, 1000));
		items.add(stack);
	}

	@Nonnull
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return stack;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

//	@Override
//	public Achievement getAchievement(ItemStack stack) {
//		return ModAchievements.craftInfiniteWaterBucket;
//	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new InfiniteFluidHandler(stack);
	}

	public static class InfiniteFluidHandler extends FluidHandlerItemStack {

		private InfiniteFluidHandler(ItemStack container) {
			super(container, 1000);
		}

		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			if (container.getCount() != 1 || maxDrain <= 0) {
				return null;
			}

			FluidStack contained = getFluid();
			if (contained == null || contained.amount <= 0 || !canDrainFluidType(contained)) {
				return null;
			}

			final int drainAmount = Math.min(contained.amount, maxDrain);

			FluidStack drained = contained.copy();
			drained.amount = drainAmount;

			return drained;
		}

		@Override
		public void setFluid(FluidStack fluid) {
			super.setFluid(fluid);
		}

	}

}
