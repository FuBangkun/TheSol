package mod.sol.init;

import mod.sol.entities.EntityTierRocket;
import mod.sol.items.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SolItems {
    public static final List<Item> ITEMS = new ArrayList<>();
    // Rocket Stuff
    public static final Item SCHEMATICS = new ItemSchematic();
    public static final Item KEYS = new ItemKey();
    public static final Item ROCKET_ENGINES = new ItemRocketPart("rocket_engine", false);
    public static final Item REINFORCED_PLATES = new ItemRocketPart("reinforced_plate", true);
    public static final Item NOSE_CONES = new ItemRocketPart("nose_cone", false);
    public static final Item ROCKET_FINS = new ItemRocketPart("rocket_fins", true);
    public static final Item ENGINE_BOOSTERS = new ItemRocketPart("engine_booster", true);
    // Rocket
    public static final Item ROCKET_T4 = new ItemRocket(4, EntityTierRocket::new);
    public static final Item ROCKET_T5 = new ItemRocket(5, EntityTierRocket::new);
    public static final Item ROCKET_T6 = new ItemRocket(6, EntityTierRocket::new);
    public static final Item ROCKET_T7 = new ItemRocket(7, EntityTierRocket::new);
    public static final Item ROCKET_T8 = new ItemRocket(8, EntityTierRocket::new);
    public static final Item ROCKET_T9 = new ItemRocket(9, EntityTierRocket::new);
    // Battery
    public static final Item ADVANCED_BATTERY_TIER_1 = new ItemAdvancedBattery("advanced_battery_t1", 1);
    public static final Item ADVANCED_BATTERY_TIER_2 = new ItemAdvancedBattery("advanced_battery_t2", 2);
    // Crafting Material
    // Sulfur
    public static final Item SULFUR_INGOT = new ItemBase("ingot_sulfur");
    public static final Item SULFUR_SHARD = new ItemBase("shard_sulfur");
    public static final Item COMPRESSED_SULFUR = new ItemBase("compressed_sulfur");
    public static final Item SULFUR_STICK = new ItemBase("stick_sulfur");
    // Manganese
    public static final Item MANGANESE_INGOT = new ItemBase("ingot_manganese");
    public static final Item COMPRESSED_MANGANESE = new ItemBase("compressed_manganese");
    public static final Item MANGANESE_STICK = new ItemBase("stick_manganese");
    // Lithium
    public static final Item LITHIUM_INGOT = new ItemBase("ingot_lithium");
    public static final Item COMPRESSED_LITHIUM = new ItemBase("compressed_lithium");
    // Magnet
    public static final Item MAGNET_INGOT = new ItemBase("ingot_magnet");
    public static final Item COMPRESSED_MAGNET = new ItemBase("compressed_magnet");
    // Magnesium
    public static final Item MAGNESIUM_INGOT = new ItemBase("ingot_magnesium");
    public static final Item COMPRESSED_MAGNESIUM = new ItemBase("compressed_magnesium");
    // Vanadium
    public static final Item VANADIUM_INGOT = new ItemBase("ingot_vanadium");
    public static final Item COMPRESSED_VANADIUM = new ItemBase("compressed_vanadium");
    // Osmium
    public static final Item OSMIUM_INGOT = new ItemBase("ingot_osmium");
    public static final Item COMPRESSED_OSMIUM = new ItemBase("compressed_osmium");
    // Armor
    public static final ArmorMaterial ARMOR_SULFUR = EnumHelper.addArmorMaterial("SULFUR", "", 42, new int[]{5, 8, 9, 5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
    public static final Item SULFUR_HELMET = new ItemArmorBase(ARMOR_SULFUR, EntityEquipmentSlot.HEAD, "sulfur", () -> SolItems.COMPRESSED_SULFUR);
    public static final Item SULFUR_CHESTPLATE = new ItemArmorBase(ARMOR_SULFUR, EntityEquipmentSlot.CHEST, "sulfur", () -> SolItems.COMPRESSED_SULFUR);
    public static final Item SULFUR_LEGGINGS = new ItemArmorBase(ARMOR_SULFUR, EntityEquipmentSlot.LEGS, "sulfur", () -> SolItems.COMPRESSED_SULFUR);
    public static final Item SULFUR_BOOTS = new ItemArmorBase(ARMOR_SULFUR, EntityEquipmentSlot.FEET, "sulfur", () -> SolItems.COMPRESSED_SULFUR);

    public static final ArmorMaterial ARMOR_MANGANESE = EnumHelper.addArmorMaterial("MANGANESE", "", 42, new int[]{6, 9, 10, 6}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
    public static final Item MANGANESE_HELMET = new ItemArmorBase(ARMOR_MANGANESE, EntityEquipmentSlot.HEAD, "manganese", () -> SolItems.COMPRESSED_MANGANESE);
    public static final Item MANGANESE_CHESTPLATE = new ItemArmorBase(ARMOR_MANGANESE, EntityEquipmentSlot.CHEST, "manganese", () -> SolItems.COMPRESSED_MANGANESE);
    public static final Item MANGANESE_LEGGINGS = new ItemArmorBase(ARMOR_MANGANESE, EntityEquipmentSlot.LEGS, "manganese", () -> SolItems.COMPRESSED_MANGANESE);
    public static final Item MANGANESE_BOOTS = new ItemArmorBase(ARMOR_MANGANESE, EntityEquipmentSlot.FEET, "manganese", () -> SolItems.COMPRESSED_MANGANESE);
    // Tools
    public static final ToolMaterial TOOL_SULFUR = EnumHelper.addToolMaterial("SULFUR", 3, 780, 8.0F, 6.5F, 12);
    public static final Item SULFUR_AXE = new ItemAxeBase(SolItems.TOOL_SULFUR, 10).setTranslationKey("sulfur_axe").setRegistryName("sulfur_axe");
    public static final Item SULFUR_HOE = new ItemHoeBase(SolItems.TOOL_SULFUR).setTranslationKey("sulfur_hoe").setRegistryName("sulfur_hoe");
    public static final Item SULFUR_PICKAXE = new ItemPickaxeBase(SolItems.TOOL_SULFUR).setTranslationKey("sulfur_pickaxe").setRegistryName("sulfur_pickaxe");
    public static final Item SULFUR_SHOVEL = new ItemShovelBase(SolItems.TOOL_SULFUR).setTranslationKey("sulfur_shovel").setRegistryName("sulfur_shovel");
    public static final Item SULFUR_SWORD = new ItemSwordBase(SolItems.TOOL_SULFUR).setTranslationKey("sulfur_sword").setRegistryName("sulfur_sword");

    public static final ToolMaterial TOOL_MANGANESE = EnumHelper.addToolMaterial("MANGANESE", 4, 2200, 4.0F, 8F, 8);
    public static final Item MANGANESE_AXE = new ItemAxeBase(SolItems.TOOL_MANGANESE, 11).setTranslationKey("manganese_axe").setRegistryName("manganese_axe");
    public static final Item MANGANESE_HOE = new ItemHoeBase(SolItems.TOOL_MANGANESE).setTranslationKey("manganese_hoe").setRegistryName("manganese_hoe");
    public static final Item MANGANESE_PICKAXE = new ItemPickaxeBase(SolItems.TOOL_MANGANESE).setTranslationKey("manganese_pickaxe").setRegistryName("manganese_pickaxe");
    public static final Item MANGANESE_SHOVEL = new ItemShovelBase(SolItems.TOOL_MANGANESE).setTranslationKey("manganese_shovel").setRegistryName("manganese_shovel");
    public static final Item MANGANESE_SWORD = new ItemSwordBase(SolItems.TOOL_MANGANESE).setTranslationKey("manganese_sword").setRegistryName("manganese_sword");
    // Magnet Boots
    public static final Item MAGNET_BOOTS = new ItemMagnetBoots("magnet_boots", ArmorMaterial.IRON, 7);
}
