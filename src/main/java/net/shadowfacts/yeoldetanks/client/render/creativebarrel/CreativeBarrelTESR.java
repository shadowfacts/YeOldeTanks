package net.shadowfacts.yeoldetanks.client.render.creativebarrel;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelFluid;
import net.shadowfacts.yeoldetanks.client.render.RenderUtils;

/**
 * @author shadowfacts
 */
public class CreativeBarrelTESR extends TileEntitySpecialRenderer {

	private ModelFluid modelFluid = new ModelFluid();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		TileEntityCreativeBarrel barrel = (TileEntityCreativeBarrel)te;

		if (YOTConfig.renderFluid && barrel.tank.getFluid() != null) {
			ResourceLocation fluidTexture = RenderUtils.getTexture(barrel.tank.getFluid().getFluid());

			if (fluidTexture != null) {
				GlStateManager.pushMatrix();

				GlStateManager.translate((float)x + .5f, (float)y + 1.5f, (float)z + .5f);
				GlStateManager.rotate(180, 1, 0, 0);

				GlStateManager.translate(0, YOTConfig.blameEllpeck ? .9f : -.9f, 0);

				bindTexture(fluidTexture);
				modelFluid.renderAll();

				GlStateManager.popMatrix();
			}
		}
	}

}
