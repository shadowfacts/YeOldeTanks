package net.shadowfacts.yeoldetanks.compat;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.shadowfacts.shadowmc.compat.Compat;
import net.shadowfacts.yeoldetanks.block.barrel.BarrelDataProvider;
import net.shadowfacts.yeoldetanks.block.barrel.BlockBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.BlockCreativeBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.CreativeBarrelDataProvider;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.BarrelMinecartEntityProvider;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

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
