package net.shadowfacts.yeoldetanks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.shadowmc.compat.CompatManager;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.ModBlocks;
import net.shadowfacts.yeoldetanks.compat.computercraft.CompatComputerCraft;
import net.shadowfacts.yeoldetanks.compat.top.CompatTOP;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.event.ForgeEventHandler;
import net.shadowfacts.yeoldetanks.item.ModItems;
import net.shadowfacts.yeoldetanks.proxy.CommonProxy;
import net.shadowfacts.yeoldetanks.recipe.ModRecipes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = YeOldeTanks.modId, name = YeOldeTanks.name, version = YeOldeTanks.version, guiFactory = "net.shadowfacts.yeoldetanks.client.gui.YOTGuiFactory", dependencies = "required-after:shadowmc@[3.4.1,);", updateJSON = "https://update.shadowfacts.net/ye-olde-tanks")
public class YeOldeTanks {

	public static final String modId = "yeoldetanks";
	public static final String name = "Ye Olde Tanks";
	public static final String version = "@VERSION@";
	private static final String proxyPrefix = "net.shadowfacts.yeoldetanks.proxy.";

	public static Logger log = LogManager.getFormatterLogger(modId);

	@SidedProxy(serverSide = proxyPrefix + "CommonProxy", clientSide = proxyPrefix + "ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static YeOldeTanks instance;

	public static CompatManager compat = new CompatManager(modId);

//	Content
	public static ModBlocks blocks = new ModBlocks();
	public static ModItems items = new ModItems();

	public static CreativeTabs tab = new CreativeTabs("yot") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(blocks.barrel);
		}
	};


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());

		YOTConfig.init(event);

		blocks.init();
		items.init();

		ModEntities.preInit();

		ModAchievements.registerAchievements();

		compat.registerModule(CompatComputerCraft.class);
		compat.registerModule(CompatTOP.class);
		proxy.registerClientModules();

		compat.preInit(event);

		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		compat.init(event);
		ModRecipes.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		compat.postInit(event);
	}

}
