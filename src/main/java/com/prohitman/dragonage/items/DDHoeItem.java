package com.prohitman.dragonage.items;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public class DDHoeItem extends HoeItem {
    public DDHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(itemTier, attackDamage, attackSpeed, properties.addToolType(net.minecraftforge.common.ToolType.HOE, itemTier.getHarvestLevel()));
    }
}
