package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class BarrelMinecartUncraftRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		int cartCount = 0;
		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (stack != null) {
				if (stack.getItem() == YeOldeTanks.items.barrelMinecart) {
					cartCount++;
				} else {
					return false;
				}
			}
		}
		return cartCount == 1;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack cart = null;
		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (stack != null && stack.getItem() == YeOldeTanks.items.barrelMinecart) {
				cart = stack;
			}
		}

		if (cart != null) {
			ItemStack stack = new ItemStack(YeOldeTanks.blocks.barrel);
			stack.stackTagCompound = (NBTTagCompound)cart.stackTagCompound.copy();
			return stack;
		}

		return null;
	}

	@Override
	public int getRecipeSize() {
		return 1;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(YeOldeTanks.blocks.barrel);
	}

}
