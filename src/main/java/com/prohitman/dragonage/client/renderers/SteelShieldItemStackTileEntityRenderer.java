package com.prohitman.dragonage.client.renderers;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import com.prohitman.dragonage.client.models.SteelShieldModel;
import com.prohitman.dragonage.init.ModItems;
import com.prohitman.dragonage.items.SteelShieldItem;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ResourceLocation;


public class SteelShieldItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
	private final SteelShieldModel shieldModel = new SteelShieldModel();
	public static final ResourceLocation RESOURCE_LOCATION_STEEL_SHIELD_BASE = new ResourceLocation(
			"dragonage:textures/entity/steel_shield_base");
	public static final ResourceLocation RESOURCE_LOCATION_STEEL_SHIELD_NO_PATTERN = new ResourceLocation(
			"dragonage:textures/entity/steel_shield_base_nopattern");
	@SuppressWarnings("deprecation")
	public static final Material LOCATION_STEEL_SHIELD_BASE = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
			RESOURCE_LOCATION_STEEL_SHIELD_BASE);
	@SuppressWarnings("deprecation")
	public static final Material LOCATION_STEEL_SHIELD_NO_PATTERN = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
			RESOURCE_LOCATION_STEEL_SHIELD_NO_PATTERN);

	@Override
	public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		Item item = itemStackIn.getItem();
		if (item == ModItems.STEEL_SHIELD.get()) {

			boolean flag = itemStackIn.getChildTag("BlockEntityTag") != null;
			matrixStackIn.push();
			matrixStackIn.scale(1.0F, -1.0F, -1.0F);
			Material material = flag ? LOCATION_STEEL_SHIELD_BASE : LOCATION_STEEL_SHIELD_NO_PATTERN;
			IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(bufferIn, this.shieldModel.getRenderType(material.getAtlasLocation()), false, itemStackIn.hasEffect()));
			this.shieldModel.getHandleParts().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn,
					1.0F, 1.0F, 1.0F, 1.0F);
			if (flag) {
				List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.func_230138_a_(
						SteelShieldItem.getColor(itemStackIn), BannerTileEntity.func_230139_a_(itemStackIn));
				BannerTileEntityRenderer.func_230180_a_(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn,
						this.shieldModel.getPlateParts(), material, false, list);
			} else {
				this.shieldModel.getPlateParts().render(matrixStackIn, ivertexbuilder, combinedLightIn,
						combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			matrixStackIn.pop();
		}
	}

}
