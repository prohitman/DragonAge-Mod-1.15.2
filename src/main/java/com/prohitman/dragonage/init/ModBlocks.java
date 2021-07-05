package com.prohitman.dragonage.init;

import com.prohitman.dragonage.DragonsDungeons;
import com.prohitman.dragonage.blocks.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.obj.MaterialLibrary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks 
{
	//Blocks
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonsDungeons.MOD_ID);

	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new SteelBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.5F, 6.5F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> FORTRESS_BRICKS = BLOCKS.register("fortress_bricks", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CRACKED_FORTRESS_BRICKS = BLOCKS.register("cracked_fortress_bricks", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SNOWY_FORTRESS_BRICKS = BLOCKS.register("snowy_fortress_bricks", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FORTRESS_BRICKS_SLABS = BLOCKS.register("fortress_bricks_slabs", () -> new SlabBlock(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FORTRESS_BRICKS_STAIRS = BLOCKS.register("fortress_bricks_stairs", () -> new StairsBlock(FORTRESS_BRICKS.get().getDefaultState(), Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).setRequiresTool().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ADAMANTITE_DEPOSIT = BLOCKS.register("adamantite_deposit", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).setRequiresTool().hardnessAndResistance(3.0F, 3.0F)));
	public static final RegistryObject<Block> MITHRIL_CRYSTAL = BLOCKS.register("mithril_crystal", () -> new MithrilCrystalBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE).sound(SoundType.GLASS).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).setLightLevel((blockState) -> 7)));

	//Decorations
	public static final RegistryObject<Block> CANDLE = BLOCKS.register("candle", () -> new CandleBlock(Block.Properties.create(Material.MISCELLANEOUS).notSolid().hardnessAndResistance(0.0F, 0.0F).setLightLevel((state) -> 10).sound(SoundType.SCAFFOLDING)));
	public static final RegistryObject<Block> CHANDELIER = BLOCKS.register("chandelier", () -> new ChandelierBlock(Block.Properties.create(Material.IRON, MaterialColor.AIR).notSolid().hardnessAndResistance(5.0F, 6.0F).setLightLevel((state) -> state.get(BlockStateProperties.LIT) ? 15 : 0).sound(SoundType.CHAIN)));

	//Container/TileEntity Blocks
	public static final RegistryObject<UrnBlock> URN = BLOCKS.register("urn", () -> new UrnBlock(Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> FORGING_TABLE = BLOCKS.register("forging_table", () -> new ForgingTableBlock(Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(2.25F, 3.25F).sound(SoundType.STONE)));

}
