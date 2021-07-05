package com.prohitman.dragonage.init;

import com.prohitman.dragonage.DragonsDungeons;

import com.prohitman.dragonage.tileentities.UrnTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, DragonsDungeons.MOD_ID);

	//public static final RegistryObject<TileEntityType<?>> URN_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("urn", () -> TileEntityType.Builder
	//		.create(UrnTileEntity::new, ModBlocks.URN.get()).build(null));
	public static final RegistryObject<TileEntityType<?>> URN_TILE_ENTITY_TYPE = TILE_ENTITY_TYPES.register("urn", () -> TileEntityType.Builder
					.create(UrnTileEntity::new, ModBlocks.URN.get()).build(null));
}
