package net.shadowfacts.yeoldetanks.block.cosmetic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.shadowfacts.yeoldetanks.achievement.ModAchievements;
import net.shadowfacts.yeoldetanks.block.base.BlockBarrelBase;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;

/**
 * @author shadowfacts
 */
public class BlockCosmeticBarrel extends BlockBarrelBase<TileEntityCosmeticBarrel> {

	public BlockCosmeticBarrel() {
		super("cosmetic_barrel");
	}

	@Override
	public boolean isCreative() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = player.getHeldItem(hand);
		TileEntityCosmeticBarrel te = getTileEntity(world, pos);
		if (heldItem.isEmpty()) {
			if (player.isSneaking()) {
				world.setBlockState(pos, state.cycleProperty(LID));
				return true;
			}
		} else {
			if (heldItem.hasCapability(FLUID_HANDLER_CAPABILITY, null)) {
				IFluidHandler handler = heldItem.getCapability(FLUID_HANDLER_CAPABILITY, null);
				te.getTank().setFluid(handler.drain(1000, false));
				te.save();
				return true;
			} else if (heldItem.hasCapability(FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				IFluidHandlerItem handler = heldItem.getCapability(FLUID_HANDLER_ITEM_CAPABILITY, null);
				te.getTank().setFluid(handler.drain(1000, false));
				te.save();
				return true;
			}
		}
		return false;
	}

	@Override
	public Class<TileEntityCosmeticBarrel> getTileEntityClass() {
		return TileEntityCosmeticBarrel.class;
	}

//	@Override
//	public Achievement getAchievement(ItemStack stack) {
//		return ModAchievements.craftCosmeticBarrel;
//	}

}
