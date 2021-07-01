package com.prohitman.dragonage.init;

import com.prohitman.dragonage.DragonsDungeons;
import com.prohitman.dragonage.client.renderers.SteelShieldItemStackTileEntityRenderer;
import com.prohitman.dragonage.items.*;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonsDungeons.MOD_ID);
	
	//Materials
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));
	public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));
	public static final RegistryObject<Item> ELVEN_BRASS_INGOT = ITEMS.register("elven_brass_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));
	public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));
	public static final RegistryObject<Item> DWARVEN_STEEL_INGOT = ITEMS.register("dwarven_steel_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));
	public static final RegistryObject<Item> DRAGON_BONE = ITEMS.register("dragon_bone", () -> new Item(new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS).maxStackSize(64)));

	//Weapons
	public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new DDSwordItem(ModItemTiers.STEEL, 4, -2.4F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> STEEL_HALBERD = ITEMS.register("steel_halberd", () -> new HalberdItem(ModItemTiers.STEEL, 4, -3.0F, 9.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> MITHRIL_SWORD = ITEMS.register("mithril_sword", () -> new DDSwordItem(ModItemTiers.MITHRIL, 6, -2.4F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> GILDED_IRON_SWORD = ITEMS.register("gilded_iron_sword", () -> new DDSwordItem(ModItemTiers.GILDED_IRON, 3, -2.4F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DRAGON_BONE_SWORD = ITEMS.register("dragon_bone_sword", () -> new DDSwordItem(ModItemTiers.DRAGON_BONE, 4, -2.4F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> ELVEN_BRASS_SWORD = ITEMS.register("elven_brass_sword", () -> new DDSwordItem(ModItemTiers.ELVEN_BRASS, 5, -2.4F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));// was 4.5F

	//Tools
	public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new DDShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new DDPickaxeItem(ModItemTiers.STEEL, 1.5F, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new DDAxeItem(ModItemTiers.STEEL, 6.5F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new DDHoeItem(ModItemTiers.STEEL, 1, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

	public static final RegistryObject<Item> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel", () -> new DDShovelItem(ModItemTiers.MITHRIL, 2.0F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe", () -> new DDPickaxeItem(ModItemTiers.MITHRIL, 3, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> MITHRIL_AXE = ITEMS.register("mithril_axe", () -> new DDAxeItem(ModItemTiers.MITHRIL, 8.0F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> MITHRIL_HOE = ITEMS.register("mithril_hoe", () -> new DDHoeItem(ModItemTiers.MITHRIL, 1, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

	public static final RegistryObject<Item> DWARVEN_STEEL_SHOVEL = ITEMS.register("dwarven_steel_shovel", () -> new DDShovelItem(ModItemTiers.DWARVEN_STEEL, -2F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DWARVEN_STEEL_PICKAXE = ITEMS.register("dwarven_steel_pickaxe", () -> new DDPickaxeItem(ModItemTiers.DWARVEN_STEEL, -2, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DWARVEN_STEEL_AXE = ITEMS.register("dwarven_steel_axe", () -> new DDAxeItem(ModItemTiers.DWARVEN_STEEL, 2.0F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DWARVEN_STEEL_HOE = ITEMS.register("dwarven_steel_hoe", () -> new DDHoeItem(ModItemTiers.DWARVEN_STEEL, -3, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

	public static final RegistryObject<Item> GILDED_IRON_SHOVEL = ITEMS.register("gilded_iron_shovel", () -> new DDShovelItem(ModItemTiers.GILDED_IRON, 1.5F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> GILDED_IRON_PICKAXE = ITEMS.register("gilded_iron_pickaxe", () -> new DDPickaxeItem(ModItemTiers.GILDED_IRON, 1, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> GILDED_IRON_AXE = ITEMS.register("gilded_iron_axe", () -> new DDAxeItem(ModItemTiers.GILDED_IRON, 6.0F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> GILDED_IRON_HOE = ITEMS.register("gilded_iron_hoe", () -> new DDHoeItem(ModItemTiers.GILDED_IRON, -2, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

	public static final RegistryObject<Item> DRAGON_BONE_SHOVEL = ITEMS.register("dragon_bone_shovel", () -> new DDShovelItem(ModItemTiers.DRAGON_BONE, 0F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DRAGON_BONE_PICKAXE = ITEMS.register("dragon_bone_pickaxe", () -> new DDPickaxeItem(ModItemTiers.DRAGON_BONE, 0, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DRAGON_BONE_AXE = ITEMS.register("dragon_bone_axe", () -> new DDAxeItem(ModItemTiers.DRAGON_BONE, 6.0F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> DRAGON_BONE_HOE = ITEMS.register("dragon_bone_hoe", () -> new DDHoeItem(ModItemTiers.DRAGON_BONE, -1, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

	public static final RegistryObject<Item> ELVEN_BRASS_SHOVEL = ITEMS.register("elven_brass_shovel", () -> new DDShovelItem(ModItemTiers.ELVEN_BRASS, 0F, -3.0F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> ELVEN_BRASS_PICKAXE = ITEMS.register("elven_brass_pickaxe", () -> new DDPickaxeItem(ModItemTiers.ELVEN_BRASS, 1, -2.8F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> ELVEN_BRASS_AXE = ITEMS.register("elven_brass_axe", () -> new DDAxeItem(ModItemTiers.ELVEN_BRASS, 5.0F, -3.1F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));
	public static final RegistryObject<Item> ELVEN_BRASS_HOE = ITEMS.register("elven_brass_hoe", () -> new DDHoeItem(ModItemTiers.ELVEN_BRASS, -1, -0.9F, new Item.Properties().group(ModItemGroups.DRAGONS_DUNGEONS)));

}
