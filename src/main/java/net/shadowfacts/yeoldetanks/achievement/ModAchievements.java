package net.shadowfacts.yeoldetanks.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.shadowfacts.yeoldetanks.YeOldeTanks;

/**
 * @author shadowfacts
 */
public class ModAchievements {

	public static AchievementPage page;

	public static Achievement craftBarrel;
	public static Achievement craftDippingStick;
	public static Achievement craftBarrelCart;
	public static Achievement craftInfiniteWaterBucket;
	public static Achievement craftCreativeBarrel;

	public static void registerAchievements() {
		YeOldeTanks.log.info("Registering achievements");

		craftBarrel = new Achievement("yot.craftBarrel", "yot.craftBarrel", 0, 0, YeOldeTanks.blocks.barrel, null)
				.initIndependentStat().registerStat();

		craftDippingStick = new Achievement("yot.craftDippingStick", "yot.craftDippingStick", 2, -1, YeOldeTanks.items.dippingStick, craftBarrel)
				.registerStat();

		craftBarrelCart = new Achievement("yot.craftBarrelCart", "yot.craftBarrelCart", 2, 1, YeOldeTanks.items.barrelMinecart, craftBarrel)
				.registerStat();

		craftInfiniteWaterBucket = new Achievement("yot.craftInfiniteWaterBucket", "yot.craftInfiniteWaterBucket", -2, 0, YeOldeTanks.items.infiniteWaterBucket, null)
				.initIndependentStat().registerStat();

		craftCreativeBarrel = new Achievement("yot.craftCreativeBarrel", "yot.craftCreativeBarrel", 0, -10, YeOldeTanks.blocks.creativeBarrel, null)
				.initIndependentStat().registerStat();


		page = new AchievementPage("Ye Olde Tanks", craftBarrel, craftDippingStick, craftBarrelCart, craftInfiniteWaterBucket, craftCreativeBarrel);
		AchievementPage.registerAchievementPage(page);
	}

}
