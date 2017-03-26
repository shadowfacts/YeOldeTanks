package net.shadowfacts.yeoldetanks.compat.top;

import com.google.common.base.Function;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.shadowfacts.shadowmc.compat.Compat;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
@Compat("theoneprobe")
public class CompatTOP {

	@Compat.Init
	public static void init(FMLInitializationEvent event) {
		FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "net.shadowfacts.yeoldetanks.compat.top.CompatTOP$Handler");
	}

	public static class Handler implements Function<ITheOneProbe, Void> {

		@Nullable
		@Override
		public Void apply(ITheOneProbe top) {
			top.registerEntityProvider(new EntityBarrelMinecartProvider());
			return null;
		}

	}

}
