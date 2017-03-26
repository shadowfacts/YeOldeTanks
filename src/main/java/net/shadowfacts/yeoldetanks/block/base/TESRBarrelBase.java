package net.shadowfacts.yeoldetanks.block.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.util.RenderHelper;
import net.shadowfacts.yeoldetanks.YOTConfig;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class TESRBarrelBase extends TileEntitySpecialRenderer<TileEntityBarrelBase> {

	@Override
	public void renderTileEntityAt(TileEntityBarrelBase barrel, double x, double y, double z, float partialTicks, int destroyStage) {
		if (YOTConfig.renderFluid && barrel.getTank().getFluid() != null && barrel.getTank().getFluidAmount() > 0) {
			FluidStack fluid = barrel.getTank().getFluid();
			ResourceLocation loc = fluid.getFluid().getStill(barrel.getTank().getFluid());
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(loc.toString());

			GlStateManager.pushMatrix();

			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			if (Minecraft.isAmbientOcclusionEnabled()) {
				GL11.glShadeModel(GL11.GL_SMOOTH);
			} else {
				GL11.glShadeModel(GL11.GL_FLAT);
			}

			GlStateManager.translate(x, y, z);

			VertexBuffer buf = Tessellator.getInstance().getBuffer();
			buf.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			int color = fluid.getFluid().getColor(fluid);
			int brightness = Minecraft.getMinecraft().world.getCombinedLight(barrel.getPos(), fluid.getFluid().getLuminosity(fluid));

			double fluidPercent = barrel.isCreative() ? 1 : (double)fluid.amount / barrel.getTank().getCapacity();
			double fluidY = fluidPercent * 29/32d + 1/16d;

			// Center
			RenderHelper.putTexturedQuad(buf, sprite, 2/16d, fluidY, 2/16d, 12/16d, 0, 12/16d, EnumFacing.UP, color, brightness);
			// Top
			RenderHelper.putTexturedQuad(buf, sprite, 3/16d, fluidY, 1/16d, 10/16d, 0, 1/16d, EnumFacing.UP, color, brightness);
			// Bottom
			RenderHelper.putTexturedQuad(buf, sprite, 3/16d, fluidY, 14/16d, 10/16d, 0, 1/16d, EnumFacing.UP, color, brightness);
			// Left
			RenderHelper.putTexturedQuad(buf, sprite, 1/16d, fluidY, 3/16d, 1/16d, 0, 10/16d, EnumFacing.UP, color, brightness);
			// Right
			RenderHelper.putTexturedQuad(buf, sprite, 14/16d, fluidY, 3/16d, 1/16d, 0, 10/16d, EnumFacing.UP, color, brightness);

			Tessellator.getInstance().draw();

			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

}
