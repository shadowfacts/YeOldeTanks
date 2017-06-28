package net.shadowfacts.yeoldetanks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.shadowfacts.shadowmc.compat.CompatManager;
import net.shadowfacts.yeoldetanks.block.ModBlocks;
import net.shadowfacts.yeoldetanks.compat.top.CompatTOP;
import net.shadowfacts.yeoldetanks.entity.ModEntities;
import net.shadowfacts.yeoldetanks.event.ForgeEventHandler;
import net.shadowfacts.yeoldetanks.item.ModItems;
import net.shadowfacts.yeoldetanks.proxy.CommonProxy;
import net.shadowfacts.yeoldetanks.recipe.RecipeBarrelMinecart;
import net.shadowfacts.yeoldetanks.recipe.RecipeUncraftBarrelMinecart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.init.Items.WATER_BUCKET;

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

	public static CreativeTabs tab = new CreativeTabs("yot") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(blocks.barrel);
		}
	};

//	Content
	public static ModBlocks blocks = new ModBlocks();
	public static ModItems items = new ModItems();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());

		YOTConfig.init(event);

		ModEntities.preInit();

		compat.registerModule(CompatTOP.class);
		proxy.registerClientModules();

		compat.preInit(event);

		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("bucketWater", WATER_BUCKET);
		OreDictionary.registerOre("bucketWater", items.infiniteWaterBucket);
		OreDictionary.registerOre("listAllwater", items.infiniteWaterBucket);

		compat.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		compat.postInit(event);
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(
					blocks.barrel,
					blocks.creativeBarrel,
					blocks.cosmeticBarrel,
					blocks.obsidianBarrel
			);

			GameRegistry.registerTileEntity(blocks.barrel.getTileEntityClass(), blocks.barrel.getRegistryName().toString());
			GameRegistry.registerTileEntity(blocks.creativeBarrel.getTileEntityClass(), blocks.creativeBarrel.getRegistryName().toString());
			GameRegistry.registerTileEntity(blocks.cosmeticBarrel.getTileEntityClass(), blocks.cosmeticBarrel.getRegistryName().toString());
			GameRegistry.registerTileEntity(blocks.obsidianBarrel.getTileEntityClass(), blocks.obsidianBarrel.getRegistryName().toString());
		}

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					blocks.barrel.createItemBlock(),
					blocks.creativeBarrel.createItemBlock(),
					blocks.cosmeticBarrel.createItemBlock(),
					blocks.obsidianBarrel.createItemBlock(),
					items.dippingStick,
					items.infiniteWaterBucket,
					items.barrelMinecart
			);
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			blocks.barrel.initItemModel();
			blocks.creativeBarrel.initItemModel();
			blocks.cosmeticBarrel.initItemModel();
			blocks.obsidianBarrel.initItemModel();
			items.dippingStick.initItemModel();
			items.infiniteWaterBucket.initItemModel();
			items.barrelMinecart.initItemModel();
		}

		@SubscribeEvent
		public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
			event.getRegistry().registerAll(
					new RecipeBarrelMinecart(),
					new RecipeUncraftBarrelMinecart()
			);
		}

	}

}
