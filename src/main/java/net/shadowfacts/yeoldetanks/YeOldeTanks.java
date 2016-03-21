package net.shadowfacts.yeoldetanks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.shadowmc.compat.CompatManager;
import net.shadowfacts.yeoldetanks.block.ModBlocks;
import net.shadowfacts.yeoldetanks.item.ModItems;
import net.shadowfacts.yeoldetanks.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author shadowfacts
 */
@Mod(modid = YeOldeTanks.modId, name = YeOldeTanks.name, version = YeOldeTanks.version, guiFactory = YeOldeTanks.guiFactory)
public class YeOldeTanks {

	public static final String modId = "YeOldeTanks";
	public static final String name = "Ye Olde Tanks";
	public static final String version = "1.2.0";
	private static final String proxyPrefix = "net.shadowfacts.yeoldetanks.proxy.";
	public static final String guiFactory = "net.shadowfacts.yeoldetanks.client.gui.YOTGuiFactory";

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
