package net.shadowfacts.yeoldetanks.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.shadowfacts.shadowmc.config.ConfigManager;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;

/**
 * @author shadowfacts
 */
public class FMLEventHandler {

	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event) {
		Item item = event.crafting.getItem();
		if (item instanceof AchievementProvider) {
			event.player.addStat(((AchievementProvider)item).getAchievement(), 1);
		} else if (item instanceof ItemBlock) {
			Block block = ((ItemBlock)item).field_150939_a;
			if (block instanceof AchievementProvider) {
				event.player.addStat(((AchievementProvider)block).getAchievement(), 1);
			}
		}

		if (item == YeOldeTanks.items.dippingStick) {
			ItemStack barrel = null;
			for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
				ItemStack stack = event.craftMatrix.getStackInSlot(i);
				if (stack != null && stack.getItem() == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
					barrel = stack;
				}
			}
			if (barrel != null) {
				event.player.inventory.addItemStackToInventory(barrel);
			}
		} else if (item == Item.getItemFromBlock(YeOldeTanks.blocks.barrel)) {
			boolean hasCart = false;

			for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
				ItemStack stack = event.craftMatrix.getStackInSlot(i);
				if (stack != null && stack.getItem() == YeOldeTanks.items.barrelMinecart) {
					hasCart = true;
					break;
				}
			}

			if (hasCart) {
				event.player.inventory.addItemStackToInventory(new ItemStack(Items.minecart));
			}
		}
	}

	@SubscribeEvent
	public void onSmelt(PlayerEvent.ItemSmeltedEvent event) {
		Item item = event.smelting.getItem();
		if (item instanceof AchievementProvider) {
			event.player.addStat(((AchievementProvider)item).getAchievement(), 1);
		} else if (item instanceof ItemBlock) {
			Block block = ((ItemBlock)item).field_150939_a;
			if (block instanceof AchievementProvider) {
				event.player.addStat(((AchievementProvider)block).getAchievement(), 1);
			}
		}
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(YeOldeTanks.modId)) {
			ConfigManager.instance.load(YeOldeTanks.modId);
		}
	}

}
