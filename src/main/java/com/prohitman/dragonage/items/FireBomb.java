package com.prohitman.dragonage.items;

import com.prohitman.dragonage.entity.AcidBombEntity;
import com.prohitman.dragonage.entity.FireBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FireBomb extends Item {
    public FireBomb(Properties properties) {
        super(properties);
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.getCooldownTracker().setCooldown(this, 10);
        if (!worldIn.isRemote) {
            FireBombEntity fireBombEntity = new FireBombEntity(worldIn, playerIn);
            fireBombEntity.setItem(itemstack);
            fireBombEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
            worldIn.addEntity(fireBombEntity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}
