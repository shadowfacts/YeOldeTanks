package net.shadowfacts.yeoldetanks.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;

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
}
