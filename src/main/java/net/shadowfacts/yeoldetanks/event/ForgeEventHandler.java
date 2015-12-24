package net.shadowfacts.yeoldetanks.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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

	private long lastTime;

	/**
	 * Renders a barrel above shadowfacts' head.
	 * This is basically copied from Ellpeck's code
	 * @see <a href="https://github.com/Ellpeck/ActuallyAdditions/blob/00766c48d8383a839fe2f4848f52112aa556ba7e/src/main/java/ellpeck/actuallyadditions/misc/special/RenderSpecial.java">Ellpeck's code</a>
	 */
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Specials.Post event) {
		EntityPlayer player = event.entityPlayer;
		Minecraft mc = Minecraft.getMinecraft();

		if (!player.isInvisible() && !player.getHideCape() && player.getUniqueID().equals(shadowfacts)) {
			int bobHeight = 70;
			long time = Minecraft.getSystemTime() / 50;

			if (time - bobHeight >= lastTime) {
				lastTime = time;
			}

			GL11.glPushMatrix();

			GL11.glTranslatef(0, -0.775f, 0);
			GL11.glRotatef(180, 1, 0, 1);
			GL11.glScalef(0.3f, 0.3f, 0.3f);

			if (time - (bobHeight / 2) >= lastTime) {
				GL11.glTranslatef(0, (time - lastTime) / 100f, 0);
			} else {
				GL11.glTranslatef(0, -(time - lastTime) / 100 + bobHeight / 100, 0);
			}

			GL11.glRotatef(time / 20, 0, 1, 0);

			GL11.glDisable(GL11.GL_LIGHTING);

			mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			RenderBlocks.getInstance().renderBlockAsItem(YeOldeTanks.blocks.barrel, 0, 1);

			GL11.glEnable(GL11.GL_LIGHTING);

			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent
	public void handleNameFormat(PlayerEvent.NameFormat event) {
		if (event.entityPlayer.getUniqueID().equals(shadowfacts)) {
			event.displayname = StringHelper.PURPLE + event.displayname + StringHelper.WHITE;
		}
	}

}
