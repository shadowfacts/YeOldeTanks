package net.shadowfacts.yeoldetanks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.shadowmc.compat.CompatManager;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.ModBlocks;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;
import net.shadowfacts.yeoldetanks.block.creativebarrel.TileEntityCreativeBarrel;
import net.shadowfacts.yeoldetanks.compat.CompatWaila;
import net.shadowfacts.yeoldetanks.compat.computercraft.CompatComputerCraft;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.event.ForgeEventHandler;
import net.shadowfacts.yeoldetanks.item.ModItems;
import net.shadowfacts.yeoldetanks.proxy.CommonProxy;
import net.shadowfacts.yeoldetanks.recipe.ModRecipes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
@Mod(modid = YeOldeTanks.modId, name = YeOldeTanks.name, version = YeOldeTanks.version, guiFactory = "net.shadowfacts.yeoldetanks.client.gui.YOTGuiFactory",dependencies = "required-after:shadowmc@[3.3.7,);")
public class YeOldeTanks {

	public static final String modId = "YeOldeTanks";
	public static final String name = "Ye Olde Tanks";
	public static final String version = "1.7.1";
	private static final String proxyPrefix = "net.shadowfacts.yeoldetanks.proxy.";

	public static Logger log = LogManager.getLogger(modId);

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
		public Item getTabIconItem() {
			return Item.getItemFromBlock(blocks.barrel);
		}
	};


	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());

		YOTConfig.init(event);

		blocks.init();
		items.init();

		registerTileEntities();

		ModEntities.preInit();

		ModAchievements.registerAchievements();

		compat.registerModule(CompatComputerCraft.class);
		compat.registerModule(CompatWaila.class);
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
		ModRecipes.postInit();
	}

	private void registerTileEntities() {
		YeOldeTanks.log.info("Registering tile entities");

		GameRegistry.registerTileEntity(TileEntityBarrel.class, YeOldeTanks.modId + ".tileentity.barrel");
		GameRegistry.registerTileEntity(TileEntityCreativeBarrel.class, YeOldeTanks.modId + ".tileentity.creativebarrel");
	}

}
