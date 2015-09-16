package net.shadowfacts.yeoldetanks.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelISBRH;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelTESR;
import net.shadowfacts.yeoldetanks.compat.YOTCompat;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	protected void registerClientModules() {
		YOTCompat.registerClientModules();
	}

	@Override
	public void registerRenderers() {
		registerTESRs();
		registerISBRHs();
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelTESR());
	}

	private void registerISBRHs() {
		RenderingRegistry.registerBlockHandler(BarrelISBRH.RENDER_ID, new BarrelISBRH());
	}
}
