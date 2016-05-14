package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.item.ItemBarrelMinecart;

/**
 * @author shadowfacts
 */
public class RecipeUncraftBarrelMinecart implements IRecipe {

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
			stack.setTagCompound((NBTTagCompound)cart.getTagCompound().copy());
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

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		ItemStack[] remaining = new ItemStack[inv.getSizeInventory()];

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (stack != null &&  stack.getItem() instanceof ItemBarrelMinecart) {
				remaining[i] = new ItemStack(Items.MINECART);
				break;
			}
		}

		return remaining;
	}

}
