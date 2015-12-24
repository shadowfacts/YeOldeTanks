package net.shadowfacts.yeoldetanks.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.network.PacketUpdateTE;

/**
 * @author shadowfacts
 */
public class YOTTileEntity extends TileEntity {

	public void onNetworkUpdate(NBTTagCompound tag) {
		readFromNBT(tag);
	}

	public void sendNetworkUpdate(NBTTagCompound tag) {
		if (!worldObj.isRemote) {
			PacketUpdateTE msg = new PacketUpdateTE(xCoord, yCoord, zCoord, tag);
			NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64);
			YeOldeTanks.network.sendToAllAround(msg, point);
		}
	}

	public void sendNetworkUpdate() {
		if (!worldObj.isRemote) {
			NBTTagCompound tag = new NBTTagCompound();
			writeToNBT(tag);
			sendNetworkUpdate(tag);
		}
	}

}
