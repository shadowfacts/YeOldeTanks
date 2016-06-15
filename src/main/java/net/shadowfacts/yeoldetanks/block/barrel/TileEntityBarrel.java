package net.shadowfacts.yeoldetanks.block.barrel;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Optional;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.shadowmc.network.PacketRequestTEUpdate;
import net.shadowfacts.shadowmc.tileentity.BaseTileEntity;
import net.shadowfacts.yeoldetanks.YOTConfig;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
@Optional.InterfaceList({
//		@Optional.Interface(modid = "ComputerCraft", iface = "dan200.computercraft.api.peripheral.IPeripheral"),
		@Optional.Interface(modid = "OpenComputers", iface = "li.cil.oc.api.network.SimpleComponent"),
		@Optional.Interface(modid = "OpenComputers", iface = "li.cil.oc.api.network.ManagedPeripheral")
})
public class TileEntityBarrel extends BaseTileEntity implements ITickable/*, IPeripheral*/, SimpleComponent, ManagedPeripheral {

	@AutoSerializeNBT
	public FluidTank tank = new FluidTank(YOTConfig.barrelCapacity);

	private int prevAmount = tank.getFluidAmount();

	void save() {
		markDirty();
		if (Math.abs(prevAmount - tank.getFluidAmount()) >= 1000) {
			prevAmount = tank.getFluidAmount();
			if (!worldObj.isRemote) {
				sync();
			}
		}
	}

	@Override
	public void update() {
		if (YOTConfig.autoOutputBottom &&
				tank.getFluid() != null && tank.getFluidAmount() > 0) {
			TileEntity te = worldObj.getTileEntity(pos.down());
			if (te != null && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
				IFluidHandler fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
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

//	Computers
	private static String[] methodNames = new String[] {
			"getFluid",
			"getFluidAmount"
	};
	private static List<String> methodList = Arrays.asList(methodNames);
	private static String peripheralName = "yot_barrel";

//	ComputerCraft
//	@Override
//	public String getType() {
//		return peripheralName;
//	}
//
//	@Override
//	public String[] getMethodNames() {
//		return methodNames;
//	}
//
//	@Override
//	@Optional.Method(modid = "ComputerCraft")
//	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
//		switch (method) {
//			case 0:
//				return new Object[]{ tank.getFluid().getLocalizedName() };
//			case 1:
//				return new Object[]{ tank.getFluidAmount() };
//			default:
//				throw new LuaException("No such method");
//		}
//	}
//
//	@Override
//	@Optional.Method(modid = "ComputerCraft")
//	public void attach(IComputerAccess computer) {
//
//	}
//
//	@Override
//	@Optional.Method(modid = "ComputerCraft")
//	public void detach(IComputerAccess computer) {
//
//	}
//
//	@Override
//	@Optional.Method(modid = "ComputerCraft")
//	public boolean equals(IPeripheral other) {
//		if (other instanceof TileEntityBarrel) {
//			TileEntityBarrel te = (TileEntityBarrel)other;
//			return te.tank.getFluid().equals(this.tank.getFluid());
//		}
//		return false;
//	}
//
//
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
