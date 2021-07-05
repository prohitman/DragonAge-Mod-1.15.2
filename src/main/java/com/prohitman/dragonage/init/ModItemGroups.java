package com.prohitman.dragonage.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroups 
{
	public static final ItemGroup DRAGONS_DUNGEONS = (new ItemGroup("dragonsdungeons") {
	      @OnlyIn(Dist.CLIENT)
	      public ItemStack createIcon() {
	         return new ItemStack(ModItems.MITHRIL_INGOT.get());
	      }
	   }).setNoTitle();
}
