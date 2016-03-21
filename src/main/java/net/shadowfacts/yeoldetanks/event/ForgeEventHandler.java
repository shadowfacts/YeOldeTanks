package net.shadowfacts.yeoldetanks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.shadowfacts.shadowmc.config.ConfigManager;
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

		if (!player.isInvisible() &&
				player.getUniqueID().equals(shadowfacts)) {

			float size = 0.3F;

			double bobHeight = 70;
			double theTime = Minecraft.getSystemTime();
			double time = theTime / 50;

			if (time - bobHeight >= lastTime) {
				this.lastTime = time;
			}

			GL11.glPushMatrix();
			GL11.glTranslated(0D, -0.775D, 0D);
			GL11.glRotatef(180F, 1.0F, 0.0F, 1.0F);
			GL11.glScalef(size, size, size);

			if (time - (bobHeight / 2) >= lastTime) {
				GL11.glTranslated(0, (time - lastTime) / 100, 0);
			} else {
				GL11.glTranslated(0, -(time - lastTime) / 100 + bobHeight / 100, 0);
			}

			GL11.glRotated(theTime / 20, 0, 1, 0);
			GL11.glTranslatef(-0.5f, 0, -0.5f);

			GL11.glDisable(GL11.GL_LIGHTING);
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
//			RenderBlocks.getInstance().renderBlockAsItem(YeOldeTanks.blocks.barrel, 0, 1F);
//			TODO: fix this
			GL11.glEnable(GL11.GL_LIGHTING);

			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void handleNameFormat(PlayerEvent.NameFormat event) {
		if (event.entityPlayer.getUniqueID().equals(shadowfacts)) {
			event.displayname = EnumChatFormatting.DARK_PURPLE + event.displayname + EnumChatFormatting.RESET;
		}
	}

	@SubscribeEvent
	public void onCraft(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
		Item item = event.crafting.getItem();
//		if (item instanceof AchievementProvider) {
//			((AchievementProvider)item).grantAchievement(event.player);
//		} else if (item instanceof ItemBlock) {
//			Block block = ((ItemBlock)item).field_150939_a;
//			if (block instanceof AchievementProvider) {
//				((AchievementProvider)block).grantAchievement(event.player);
//			}
//		}

//		if (item == YeOldeTanks.items.dippingStick) {
//			ItemStack barrel = null;
//			for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
//				ItemStack stack = event.craftMatrix.getStackInSlot(i);
//				if (stack != null && stack.getItem() == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
//					barrel = stack;
//				}
//			}
//			if (barrel != null) {
//				event.player.inventory.addItemStackToInventory(barrel);
//			}
////		} else if (item == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
//			boolean hasCart = false;
//
//			for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
//				ItemStack stack = event.craftMatrix.getStackInSlot(i);
//				if (stack != null && stack.getItem() == YeOldeTanks.items.barrelMinecart) {
//					hasCart = true;
//					break;
//				}
//			}
//
//			if (hasCart) {
//				event.player.inventory.addItemStackToInventory(new ItemStack(Items.minecart));
//			}
//		}
	}

//	@SubscribeEvent
//	public void onSmelt(PlayerEvent.ItemSmeltedEvent event) {
//		Item item = event.smelting.getItem();
//		if (item instanceof AchievementProvider) {
//			((AchievementProvider)item).grantAchievement(event.player);
//		} else if (item instanceof ItemBlock) {
//			Block block = ((ItemBlock)item).field_150939_a;
//			if (block instanceof AchievementProvider) {
//				((AchievementProvider)block).grantAchievement(event.player);
//			}
//		}
//	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(YeOldeTanks.modId)) {
			ConfigManager.instance.load(YeOldeTanks.modId);
		}
	}

}
