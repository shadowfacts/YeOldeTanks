package net.shadowfacts.yeoldetanks.client.render.minecart;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.client.model.ModelFluid;
import net.shadowfacts.yeoldetanks.client.render.RenderUtils;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class RenderBarrelMinecart extends RenderMinecart<EntityBarrelMinecart> {

	private ModelFluid modelFluid = new ModelFluid();

	public RenderBarrelMinecart(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected void renderCartContents(EntityBarrelMinecart minecart, float partialTicks, IBlockState state) {
		super.renderCartContents(minecart, partialTicks, state);

		if (YOTConfig.renderFluid && minecart.tank.getFluid() != null && minecart.tank.getFluidAmount() > 0) {
			ResourceLocation fluidTexture = RenderUtils.getTexture(minecart.tank.getFluid().getFluid());

			if (fluidTexture != null) {

				GlStateManager.pushMatrix();

				GlStateManager.translate(0.5f, 1.5f, -0.5f);
				GlStateManager.rotate(180, 1, 0, 0);

				float fluidPercent = -(float)minecart.tank.getFluidAmount() / minecart.tank.getCapacity();
				GlStateManager.translate(0, fluidPercent * 0.9f, 0);


				bindTexture(fluidTexture);
				modelFluid.renderAll();

				GlStateManager.popMatrix();
			}
		}
	}

}
