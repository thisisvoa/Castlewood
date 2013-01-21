package com.castlewood.io.event.outbound.impl;

import com.castlewood.io.event.outbound.OutboundEvent;
import com.castlewood.io.service.game.packet.outbound.OutboundPacket;
import com.castlewood.world.model.entity.Location;

public class UpdateRegionEvent implements OutboundEvent
{

	private Location location;

	public UpdateRegionEvent(Location location)
	{
		this.location = location;
	}

	@Override
	public OutboundPacket encode()
	{
		OutboundPacket packet = new OutboundPacket(73);
		packet.getBuffer().writeShort(location.getRegionY());
		packet.getBuffer().writeShort(location.getRegionX());
		return packet;
	}

}
