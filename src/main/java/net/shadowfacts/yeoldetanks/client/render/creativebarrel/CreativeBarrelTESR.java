package net.shadowfacts.yeoldetanks.client.render.creativebarrel;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelFluid;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class CreativeBarrelTESR extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation("yeoldetanks", "textures/model/barrel.png");
	private ModelBarrel model = new ModelBarrel();
	private ModelFluid modelFluid = new ModelFluid();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float delta) {
		TileEntityCreativeBarrel barrel = (TileEntityCreativeBarrel)te;

		GL11.glPushMatrix();

		GL11.glTranslatef((float)x + .5f, (float)y + 1.5f, (float)z + .5f);
		GL11.glRotatef(180, 1, 0, 0);

		bindTexture(texture);
		model.renderAll(barrel.lid);

		if (YOTConfig.renderFluid && barrel.tank.getFluid() != null) {
			IIcon fluidTexture = barrel.tank.getFluid().getFluid().getStillIcon();

			String domain = "minecraft";
			String name;
			if (fluidTexture.getIconName().contains(":")) {
				domain = fluidTexture.getIconName().split(":")[0];
				name = fluidTexture.getIconName().split(":")[1];
			} else {
				name = fluidTexture.getIconName();
			}

			ResourceLocation fluidLocation = new ResourceLocation(domain, "textures/blocks/" + name + ".png");

			GL11.glPushMatrix();

			GL11.glTranslatef(0, .9f, 0);

			bindTexture(fluidLocation);
			modelFluid.renderAll();

			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
	}

}
