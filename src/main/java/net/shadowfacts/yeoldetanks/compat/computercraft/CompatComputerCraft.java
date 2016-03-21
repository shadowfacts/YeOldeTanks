package net.shadowfacts.yeoldetanks.compat.computercraft;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.shadowfacts.shadowmc.compat.Compat;

/**
 * @author shadowfacts
 */
@Compat("ComputerCraft")
public class CompatComputerCraft {

	@Compat.Init
	public static void init(FMLInitializationEvent event) {
//		ComputerCraftAPI.registerPeripheralProvider(new YOTPeripheralProvider());
	}

}
