package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.item.ItemBlockBarrel;

/**
 * @author shadowfacts
 */
public class RecipeDippingStick implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		int sticks = 0;
		int barrels = 0;

		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (!stack.isEmpty()) {
				if (stack.getItem() == Items.STICK) {
					sticks++;
				} else if (stack.getItem() == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
					barrels++;
				} else {
					return false;
				}
			}
		}

		return sticks == 1 && barrels == 1;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getRecipeOutput();
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(YeOldeTanks.items.dippingStick);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {

		NonNullList<ItemStack> list = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() instanceof ItemBlockBarrel) {
				list.set(i, stack.copy());
				break;
			}
		}

		return list;
	}

}
