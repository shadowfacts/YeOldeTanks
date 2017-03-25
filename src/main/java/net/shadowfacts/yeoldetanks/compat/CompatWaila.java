package net.shadowfacts.yeoldetanks.compat;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.shadowfacts.shadowmc.compat.Compat;

/**
 * @author shadowfacts
 */
@Compat("Waila")
public class CompatWaila {

	@Compat.Init
	public static void init(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", "net.shadowfacts.yeoldetanks.compat.CompatWaila.callback");
	}

//	public static void callback(IWailaRegistrar registrar) {
//		registrar.registerBodyProvider(new BarrelDataProvider(), BlockBarrel.class);
//		registrar.registerBodyProvider(new CreativeBarrelDataProvider(), BlockCreativeBarrel.class);
//		registrar.registerBodyProvider(new BarrelMinecartEntityProvider(), EntityBarrelMinecart.class);
//	}

}
