package net.shadowfacts.yeoldetanks.block.cosmetic;

import lombok.Getter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.fluid.FluidTank;
import net.shadowfacts.shadowmc.nbt.AutoNBTSerializer;
import net.shadowfacts.shadowmc.nbt.AutoSerializeNBT;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class TileEntityCosmeticBarrel extends TileEntityBarrelBase {

	static {
		AutoNBTSerializer.registerSerializer(CosmeticFluidTank.class, (tag, name, val) -> {
			NBTTagCompound tankTag = new NBTTagCompound();
			val.writeToNBT(tankTag);
			tag.setTag(name, tankTag);
		}, (tag, name) -> {
			return (CosmeticFluidTank)new CosmeticFluidTank().readFromNBT(tag.getCompoundTag(name));
		});
	}

	@Getter
	@AutoSerializeNBT
	public CosmeticFluidTank tank = new CosmeticFluidTank();

	@Override
	public boolean isCreative() {
		return false;
	}

	@Override
	public void update() {
	}

	public static class CosmeticFluidTank extends FluidTank {

		private CosmeticFluidTank() {
			super(1000);
		}

		@Override
		public void setFluid(@Nullable FluidStack fluid) {
			super.setFluid(fluid);
		}

		@Override
		public boolean canDrain() {
			return false;
		}

		@Override
		public boolean canFill() {
			return false;
		}

	}

}
