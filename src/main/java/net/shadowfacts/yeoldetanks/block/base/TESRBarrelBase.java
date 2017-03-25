package net.shadowfacts.yeoldetanks.block.base;

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
public class TESRBarrelBase extends TileEntitySpecialRenderer<TileEntityBarrelBase> {

	private ModelFluid modelFluid = new ModelFluid();

	@Override
	public void renderTileEntityAt(TileEntityBarrelBase barrel, double x, double y, double z, float partialTicks, int destroyStage) {
		if (YOTConfig.renderFluid && barrel.getTank().getFluid() != null) {
			ResourceLocation fluidTexture = RenderUtils.getTexture(barrel.getTank().getFluid().getFluid());

			if (fluidTexture != null) {
				GlStateManager.pushMatrix();

				GlStateManager.translate((float)x + .5f, (float)y + 1.5f, (float)z + .5f);
				GlStateManager.rotate(180, 1, 0, 0);

				float fluidPercent;
				if (barrel.isCreative()) {
					fluidPercent = 1;
				} else {
					fluidPercent = (float)barrel.getTank().getFluidAmount() / barrel.getTank().getCapacity();
				}
				if (YOTConfig.blameEllpeck) fluidPercent *= -1;
				GlStateManager.translate(0, fluidPercent * -0.9f, 0);

				bindTexture(fluidTexture);
				modelFluid.renderAll();

				GlStateManager.popMatrix();
			}
		}
	}

}
