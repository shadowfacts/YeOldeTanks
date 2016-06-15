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
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.shadowfacts.shadowmc.fluid.EntityFluidTank;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.ModEntities;

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

	@Override
	public boolean processInitialInteract(EntityPlayer player, ItemStack stack, EnumHand hand) {
		return super.processInitialInteract(player, stack, hand);
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, ItemStack stack, EnumHand hand) {
		if (!player.isSneaking()) {
			if (player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() == YeOldeTanks.items.dippingStick && !worldObj.isRemote) {
				if (tank.getFluid() != null) {
					player.addChatComponentMessage(new TextComponentString("Fluid: " + tank.getFluid().getLocalizedName()));
					player.addChatComponentMessage(new TextComponentString(tank.getFluidAmount() + "mb / " + tank.getCapacity() + "mb"));
				} else {
					player.addChatComponentMessage(new TextComponentString("Empty"));
				}
				return EnumActionResult.SUCCESS;
			}
			if (!CoFHUtils.fillHandlerWithContainer(player.worldObj, tank, player, hand)) {
				CoFHUtils.fillContainerFromHandler(player.worldObj, tank, player, hand, tank.getFluid());
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
