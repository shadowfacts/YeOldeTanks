package net.shadowfacts.yeoldetanks.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelISBRH;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelTESR;
import net.shadowfacts.yeoldetanks.client.render.creativebarrel.CreativeBarrelISBRH;
import net.shadowfacts.yeoldetanks.client.render.creativebarrel.CreativeBarrelTESR;
import net.shadowfacts.yeoldetanks.client.render.minecart.RenderBarrelMinecart;
import net.shadowfacts.yeoldetanks.compat.ModCompat;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;
import net.shadowfacts.yeoldetanks.network.PacketUpdateTE;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	protected void registerClientModules() {
		ModCompat.registerClientModules();
	}

	@Override
	protected void registerPackets(SimpleNetworkWrapper network) {
		super.registerPackets(network);
		network.registerMessage(PacketUpdateTE.Handler.class, PacketUpdateTE.class, 0, Side.CLIENT);
	}

	@Override
	public void registerRenderers() {
		registerTESRs();
		registerISBRHs();

		RenderingRegistry.registerEntityRenderingHandler(EntityBarrelMinecart.class, new RenderBarrelMinecart());
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreativeBarrel.class, new CreativeBarrelTESR());
	}

	private void registerISBRHs() {
		RenderingRegistry.registerBlockHandler(BarrelISBRH.RENDER_ID, new BarrelISBRH());
		RenderingRegistry.registerBlockHandler(CreativeBarrelISBRH.RENDER_ID, new CreativeBarrelISBRH());
	}
}
