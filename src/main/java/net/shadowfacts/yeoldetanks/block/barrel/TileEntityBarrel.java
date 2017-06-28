package net.shadowfacts.yeoldetanks.block.barrel;

import lombok.Getter;
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
	public FluidTank tank = new FluidTank(YOTConfig.barrelCapacity) {
		@Override
		protected void onContentsChanged() {
			save();
		}
	};

	@Override
	public boolean isCreative() {
		return false;
	}

}
