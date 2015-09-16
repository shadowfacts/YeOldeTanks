package net.shadowfacts.yeoldetanks.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.compat.modules.CompatWaila;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowfacts
 */
public class YOTCompat {

	private static List<Class> modules = new ArrayList<>();

	public static void registerModules() {

	}

	public static void registerClientModules() {
		register(CompatWaila.class);
	}

	private static boolean register(Class clazz) {
		if (clazz.isAnnotationPresent(Compat.class)) {
			Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
			if (Loader.isModLoaded(annotation.value())) {
				modules.add(clazz);
				return true;
			} else {
				YeOldeTanks.log.info(String.format("Mod %s was not loaded, skipping compatibility module.", annotation.value()));
				return false;
			}
		} else {
			YeOldeTanks.log.error("Cannot register compatibility module class without @Compat annotation");
			return false;
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		YeOldeTanks.log.info("Attempting to run pre-initialization methods for all registered compat modules");
		modules.forEach(clazz -> {
			for (Method m : clazz.getMethods()) {
				if (m.isAnnotationPresent(Compat.PreInit.class) && Modifier.isStatic(m.getModifiers())) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						YeOldeTanks.log.error("There was an exception trying to invoke the pre-initialization method of the compatibility module for " + annotation.value());
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void init(FMLInitializationEvent event) {
		YeOldeTanks.log.info("Attempting to run initialization methods for all registered compat modules");
		modules.forEach(clazz -> {
			for (Method m : clazz.getMethods()) {
				if (m.isAnnotationPresent(Compat.Init.class) && Modifier.isStatic(m.getModifiers())) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						YeOldeTanks.log.error("There was an exception trying to invoke the initialization method of the compatibility module for " + annotation.value());
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void postInit(FMLPostInitializationEvent event) {
		YeOldeTanks.log.info("Attempting to run post-initialization methods for all registered compat modules");
		modules.forEach(clazz -> {
			for (Method m : clazz.getMethods()) {
				if (m.isAnnotationPresent(Compat.PostInit.class) && Modifier.isStatic(m.getModifiers())) {
					try {
						m.invoke(null, event);
					} catch (ReflectiveOperationException e) {
						Compat annotation = (Compat)clazz.getAnnotation(Compat.class);
						YeOldeTanks.log.error("There was an exception trying to invoke the initialization method of the compatibility module for " + annotation.value());
						e.printStackTrace();
					}
				}
			}
		});
	}

}
