package net.shadowfacts.yeoldetanks.entity.barrelminecart;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import net.shadowfacts.yeoldetanks.CoFHUtils;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.entity.EntityFluidTank;

/**
 * @author shadowfacts
 */
public class EntityBarrelMinecart extends EntityMinecart implements IFluidHandler {


	public static final int FLUID_AMOUNT = 16;
	public static final int FLUID_TYPE = 15;
	public static final int FLUID_CAPACITY = 14;

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
//		func_145778_a(Item.getItemFromBlock(YeOldeTanks.blocks.barrel), 1, 0);
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
}
