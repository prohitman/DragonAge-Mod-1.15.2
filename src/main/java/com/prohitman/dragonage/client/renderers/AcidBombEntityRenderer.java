package com.prohitman.dragonage.client.renderers;

import com.prohitman.dragonage.entity.AcidBombEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

public class AcidBombEntityRenderer extends SpriteRenderer<AcidBombEntity> {
    public AcidBombEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }

}

