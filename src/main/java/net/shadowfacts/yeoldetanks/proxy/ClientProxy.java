package net.shadowfacts.yeoldetanks.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
	public void init(FMLInitializationEvent event) {
		super.init(event);

		YeOldeTanks.items.initModels();
		YeOldeTanks.blocks.initModels();
	}

	public static void registerInvModel(Block block, int meta, String id) {
		registerInvModel(Item.getItemFromBlock(block), meta, id);
	}

	public static void registerInvModel(Item item, int meta, String id) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("yeoldetanks:" + id, "inventory"));
	}

	@Override
	protected void registerClientModules() {
		YeOldeTanks.compat.registerModule(CompatWaila.class);
	}

	@Override
	public void registerRenderers() {
		registerTESRs();
		registerISBRHs();

		RenderingRegistry.registerEntityRenderingHandler(EntityBarrelMinecart.class, RenderBarrelMinecart::new);
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBarrel.class, new BarrelTESR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreativeBarrel.class, new CreativeBarrelTESR());
	}

	private void registerISBRHs() {
//		RenderingRegistry.registerBlockHandler(BarrelISBRH.RENDER_ID, new BarrelISBRH());
//		RenderingRegistry.registerBlockHandler(CreativeBarrelISBRH.RENDER_ID, new CreativeBarrelISBRH());
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}
