package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.item.ItemModelProvider;
import net.shadowfacts.yeoldetanks.proxy.ClientProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class BlockBarrel extends Block implements ITileEntityProvider, ItemModelProvider, AchievementProvider {

	public static final PropertyBool LID = PropertyBool.create("lid");

	public BlockBarrel() {
		super(Material.rock);
		setUnlocalizedName("barrel");
		setRegistryName("barrel");
		setCreativeTab(YeOldeTanks.tab);
		setHardness(.5f);
		setDefaultState(getDefaultState().withProperty(LID, false));
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, LID);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LID) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LID, meta == 1);
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity entity) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, entity);
		float f = 0.05f;

		if (state.getValue(LID)) {
			setBlockBounds(0, 1, 0, 1, 1, 1);
			super.addCollisionBoxesToList(world, pos, state, mask, list, entity);
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, entity);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		super.addCollisionBoxesToList(world, pos, state, mask, list, entity);
		this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, entity);
		this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, pos, state, mask, list, entity);

		setBlockBounds(0, 0, 0, 1, 1, 1);

		this.setBlockBoundsForItemRender();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
		if (entity.posX > pos.getY() && entity.posX < pos.getX() + 1 &&
				entity.posY > pos.getY() && entity.posY < pos.getY() + 1 &&
				entity.posZ > pos.getZ() && entity.posZ < pos.getZ() + 1) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel)te;
				if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
					Block fluidBlock = barrel.tank.getFluid().getFluid().getBlock();
					if (fluidBlock != null) {
						fluidBlock.onEntityCollidedWithBlock(world, pos, entity);
					}
				}
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		ItemStack dropStack = new ItemStack(YeOldeTanks.blocks.barrel);

		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;

			if (dropStack.getTagCompound() == null) dropStack.setTagCompound(new NBTTagCompound());

			barrel.tank.writeToNBT(dropStack.getTagCompound());
		}

		EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), dropStack);
		world.spawnEntityInWorld(item);

		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityBarrel) {
			if (player.getHeldItem() == null && player.isSneaking()) {
				setLidState(world, pos, state, !state.getValue(LID));
			} else {
				TileEntityBarrel barrel = (TileEntityBarrel) te;
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
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return true;
		} else if (side == EnumFacing.UP) {
			return world.getBlockState(pos).getValue(LID);
		}
		return false;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = new ItemStack(YeOldeTanks.blocks.barrel);

		TileEntity te = world.getTileEntity(pos);
		if (te != null && te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;
			if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
				if (stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
				barrel.tank.writeToNBT(stack.getTagCompound());
			}
		}

		return stack;
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);

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
	public void initModel() {
		ClientProxy.registerInvModel(this, 0, "barrel");
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftBarrel;
	}

	public static void setLidState(World world, BlockPos pos, IBlockState state, boolean value) {
		TileEntity originalTE = world.getTileEntity(pos);
		NBTTagCompound tag = new NBTTagCompound();
		originalTE.writeToNBT(tag);

		world.setBlockState(pos, state.withProperty(LID, value));

		TileEntity newTE = world.getTileEntity(pos);
		newTE.readFromNBT(tag);
	}
}
