package com.prohitman.dragonage.items;

import net.minecraft.item.*;;

public class DDAxeItem extends AxeItem {
    public DDAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder.addToolType(net.minecraftforge.common.ToolType.AXE, tier.getHarvestLevel()));
    }
}
