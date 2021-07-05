package com.prohitman.dragonage.util;

import com.prohitman.dragonage.DragonsDungeons;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = DragonsDungeons.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModAtlasTextures {
/*
    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
    	if (event.getMap().getTextureLocation() == AtlasTexture.LOCATION_BLOCKS_TEXTURE) {
            event.addSprite(SteelShieldItemStackTileEntityRenderer.RESOURCE_LOCATION_STEEL_SHIELD_BASE);
            event.addSprite(SteelShieldItemStackTileEntityRenderer.RESOURCE_LOCATION_STEEL_SHIELD_NO_PATTERN);
        }
    }*/
}
