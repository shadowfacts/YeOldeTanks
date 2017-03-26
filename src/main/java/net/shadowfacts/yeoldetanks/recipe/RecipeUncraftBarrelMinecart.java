package net.shadowfacts.yeoldetanks.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.item.ItemBarrelMinecart;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

/**
 * @author shadowfacts
 */
public class RecipeUncraftBarrelMinecart implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		int cartCount = 0;
		for (int i = 0; i < crafting.getSizeInventory(); i++) {
			ItemStack stack = crafting.getStackInSlot(i);
			if (!stack.isEmpty()) {
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
			if (!stack.isEmpty() && stack.getItem() == YeOldeTanks.items.barrelMinecart) {
				cart = stack;
			}
		}

		if (cart != null) {
			ItemStack stack = new ItemStack(YeOldeTanks.blocks.barrel);
			FluidTank tank = (FluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
			tank.setFluid(((IFluidTank)cart.getCapability(FLUID_HANDLER_CAPABILITY, null)).getFluid());
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
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> list = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() &&  stack.getItem() instanceof ItemBarrelMinecart) {
				list.set(i, new ItemStack(Items.MINECART));
				break;
			}
		}

		return list;
	}

}
