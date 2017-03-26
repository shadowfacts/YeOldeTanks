package net.shadowfacts.yeoldetanks.compat.waila;

import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;
import net.shadowfacts.yeoldetanks.block.base.BlockBarrelBase;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
@WailaPlugin
public class YOTWailaPlugin implements IWailaPlugin {

	@Override
	public void register(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(new BarrelDataProvider(), BlockBarrelBase.class);
		registrar.registerBodyProvider(new BarrelMinecartEntityProvider(), EntityBarrelMinecart.class);
	}

}
