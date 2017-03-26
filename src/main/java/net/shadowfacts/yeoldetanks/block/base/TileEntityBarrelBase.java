package net.shadowfacts.yeoldetanks.block.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.util.YOTBarrel;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

/**
 * @author shadowfacts
 */
public abstract class TileEntityBarrelBase extends BaseTileEntity implements ITickable, YOTBarrel {

	private int prevAmount;

	public void save() {
		markDirty();
		if (Math.abs(prevAmount - getTank().getFluidAmount()) >= 1000) {
			prevAmount = getTank().getFluidAmount();
			if (!world.isRemote) {
				sync();
			}
		}
	}

	@Override
	public void update() {
		if (YOTConfig.autoOutputBottom &&
				getTank().getFluid() != null && getTank().getFluidAmount() > 0) {

			TileEntity te = world.getTileEntity(pos.down());
			if (te != null && te.hasCapability(FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
				IFluidHandler handler = te.getCapability(FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
				getTank().drain(handler.fill(getTank().drain(getTank().getCapacity(), false), true), true);
			}
		}
	}

	@Override
	public void onLoad() {
		if (world.isRemote) {
			ShadowMC.network.sendToServer(new PacketRequestTEUpdate(this));
		}
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == FLUID_HANDLER_CAPABILITY) {
			return (T)getTank();
		}
		return super.getCapability(capability, facing);
	}

}
