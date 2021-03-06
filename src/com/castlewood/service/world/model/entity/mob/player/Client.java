package com.castlewood.service.world.model.entity.mob.player;

import io.netty.channel.Channel;

import java.util.LinkedList;

import com.castlewood.service.net.game.event.outbound.OutboundEvent;
import com.castlewood.service.net.game.packet.inbound.InboundPacket;
import com.castlewood.service.net.game.packet.outbound.OutboundPacket;

import net.burtleburtle.bob.rand.IsaacRandom;

public class Client
{

	private Channel channel;

	private IsaacRandom decoder;

	private IsaacRandom encoder;

	private LinkedList<InboundPacket> inbound = new LinkedList<>();

	private LinkedList<OutboundPacket> outbound = new LinkedList<>();

	public Client(Channel channel, IsaacRandom decoder, IsaacRandom encoder)
	{
		this.channel = channel;
		this.decoder = decoder;
		this.encoder = encoder;
	}

	public void push(InboundPacket request)
	{
		inbound.add(request);
	}

	public void send(OutboundEvent outboundEvent)
	{
		outbound.add(outboundEvent.encode());
	}

	public void flush()
	{
		if (channel.isOpen())
		{
			OutboundPacket packet;
			while ((packet = outbound.poll()) != null)
			{
				channel.write(packet);
			}
		}
	}

	public void close()
	{
		channel.close();
	}

	public InboundPacket next()
	{
		return inbound.poll();
	}

	public boolean isConnected()
	{
		return channel.isOpen();
	}

	public IsaacRandom getDecoder()
	{
		return decoder;
	}

	public IsaacRandom getEncoder()
	{
		return encoder;
	}

}
