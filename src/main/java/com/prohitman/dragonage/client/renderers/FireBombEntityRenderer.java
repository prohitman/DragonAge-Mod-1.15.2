package com.prohitman.dragonage.client.renderers;

import com.prohitman.dragonage.entity.FireBombEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;

public class FireBombEntityRenderer extends SpriteRenderer<FireBombEntity> {
    public FireBombEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }

}
