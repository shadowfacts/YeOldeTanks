package net.shadowfacts.yeoldetanks.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ModEntities {

	public static EntityMinecart.EnumMinecartType TANK_CART_TYPE = EnumHelper.addEnum(EntityMinecart.EnumMinecartType.class, "YOTTANK", 7, "MinecartYOTTank");

	public static void preInit() {
		YeOldeTanks.log.info("Registering entities");

		int id = 0;

		EntityRegistry.registerModEntity(EntityBarrelMinecart.class, "yot_barrel_minecart", id++, YeOldeTanks.instance, 80, 3, true);
	}

}
