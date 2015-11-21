package net.shadowfacts.yeoldetanks.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemBlockBarrel extends ItemBlock {

	public ItemBlockBarrel(Block block) {
		super(block);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)) {

			if (stack.stackTagCompound != null) {
				TileEntity te = world.getTileEntity(x, y, z);
				if (te instanceof TileEntityBarrel) {
					((TileEntityBarrel) te).readFromNBT(stack.stackTagCompound, false);
				}
			}

			return true;

		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		if (stack.stackTagCompound == null) return;
		if (stack.stackTagCompound.hasKey("Empty")) return;

		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.stackTagCompound);
		tooltip.add(fluid.getLocalizedName());
		tooltip.add(fluid.amount + "mb / " + YOTConfig.barrelCapacity + "mb");
	}

//	@Override
//	public ItemStack getContainerItem(ItemStack itemStack) {
//		return itemStack;
//	}
//
//	@Override
//	public boolean hasContainerItem(ItemStack stack) {
//		return true;
//	}
}
