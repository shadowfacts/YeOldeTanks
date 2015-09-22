package net.shadowfacts.yeoldetanks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Redistributed, with permission from CoFHLib
 *
 * {@see https://github.com/CoFH/CoFHLib}
 * {@see https://github.com/CoFH/CoFHLib/blob/45ab41b95609442a301e048cb25f312eb48e6ed8/LICENSE%20-%20LGPLv3.txt}
 *
 * @author Team CoFH
 */
public class CoFHUtils {

//	FluidHelper
	public static boolean fillHandlerWithContainer(World world, IFluidHandler handler, EntityPlayer player) {

		ItemStack container = player.getCurrentEquippedItem();
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(container);

		if (fluid != null) {
			if (handler.fill(ForgeDirection.UNKNOWN, fluid, false) == fluid.amount || player.capabilities.isCreativeMode) {
				ItemStack returnStack = FluidContainerRegistry.drainFluidContainer(container);
				if (world.isRemote) {
					return true;
				}
				if (!player.capabilities.isCreativeMode) {
					if (disposePlayerItem(player.getCurrentEquippedItem(), returnStack, player, true)) {
						if (!world.isRemote) {
							player.openContainer.detectAndSendChanges();
							((EntityPlayerMP) player).sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
						}
					}
				}
				handler.fill(ForgeDirection.UNKNOWN, fluid, true);
				return true;
			}
		}
		return false;
	}

	public static boolean fillContainerFromHandler(World world, IFluidHandler handler, EntityPlayer player, FluidStack tankFluid) {

		ItemStack container = player.getCurrentEquippedItem();

		if (FluidContainerRegistry.isEmptyContainer(container)) {
			ItemStack returnStack = FluidContainerRegistry.fillFluidContainer(tankFluid, container);
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(returnStack);

			if (fluid == null || returnStack == null) {
				return false;
			}
			if (world.isRemote) {
				return true;
			}
			if (!player.capabilities.isCreativeMode) {
				if (container.stackSize == 1) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, returnStack);
					container.stackSize--;
					if (container.stackSize <= 0) {
						container = null;
					}
				} else {
					if (disposePlayerItem(player.getCurrentEquippedItem(), returnStack, player, true)) {
						player.openContainer.detectAndSendChanges();
						((EntityPlayerMP) player).sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
					}
				}
			}
			handler.drain(ForgeDirection.UNKNOWN, fluid.amount, true);
			return true;
		}
		return false;
	}


//	ItemHelper
	public static boolean disposePlayerItem(ItemStack stack, ItemStack dropStack, EntityPlayer entityplayer, boolean allowDrop) {

		return disposePlayerItem(stack, dropStack, entityplayer, allowDrop, true);
	}

	public static boolean disposePlayerItem(ItemStack stack, ItemStack dropStack, EntityPlayer entityplayer, boolean allowDrop, boolean allowReplace) {

		if (entityplayer == null || entityplayer.capabilities.isCreativeMode) {
			return true;
		}
		if (allowReplace && stack.stackSize <= 1) {
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
			entityplayer.inventory.addItemStackToInventory(dropStack);
			return true;
		} else if (allowDrop) {
			stack.stackSize -= 1;
			if (dropStack != null && !entityplayer.inventory.addItemStackToInventory(dropStack)) {
				entityplayer.func_146097_a(dropStack, false, true);
			}
			return true;
		}
		return false;
	}

}
