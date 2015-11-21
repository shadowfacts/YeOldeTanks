package net.shadowfacts.yeoldetanks.entity;

import net.minecraft.entity.DataWatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.*;

/**
 * @author shadowfacts
 */
public class EntityFluidTank implements IFluidTank {

	public static final int AMOUNT = 16;
	public static final int TYPE = 15;
	public static final int CAPACITY = 14;

	private DataWatcher dataWatcher;

	public EntityFluidTank(DataWatcher dataWatcher) {
		this.dataWatcher = dataWatcher;
	}

	public void writeToNBT(NBTTagCompound tag) {
		FluidStack fluid = getFluid();
		if (fluid != null) {
			fluid.writeToNBT(tag);
		} else {
			tag.setString("Empty", "");
		}
	}

	public void readFromNBT(NBTTagCompound tag) {
		if (!tag.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag);
			setFluid(fluid);
		} else {
			setFluid(null);
		}
	}

	public void init(int capacity) {
		dataWatcher.addObject(CAPACITY, capacity);
		dataWatcher.addObject(AMOUNT, 0);
		dataWatcher.addObject(TYPE, -1);
	}

	private int getFluidID() {
		return dataWatcher.getWatchableObjectInt(TYPE);
	}

	private Fluid getFluidFromDataWatcher() {
		int id = getFluidID();
		if (id == -1) return null;
		else return FluidRegistry.getFluid(id);
	}

	private void setFluidID(int id) {
		dataWatcher.updateObject(TYPE, id);
	}

	private void setFluidAmount(int amount) {
		dataWatcher.updateObject(AMOUNT, amount);
	}

	private void setCapacity(int capacity) {
		dataWatcher.updateObject(CAPACITY, capacity);
	}

	private void setFluid(FluidStack fluid) {
		if (fluid != null) {
			setFluidAmount(fluid.amount);
			setFluidID(fluid.getFluidID());
		} else {
			setFluidAmount(0);
			setFluidID(-1);
		}
	}

	@Override
	public FluidStack getFluid() {
		return new FluidStack(getFluidFromDataWatcher(), getFluidAmount());
	}

	@Override
	public int getFluidAmount() {
		return dataWatcher.getWatchableObjectInt(AMOUNT);
	}

	@Override
	public int getCapacity() {
		return dataWatcher.getWatchableObjectInt(CAPACITY);
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) return 0;

		int capacity = getCapacity();
		FluidStack fluid = getFluid();

		if (!doFill) {
			if (fluid == null) {
				return Math.min(getCapacity(), resource.amount);
			}
			if (!getFluid().isFluidEqual(resource)) {
				return 0;
			}
			return Math.min(capacity - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			FluidStack newFluid = new FluidStack(resource, Math.min(capacity, resource.amount));
			setFluid(newFluid);
			return newFluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}

		int filled = capacity - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = capacity;
		}

		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		int capacity = getCapacity();
		FluidStack fluid = getFluid();

		if (fluid == null) return null;

		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}

		FluidStack stack = new FluidStack(fluid, drained);

		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				setFluid(null);
			}
		}

		return stack;
	}
}
