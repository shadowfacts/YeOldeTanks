package net.shadowfacts.yeoldetanks.block.creative;

import lombok.Getter;
import net.shadowfacts.shadowmc.fluid.CreativeFluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

/**
 * @author shadowfacts
 */
public class TileEntityCreativeBarrel extends TileEntityBarrelBase {

	@Getter
	@AutoSerializeNBT
	public CreativeFluidTank tank = new CreativeFluidTank(100000) {
		@Override
		protected void onContentsChanged() {
			save();
		}
	};

	@Override
	public boolean isCreative() {
		return true;
	}

}
