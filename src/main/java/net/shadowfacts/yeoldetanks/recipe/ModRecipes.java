package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import static net.minecraft.init.Items.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.blocks;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.items;

/**
 * @author shadowfacts
 */
public class ModRecipes {

	public static void init() {
		YeOldeTanks.log.info("Registering recipes");

		OreDictionary.registerOre("bucketWater", WATER_BUCKET);
		OreDictionary.registerOre("bucketWater", items.infiniteWaterBucket);
		OreDictionary.registerOre("listAllwater", items.infiniteWaterBucket);

		addRecipe(new ShapedOreRecipe(blocks.barrel, "I I", "I I", "ICI", 'I', "ingotIron", 'C', CAULDRON));
		addRecipe(new ShapedOreRecipe(items.infiniteWaterBucket, "I I", "BIB", 'I', "ingotIron", 'B', "bucketWater"));
		addShapelessRecipe(new ItemStack(blocks.cosmeticBarrel), blocks.barrel);
		addRecipe(new ShapedOreRecipe(blocks.obsidianBarrel, "O O", "O O", "OBO", 'O', "obsidian", 'B', blocks.barrel));
		addRecipe(new RecipeBarrelMinecart());
		addRecipe(new RecipeUncraftBarrelMinecart());
		addRecipe(new RecipeDippingStick());

		RecipeSorter.register("BarrelMinecart", RecipeBarrelMinecart.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register("UncraftBarrelMinecart", RecipeUncraftBarrelMinecart.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register("DippingStick", RecipeDippingStick.class, RecipeSorter.Category.SHAPELESS, "");

	}

}
