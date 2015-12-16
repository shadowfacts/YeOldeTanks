package net.shadowfacts.yeoldetanks.client.render.minecart;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.yeoldetanks.YOTConfig;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelBarrel;
import net.shadowfacts.yeoldetanks.client.model.ModelFluid;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class RenderBarrelMinecart extends RenderMinecart {

	private static final ResourceLocation texture = new ResourceLocation("yeoldetanks", "textures/model/barrel.png");
	private ModelBarrel model = new ModelBarrel();
	private ModelFluid modelFluid = new ModelFluid();

	@Override
	protected void func_147910_a(EntityMinecart entity, float f, Block block, int i) {
		EntityBarrelMinecart barrel = (EntityBarrelMinecart)entity;

		GL11.glPushMatrix();

		GL11.glTranslatef(0, .85f, 0);

		GL11.glRotatef(180, 1, 0, 0);

		bindTexture(texture);
		model.renderAll(false);

		if (YOTConfig.renderFluid && barrel.tank.getFluid() != null && barrel.tank.getFluidAmount() > 0) {
			IIcon fluidTexture = barrel.tank.getFluid().getFluid().getStillIcon();

			String domain = "minecraft";
			String name;
			if (fluidTexture.getIconName().contains(":")) {
				domain = fluidTexture.getIconName().split(":")[0].toLowerCase();
				name = fluidTexture.getIconName().split(":")[1];
			} else {
				name = fluidTexture.getIconName();
			}

			ResourceLocation fluidLocation = new ResourceLocation(domain, "textures/blocks/" + name + ".png");

			GL11.glPushMatrix();

			float fluidPercent = -(float) barrel.tank.getFluidAmount() / barrel.tank.getCapacity();
			GL11.glTranslatef(0, fluidPercent * .9f, 0);


			bindTexture(fluidLocation);
			modelFluid.renderAll();

			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
	}
}
