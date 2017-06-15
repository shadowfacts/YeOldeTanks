package net.shadowfacts.yeoldetanks.compat.top;

//import mcjty.theoneprobe.api.IProbeHitEntityData;
//import mcjty.theoneprobe.api.IProbeInfo;
//import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
//import mcjty.theoneprobe.api.ProbeMode;
//import mcjty.theoneprobe.apiimpl.ProbeConfig;
//import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
//import mcjty.theoneprobe.config.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

//import static mcjty.theoneprobe.api.TextStyleClass.NAME;
//import static mcjty.theoneprobe.api.TextStyleClass.PROGRESS;

/**
 * @author shadowfacts
 */
public class EntityBarrelMinecartProvider /*implements IProbeInfoEntityProvider */{

//	@Override
//	public String getID() {
//		return "yeoldetanks:barrel_minecart";
//	}
//
//	@Override
//	public void addProbeEntityInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data) {
//		if (entity instanceof EntityBarrelMinecart && mode == ProbeMode.EXTENDED) {
//			EntityBarrelMinecart barrel = (EntityBarrelMinecart)entity;
//
//			FluidStack fluid = barrel.tank.getFluid();
//			addFluidInfo(probeInfo, Config.getDefaultConfig(), fluid, barrel.tank.getCapacity());
//		}
//	}
//
//	private void addFluidInfo(IProbeInfo probeInfo, ProbeConfig config, FluidStack fluidStack, int maxContents) {
//		int contents = fluidStack == null ? 0 : fluidStack.amount;
//		if (fluidStack != null) {
//			probeInfo.text(NAME + "Liquid: " + fluidStack.getLocalizedName());
//		}
//		if (config.getTankMode() == 1) {
//			probeInfo.progress(contents, maxContents,
//					probeInfo.defaultProgressStyle()
//							.suffix("mB")
//							.filledColor(Config.tankbarFilledColor)
//							.alternateFilledColor(Config.tankbarAlternateFilledColor)
//							.borderColor(Config.tankbarBorderColor)
//							.numberFormat(Config.tankFormat));
//		} else {
//			probeInfo.text(PROGRESS + ElementProgress.format(contents, Config.tankFormat, "mB"));
//		}
//	}

}
