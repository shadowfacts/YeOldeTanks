package net.shadowfacts.yeoldetanks.recipe;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import static net.minecraft.init.Items.*;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.blocks;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.items;

/**
 * @author shadowfacts
 */
public class ModRecipes {

	public static void init() {
		OreDictionary.registerOre("bucketWater", WATER_BUCKET);
		OreDictionary.registerOre("bucketWater", items.infiniteWaterBucket);
		OreDictionary.registerOre("listAllwater", items.infiniteWaterBucket);

		GameRegistry.register(new RecipeBarrelMinecart());
		GameRegistry.register(new RecipeUncraftBarrelMinecart());
	}

}
