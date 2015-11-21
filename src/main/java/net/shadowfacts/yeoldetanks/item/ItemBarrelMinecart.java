package net.shadowfacts.yeoldetanks.item;


import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.AchievementProvider;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.entity.barrelminecart.EntityBarrelMinecart;

/**
 * @author shadowfacts
 */
public class ItemBarrelMinecart extends Item implements AchievementProvider {

	public ItemBarrelMinecart() {
		setMaxStackSize(1);
		setUnlocalizedName("yot.barrelminecart");
		setTextureName("yeoldetanks:barrelMinecart");
		setCreativeTab(YeOldeTanks.tab);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y, z) instanceof BlockRailBase) {
			if (!world.isRemote) {
				EntityMinecart minecart = new EntityBarrelMinecart(world, x + .5, y + .5, z + .5);

				if (stack.hasDisplayName()) minecart.setMinecartName(stack.getDisplayName());

				world.spawnEntityInWorld(minecart);
			}

			stack.stackSize--;
			return true;
		}

		return false;
	}

	@Override
	public Achievement getAchievement() {
		return ModAchievements.craftBarrelCart;
	}
}
