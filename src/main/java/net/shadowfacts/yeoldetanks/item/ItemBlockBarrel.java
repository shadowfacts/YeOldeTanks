package net.shadowfacts.yeoldetanks.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
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
		setRegistryName(block.getRegistryName());
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState state) {
		if (super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, state)) {

			if (stack.getTagCompound() != null) {
				TileEntity te = world.getTileEntity(pos);
				if (te instanceof TileEntityBarrel) {
					NBTTagCompound tag = (NBTTagCompound)stack.getTagCompound().copy();
					tag.setInteger("x", pos.getX());
					tag.setInteger("y", pos.getY());
					tag.setInteger("z", pos.getZ());
					te.readFromNBT(tag);
					((TileEntityBarrel)te).sync();
				}
			}

			return true;

		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack.getTagCompound() == null) return;
		if (stack.getTagCompound().hasKey("Empty")) return;

		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTagCompound());
		tooltip.add(fluid.getLocalizedName());
		tooltip.add(fluid.amount + "mb / " + YOTConfig.barrelCapacity + "mb");
	}

}
