package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.Tags;
import mod.sol.TheSol;
import mod.sol.celestialbodies.RoomBossUniversal;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.client.gui.GuiSchematicRocket;
import mod.sol.entities.EntityHugeFireball;
import mod.sol.entities.EntityTierRocket;
import mod.sol.entities.boss.*;
import mod.sol.inventory.ContainerSchematicRocket;
import mod.sol.items.ItemSchematic;
import mod.sol.tile.TileEntityDungeonSpawnerSol;
import mod.sol.util.RecipeUtil;
import mod.sol.util.SchematicRocket;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class SolRegistries {
    public static void register() {
        registerStructures();
        registerEntities();
        registerSchematics();
        registryCompressorRecipe();
        registrySmeltingRecipe();
        registerOres();
        registerDungeons();
        registerEventHandlers();
    }

    private static void registerStructures() {
        MapGenStructureIO.registerStructureComponent(RoomBossUniversal.class, "SolDungeonBossRoom");
        MapGenStructureIO.registerStructureComponent(RoomTreasureUniversal.class, "SolDungeonTreasureRoom");
    }

    private static void registerEntities() {
        TheSol.proxy.registerNonMobEntity(EntityHugeFireball.class, "fireball_huge", 150, 1, false);
        TheSol.proxy.registerNonMobEntity(EntityTierRocket.class, "rocket", 150, 1, false);

        TheSol.proxy.registerEntityCreature(EntityMercuryBossBlaze.class, "mercury_blaze_boss", ColorUtil.to32BitColor(255, 63, 0, 0), ColorUtil.to32BitColor(255, 220, 0, 0));
        TheSol.proxy.registerEntityCreature(EntityJupiterBossGhast.class, "jupiter_ghast_boss", ColorUtil.to32BitColor(255, 127, 0, 0), ColorUtil.to32BitColor(255, 0, 0, 0));
        TheSol.proxy.registerEntityCreature(EntitySaturnBossStray.class, "saturn_stray_boss", ColorUtil.to32BitColor(255, 225, 255, 225), ColorUtil.to32BitColor(255, 0, 15, 255));
        TheSol.proxy.registerEntityCreature(EntityUranusBossSlime.class, "uranus_slime_boss", ColorUtil.to32BitColor(255, 5, 31, 127), ColorUtil.to32BitColor(255, 0, 0, 255));
        TheSol.proxy.registerEntityCreature(EntityNeptuneBossSpider.class, "neptune_spider_boss", ColorUtil.to32BitColor(255, 4, 4, 4), ColorUtil.to32BitColor(255, 127, 0, 16));
        TheSol.proxy.registerEntityCreature(EntityBossSilverfish.class, "silverfish_boss", ColorUtil.to32BitColor(255, 64, 64, 64), ColorUtil.to32BitColor(255, 127, 127, 127));
        TheSol.proxy.registerEntityCreature(EntityBossMagmaCube.class, "magmacube_boss", ColorUtil.to32BitColor(255, 127, 31, 31), ColorUtil.to32BitColor(255, 255, 127, 127));
    }

    private static void registerSchematics() {
        Item schematicItem = SolItems.SCHEMATICS;
        ItemSchematic schematicClass = (ItemSchematic) schematicItem;

        Item[] tierRockets = {
                SolItems.ROCKET_T4,
                SolItems.ROCKET_T5,
                SolItems.ROCKET_T6,
                SolItems.ROCKET_T7,
                SolItems.ROCKET_T8,
                SolItems.ROCKET_T9
        };

        for (int tier = 4; tier <= 9; tier++) {
            final int t = tier;
            final int meta = schematicClass.getMetaFromTier(tier);
            Item rocketItem = tierRockets[t - 4];

            SchematicRegistry.registerSchematicRecipe(new SchematicRocket(
                    t,
                    schematicItem,
                    meta,
                    (inv, pos) -> new GuiSchematicRocket(inv, pos, t, rocketItem),
                    (inv, pos) -> new ContainerSchematicRocket(inv, pos, t)
            ));
            ItemSchematic.registerSchematicItems(new ItemStack(schematicItem, 1, meta));

            if (tier == 4) {
                registerRocketRecipe(
                        4,
                        new ItemStack(micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems.heavyNoseCone),
                        new ItemStack(SolItems.REINFORCED_PLATES, 1, 0),
                        new ItemStack(SolItems.ENGINE_BOOSTERS, 1, 0),
                        new ItemStack(SolItems.ROCKET_FINS, 1, 0),
                        new ItemStack(micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems.basicItem, 1, 1),
                        new ItemStack(micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems.tier3Rocket, 1, 0),
                        new ItemStack(SolItems.ROCKET_T4, 1, 0)
                );
            } else {
                int k = tier - 5;
                registerRocketRecipe(
                        tier,
                        new ItemStack(SolItems.NOSE_CONES, 1, k),
                        new ItemStack(SolItems.REINFORCED_PLATES, 1, k + 1),
                        new ItemStack(SolItems.ENGINE_BOOSTERS, 1, k + 1),
                        new ItemStack(SolItems.ROCKET_FINS, 1, k + 1),
                        new ItemStack(SolItems.ROCKET_ENGINES, 1, k),
                        new ItemStack(tierRockets[k], 1, 0),
                        new ItemStack(tierRockets[k + 1], 1, 0)
                );
            }
        }
    }

    private static void registerRocketRecipe(int tier, ItemStack noseCone, ItemStack plate, ItemStack booster, ItemStack fins, ItemStack engine, ItemStack previousRocket, ItemStack outputRocket) {
        HashMap<Integer, ItemStack> baseInput = new HashMap<>();
        baseInput.put(1, noseCone);
        for (int i = 2; i <= 11; i++) {
            baseInput.put(i, plate);
        }
        baseInput.put(12, booster);
        baseInput.put(13, fins);
        baseInput.put(14, fins);
        baseInput.put(15, engine);
        baseInput.put(16, booster);
        baseInput.put(17, fins);
        baseInput.put(18, fins);

        baseInput.put(19, ItemStack.EMPTY);
        baseInput.put(20, ItemStack.EMPTY);
        baseInput.put(21, ItemStack.EMPTY);
        baseInput.put(22, ItemStack.EMPTY);

        NonNullList<ItemStack> woodChests = OreDictionary.getOres("chestWood");

        int[][] chestLayouts = {
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 1},
                {2, 1, 1, 0},
                {2, 1, 0, 1},
                {2, 0, 1, 1},
                {3, 1, 1, 1}
        };

        for (ItemStack woodChest : woodChests) {
            for (int[] layout : chestLayouts) {
                int rocketMeta = layout[0];
                boolean slot19 = layout[1] == 1;
                boolean slot20 = layout[2] == 1;
                boolean slot21 = layout[3] == 1;

                for (int i = 0; i < 4; ++i) {
                    HashMap<Integer, ItemStack> recipeInput = new HashMap<>(baseInput);

                    recipeInput.put(19, slot19 ? woodChest : ItemStack.EMPTY);
                    recipeInput.put(20, slot20 ? woodChest : ItemStack.EMPTY);
                    recipeInput.put(21, slot21 ? woodChest : ItemStack.EMPTY);

                    ItemStack prevRocketCopy = previousRocket.copy();
                    prevRocketCopy.setItemDamage(i);
                    recipeInput.put(22, prevRocketCopy);

                    ItemStack outputCopy = outputRocket.copy();
                    outputCopy.setItemDamage(rocketMeta);

                    RecipeUtil.addRocketRecipe(tier, new NasaWorkbenchRecipe(outputCopy, recipeInput));
                }
            }
        }
    }

    private static void registryCompressorRecipe() {
        //Plates
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 0), new ItemStack(AsteroidsItems.basicItem, 1, 6), new ItemStack(AsteroidsItems.basicItem, 1, 5));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 1), new ItemStack(SolItems.COMPRESSED_SULFUR, 1, 0), new ItemStack(SolItems.REINFORCED_PLATES, 1, 0));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 2), new ItemStack(SolItems.COMPRESSED_MANGANESE, 1, 0), new ItemStack(SolItems.REINFORCED_PLATES, 1, 1));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 3), new ItemStack(SolItems.COMPRESSED_LITHIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATES, 1, 2));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 4), new ItemStack(SolItems.COMPRESSED_MAGNESIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATES, 1, 3));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.REINFORCED_PLATES, 2, 5), new ItemStack(SolItems.COMPRESSED_VANADIUM, 1, 0), new ItemStack(SolItems.REINFORCED_PLATES, 1, 4));
        //Sulfur
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.SULFUR_SHARD, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1), new ItemStack(SolBlocks.IO_SULFUR_BLOCK, 1));
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_SULFUR, 1), new ItemStack(SolItems.SULFUR_INGOT, 1), new ItemStack(SolItems.SULFUR_INGOT, 1));
        //Ash
        CompressorRecipes.addShapelessRecipe(new ItemStack(MarsItems.carbonFragments, 2), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
        CompressorRecipes.addShapelessRecipe(new ItemStack(Items.COAL, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1), new ItemStack(SolBlocks.IO_ASH_BLOCK, 1));
        //Manganese
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MANGANESE), new ItemStack(SolItems.MANGANESE_INGOT), new ItemStack(SolItems.MANGANESE_INGOT));
        //Lithium
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_LITHIUM), new ItemStack(SolItems.LITHIUM_INGOT), new ItemStack(SolItems.LITHIUM_INGOT));
        //Magnet
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MAGNET), new ItemStack(SolItems.MAGNET_INGOT), new ItemStack(SolItems.MAGNET_INGOT));
        //Magnesium
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_MAGNESIUM), new ItemStack(SolItems.MAGNESIUM_INGOT), new ItemStack(SolItems.MAGNESIUM_INGOT));
        //Vanadium
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_VANADIUM), new ItemStack(SolItems.VANADIUM_INGOT), new ItemStack(SolItems.VANADIUM_INGOT));
        //Osmium
        CompressorRecipes.addShapelessRecipe(new ItemStack(SolItems.COMPRESSED_OSMIUM), new ItemStack(SolItems.OSMIUM_INGOT), new ItemStack(SolItems.OSMIUM_INGOT));
    }

    private static void registrySmeltingRecipe() {
        GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1.0F);

        ItemStack gcAluminum = new ItemStack(GCItems.basicItem, 1, 5);
        ItemStack gcCopper = new ItemStack(GCItems.basicItem, 1, 3);
        ItemStack gcTin = new ItemStack(GCItems.basicItem, 1, 4);
        ItemStack gcSilicon = new ItemStack(GCItems.basicItem, 1, 2);
        ItemStack vanillaIron = new ItemStack(Items.IRON_INGOT, 1, 0);
        ItemStack marsDesh = new ItemStack(MarsItems.marsItemBasic, 1, 2);
        ItemStack astIlmenite = new ItemStack(AsteroidsItems.basicItem, 1, 0);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 1), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 2), marsDesh, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 3), astIlmenite, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 4), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 5), gcSilicon, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MERCURY_ORES, 1, 6), gcTin, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.IO_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.IO_ORES, 1, 1), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.IO_ORES, 1, 2), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.IO_ORES, 1, 3), new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.EUROPA_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.EUROPA_ORES, 1, 1), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.EUROPA_ORES, 1, 2), vanillaIron, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.MIMAS_ORES, 1, 0), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MIMAS_ORES, 1, 1), marsDesh, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MIMAS_ORES, 1, 2), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MIMAS_ORES, 1, 3), new ItemStack(SolItems.MANGANESE_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.MIMAS_ORES, 1, 4), gcTin, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 1), marsDesh, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 2), new ItemStack(Items.DIAMOND, 2, 0), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 3), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 4), new ItemStack(SolItems.MANGANESE_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TITAN_ORES, 1, 5), gcTin, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 1), marsDesh, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 2), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 3), astIlmenite, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 4), new ItemStack(SolItems.LITHIUM_INGOT, 1, 0), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.ARIEL_ORES, 1, 5), gcTin, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 0), gcAluminum, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 1), marsDesh, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 2), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 3), astIlmenite, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 4), new ItemStack(SolItems.LITHIUM_INGOT, 1, 0), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 5), new ItemStack(SolItems.MAGNESIUM_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 6), new ItemStack(SolItems.MAGNET_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 7), gcSilicon, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.TRITON_ORES, 1, 8), gcTin, 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.PLUTO_ORES, 1, 0), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.PLUTO_ORES, 1, 1), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.PLUTO_ORES, 1, 2), gcTin, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.PLUTO_ORES, 1, 3), new ItemStack(SolItems.VANADIUM_INGOT, 1, 0), 1.0F);

        GameRegistry.addSmelting(new ItemStack(SolBlocks.SEDNA_ORES, 1, 0), gcCopper, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.SEDNA_ORES, 1, 1), vanillaIron, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.SEDNA_ORES, 1, 2), new ItemStack(SolItems.OSMIUM_INGOT, 1, 0), 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.SEDNA_ORES, 1, 3), gcTin, 1.0F);
        GameRegistry.addSmelting(new ItemStack(SolBlocks.SEDNA_ORES, 1, 4), new ItemStack(SolItems.VANADIUM_INGOT, 1, 0), 1.0F);
    }

    private static void registerOres() {
        //ore
        //aluminum
        OreDictionary.registerOre("oreAluminum", new ItemStack(SolBlocks.MERCURY_ORES, 1, 0));
        OreDictionary.registerOre("oreAluminum", new ItemStack(SolBlocks.IO_ORES, 1, 0));
        //naturalAluminum
        OreDictionary.registerOre("oreNaturalAluminum", new ItemStack(SolBlocks.MERCURY_ORES, 1, 0));
        OreDictionary.registerOre("oreNaturalAluminum", new ItemStack(SolBlocks.IO_ORES, 1, 0));
        //copper
        OreDictionary.registerOre("oreCopper", new ItemStack(SolBlocks.MERCURY_ORES, 1, 1));
        OreDictionary.registerOre("oreCopper", new ItemStack(SolBlocks.IO_ORES, 1, 1));
        //tin
        OreDictionary.registerOre("oreTin", new ItemStack(SolBlocks.MERCURY_ORES, 1, 6));
        //sulfur
        OreDictionary.registerOre("oreSulfur", new ItemStack(SolBlocks.IO_ORES, 1, 3));
        //ingot
        //sulfur
        OreDictionary.registerOre("ingotSulfur", SolItems.SULFUR_INGOT);
        //compressed
        //sulfur
        OreDictionary.registerOre("compressedSulfur", SolItems.COMPRESSED_SULFUR);
        //lithium
        OreDictionary.registerOre("compressedLithium", SolItems.COMPRESSED_LITHIUM);
        //shard
        //sulfur
        OreDictionary.registerOre("shardSulfur", SolItems.SULFUR_SHARD);
    }

    private static void registerDungeons() {
        TileEntity.register("sol:treasure_chest", mod.sol.tile.TileEntityTreasureChest.class);
        for (int tier = 4; tier <= 10; tier++) {
            int meta = tier - 4;
            GalacticraftRegistry.addDungeonLoot(tier, new ItemStack(SolItems.SCHEMATICS, 1, meta));
        }

        TileEntity.register("sol:universal_dungeon_spawner", TileEntityDungeonSpawnerSol.class);
        GCBlocks.hiddenBlocks.addAll(SolBlocks.BOSS_SPAWNERS.values());
    }

    private static void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new TheSol());
    }

    @SubscribeEvent
    public static void registerRocketPartRecipe(RegistryEvent.Register<IRecipe> event) {
        Item airVent = GCItems.oxygenVent;
        Item fuelCanisterPartial = GCItems.fuelCanister;
        Item gcCanister = GCItems.canister;

        Object[] tierMaterials = new Object[]{
                new OreIngredient("compressedTitanium"),
                new ItemStack(SolItems.COMPRESSED_SULFUR),
                new ItemStack(SolItems.COMPRESSED_MANGANESE),
                new ItemStack(SolItems.COMPRESSED_LITHIUM),
                new ItemStack(SolItems.COMPRESSED_MAGNESIUM),
                new ItemStack(SolItems.COMPRESSED_VANADIUM)
        };

        int[] woolDataList = new int[]{3, 3, 13, 10, 1, 9};

        for (int meta = 0; meta < 6; meta++) {
            int tier = meta + 4;
            ItemStack currentPlate = new ItemStack(SolItems.REINFORCED_PLATES, 1, meta);
            Object currentMaterial = tierMaterials[meta];

            if (airVent != null && fuelCanisterPartial != null) {
                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "engine_booster_t" + tier);
                event.getRegistry().register(new ShapedOreRecipe(regName, new ItemStack(SolItems.ENGINE_BOOSTERS, 1, meta),
                        "ZYZ", "ZWZ", "XVX",
                        'V', airVent,
                        'W', new ItemStack(fuelCanisterPartial, 1, 1),
                        'X', currentPlate,
                        'Y', new ItemStack(Blocks.WOOL, 1, woolDataList[meta]),
                        'Z', currentMaterial
                ).setRegistryName(regName));
            }

            if (tier >= 5 && gcCanister != null) {
                int targetMeta = meta - 1;
                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier);
                event.getRegistry().register(new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        " YV", "XWX", "XZX",
                        'V', Blocks.STONE_BUTTON, 'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate, 'Y', Items.FLINT_AND_STEEL, 'Z', airVent
                ).setRegistryName(regName));

                ResourceLocation regNameAlt = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier + "_alt");
                event.getRegistry().register(new ShapedOreRecipe(regNameAlt, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        "VY ", "XWX", "XZX",
                        'V', Blocks.STONE_BUTTON, 'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate, 'Y', Items.FLINT_AND_STEEL, 'Z', airVent
                ).setRegistryName(regNameAlt));
            }

            if (tier >= 5) {
                int targetMeta = meta - 1;
                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "nose_cone_t" + tier);
                event.getRegistry().register(new ShapedOreRecipe(regName, new ItemStack(SolItems.NOSE_CONES, 1, targetMeta),
                        " Y ", " X ", "X X",
                        'X', currentPlate, 'Y', Blocks.REDSTONE_TORCH
                ).setRegistryName(regName));
            }

            ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_fins_t" + tier);
            event.getRegistry().register(new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_FINS, 1, meta),
                    " Y ", "XYX", "X X",
                    'X', currentPlate, 'Y', currentMaterial
            ).setRegistryName(regName));
        }
    }
}
