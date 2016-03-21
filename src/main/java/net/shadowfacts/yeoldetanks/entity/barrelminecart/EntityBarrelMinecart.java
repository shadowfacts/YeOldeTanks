package net.shadowfacts.yeoldetanks.entity.barrelminecart;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.shadowfacts.shadowmc.fluid.EntityFluidTank;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.ModEntities;

/**
 * @author shadowfacts
 */
public class EntityBarrelMinecart extends EntityMinecart implements IFluidHandler {

	public EntityFluidTank tank;

	public EntityBarrelMinecart(World world) {
		super(world);
		tank = new EntityFluidTank(dataWatcher, 23, 24, 25, YOTConfig.barrelCapacity);
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
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!player.isSneaking()) {
			if (player.getHeldItem() != null && player.getHeldItem().getItem() == YeOldeTanks.items.dippingStick && !worldObj.isRemote) {
				if (tank.getFluid() != null) {
					player.addChatComponentMessage(new ChatComponentText("Fluid: " + tank.getFluid().getLocalizedName()));
					player.addChatComponentMessage(new ChatComponentText(tank.getFluidAmount() + "mb / " + tank.getCapacity() + "mb"));
				} else {
					player.addChatComponentMessage(new ChatComponentText("Empty"));
				}
				return true;
			}
			if (CoFHUtils.fillHandlerWithContainer(player.worldObj, this, player)) {
				onEntityUpdate();
			} else if (CoFHUtils.fillContainerFromHandler(player.worldObj, this, player, tank.getFluid())) {
				onEntityUpdate();
			}
		}
		return true;
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
	public EnumMinecartType getMinecartType() {
		return ModEntities.TANK_CART_TYPE;
	}

//	IFluidHandler
	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[]{ tank.getInfo() };
	}

}
