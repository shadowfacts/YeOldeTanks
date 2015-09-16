package net.shadowfacts.yeoldetanks.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class FMLEventHandler {

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(YeOldeTanks.modId)) {
			ConfigManager.instance.load(YeOldeTanks.modId);
		}
	}

}
