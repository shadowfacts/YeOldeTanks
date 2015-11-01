package net.shadowfacts.yeoldetanks.compat.modules.computercraft;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.block.barrel.TileEntityBarrel;

/**
 * @author shadowfacts
 */
public class YOTPeripheralProvider implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityBarrel) {
			return (TileEntityBarrel)te;
		}
		return null;
	}

}
