package net.shadowfacts.yeoldetanks.item;

import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class ModItems extends net.shadowfacts.shadowmc.item.ModItems {

	public ItemDippingStick dippingStick;
	public ItemInfiniteWaterBucket infiniteWaterBucket;
	public ItemBarrelMinecart barrelMinecart;

	@Override
	public void init() {
		YeOldeTanks.log.info("Initializing items");

		dippingStick = register(new ItemDippingStick());
		infiniteWaterBucket = register(new ItemInfiniteWaterBucket());
		barrelMinecart = register(new ItemBarrelMinecart());
	}

}
