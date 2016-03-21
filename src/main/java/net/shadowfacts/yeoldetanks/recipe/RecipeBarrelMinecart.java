package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class RecipeBarrelMinecart implements IRecipe {

//	TODO: fix broken recipe

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		int cartCount = 0;
		int barrelCount = 0;
		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() == Items.minecart) {
					cartCount++;
				} else if (stack.getItem() == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
					barrelCount++;
				} else {
					return false;
				}
			}
		}

		return cartCount == 1 && barrelCount == 1;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack barrel = null;
		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (stack != null && stack.getItem() == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
				barrel = stack;
			}
		}

		if (barrel != null) {
			ItemStack stack = new ItemStack(YeOldeTanks.items.barrelMinecart);
			if (barrel.getTagCompound() != null) {
				stack.setTagCompound((NBTTagCompound)barrel.getTagCompound().copy());
			}
			return stack;
		}

		return null;
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(YeOldeTanks.items.barrelMinecart);
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		return new ItemStack[inv.getSizeInventory()];
	}


}
