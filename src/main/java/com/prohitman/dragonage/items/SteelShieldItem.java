package com.prohitman.dragonage.items;

import com.prohitman.dragonage.init.ModItems;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ResourceLocation;

public class SteelShieldItem extends ShieldItem
{

	public SteelShieldItem(Item.Properties builder) 
	{
		super(builder);
		this.addPropertyOverride(new ResourceLocation("blocking"), (itemstack, world, entity) -> {
	         return entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemstack ? 1.0F : 0.0F;
	      });
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
	
	
}
