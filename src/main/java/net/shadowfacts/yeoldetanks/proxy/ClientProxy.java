package net.shadowfacts.yeoldetanks.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.base.TESRBarrelBase;
import net.shadowfacts.yeoldetanks.block.cosmetic.TileEntityCosmeticBarrel;
import net.shadowfacts.yeoldetanks.block.creative.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.block.obsidian.TileEntityObsidianBarrel;
import net.shadowfacts.yeoldetanks.client.render.minecart.RenderBarrelMinecart;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void registerClientModules() {
	}

	@Override
	public void registerRenderers() {
		registerTESRs();

		RenderingRegistry.registerEntityRenderingHandler(EntityBarrelMinecart.class, RenderBarrelMinecart::new);
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new TESRBarrelBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreativeBarrel.class, new TESRBarrelBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCosmeticBarrel.class, new TESRBarrelBase());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObsidianBarrel.class, new TESRBarrelBase());
	}

}
