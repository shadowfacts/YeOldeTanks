package net.shadowfacts.yeoldetanks.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ModEntities {

	public static void preInit() {
		YeOldeTanks.log.info("Registering entities");

		int id = 0;

		EntityRegistry.registerModEntity(EntityBarrelMinecart.class, "yot_barrel_minecart", id++, YeOldeTanks.instance, 80, 3, true);
	}

}
