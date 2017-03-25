package net.shadowfacts.yeoldetanks.block.barrel;

import lombok.Getter;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

/**
 * @author shadowfacts
 */
public class TileEntityBarrel extends TileEntityBarrelBase {

	@Getter
	@AutoSerializeNBT
	@CapHolder(capabilities = IFluidHandler.class)
	public FluidTank tank = new FluidTank(YOTConfig.barrelCapacity);

	@Override
	public boolean isCreative() {
		return false;
	}

}
