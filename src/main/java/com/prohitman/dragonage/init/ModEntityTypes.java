package com.prohitman.dragonage.init;

import com.prohitman.dragonage.DragonsDungeons;
import com.prohitman.dragonage.entity.AcidBombEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.ENTITIES, DragonsDungeons.MOD_ID);

    public static final RegistryObject<EntityType<AcidBombEntity>> ACID_BOMB_ENTITY = ENTITY_TYPES.register(
            "acid_bomb_entity",
            () -> EntityType.Builder.<AcidBombEntity>create(AcidBombEntity::new, EntityClassification.MISC).size(0.25F, 0.25F)
                    .build(new ResourceLocation(DragonsDungeons.MOD_ID, "acid_bomb_entity").toString()));
}
