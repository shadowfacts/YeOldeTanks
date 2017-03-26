package net.shadowfacts.yeoldetanks.block.obsidian;

import lombok.Getter;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

/**
 * @author shadowfacts
 */
public class TileEntityObsidianBarrel extends TileEntityBarrelBase {

	@Getter
	@AutoSerializeNBT
	public FluidTank tank = new FluidTank(YOTConfig.obsidianBarrelCapacity);

	@Override
	public boolean isCreative() {
		return false;
	}

}
