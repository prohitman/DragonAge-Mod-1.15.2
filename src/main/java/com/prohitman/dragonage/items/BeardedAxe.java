package com.prohitman.dragonage.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class BeardedAxe extends AxeItem {
    public BeardedAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Vector3d vector3d = (new Vector3d(attacker.getPosX() - target.getPosX(), attacker.getPosY() - target.getPosY(), attacker.getPosZ() - target.getPosZ())).scale(0.3D);
        target.setMotion(target.getMotion().add(vector3d));
        return super.hitEntity(stack, target, attacker);
    }
}
