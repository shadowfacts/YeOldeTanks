package net.shadowfacts.yeoldetanks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.shadowfacts.shadowmc.ShadowMC;
import net.shadowfacts.shadowmc.achievement.AchievementProvider;
import net.shadowfacts.shadowmc.item.ItemBase;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.util.YOTBarrel;

import java.text.NumberFormat;

/**
 * @author shadowfacts
 */
public class ItemDippingStick extends ItemBase implements AchievementProvider {

	private static final int ID = 5490;
	private static final NumberFormat FORMAT = NumberFormat.getInstance();

	public ItemDippingStick() {
		super("dipping_stick");
		setCreativeTab(YeOldeTanks.tab);
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);

			if (te instanceof YOTBarrel) {
				handleBarrel(player, (YOTBarrel)te);
				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public Achievement getAchievement(ItemStack stack) {
		return ModAchievements.craftDippingStick;
	}

	public static void handleBarrel(EntityPlayer player, YOTBarrel barrel) {
		if (barrel.getTank().getFluid() != null) {
			String capacity = barrel.isCreative() ? "∞" : FORMAT.format(barrel.getTank().getCapacity());

			sendSpamlessMessage(player, new TextComponentTranslation("item.dipping_stick.fluid", barrel.getTank().getFluid().getLocalizedName()));
			sendSpamlessMessage(player, new TextComponentString(barrel.getTank().getFluidAmount() + " mb / " + capacity +  " mb"));
		} else {
			sendSpamlessMessage(player, new TextComponentTranslation("item.dipping_stick.empty"));
		}
	}

	public static void sendSpamlessMessage(EntityPlayer player, ITextComponent msg) {
		ShadowMC.proxy.sendSpamlessMessage(player, msg, ID);
	}

}
