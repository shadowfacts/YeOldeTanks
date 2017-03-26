package net.shadowfacts.yeoldetanks.compat.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BarrelMinecartEntityProvider implements IWailaEntityProvider {

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		Entity e = accessor.getEntity();
		if (e instanceof EntityBarrelMinecart) {
			EntityBarrelMinecart barrel = (EntityBarrelMinecart)e;

			if (barrel.tank.getFluid() != null) {
				currenttip.add(barrel.tank.getFluid().getLocalizedName());
				currenttip.add(barrel.tank.getFluidAmount() + "mb / " + barrel.tank.getCapacity() + "mb");
			}
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
		return tag;
	}

}
