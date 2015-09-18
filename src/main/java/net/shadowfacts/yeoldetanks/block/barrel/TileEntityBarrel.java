package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import net.shadowfacts.yeoldetanks.YOTConfig;

/**
 * @author shadowfacts
 */
public class TileEntityBarrel extends TileEntity implements IFluidHandler {

	public FluidTank tank = new FluidTank(YOTConfig.barrelCapacity);

	public boolean lid;

	@Override
	public void updateEntity() {
		if (YOTConfig.autoOutputBottom && tank.getFluid() != null && tank.getFluidAmount() > 0) {
			TileEntity te = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
			if (te != null && te instanceof IFluidHandler) {
				IFluidHandler fluidHandler = (IFluidHandler) te;
				if (fluidHandler.canFill(ForgeDirection.UP, tank.getFluid().getFluid())) {
					drain(ForgeDirection.DOWN, fluidHandler.fill(ForgeDirection.UP, drain(ForgeDirection.DOWN, tank.getCapacity(), false), true), true);
					update();
				}
			}
		}
	}

	private void update() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tank.writeToNBT(tag);
		tag.setBoolean("Lid", lid);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		readFromNBT(tag, true);
	}

	public void readFromNBT(NBTTagCompound tag, boolean loadCoords) {
		if (loadCoords) super.readFromNBT(tag);
		tank.readFromNBT(tag);
		lid = tag.getBoolean("Lid");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}

	//	IFluidHandler
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (canFill(from, null)) {
			update();
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (canDrain(from, null)) {
			update();
			if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
				return null;
			}
			return tank.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (canDrain(from, null)) {
			update();
			return tank.drain(maxDrain, doDrain);
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return YOTConfig.fillFromAnySide || from == ForgeDirection.UP;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return YOTConfig.drainFromAnySide || from == ForgeDirection.DOWN || from == ForgeDirection.UP;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{tank.getInfo()};
	}

}
