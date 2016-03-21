package net.shadowfacts.yeoldetanks.block.creativebarrel;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;
import net.shadowfacts.yeoldetanks.YOTConfig;

/**
 * @author shadowfacts
 */
public class TileEntityCreativeBarrel extends BaseTileEntity implements IFluidHandler, ITickable {

	@AutoSerializeNBT
	public CreativeFluidTank tank = new CreativeFluidTank(100000);

	@AutoSerializeNBT
	public boolean lid;

	private int prevAmount = tank.getFluidAmount();
	
	private void save() {
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
			if (te != null && te instanceof IFluidHandler) {
				IFluidHandler fluidHandler = (IFluidHandler)te;
				if (fluidHandler.canFill(EnumFacing.UP, tank.getFluid().getFluid())) {
					drain(EnumFacing.DOWN, fluidHandler.fill(EnumFacing.UP, drain(EnumFacing.DOWN, tank.getCapacity(), false), true), true);
				}
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
	public void load(NBTTagCompound tag, boolean loadInventory) {

	}

	@Override
	public NBTTagCompound save(NBTTagCompound tag, boolean saveInventory) {
		return tag;
	}

//	IFluidHandler
	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		if (canFill(from, null)) {
			int filled = tank.fill(resource, doFill);
			if (doFill) save();
			return filled;
		}
		return 0;
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (canDrain(from, null)) {
			if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
				return null;
			}
			FluidStack stack = tank.drain(resource.amount, doDrain);
			if (doDrain) save();
			return stack;
		}
		return null;
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		if (canDrain(from, null)) {
			FluidStack stack = tank.drain(maxDrain, doDrain);
			if (doDrain) save();
			return stack;
		}
		return null;
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return YOTConfig.fillFromAnySide || from == EnumFacing.UP || from == null;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return YOTConfig.drainFromAnySide|| from == EnumFacing.DOWN || from == null;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[]{ tank.getInfo() };
	}

}
