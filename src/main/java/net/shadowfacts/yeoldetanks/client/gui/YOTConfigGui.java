package net.shadowfacts.yeoldetanks.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.shadowfacts.shadowmc.config.GUIConfig;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class YOTConfigGui extends GUIConfig {

	public YOTConfigGui(GuiScreen parent) {
		super(parent, YeOldeTanks.modId, YOTConfig.config);
	}

}
