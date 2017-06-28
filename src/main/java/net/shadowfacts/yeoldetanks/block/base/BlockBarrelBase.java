package net.shadowfacts.yeoldetanks.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.block.BlockTE;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

/**
 * @author shadowfacts
 */
public abstract class BlockBarrelBase<TE extends TileEntityBarrelBase> extends BlockTE<TE> implements AchievementProvider {

	public static final PropertyBool LID = PropertyBool.create("lid");

	private static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	private static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	public BlockBarrelBase(String name) {
		super(Material.IRON, name);
		setCreativeTab(YeOldeTanks.tab);
		setHardness(.5f);
		setDefaultState(getDefaultState().withProperty(LID, false));
	}

	public abstract boolean isCreative();

	@Override
	public Item createItemBlock() {
		return new ItemBlockBarrelBase(this);
	}

	protected Item getBarrelItem() {
		return Item.getItemFromBlock(this);
	}

	private ItemStack writeBarrelToStack(IBlockAccess world, BlockPos pos) {
		ItemStack stack = new ItemStack(getBarrelItem());

		if (YOTConfig.itemsStoreFluids) {
			TE te = getTileEntity(world, pos);
			if (te.getTank().getFluid() != null && te.getTank().getFluidAmount() > 0) {
				FluidTank tank = (FluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
				tank.setFluid(te.getTank().getFluid().copy());
			}
		}

		return stack;
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
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LID, meta == 1);
	}

	@Override
	@Deprecated
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
	}

	@Override
	@Deprecated
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		ItemStack dropStack = writeBarrelToStack(world, pos);

		super.breakBlock(world, pos, state);

		EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), dropStack);
		world.spawnEntity(item);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = player.getHeldItem(hand);
		TE te = getTileEntity(world, pos);
		if (heldItem.isEmpty() && player.isSneaking()) {
			world.setBlockState(pos, state.cycleProperty(LID));
			return true;
		} else {
			boolean res = FluidUtil.interactWithFluidHandler(player, hand, world, pos, side);
			if (res) {
				te.save();
				return true;
			}
			return false;
		}
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN || (side == EnumFacing.UP && state.getValue(LID));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return writeBarrelToStack(world, pos);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity.posX > pos.getX() && entity.posX < pos.getX() + 1 &&
				entity.posY > pos.getY() && entity.posY < pos.getY() + 1 &&
				entity.posZ > pos.getZ() && entity.posZ < pos.getZ() + 1) {
			TE barrel = getTileEntity(world, pos);
			if (barrel.getTank().getFluidAmount() > 0) {
				Block fluidBlock = barrel.getTank().getFluid().getFluid().getBlock();
				if (fluidBlock != null) {
					fluidBlock.onEntityCollidedWithBlock(world, pos, fluidBlock.getDefaultState(), entity);
				}
			}
		}
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		TE barrel = getTileEntity(world, pos);
		if (barrel.getTank().getFluid() != null && barrel.getTank().getFluidAmount() > 0) {
			return barrel.getTank().getFluid().getFluid().getLuminosity(barrel.getTank().getFluid());
		}

		return 0;
	}

}
