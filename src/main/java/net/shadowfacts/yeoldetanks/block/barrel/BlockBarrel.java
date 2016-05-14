package net.shadowfacts.yeoldetanks.block.barrel;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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

	private static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	private static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	public BlockBarrel() {
		super(Material.ROCK);
		setUnlocalizedName("barrel");
		setRegistryName("barrel");
		setCreativeTab(YeOldeTanks.tab);
		setHardness(.5f);
		setDefaultState(getDefaultState().withProperty(LID, false));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LID);
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
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity.posX > pos.getY() && entity.posX < pos.getX() + 1 &&
				entity.posY > pos.getY() && entity.posY < pos.getY() + 1 &&
				entity.posZ > pos.getZ() && entity.posZ < pos.getZ() + 1) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel)te;
				if (barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
					Block fluidBlock = barrel.tank.getFluid().getFluid().getBlock();
					if (fluidBlock != null) {
						fluidBlock.onEntityCollidedWithBlock(world, pos, state, entity);
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityBarrel) {
			if (player.getHeldItem(hand) == null && player.isSneaking()) {
				setLidState(world, pos, state, !state.getValue(LID));
			} else {
				TileEntityBarrel barrel = (TileEntityBarrel) te;
				if (CoFHUtils.fillHandlerWithContainer(world, barrel, player, hand)) {
					return true;
				} else if (CoFHUtils.fillContainerFromHandler(world, barrel, player, hand, barrel.tank.getFluid())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return true;
		} else if (side == EnumFacing.UP) {
			return world.getBlockState(pos).getValue(LID);
		}
		return false;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
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
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
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
