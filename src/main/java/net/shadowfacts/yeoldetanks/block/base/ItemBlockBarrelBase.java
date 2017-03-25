package net.shadowfacts.yeoldetanks.block.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.yeoldetanks.YOTConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

/**
 * @author shadowfacts
 */
public class ItemBlockBarrelBase extends ItemBlock {

	private BlockBarrelBase<?> block;

	public ItemBlockBarrelBase(BlockBarrelBase<?> block) {
		super(block);
		this.block = block;
		setRegistryName(block.getRegistryName());
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		if (super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState)) {

			TileEntityBarrelBase te = block.getTileEntity(world, pos);

			FluidTank tank = (FluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
			if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
				te.getTank().setFluid(tank.getFluid().copy());
			}

			return true;
		}

		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		IFluidTank tank = (IFluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
		if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
			String capacity = block.isCreative() ? "∞" : Integer.toString(tank.getCapacity());

			tooltip.add(tank.getFluid().getLocalizedName());
			tooltip.add(tank.getFluidAmount() + " mb / " + capacity + " mb");
		}
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new BarrelCapProvider(block.isCreative());
	}

	public static class BarrelCapProvider implements ICapabilitySerializable<NBTTagCompound> {

		private FluidTank tank;

		public BarrelCapProvider(boolean isCreative) {
			tank = isCreative ? new CreativeFluidTank(100000) : new FluidTank(YOTConfig.barrelCapacity);
		}

		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
			return capability == FLUID_HANDLER_CAPABILITY;
		}

		@Nullable
		@Override
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
			return capability == FLUID_HANDLER_CAPABILITY ? (T)tank : null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return tank.writeToNBT(new NBTTagCompound());
		}

		@Override
		public void deserializeNBT(NBTTagCompound tag) {
			tank.readFromNBT(tag);
		}

	}

}
