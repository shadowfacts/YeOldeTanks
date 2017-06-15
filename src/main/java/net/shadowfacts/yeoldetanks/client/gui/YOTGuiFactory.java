package net.shadowfacts.yeoldetanks.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

/**
 * @author shadowfacts
 */
public class YOTGuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new YOTConfigGui(parentScreen);
	}

	@Override
	@Deprecated
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return YOTConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	@Deprecated
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

}
