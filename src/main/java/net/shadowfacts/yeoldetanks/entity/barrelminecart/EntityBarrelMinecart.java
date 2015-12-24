package net.shadowfacts.yeoldetanks.entity.barrelminecart;

import cpw.mods.fml.common.Optional;
import mods.railcraft.api.carts.IFluidCart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.EntityFluidTank;

/**
 * @author shadowfacts
 */
@Optional.Interface(iface = "mods.railcraft.api.carts.IFluidCart", modid = "Railcraft")
public class EntityBarrelMinecart extends EntityMinecart implements IFluidHandler, IFluidCart {

	public EntityFluidTank tank;

	public EntityBarrelMinecart(World world) {
		super(world);
	}

	public EntityBarrelMinecart(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		tank = new EntityFluidTank(dataWatcher);
		tank.init(YOTConfig.barrelCapacity);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!player.isSneaking()) {
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
		stack.stackTagCompound = new NBTTagCompound();
		tank.writeToNBT(stack.stackTagCompound);
		entityDropItem(stack, 0);
	}

	@Override
	public ItemStack getCartItem() {
		return new ItemStack(YeOldeTanks.items.barrelMinecart);
	}

	@Override
	public Block func_145817_o() {
		return YeOldeTanks.blocks.barrel;
	}

	@Override
	public int getMinecartType() {
		return 0;
	}

//	IFluidHandler
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{ tank.getInfo() };
	}


//	IFluidCart
	@Override
	public boolean canPassFluidRequests(Fluid fluid) {
		return false;
	}

	@Override
	public boolean canAcceptPushedFluid(EntityMinecart requester, Fluid fluid) {
		return tank.getFluid() != null && tank.getFluidAmount() > 0 && tank.getFluid().getFluid().equals(fluid);
	}

	@Override
	public boolean canProvidePulledFluid(EntityMinecart requester, Fluid fluid) {
		return tank.getFluid() != null && tank.getFluidAmount() > 0 && tank.getFluid().getFluid().equals(fluid);
	}

	@Override
	public void setFilling(boolean filling) {

	}
}
