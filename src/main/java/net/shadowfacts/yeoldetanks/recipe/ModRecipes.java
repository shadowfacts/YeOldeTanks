package net.shadowfacts.yeoldetanks.recipe;


import net.minecraft.item.ItemStack;

import static net.minecraft.init.Items.*;

import static net.shadowfacts.yeoldetanks.YeOldeTanks.blocks;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.items;

import static cpw.mods.fml.common.registry.GameRegistry.addShapedRecipe;
import static cpw.mods.fml.common.registry.GameRegistry.addShapelessRecipe;

/**
 * @author shadowfacts
 */
public class ModRecipes {

	public static void register() {
		addShapelessRecipe(new ItemStack(items.dippingStick), blocks.barrel, stick);
		addShapedRecipe(new ItemStack(blocks.barrel), "I I", "I I", "ICI", 'I', iron_ingot, 'C', cauldron);
		addShapedRecipe(new ItemStack(items.infiniteWaterBucket), "I I", "BIB", 'I', iron_ingot, 'B', water_bucket);
		addShapelessRecipe(new ItemStack(items.barrelMinecart), minecart, blocks.barrel);
	}

}