package com.prohitman.dragonage.items;

import com.prohitman.dragonage.init.ModItems;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SteelShieldItem extends ShieldItem
{

	public SteelShieldItem(Item.Properties builder) 
	{
		super(builder);
		//this.addPropertyOverride(new ResourceLocation("blocking"), (itemstack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemstack ? 1.0F : 0.0F);

		DispenserBlock.registerDispenseBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) 
	{
		return repair.getItem() == ModItems.STEEL_INGOT.get() || super.getIsRepairable(toRepair, repair);
	}
	
	@Override
	public boolean isShield(ItemStack stack, LivingEntity entity)
	{
		return true;
	}

	public static DyeColor getColor(ItemStack stack) {
		return DyeColor.byId(stack.getOrCreateChildTag("BlockEntityTag").getInt("Base"));
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		BannerItem.appendHoverTextFromTileEntityTag(stack, tooltip);
	}
}
