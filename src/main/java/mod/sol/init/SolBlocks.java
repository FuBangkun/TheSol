package mod.sol.init;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.TheSol;
import mod.sol.blocks.*;
import mod.sol.blocks.BlockBaseOre.OreVariant;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolBlocks {
    public static final List<Block> Blocks = new ArrayList<>();
    //Fluid
    public static final Block METHANE_FLUID_BLOCK = new BlockFluidBase("liquid_methane", SolFluid.METHANE, Material.WATER);
    //Misc
    public static final Block IRON_DECORATION_BLOCK = new BlockBase("iron_decoration_block", Material.IRON, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(4F);
    public static final Block IRON_WALL_BLOCK = new BlockBase("iron_wall_block", Material.IRON, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(4F);
    //Mercury
    public static final Block MERCURY_TURF = new BlockBase("mercury_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
    public static final Block MERCURY_DIRT = new BlockBase("mercury_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(3F);
    public static final Block MERCURY_ROCK = new BlockBase("mercury_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(6F);
    public static final Block MERCURY_DUNGEON_BRICK = new BlockBase("mercury_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T4 = new BlockTreasureChest(4);
    //Jupiter
    //Io
    public static final Block IO_SURFACE_ROCK = new BlockBase("io_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block IO_SULFUR_BLOCK = new BlockBase("io_sulfur_block", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(2F);
    public static final Block IO_ASH_BLOCK = new BlockBase("io_ash_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(2F);
    public static final Block IO_SUB_SURFACE_ROCK = new BlockBase("io_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block IO_ROCK = new BlockBase("io_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    //Europa
    public static final Block EUROPA_SURFACE_ROCK = new BlockBase("europa_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block EUROPA_SUB_SURFACE_ROCK = new BlockBase("europa_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block EUROPA_ROCK = new BlockBase("europa_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    //Jupiter misc
    public static final Block JUPITER_DUNGEON_BRICK = new BlockBase("jupiter_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T5 = new BlockTreasureChest(5);
    //Saturn
    //Mimas
    public static final Block MIMAS_TURF = new BlockBase("mimas_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(0.75F).setResistance(2F);
    public static final Block MIMAS_DIRT = new BlockBase("mimas_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1F).setResistance(3F);
    public static final Block MIMAS_ROCK = new BlockBase("mimas_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(6F);
    //Titan
    public static final Block TITAN_SURFACE_ROCK = new BlockBase("titan_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block TITAN_SUB_SURFACE_ROCK = new BlockBase("titan_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block TITAN_ROCK = new BlockBase("titan_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    //Saturn misc
    public static final Block SATURN_DUNGEON_BRICK = new BlockBase("saturn_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T6 = new BlockTreasureChest(6);
    //Ariel
    public static final Block ARIEL_TURF = new BlockBase("ariel_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block ARIEL_DIRT = new BlockBase("ariel_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block ARIEL_ROCK = new BlockBase("ariel_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    public static final Block CARBON_DIOXIDE = new BlockGas("carbon_dioxide", TheSol.BLOCK_TAB).setHardness(0.0F).setResistance(0F);
    //Uranus misc
    public static final Block URANUS_DUNGEON_BRICK = new BlockBase("uranus_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T7 = new BlockTreasureChest(7);
    //Triton
    public static final Block TRITON_SOFT_ROCK = new BlockBase("triton_soft_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block TRITON_SURFACE_ROCK = new BlockBase("triton_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block TRITON_TURF = new BlockBase("triton_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block TRITON_DIRT = new BlockBase("triton_dirt", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block TRITON_ROCK = new BlockBase("triton_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    //Uranus misc
    public static final Block NEPTUNE_DUNGEON_BRICK = new BlockBase("neptune_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T8 = new BlockTreasureChest(8);
    //Pluto
    public static final Block PLUTO_SURFACE_ROCK = new BlockBase("pluto_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block PLUTO_SUB_SURFACE_ROCK = new BlockBase("pluto_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block PLUTO_ROCK = new BlockBase("pluto_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    public static final Block PLUTO_DUNGEON_BRICK = new BlockBase("pluto_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T9 = new BlockTreasureChest(9);
    //Sedna
    public static final Block SEDNA_TURF = new BlockBase("sedna_turf", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block SEDNA_SURFACE_ROCK = new BlockBase("sedna_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.25F).setResistance(2F);
    public static final Block SEDNA_SUB_SURFACE_ROCK = new BlockBase("sedna_sub_surface_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(1.5F).setResistance(3F);
    public static final Block SEDNA_ROCK = new BlockBase("sedna_rock", Material.ROCK, "pickaxe", 0, TheSol.BLOCK_TAB).setHardness(2F).setResistance(6F);
    public static final Block SEDNA_DUNGEON_BRICK = new BlockBase("sedna_dungeon_brick", Material.ROCK, "pickaxe", 1, TheSol.BLOCK_TAB).setHardness(4F).setResistance(40F);
    public static final Block TREASURE_CHEST_T10 = new BlockTreasureChest(10);
    //Spawner
    public static Block BOSS_SPAWNER_MERCURY = new BlockBossSpawnerMercury("boss_spawner_mercury");
    public static Block BOSS_SPAWNER_JUPITER = new BlockBossSpawnerJupiter("boss_spawner_jupiter");
    public static Block BOSS_SPAWNER_SATURN = new BlockBossSpawnerSaturn("boss_spawner_saturn");
    public static Block BOSS_SPAWNER_URANUS = new BlockBossSpawnerUranus("boss_spawner_uranus");
    public static Block BOSS_SPAWNER_NEPTUNE = new BlockBossSpawnerNeptune("boss_spawner_neptune");
    public static Block BOSS_SPAWNER_PLUTO = new BlockBossSpawnerPluto("boss_spawner_pluto");
    public static Block BOSS_SPAWNER_SEDNA = new BlockBossSpawnerSedna("boss_spawner_sedna");

    public static final Block MERCURY_ORES;
    public static final Block IO_ORES;
    public static final Block EUROPA_ORES;
    public static final Block MIMAS_ORES;
    public static final Block TITAN_ORES;
    public static final Block ARIEL_ORES;
    public static final Block TRITON_ORES;
    public static final Block PLUTO_ORES;
    public static final Block SEDNA_ORES;

    static {
        // ==================== MERCURY ====================
        List<OreVariant> mercuryList = new ArrayList<>();
        mercuryList.add(new OreVariant("aluminum_ore", 1)); // Meta 0
        mercuryList.add(new OreVariant("copper_ore", 1));   // Meta 1
        mercuryList.add(new OreVariant("desh_ore", 1, new ItemStack(MarsItems.marsItemBasic, 2, 0))); // Meta 2
        mercuryList.add(new OreVariant("ilmenite_ore", 1, Arrays.asList(new ItemStack(AsteroidsItems.basicItem, 1, 3), new ItemStack(AsteroidsItems.basicItem, 1, 4)))); // Meta 3
        mercuryList.add(new OreVariant("iron_ore", 1));     // Meta 4
        mercuryList.add(new OreVariant("silicon_ore", 1, new ItemStack(GCItems.basicItem, 3, 2))); // Meta 5
        mercuryList.add(new OreVariant("tin_ore", 1));      // Meta 6
        MERCURY_ORES = new BlockBaseOre("mercury", mercuryList).setResistance(8F);

        // ==================== IO ====================
        List<OreVariant> ioList = new ArrayList<>();
        ioList.add(new OreVariant("aluminum_ore", 0)); // Meta 0
        ioList.add(new OreVariant("copper_ore", 0));   // Meta 1
        ioList.add(new OreVariant("iron_ore", 0));     // Meta 2
        ioList.add(new OreVariant("sulfur_ore", 0, new ItemStack(SolItems.SULFUR_SHARD, 2))); // Meta 3
        IO_ORES = new BlockBaseOre("io", ioList).setResistance(6F);

        // ==================== EUROPA ====================
        List<OreVariant> europaList = new ArrayList<>();
        europaList.add(new OreVariant("aluminum_ore", 0)); // Meta 0
        europaList.add(new OreVariant("copper_ore", 0));   // Meta 1
        europaList.add(new OreVariant("iron_ore", 0));     // Meta 2
        EUROPA_ORES = new BlockBaseOre("europa", europaList).setResistance(6F);

        // ==================== MIMAS ====================
        List<OreVariant> mimasList = new ArrayList<>();
        mimasList.add(new OreVariant("copper_ore", 1)); // Meta 0
        mimasList.add(new OreVariant("desh_ore", 1, new ItemStack(MarsItems.marsItemBasic, 2, 0))); // Meta 1
        mimasList.add(new OreVariant("iron_ore", 1));   // Meta 2
        mimasList.add(new OreVariant("manganese_ore", 2)); // Meta 3
        mimasList.add(new OreVariant("tin_ore", 1));    // Meta 4
        MIMAS_ORES = new BlockBaseOre("mimas", mimasList).setResistance(8F);

        // ==================== TITAN ====================
        List<OreVariant> titanList = new ArrayList<>();
        titanList.add(new OreVariant("aluminum_ore", 1)); // Meta 0
        titanList.add(new OreVariant("desh_ore", 1, new ItemStack(MarsItems.marsItemBasic, 2, 0))); // Meta 1
        titanList.add(new OreVariant("diamond_ore", 3, new ItemStack(Items.DIAMOND, 3, 0))); // Meta 2
        titanList.add(new OreVariant("iron_ore", 1)); // Meta 3
        titanList.add(new OreVariant("manganese_ore", 2)); // Meta 4
        titanList.add(new OreVariant("tin_ore", 1));    // Meta 5
        TITAN_ORES = new BlockBaseOre("titan", titanList).setResistance(8F);

        // ==================== ARIEL ====================
        List<OreVariant> arielList = new ArrayList<>();
        arielList.add(new OreVariant("aluminum_ore", 1)); // Meta 0
        arielList.add(new OreVariant("desh_ore", 1, new ItemStack(MarsItems.marsItemBasic, 2, 0))); // Meta 1
        arielList.add(new OreVariant("iron_ore", 1));   // Meta 2
        arielList.add(new OreVariant("ilmenite_ore", 1, Arrays.asList(new ItemStack(AsteroidsItems.basicItem, 1, 3), new ItemStack(AsteroidsItems.basicItem, 1, 4)))); // Meta 3
        arielList.add(new OreVariant("lithium_ore", 0));  // Meta 4
        arielList.add(new OreVariant("tin_ore", 1));    // Meta 5
        ARIEL_ORES = new BlockBaseOre("ariel", arielList).setResistance(8F);

        // ==================== TRITON ====================
        List<OreVariant> tritonList = new ArrayList<>();
        tritonList.add(new OreVariant("aluminum_ore", 1));  // Meta 0
        tritonList.add(new OreVariant("desh_ore", 1, new ItemStack(MarsItems.marsItemBasic, 2, 0))); // Meta 1
        tritonList.add(new OreVariant("iron_ore", 1));      // Meta 2
        tritonList.add(new OreVariant("ilmenite_ore", 1, Arrays.asList(new ItemStack(AsteroidsItems.basicItem, 1, 3), new ItemStack(AsteroidsItems.basicItem, 1, 4)))); // Meta 3
        tritonList.add(new OreVariant("lithium_ore", 0));   // Meta 4
        tritonList.add(new OreVariant("magnesium_ore", 1)); // Meta 5
        tritonList.add(new OreVariant("magnet_ore", 1));    // Meta 6
        tritonList.add(new OreVariant("silicon_ore", 1, new ItemStack(GCItems.basicItem, 3, 2))); // Meta 7
        tritonList.add(new OreVariant("tin_ore", 1));       // Meta 8
        TRITON_ORES = new BlockBaseOre("triton", tritonList).setResistance(8F);

        // ==================== PLUTO ====================
        List<OreVariant> plutoList = new ArrayList<>();
        plutoList.add(new OreVariant("copper_ore", 0));   // Meta 0
        plutoList.add(new OreVariant("iron_ore", 0));     // Meta 1
        plutoList.add(new OreVariant("tin_ore", 0));      // Meta 2
        plutoList.add(new OreVariant("vanadium_ore", 0)); // Meta 3
        PLUTO_ORES = new BlockBaseOre("pluto", plutoList).setResistance(6F);

        // ==================== SEDNA ====================
        List<OreVariant> sednaList = new ArrayList<>();
        sednaList.add(new OreVariant("copper_ore", 0));   // Meta 0
        sednaList.add(new OreVariant("iron_ore", 0));     // Meta 1
        sednaList.add(new OreVariant("osmium_ore", 0));   // Meta 2
        sednaList.add(new OreVariant("tin_ore", 0));      // Meta 3
        sednaList.add(new OreVariant("vanadium_ore", 0)); // Meta 4
        SEDNA_ORES = new BlockBaseOre("sedna", sednaList).setResistance(6F);
    }
}