package com.prohitman.dragonage.items;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;

import java.util.Set;

public class DDPickaxeItem extends PickaxeItem {

    public DDPickaxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, (int)attackDamageIn, attackSpeedIn, builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()));
    }
}
