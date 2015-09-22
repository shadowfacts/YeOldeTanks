package net.shadowfacts.yeoldetanks.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class YOTRecipes {

	public static void register() {
		GameRegistry.addShapelessRecipe(new ItemStack(YeOldeTanks.items.dippingStick), YeOldeTanks.blocks.barrel, Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(YeOldeTanks.blocks.barrel), "I I", "I I", "ICI", 'I', Items.iron_ingot, 'C', Items.cauldron);
	}

}
