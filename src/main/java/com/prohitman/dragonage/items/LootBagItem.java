package com.prohitman.dragonage.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.loot.*;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.loot.IGlobalLootModifier;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LootBagItem extends Item {
    Random rand = new Random();
    public LootBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote()){
            ItemStack itemstackIn = playerIn.getHeldItem(handIn);
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) Objects.requireNonNull(playerIn).world)).withParameter(LootParameters.ORIGIN, playerIn.getPositionVec()).withParameter(LootParameters.TOOL, this.getDefaultInstance()).withRandom(rand).withLuck(5);
            lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, playerIn);
            LootTable loottable = Objects.requireNonNull(worldIn.getServer()).getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_TREASURE);
            List<ItemStack> list = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));

            for(ItemStack itemstack : list) {
                ItemEntity itementity = new ItemEntity(playerIn.world, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), itemstack);
                worldIn.addEntity(itementity);
                playerIn.world.addEntity(new ExperienceOrbEntity(playerIn.world, playerIn.getPosX(), playerIn.getPosY() + 0.5D, playerIn.getPosZ() + 0.5D, this.rand.nextInt(6) + 1));

            }
            playerIn.addStat(Stats.ITEM_USED.get(this));
            if (!playerIn.abilities.isCreativeMode) {
                itemstackIn.shrink(1);
            }
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
