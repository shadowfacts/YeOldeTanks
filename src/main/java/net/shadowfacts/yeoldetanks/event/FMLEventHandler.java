package net.shadowfacts.yeoldetanks.event;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
