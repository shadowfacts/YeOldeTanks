package net.shadowfacts.yeoldetanks.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class ModItems {

	private List<ItemModelProvider> modelProviders = new ArrayList<>();

	public ItemDippingStick dippingStick;
	public ItemInfiniteWaterBucket infiniteWaterBucket;
	public ItemBarrelMinecart barrelMinecart;

	public void initItems() {
		YeOldeTanks.log.info("Initializing items");

		dippingStick = register(new ItemDippingStick());
		infiniteWaterBucket = register(new ItemInfiniteWaterBucket());
		barrelMinecart = register(new ItemBarrelMinecart());
	}

	public void initModels() {
		modelProviders.forEach(ItemModelProvider::initModel);
	}

	private <T extends Item> T register(T item) {
		GameRegistry.register(item);
		if (item instanceof ItemModelProvider) modelProviders.add((ItemModelProvider)item);
		return item;
	}

}
