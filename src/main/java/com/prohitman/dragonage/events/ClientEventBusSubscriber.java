package com.prohitman.dragonage.events;

import com.prohitman.dragonage.DragonsDungeons;
import com.prohitman.dragonage.client.gui.ForgingTableScreen;
import com.prohitman.dragonage.client.gui.UrnScreen;
import com.prohitman.dragonage.client.renderers.AcidBombEntityRenderer;
import com.prohitman.dragonage.client.renderers.FireBombEntityRenderer;
import com.prohitman.dragonage.init.ModBlocks;
import com.prohitman.dragonage.init.ModContainerTypes;

import com.prohitman.dragonage.init.ModEntityTypes;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = DragonsDungeons.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber 
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.FORGING_TABLE_CONTAINER.get(), ForgingTableScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.URN_CONTAINER.get(), UrnScreen::new);

		RenderTypeLookup.setRenderLayer(ModBlocks.FORGING_TABLE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(ModBlocks.URN.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(ModBlocks.CHANDELIER.get(), RenderType.getCutoutMipped());

		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ACID_BOMB_ENTITY.get(), AcidBombEntityRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FIRE_BOMB_ENTITY.get(), FireBombEntityRenderer::new);
	}
}
