package com.prohitman.dragonage.network;

import com.prohitman.dragonage.DragonsDungeons;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class DDPacketHandler {
	private static final String PROTOCOL_VERSION = "1";

	private static int id = 0;

	public static SimpleChannel HANDLER;

	public static void init() {
		HANDLER = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(DragonsDungeons.MOD_ID, "network"))
				.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
				.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

		register(MessageExtendedReachAttack.class, new MessageExtendedReachAttack());
	}

	public static <T> void register(Class<T> classIn, IMessage<T> message) {
		HANDLER.registerMessage(id++, classIn, message::encode, message::decode, message::handle);

	}

}
