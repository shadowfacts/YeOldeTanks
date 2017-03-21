package net.shadowfacts.yeoldetanks.entity.barrelminecart;

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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.shadowfacts.shadowmc.fluid.EntityFluidTank;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.ModEntities;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public class EntityBarrelMinecart extends EntityMinecart {

	private static final DataParameter<Integer> AMOUNT = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CAPACITY = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.VARINT);
	private static final DataParameter<String> NAME = EntityDataManager.createKey(EntityBarrelMinecart.class, DataSerializers.STRING);

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

	@Nonnull
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!player.isSneaking()) {
			ItemStack stack = player.getHeldItem(hand);
			if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() == YeOldeTanks.items.dippingStick && !world.isRemote) {
				if (tank.getFluid() != null) {
					player.sendMessage(new TextComponentString("Fluid: " + tank.getFluid().getLocalizedName()));
					player.sendMessage(new TextComponentString(tank.getFluidAmount() + "mb / " + tank.getCapacity() + "mb"));
				} else {
					player.sendMessage(new TextComponentString("Empty"));
				}
				return EnumActionResult.SUCCESS;
			}
			FluidActionResult res = FluidUtil.interactWithFluidHandler(stack, tank, player);
			if (res.isSuccess()) {
				player.setHeldItem(hand, res.getResult());
			}
		}
		return EnumActionResult.SUCCESS;
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
