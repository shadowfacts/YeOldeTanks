package net.shadowfacts.yeoldetanks.block.creativebarrel;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;
import net.shadowfacts.yeoldetanks.YOTConfig;

/**
 * @author shadowfacts
 */
public class TileEntityCreativeBarrel extends BaseTileEntity implements ITickable {

	@AutoSerializeNBT
	public CreativeFluidTank tank = new CreativeFluidTank(100000);

	private int prevAmount = tank.getFluidAmount();
	
	void save() {
		markDirty();
		if (Math.abs(prevAmount - tank.getFluidAmount()) > 1000) {
			prevAmount = tank.getFluidAmount();
			sync();
		}
	}

	@Override
	public void update() {
		if (YOTConfig.autoOutputBottom &&
				tank.getFluid() != null) {
			TileEntity te = worldObj.getTileEntity(pos.down());
			if (te != null && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
				IFluidHandler fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
				tank.drain(fluidHandler.fill(tank.drain(tank.getCapacity(), false), true), true);
			}
		}
	}

	@Override
	public void onLoad() {
		if (worldObj.isRemote) {
			ShadowMC.network.sendToServer(new PacketRequestTEUpdate(this));
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T)tank;
		} else {
			return super.getCapability(capability, facing);
		}
	}

}
