package com.prohitman.dragonage.util;

import com.prohitman.dragonage.DragonAge;
import com.prohitman.dragonage.client.renderers.SteelShieldItemStackTileEntityRenderer;

import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = DragonAge.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModAtlasTextures {

    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
    	if (event.getMap().getTextureLocation() == AtlasTexture.LOCATION_BLOCKS_TEXTURE) {
            event.addSprite(SteelShieldItemStackTileEntityRenderer.RESOURCE_LOCATION_STEEL_SHIELD_BASE);
            event.addSprite(SteelShieldItemStackTileEntityRenderer.RESOURCE_LOCATION_STEEL_SHIELD_NO_PATTERN);
        }
    }
}
