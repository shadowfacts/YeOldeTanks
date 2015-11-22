package net.shadowfacts.yeoldetanks.compat.modules;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.shadowfacts.yeoldetanks.compat.Compat;

/**
 * @author shadowfacts
 */
@Compat("ThermalExpansion")
public class CompatThermalExpansion {

//	The automatic recipe registration doesn't work because friggin' Thermal Expansion registers its recipes post-init
	@Compat.Init
	public static void init(FMLInitializationEvent event) {
		ItemStack sawdust = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 512);
		ItemStack slag = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 514);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.paper, 2), " S ", "S#S", " S ", 'S', sawdust, '#', "bucketWater"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.clay_ball, 4), slag, slag, Blocks.dirt, "bucketWater"));
	}

}
