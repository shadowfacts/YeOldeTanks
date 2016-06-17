package net.shadowfacts.yeoldetanks.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.ItemFluidContainer;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.item.ItemModelProvider;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemInfiniteWaterBucket extends ItemFluidContainer implements ItemModelProvider, AchievementProvider {

	public ItemInfiniteWaterBucket() {
		super(1000);
		setUnlocalizedName("infiniteWaterBucket");
		setRegistryName("infiniteWaterBucket");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(this), new ItemStack(this));
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
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
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(this);
		stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound fluid = new NBTTagCompound();
		new FluidStack(FluidRegistry.WATER, 1000).writeToNBT(fluid);
		stack.getTagCompound().setTag("Fluid", fluid);
		list.add(stack);
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

	@Override
	public void initItemModel() {
		YeOldeTanks.proxy.registerInvModel(this, 0, "infiniteWaterBucket");
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftInfiniteWaterBucket;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new InfiniteFluidHandler(stack, capacity);
	}

	public static class InfiniteFluidHandler extends FluidHandlerItemStack {

		public InfiniteFluidHandler(ItemStack container, int capacity) {
			super(container, capacity);
		}

		@Override
		public FluidStack drain(int maxDrain, boolean doDrain) {
			if (container.stackSize != 1 || maxDrain <= 0) {
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

	}

}
