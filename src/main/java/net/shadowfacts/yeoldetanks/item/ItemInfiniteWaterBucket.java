package net.shadowfacts.yeoldetanks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author shadowfacts
 */
public class ItemInfiniteWaterBucket extends ItemBucket{

	public ItemInfiniteWaterBucket() {
		super(Blocks.water);
		setUnlocalizedName("yot.infiniteWaterBucket");
		setTextureName("yeoldetanks:infinitewater");
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);
			if (mop == null) return stack;

			ForgeDirection dir = ForgeDirection.getOrientation(mop.sideHit);
			int placeX = mop.blockX + dir.offsetX;
			int placeY = mop.blockY + dir.offsetY;
			int placeZ = mop.blockZ + dir.offsetZ;
			if (world.getBlock(placeX, placeY, placeZ).getMaterial().isReplaceable()) {
				world.setBlock(placeX, placeY, placeZ, Blocks.flowing_water, 0, 2);
			}
		}

		player.swingItem();
		return stack;
	}
}
