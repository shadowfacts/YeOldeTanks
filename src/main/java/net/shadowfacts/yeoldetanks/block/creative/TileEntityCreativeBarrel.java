package net.shadowfacts.yeoldetanks.block.creative;

import lombok.Getter;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.shadowfacts.shadowmc.capability.CapHolder;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

/**
 * @author shadowfacts
 */
public class TileEntityCreativeBarrel extends TileEntityBarrelBase {

	@Getter
	@AutoSerializeNBT
	@CapHolder(capabilities = IFluidHandler.class)
	public CreativeFluidTank tank = new CreativeFluidTank(100000);

	@Override
	public boolean isCreative() {
		return true;
	}

}
