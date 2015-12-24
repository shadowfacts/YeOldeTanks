package net.shadowfacts.yeoldetanks.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.shadowfacts.shadowmc.util.StringHelper;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import org.lwjgl.opengl.GL11;

import java.util.UUID;

/**
 * @author shadowfacts
 */
public class ForgeEventHandler {

	public static final UUID shadowfacts = UUID.fromString("1854f5e6-0b21-4817-9284-6219f733790c");

	private double lastTime;

	/**
	 * Renders a barrel above shadowfacts' head.
	 * This is basically copied from Ellpeck's code
	 * @see <a href="https://github.com/Ellpeck/ActuallyAdditions/blob/00766c48d8383a839fe2f4848f52112aa556ba7e/src/main/java/ellpeck/actuallyadditions/misc/special/RenderSpecial.java">Ellpeck's code</a>
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderPlayer(RenderPlayerEvent.Specials.Post event) {
		EntityPlayer player = event.entityPlayer;

		if(player.isInvisible() || player.getHideCape()){
			return;
		}

		float size = 0.3F;

		double bobHeight = 70;
		double theTime = Minecraft.getSystemTime();
		double time = theTime/50;

		if(time-bobHeight >= lastTime){
			this.lastTime = time;
		}

		GL11.glPushMatrix();
		GL11.glTranslated(0D, -0.775D, 0D);
		GL11.glRotatef(180F, 1.0F, 0.0F, 1.0F);
		GL11.glScalef(size, size, size);

		if(time - (bobHeight / 2) >= lastTime){
			GL11.glTranslated(0, (time - lastTime)/100, 0);
		} else {
			GL11.glTranslated(0, -(time - lastTime) / 100 + bobHeight / 100, 0);
		}

		GL11.glRotated(theTime / 20, 0, 1, 0);
		GL11.glTranslatef(-0.5f, 0, -0.5f);

		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		RenderBlocks.getInstance().renderBlockAsItem(YeOldeTanks.blocks.barrel, 0, 1F);
		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glPopMatrix();
	}

	@SubscribeEvent
	public void handleNameFormat(PlayerEvent.NameFormat event) {
		if (event.entityPlayer.getUniqueID().equals(shadowfacts)) {
			event.displayname = StringHelper.PURPLE + event.displayname + StringHelper.WHITE;
		}
	}

}
