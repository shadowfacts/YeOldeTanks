package net.shadowfacts.yeoldetanks.client.render.barrel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelFluid;
import net.shadowfacts.yeoldetanks.client.render.RenderUtils;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class BarrelTESR extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation("yeoldetanks", "textures/model/barrel.png");
	private ModelBarrel model = new ModelBarrel();
	private ModelFluid modelFluid = new ModelFluid();



	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float delta) {
		TileEntityBarrel barrel = (TileEntityBarrel)te;

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + .5f, (float) y + 1.5f, (float) z + .5f);
		GL11.glRotatef(180, 1, 0, 0);

		bindTexture(texture);
		model.renderAll(barrel.lid);

		if (YOTConfig.renderFluid && barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
			ResourceLocation fluidTexture = RenderUtils.getTexture(barrel.tank.getFluid().getFluid());

			if (fluidTexture != null) {
				GL11.glPushMatrix();

				float fluidPercent = -(float) barrel.tank.getFluidAmount() / barrel.tank.getCapacity();
				GL11.glTranslatef(0, fluidPercent * .9f, 0);

				bindTexture(fluidTexture);
				modelFluid.renderAll();

				GL11.glPopMatrix();
			}
		}

		GL11.glPopMatrix();
	}

}
