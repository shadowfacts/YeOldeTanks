package net.shadowfacts.yeoldetanks.block.barrel;

import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import net.shadowfacts.yeoldetanks.YOTConfig;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
@Optional.InterfaceList({
		@Optional.Interface(modid = "ComputerCraft", iface = "dan200.computercraft.api.peripheral.IPeripheral"),
		@Optional.Interface(modid = "OpenComputers", iface = "li.cil.oc.api.network.SimpleComponent"),
		@Optional.Interface(modid = "OpenComputers", iface = "li.cil.oc.api.network.ManagedPeripheral")
})
public class TileEntityBarrel extends TileEntity implements IFluidHandler, IPeripheral, SimpleComponent, ManagedPeripheral {

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
		return YOTConfig.fillFromAnySide || from == ForgeDirection.UP || from == ForgeDirection.UNKNOWN;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return YOTConfig.drainFromAnySide || from == ForgeDirection.DOWN || from == ForgeDirection.UP || from == ForgeDirection.UNKNOWN;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{tank.getInfo()};
	}

	// Computers
	private static String[] methodNames = new String[] {
			"getFluid",
			"getFluidAmount"
	};
	private static List<String> methodList = Arrays.asList(methodNames);
	private static String peripheralName = "yot_barrel";

	// ComputerCraft
	@Override
	public String getType() {
		return peripheralName;
	}

	@Override
	public String[] getMethodNames() {
		return methodNames;
	}

	@Override
	@Optional.Method(modid = "ComputerCraft")
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		switch (method) {
			case 0:
				return new Object[]{ tank.getFluid().getLocalizedName() };
			case 1:
				return new Object[]{ tank.getFluidAmount() };
			default:
				throw new LuaException("No such method");
		}
	}

	@Override
	@Optional.Method(modid = "ComputerCraft")
	public void attach(IComputerAccess computer) {

	}

	@Override
	@Optional.Method(modid = "ComputerCraft")
	public void detach(IComputerAccess computer) {

	}

	@Override
	@Optional.Method(modid = "ComputerCraft")
	public boolean equals(IPeripheral other) {
		if (other instanceof TileEntityBarrel) {
			TileEntityBarrel te = (TileEntityBarrel)other;
			return te.tank.getFluid().equals(this.tank.getFluid());
		}
		return false;
	}


//	OpenComputers
	@Override
	public String[] methods() {
		return methodNames;
	}

	@Override
	public Object[] invoke(String method, Context context, Arguments args) throws Exception {
		int methodId = methodList.indexOf(method);

		switch (methodId) {
			case 0:
				return new Object[]{ tank.getFluid().getLocalizedName() };
			case 1:
				return new Object[]{ tank.getFluidAmount() };
		}

		throw new RuntimeException("No such method");
	}

	@Override
	public String getComponentName() {
		return peripheralName;
	}
}
