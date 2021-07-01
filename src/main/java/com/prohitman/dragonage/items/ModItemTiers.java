package com.prohitman.dragonage.items;

import com.prohitman.dragonage.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ModItemTiers implements IItemTier {
    STEEL(2, 800, 6.0F, 2.5F, 12, () -> {
        return Ingredient.fromItems(ModItems.STEEL_INGOT.get());
    }),
    MITHRIL(4, 1800, 9.0F, 3F, 12, () -> {
        return Ingredient.fromItems(ModItems.MITHRIL_INGOT.get());
    }),
    ELVEN_BRASS(3, 910, 8.0F, 4.0F, 14, () -> {
        return Ingredient.fromItems(ModItems.ELVEN_BRASS_INGOT.get());
    }),
    DRAGON_BONE(6, 3000, 10.0F, 6.0F, 16, () -> {
        return Ingredient.fromItems(ModItems.DRAGON_BONE.get());
    }),
    DWARVEN_STEEL(3, 1400, 8.0F, 7F, 15, () -> {
        return Ingredient.fromItems(ModItems.DWARVEN_STEEL_INGOT.get());
    }),
    GILDED_IRON(2, 275, 6.0F, 2.0F, 20, () -> {
        return Ingredient.fromItems(Items.IRON_INGOT);
    });


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    ModItemTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

}
