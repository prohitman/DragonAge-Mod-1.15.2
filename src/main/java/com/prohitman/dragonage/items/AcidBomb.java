package com.prohitman.dragonage.items;

import com.prohitman.dragonage.entity.AcidBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AcidBomb extends Item {
    public AcidBomb(Properties properties) {
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
            AcidBombEntity acidBombEntity = new AcidBombEntity(worldIn, playerIn);
            acidBombEntity.setItem(itemstack);
            acidBombEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
            worldIn.addEntity(acidBombEntity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}
