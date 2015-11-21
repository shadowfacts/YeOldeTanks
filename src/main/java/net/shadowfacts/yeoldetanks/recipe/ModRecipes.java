package net.shadowfacts.yeoldetanks.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import java.util.ArrayList;
import java.util.List;

import static cpw.mods.fml.common.registry.GameRegistry.*;
import static net.minecraft.init.Items.*;

import static net.shadowfacts.yeoldetanks.YeOldeTanks.blocks;
import static net.shadowfacts.yeoldetanks.YeOldeTanks.items;

/**
 * @author shadowfacts
 */
public class ModRecipes {

	public static void preInit() {
		YeOldeTanks.log.info("Registering recipes");

		addShapedRecipe(new ItemStack(blocks.barrel), "I I", "I I", "ICI", 'I', iron_ingot, 'C', cauldron);
		addShapedRecipe(new ItemStack(items.infiniteWaterBucket), "I I", "BIB", 'I', iron_ingot, 'B', water_bucket);
		addRecipe(new BarrelMinecartRecipe());
		addRecipe(new BarrelMinecartUncraftRecipe());
		addShapelessRecipe(new ItemStack(items.dippingStick), blocks.barrel, stick);
	}

	@SuppressWarnings("unchecked")
	public static void postInit() {
		YeOldeTanks.log.info("Adding Infinite Water Bucket recipes");

		List<IRecipe> toAdd = new ArrayList<>();

		for (IRecipe recipe : (List<IRecipe>)CraftingManager.getInstance().getRecipeList()) {
			if (recipe instanceof ShapedRecipes) {
				ShapedRecipes shaped = (ShapedRecipes)recipe;

				ItemStack[] newStacks = shaped.recipeItems.clone();
				boolean addNew = false;
				for (int i = 0; i < newStacks.length; i++) {
					ItemStack stack = newStacks[i];
					if (stack != null && stack.getItem() == water_bucket) {
						newStacks[i] = new ItemStack(items.infiniteWaterBucket);
						addNew = true;
					}
				}

				if (addNew) {
					toAdd.add(new ShapedRecipes(shaped.recipeWidth, shaped.recipeHeight, newStacks, shaped.getRecipeOutput().copy()));
				}
			} else if (recipe instanceof ShapelessRecipes) {
				ShapelessRecipes shapeless = (ShapelessRecipes)recipe;

				List<ItemStack> newStacks = new ArrayList(shapeless.recipeItems);
				boolean addNew = false;
				for (int i = 0; i < newStacks.size(); i++) {
					ItemStack stack = newStacks.get(i);
					if (stack != null && stack.getItem() == water_bucket) {
						newStacks.set(i, new ItemStack(items.infiniteWaterBucket));
						addNew = true;
					}
				}

				if (addNew) {
					toAdd.add(new ShapelessRecipes(shapeless.getRecipeOutput(), newStacks));
				}
			}
		}

		toAdd.stream().forEach(GameRegistry::addRecipe);
	}
}
