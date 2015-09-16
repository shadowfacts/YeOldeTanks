package net.shadowfacts.yeoldetanks.client.gui;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.shadowmc.util.StringHelper;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class YOTConfigGui extends GuiConfig {

	public YOTConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), YeOldeTanks.modId, false, false, "YeOldeTanks Configuration");
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<>();

		list.add(getCategory("general", StringHelper.localize("yot.config.ctgy.general"), ""));

		return list;
	}

	@SuppressWarnings("unchecked")
	private static IConfigElement getCategory(String category, String name, String tooltipKey) {
		IConfigElement configElement = new ConfigElement(ConfigManager.instance.getConfigurationObject(YeOldeTanks.modId).getCategory(category));
		return new DummyConfigElement.DummyCategoryElement(name, tooltipKey, configElement.getChildElements());
	}

}
