package net.shadowfacts.yeoldetanks.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.yeoldetanks.YeOldeTanks;
import net.shadowfacts.yeoldetanks.tileentity.YOTTileEntity;

/**
 * @author shadowfacts
 */
public class PacketUpdateTE implements IMessage {

	private int x;
	private int y;
	private int z;
	private NBTTagCompound tag;

	public PacketUpdateTE() {

	}

	public PacketUpdateTE(int x, int y, int z, NBTTagCompound tag) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.tag = tag;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeTag(buf, tag);
	}

	public static class Handler implements IMessageHandler<PacketUpdateTE, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdateTE msg, MessageContext ctx) {
			World world = YeOldeTanks.proxy.getClientWorld();
			TileEntity te = world.getTileEntity(msg.x, msg.y, msg.z);
			if (te instanceof YOTTileEntity) {
				((YOTTileEntity)te).onNetworkUpdate(msg.tag);
			}

			return null;
		}
	}
}
