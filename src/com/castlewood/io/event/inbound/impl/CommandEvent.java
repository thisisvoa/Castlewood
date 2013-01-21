package com.castlewood.io.event.inbound.impl;

import com.castlewood.io.event.inbound.InboundEvent;
import com.castlewood.io.service.game.packet.inbound.InboundPacket;
import com.castlewood.util.buffer.DataBuffer;
import com.castlewood.world.model.entity.mob.player.Player;

public class CommandEvent implements InboundEvent
{

	@Override
	public void decode(Player player, InboundPacket packet)
	{
		String command = DataBuffer.readString(packet.getBuffer());
	}

}
