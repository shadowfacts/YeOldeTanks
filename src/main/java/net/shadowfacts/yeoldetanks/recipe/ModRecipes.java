package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.init.Items.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
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

		addRecipe(new ShapedOreRecipe(blocks.barrel, "I I", "I I", "ICI", 'I', "ingotIron", 'C', CAULDRON));
		addRecipe(new ShapedOreRecipe(items.infiniteWaterBucket, "I I", "BIB", 'I', "ingotIron", 'B', "bucketWater"));
		addRecipe(new RecipeBarrelMinecart());
		addRecipe(new RecipeUncraftBarrelMinecart());
		addRecipe(new RecipeDippingStick());

		RecipeSorter.register("BarrelMinecart", RecipeBarrelMinecart.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register("UncraftBarrelMinecart", RecipeUncraftBarrelMinecart.class, RecipeSorter.Category.SHAPED, "");
		RecipeSorter.register("DippingStick", RecipeDippingStick.class, RecipeSorter.Category.SHAPELESS, "");

	}

	@SuppressWarnings("unchecked")
	public static void postInit() {
		YeOldeTanks.log.info("Adding Infinite Water Bucket recipes");

		List<IRecipe> toAdd = new ArrayList<>();

		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {

			if (recipe instanceof ShapedRecipes) {
				ShapedRecipes shaped = (ShapedRecipes)recipe;

				ItemStack[] newStacks = shaped.recipeItems.clone();
				boolean addNew = false;
				for (int i = 0; i < newStacks.length; i++) {
					ItemStack stack = newStacks[i];
					if (stack != null && stack.getItem() == WATER_BUCKET) {
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
					if (stack != null && stack.getItem() == WATER_BUCKET) {
						newStacks.set(i, new ItemStack(items.infiniteWaterBucket));
						addNew = true;
					}
				}

				if (addNew) {
					toAdd.add(new ShapelessRecipes(shapeless.getRecipeOutput(), newStacks));
				}
			} else if (recipe instanceof ShapedOreRecipe) {
				ShapedOreRecipe shaped = (ShapedOreRecipe)recipe;

				try {
					Field inputField = ShapedOreRecipe.class.getDeclaredField("input");
					inputField.setAccessible(true);
					Object[] newInputs = ((Object[])inputField.get(shaped)).clone();


					boolean addNew = false;
					for (int i = 0; i < newInputs.length; i++) {
						if (newInputs[i] != null && newInputs[i] instanceof ItemStack) {
							if (((ItemStack)newInputs[i]).getItem() == WATER_BUCKET) {
								newInputs[i] = new ItemStack(items.infiniteWaterBucket);
								addNew = true;
							}
						}
					}

					if (addNew) {
						ShapedOreRecipe newRecipe = new ShapedOreRecipe(recipe.getRecipeOutput(), "FFF", "FFF", "FFF", 'F', Blocks.FIRE);

						inputField.set(newRecipe, newInputs);

						toAdd.add(newRecipe);
					}

				} catch (ReflectiveOperationException e) {
					YeOldeTanks.log.error("Couldn't create an Infinite Water Bucket recipe from a ShapedOreRecipe");
					e.printStackTrace();
				}
			} else if (recipe instanceof ShapelessOreRecipe) {
				ShapelessOreRecipe shapeless = (ShapelessOreRecipe)recipe;

				try {

					Field inputsField = ShapelessOreRecipe.class.getDeclaredField("input");
					inputsField.setAccessible(true);
					List<Object> newInputs = new ArrayList<>((List<Object>)inputsField.get(shapeless));

					boolean addNew = false;
					for (int i = 0; i < newInputs.size(); i++) {
						if (newInputs.get(i) != null && newInputs.get(i) instanceof ItemStack) {
							if (((ItemStack)newInputs.get(i)).getItem() == WATER_BUCKET) {
								newInputs.set(i, new ItemStack(items.infiniteWaterBucket));
								addNew = true;
							}
						}
					}

					if (addNew) {
						ShapelessOreRecipe newRecipe = new ShapelessOreRecipe(recipe.getRecipeOutput(), Blocks.FIRE);

						inputsField.set(newRecipe, newInputs);

						toAdd.add(newRecipe);
					}

				} catch (ReflectiveOperationException e) {
					YeOldeTanks.log.error("Couldn't create an Infinite Water Bucket recipe from a ShapelessOreRecipe");
				}

			}
		}

		toAdd.stream().forEach(GameRegistry::addRecipe);
	}

}
