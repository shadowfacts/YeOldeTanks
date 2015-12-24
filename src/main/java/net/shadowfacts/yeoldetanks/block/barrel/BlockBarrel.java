package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelISBRH;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class BlockBarrel extends Block implements ITileEntityProvider, AchievementProvider {

	public BlockBarrel() {
		super(Material.rock);
		setBlockName("yot.barrel");
		setCreativeTab(YeOldeTanks.tab);
		setHardness(.5f);
		setBlockTextureName("iron_block");
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		float f = 0.05f;

		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityBarrel) {
			if (((TileEntityBarrel)te).lid) {
				this.setBlockBounds(0, 1, 0, 1, 1, 1);
				super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
			}
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);

		setBlockBounds(0, 0, 0, 1, 1, 1);

		this.setBlockBoundsForItemRender();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.posX > x && entity.posX < x + 1 &&
				entity.posY > y && entity.posY < y + 1 &&
				entity.posZ > z && entity.posZ < z + 1) {
			TileEntity te = world.getTileEntity(x, y, z);
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel)te;
				if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
					Block fluidBlock = barrel.tank.getFluid().getFluid().getBlock();
					if (fluidBlock != null) {
						fluidBlock.onEntityCollidedWithBlock(world, x, y, z, entity);
					}
				}
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		ItemStack dropStack = new ItemStack(YeOldeTanks.blocks.barrel);

		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;

			if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {

				if (dropStack.stackTagCompound == null) dropStack.stackTagCompound = new NBTTagCompound();

				barrel.tank.writeToNBT(dropStack.stackTagCompound);
			}
		}

		EntityItem item = new EntityItem(world, x, y, z, dropStack);
		world.spawnEntityInWorld(item);

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;

			if (player.getHeldItem() == null && player.isSneaking()) {
				barrel.lid = !barrel.lid;
			} else {
				if (CoFHUtils.fillHandlerWithContainer(world, barrel, player)) {
					return true;
				} else if (CoFHUtils.fillContainerFromHandler(world, barrel, player, barrel.tank.getFluid())) {
					return true;
				}
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
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		ItemStack stack = new ItemStack(YeOldeTanks.blocks.barrel);

		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;
			if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
				if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound();
				barrel.tank.writeToNBT(stack.stackTagCompound);
			}
		}

		return stack;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);

		if (te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;

			if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
				return barrel.tank.getFluid().getFluid().getLuminosity(barrel.tank.getFluid());
			}
		}

		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityBarrel();
	}

	@Override
	public Achievement getAchievement() {
		return ModAchievements.craftBarrel;
	}
}
