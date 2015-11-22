package net.shadowfacts.yeoldetanks.recipe;

import li.cil.oc.common.block.Item;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class BarrelMinecartRecipe implements IRecipe {

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
			if (barrel.stackTagCompound != null) {
				stack.stackTagCompound = (NBTTagCompound)barrel.stackTagCompound.copy();
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

}
