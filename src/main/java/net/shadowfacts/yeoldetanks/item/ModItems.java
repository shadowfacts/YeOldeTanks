package net.shadowfacts.yeoldetanks.item;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author shadowfacts
 */
public class ModItems {

	public ItemDippingStick dippingStick;
	public ItemInfiniteWaterBucket infiniteWaterBucket;
	public ItemBarrelMinecart barrelMinecart;

	public void preInit(FMLPreInitializationEvent event) {
		createItems();
		registerItems();
	}

	private void createItems() {
		dippingStick = new ItemDippingStick();
		infiniteWaterBucket = new ItemInfiniteWaterBucket();
		barrelMinecart = new ItemBarrelMinecart();
	}

	private void registerItems() {
		GameRegistry.registerItem(dippingStick, "yot.dippingstick");
		GameRegistry.registerItem(infiniteWaterBucket, "yot.infiniteWaterBucket");
		GameRegistry.registerItem(barrelMinecart, "yot.barrelminecart");
	}

}
