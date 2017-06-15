package net.shadowfacts.yeoldetanks.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.shadowfacts.yeoldetanks.block.base.ItemBlockBarrelBase;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class RecipePreserveBarrel extends ShapelessOreRecipe {

	public RecipePreserveBarrel(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result) {
		super(group, input, result);
	}

	@Nonnull
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> res = super.getRemainingItems(inv);
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() instanceof ItemBlockBarrelBase) {
				res.set(i, stack.copy());
			}
		}
		return res;
	}

	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			String group = JsonUtils.getString(json, "group", "");

			NonNullList<Ingredient> ings = NonNullList.create();
			for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
				ings.add(CraftingHelper.getIngredient(ele, context));

			if (ings.isEmpty())
				throw new JsonParseException("No ingredients for shapeless recipe");

			ItemStack itemstack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
			return new RecipePreserveBarrel(group.isEmpty() ? null : new ResourceLocation(group), ings, itemstack);
		}

	}

}
