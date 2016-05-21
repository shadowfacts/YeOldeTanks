package net.shadowfacts.yeoldetanks.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author shadowfacts
 */
public class CommonProxy {

	public void registerClientModules() {}

	public void registerRenderers() {}

	public void registerInvModel(Block block, int meta, String id) {
		registerInvModel(Item.getItemFromBlock(block), meta, id);
	}

	public void registerInvModel(Item item, int meta, String id) {

	}

}
