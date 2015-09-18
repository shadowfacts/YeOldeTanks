package net.shadowfacts.yeoldetanks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.shadowfacts.yeoldetanks.block.ModBlocks;
import net.shadowfacts.yeoldetanks.event.FMLEventHandler;
import net.shadowfacts.yeoldetanks.item.ModItems;
import net.shadowfacts.yeoldetanks.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = YeOldeTanks.modId, name = YeOldeTanks.name, guiFactory = YeOldeTanks.guiFactory)
public class YeOldeTanks {

	public static final String modId = "YeOldeTanks";
	public static final String name = "Ye Olde Tanks";
	private static final String proxyPrefix = "net.shadowfacts.yeoldetanks.proxy.";
	public static final String guiFactory = "net.shadowfacts.yeoldetanks.client.gui.YOTGuiFactory";

	public static Logger log = LogManager.getLogger(modId);

	@SidedProxy(serverSide = proxyPrefix + "CommonProxy", clientSide = proxyPrefix + "ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(modId)
	public static YeOldeTanks instance;

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
		FMLCommonHandler.instance().bus().register(new FMLEventHandler());
		YOTConfig.init(event);

		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
