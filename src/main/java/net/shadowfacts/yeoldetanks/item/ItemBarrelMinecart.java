package net.shadowfacts.yeoldetanks.item;


import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fluids.IFluidTank;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.item.ItemBase;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

/**
 * @author shadowfacts
 */
public class ItemBarrelMinecart extends ItemBase implements AchievementProvider {

	public ItemBarrelMinecart() {
		super("barrel_minecart");
		setMaxStackSize(1);
		setCreativeTab(YeOldeTanks.tab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.getBlockState(pos).getBlock() instanceof BlockRailBase) {
			if (!world.isRemote) {
				EntityBarrelMinecart cart = new EntityBarrelMinecart(world, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);

				if (YOTConfig.itemsStoreFluids) {
					IFluidTank tank = (IFluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
					cart.tank.setFluid(tank.getFluid().copy());
				}

				if (stack.hasDisplayName()) {
					cart.setCustomNameTag(stack.getDisplayName());
				}

				world.spawnEntityInWorld(cart);
			}

			stack.stackSize--;
			if (stack.stackSize <= 0) {
				player.setHeldItem(hand, null);
			}
			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (YOTConfig.itemsStoreFluids) {
			IFluidTank tank = (IFluidTank)stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
			if (tank.getFluid() != null && tank.getFluidAmount() > 0) {
				tooltip.add(tank.getFluid().getLocalizedName());
				tooltip.add(tank.getFluidAmount() + " mb / " + tank.getCapacity() + " mb");
			}
		}
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftBarrelCart;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return YOTConfig.itemsStoreFluids ? new BarrelMinecartCapProvider() : null;
	}

	public static class BarrelMinecartCapProvider implements ICapabilitySerializable<NBTTagCompound> {

		private FluidTank tank = new FluidTank(YOTConfig.barrelCapacity);

		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
			return capability == FLUID_HANDLER_CAPABILITY;
		}

		@Nullable
		@Override
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
			return capability == FLUID_HANDLER_CAPABILITY ? (T)tank : null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return tank.writeToNBT(new NBTTagCompound());
		}

		@Override
		public void deserializeNBT(NBTTagCompound tag) {
			tank.readFromNBT(tag);
		}

	}

}
