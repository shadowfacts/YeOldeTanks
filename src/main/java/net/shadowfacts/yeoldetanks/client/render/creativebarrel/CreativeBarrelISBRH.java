package net.shadowfacts.yeoldetanks.client.render.creativebarrel;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class CreativeBarrelISBRH implements ISimpleBlockRenderingHandler {

	public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

	private static final TileEntityCreativeBarrel te = new TileEntityCreativeBarrel();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();

		GL11.glTranslatef(0, -.2f, 0);

		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return 0;
	}
}
