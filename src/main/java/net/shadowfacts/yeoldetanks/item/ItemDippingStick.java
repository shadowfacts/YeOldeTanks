package net.shadowfacts.yeoldetanks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;

/**
 * @author shadowfacts
 */
public class ItemDippingStick extends Item {

	public ItemDippingStick() {
		setUnlocalizedName("yot.dippingstick");
		setTextureName("dippingstick");
		setCreativeTab(YeOldeTanks.tab);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel)te;

			if (barrel.tank.getFluid() != null) {
				player.addChatComponentMessage(new ChatComponentText("Fluid: " + barrel.tank.getFluid().getLocalizedName()));
				player.addChatComponentMessage(new ChatComponentText(barrel.tank.getFluidAmount() + "mb / " + barrel.tank.getCapacity() + "mb"));
			} else {
				player.addChatComponentMessage(new ChatComponentText("Empty"));
			}


			return true;
		}

		return false;
	}
}
