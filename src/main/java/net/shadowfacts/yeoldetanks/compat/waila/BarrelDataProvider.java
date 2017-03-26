package net.shadowfacts.yeoldetanks.compat.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.block.base.TileEntityBarrelBase;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BarrelDataProvider implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity te = accessor.getTileEntity();

		if (te instanceof TileEntityBarrelBase) {
			TileEntityBarrelBase barrel = (TileEntityBarrelBase) te;

			if (barrel.getTank().getFluid() != null) {
				String capacity = barrel.isCreative() ? "∞" : Integer.toString(barrel.getTank().getCapacity());

				currenttip.add(barrel.getTank().getFluid().getLocalizedName());
				currenttip.add(barrel.getTank().getFluidAmount() + " mb / " + capacity + " mb");
			}
		}


		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
		return tag;
	}

}
