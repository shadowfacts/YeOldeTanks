package net.shadowfacts.yeoldetanks.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.client.render.barrel.BarrelTESR;
import net.shadowfacts.yeoldetanks.client.render.creativebarrel.CreativeBarrelTESR;
import net.shadowfacts.yeoldetanks.client.render.minecart.RenderBarrelMinecart;
import net.shadowfacts.yeoldetanks.compat.CompatWaila;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void registerInvModel(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("yeoldetanks:" + id, "inventory"));
	}

	@Override
	public void registerClientModules() {
		YeOldeTanks.compat.registerModule(CompatWaila.class);
	}

	@Override
	public void registerRenderers() {
		registerTESRs();

		RenderingRegistry.registerEntityRenderingHandler(EntityBarrelMinecart.class, RenderBarrelMinecart::new);
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreativeBarrel.class, new CreativeBarrelTESR());
	}

}
