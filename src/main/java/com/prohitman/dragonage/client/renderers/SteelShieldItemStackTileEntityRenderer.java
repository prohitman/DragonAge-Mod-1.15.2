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
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
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
	public static final RenderMaterial LOCATION_STEEL_SHIELD_BASE = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
			RESOURCE_LOCATION_STEEL_SHIELD_BASE);
	@SuppressWarnings("deprecation")
	public static final RenderMaterial LOCATION_STEEL_SHIELD_NO_PATTERN = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE,
			RESOURCE_LOCATION_STEEL_SHIELD_NO_PATTERN);

	@Override
	public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		super.func_239207_a_(stack, p_239207_2_, matrixStack, buffer, combinedLight, combinedOverlay);
		Item item = stack.getItem();
		if (item == ModItems.STEEL_SHIELD.get()) {

			boolean flag = stack.getChildTag("BlockEntityTag") != null;
			matrixStack.push();
			matrixStack.scale(1.0F, -1.0F, -1.0F);
			RenderMaterial material = flag ? LOCATION_STEEL_SHIELD_BASE : LOCATION_STEEL_SHIELD_NO_PATTERN;
			IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(buffer, this.shieldModel.getRenderType(material.getAtlasLocation()), false, stack.hasEffect()));
			this.shieldModel.getHandleParts().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay,
					1.0F, 1.0F, 1.0F, 1.0F);
			if (flag) {
				List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.getPatternColorData(
						SteelShieldItem.getColor(stack), BannerTileEntity.getPatternData(stack));
				BannerTileEntityRenderer.func_230180_a_(matrixStack, buffer, combinedLight, combinedOverlay,
						this.shieldModel.getPlateParts(), material, false, list);
			} else {
				this.shieldModel.getPlateParts().render(matrixStack, ivertexbuilder, combinedLight,
						combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			matrixStack.pop();
		}
	}
}
