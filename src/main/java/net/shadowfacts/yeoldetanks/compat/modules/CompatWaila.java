package net.shadowfacts.yeoldetanks.compat.modules;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.shadowfacts.yeoldetanks.block.barrel.BarrelDataProvider;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.compat.Compat;

/**
 * @author shadowfacts
 */
@Compat("Waila")
public class CompatWaila {

	@Compat.Init
	public static void init(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", "net.shadowfacts.yeoldetanks.compat.modules.CompatWaila.callback");
	}

	public static void callback(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new BarrelDataProvider(), BlockBarrel.class);
	}

}
