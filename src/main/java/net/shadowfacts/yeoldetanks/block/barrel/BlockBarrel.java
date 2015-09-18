package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelISBRH;

import java.util.ArrayList;

/**
 * @author shadowfacts
 */
public class BlockBarrel extends Block implements ITileEntityProvider {

	public BlockBarrel() {
		super(Material.rock);
		setHarvestLevel("pickaxe", 2);
		setBlockName("yot.barrel");
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		ItemStack dropStack = new ItemStack(YeOldeTanks.blocks.barrel);
		if (dropStack.stackTagCompound == null) dropStack.stackTagCompound = new NBTTagCompound();

		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityBarrel) {
			((TileEntityBarrel)te).tank.writeToNBT(dropStack.stackTagCompound);
		}

		EntityItem item = new EntityItem(world, x, y, z, dropStack);
		world.spawnEntityInWorld(item);

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem() == null && player.isSneaking()) {
			TileEntity te = world.getTileEntity(x, y, z);
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel)te;
				barrel.lid = !barrel.lid;
			}
		}
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return BarrelISBRH.RENDER_ID;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		if (side == ForgeDirection.DOWN) {
			return true;
		} else if (side == ForgeDirection.UP) {
			TileEntity te = world.getTileEntity(x, y, z);
			if (te instanceof TileEntityBarrel) {
				return ((TileEntityBarrel)te).lid;
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityBarrel();
	}
}
