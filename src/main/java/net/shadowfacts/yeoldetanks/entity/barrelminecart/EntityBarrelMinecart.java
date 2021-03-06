package net.shadowfacts.yeoldetanks.entity.barrelminecart;

import lombok.Getter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.shadowfacts.shadowmc.fluid.EntityFluidTank;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.item.ItemDippingStick;
import net.shadowfacts.yeoldetanks.util.YOTBarrel;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class EntityBarrelMinecart extends EntityMinecart implements YOTBarrel {

	private static final DataParameter<Integer> AMOUNT = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CAPACITY = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.VARINT);
	private static final DataParameter<String> NAME = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.STRING);

	@Getter
	public EntityFluidTank tank;

	public EntityBarrelMinecart(World world) {
		super(world);
		tank = new EntityFluidTank(dataManager, AMOUNT, CAPACITY, NAME, null, YOTConfig.barrelCapacity);
	}

	public EntityBarrelMinecart(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@Override
	public boolean isCreative() {
		return false;
	}

	@Nonnull
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!player.isSneaking()) {
			ItemStack stack = player.getHeldItem(hand);
			if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() == YeOldeTanks.items.dippingStick && !world.isRemote) {
				ItemDippingStick.handleBarrel(player, this);
				return EnumActionResult.SUCCESS;
			}
			FluidActionResult res = interactWithFluidHandler(stack, tank, player);
			if (res.isSuccess()) {
				player.setHeldItem(hand, res.getResult());
			}
		}
		return EnumActionResult.SUCCESS;
	}

	// Since the old Forge FluidUtil.interactWithFluidHandler(ItemStack, IFluidHandler, EntityPlayer) was removed
	private FluidActionResult interactWithFluidHandler(ItemStack stack, IFluidHandler handler, EntityPlayer player) {
		if (stack.isEmpty() || handler == null || player == null) return FluidActionResult.FAILURE;

		IItemHandler playerInventory = new InvWrapper(player.inventory);

		FluidActionResult fillResult = FluidUtil.tryFillContainerAndStow(stack, handler, playerInventory, Integer.MAX_VALUE, player);
		if (fillResult.isSuccess()) {
			return fillResult;
		} else {
			return FluidUtil.tryEmptyContainerAndStow(stack, handler, playerInventory, Integer.MAX_VALUE, player);
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tank.writeToNBT(tag);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		tank.readFromNBT(tag);
	}

	@Override
	public void killMinecart(DamageSource damageSource) {
		super.killMinecart(damageSource);
		ItemStack stack = new ItemStack(YeOldeTanks.blocks.barrel);
		stack.setTagCompound(new NBTTagCompound());
		tank.writeToNBT(stack.getTagCompound());
		entityDropItem(stack, 0);
	}

	@Override
	public ItemStack getCartItem() {
		return new ItemStack(YeOldeTanks.items.barrelMinecart);
	}

	@Override
	public boolean hasDisplayTile() {
		return true;
	}

	@Override
	public IBlockState getDisplayTile() {
		return YeOldeTanks.blocks.barrel.getDefaultState();
	}

	@Override
	public Type getType() {
		return ModEntities.TANK_CART_TYPE;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T)tank;
		} else {
			return super.getCapability(capability, facing);
		}
	}

}
