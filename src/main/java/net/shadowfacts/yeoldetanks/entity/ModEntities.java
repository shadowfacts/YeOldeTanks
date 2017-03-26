package net.shadowfacts.yeoldetanks.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ModEntities {

	public static EntityMinecart.Type TANK_CART_TYPE = EnumHelper.addEnum(EntityMinecart.Type.class, "YOTTANK", new Class<?>[]{int.class, String.class}, 7, "MinecartYOTTank");

	public static void preInit() {
		YeOldeTanks.log.info("Registering entities");

		EntityRegistry.registerModEntity(EntityBarrelMinecart.class, "yot_barrel_minecart", 1, YeOldeTanks.instance, 80, 3, true);
	}

}
