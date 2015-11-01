package net.shadowfacts.yeoldetanks.compat.modules.computercraft;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import dan200.computercraft.api.ComputerCraftAPI;
import net.shadowfacts.yeoldetanks.compat.Compat;

/**
 * @author shadowfacts
 */
@Compat("ComputerCraft")
public class CompatComputerCraft {

	@Compat.Init
	public static void init(FMLInitializationEvent event) {
		ComputerCraftAPI.registerPeripheralProvider(new YOTPeripheralProvider());
	}

}
