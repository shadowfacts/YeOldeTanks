package net.shadowfacts.yeoldetanks.client.render.minecart;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.shadowfacts.shadowmc.util.RenderHelper;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class RenderBarrelMinecart extends RenderMinecart<EntityBarrelMinecart> {

	public RenderBarrelMinecart(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected void renderCartContents(EntityBarrelMinecart minecart, float partialTicks, IBlockState state) {
		super.renderCartContents(minecart, partialTicks, state);

		if (YOTConfig.renderFluid && minecart.tank.getFluid() != null && minecart.tank.getFluidAmount() > 0) {
			FluidStack fluid = minecart.tank.getFluid();
			ResourceLocation loc = fluid.getFluid().getStill(minecart.tank.getFluid());
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

			GlStateManager.translate(0, 0, -1);

			VertexBuffer buf = Tessellator.getInstance().getBuffer();
			buf.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			int color = fluid.getFluid().getColor(fluid);
			int brightness = Minecraft.getMinecraft().world.getCombinedLight(minecart.getPosition(), fluid.getFluid().getLuminosity(fluid));

			double fluidPercent = (double)fluid.amount / minecart.tank.getCapacity();
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
