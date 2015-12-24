package net.shadowfacts.yeoldetanks.client.render;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadowfacts
 */
public class RenderUtils {

	private static final Map<Fluid, ResourceLocation> fluidTextureCache = new HashMap<>();

	public static ResourceLocation getTexture(Fluid fluid) {
		if (fluidTextureCache.containsKey(fluid)) {
			return fluidTextureCache.get(fluid);
		}

		IIcon icon = fluid.getStillIcon();

		if (icon != null) {

			String domain = "minecraft";
			String name;

			if (icon.getIconName().contains(":")) {
				domain = icon.getIconName().split(":")[0].toLowerCase();
				name = icon.getIconName().split(":")[1];
			} else {
				name = icon.getIconName();
			}

			return fluidTextureCache.put(fluid, new ResourceLocation(domain, "textures/blocks/" + name + ".png"));
		}

		return null;
	}

}
