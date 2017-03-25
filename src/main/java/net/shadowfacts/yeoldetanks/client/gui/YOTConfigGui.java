package net.shadowfacts.yeoldetanks.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.shadowfacts.shadowmc.config.GUIConfig;
import net.shadowfacts.shadowmc.util.StringHelper;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class YOTConfigGui extends GUIConfig {

	public YOTConfigGui(GuiScreen parent) {
		super(parent, YeOldeTanks.modId, YOTConfig.config);
	}

}
