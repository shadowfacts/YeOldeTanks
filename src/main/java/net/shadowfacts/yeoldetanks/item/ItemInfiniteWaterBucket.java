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
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			RayTraceResult rayTrace = this.rayTrace(world, player, false);
			if (rayTrace == null) return new ActionResult<>(EnumActionResult.FAIL, stack);

			int placeX = rayTrace.getBlockPos().getX() + rayTrace.sideHit.getFrontOffsetX();
			int placeY = rayTrace.getBlockPos().getY() + rayTrace.sideHit.getFrontOffsetY();
			int placeZ = rayTrace.getBlockPos().getZ() + rayTrace.sideHit.getFrontOffsetZ();
			BlockPos placePos = new BlockPos(placeX, placeY, placeZ);
			IBlockState placeState = world.getBlockState(placePos);
			if (placeState.getBlock().getMaterial(placeState).isReplaceable()) {
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
