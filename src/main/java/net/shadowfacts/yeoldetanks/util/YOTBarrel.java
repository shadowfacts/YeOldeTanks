package net.shadowfacts.yeoldetanks.util;

import net.minecraftforge.fluids.IFluidTank;

/**
 * @author shadowfacts
 */
public interface YOTBarrel {

	IFluidTank getTank();

	default boolean isCreative() {
		return false;
	}

}
