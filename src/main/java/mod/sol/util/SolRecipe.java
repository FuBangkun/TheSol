package mod.sol.util;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.Tags;
import mod.sol.blocks.*;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class SolRecipe {
    public static void registryCompressorRecipe() {
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

    public static void registrySmeltingRecipe() {
        GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);

        Map<Class<? extends Block>, ItemStack> oreSmeltingMap = new HashMap<>();

        oreSmeltingMap.put(BlockBaseAluminumOre.class, new ItemStack(GCItems.basicItem, 1, 5));
        oreSmeltingMap.put(BlockBaseCopperOre.class, new ItemStack(GCItems.basicItem, 1, 3));
        oreSmeltingMap.put(BlockBaseTinOre.class, new ItemStack(GCItems.basicItem, 1, 4));
        oreSmeltingMap.put(BlockBaseSiliconOre.class, new ItemStack(GCItems.basicItem, 1, 2));
        oreSmeltingMap.put(BlockBaseIronOre.class, new ItemStack(Items.IRON_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseDiamondOre.class, new ItemStack(Items.DIAMOND, 2, 0));
        oreSmeltingMap.put(BlockBaseDeshOre.class, new ItemStack(MarsItems.marsItemBasic, 1, 2));
        oreSmeltingMap.put(BlockBaseIlmeniteOre.class, new ItemStack(AsteroidsItems.basicItem, 1, 0));

        oreSmeltingMap.put(BlockBaseMagnesiumOre.class, new ItemStack(SolItems.MAGNESIUM_INGOT));
        oreSmeltingMap.put(BlockBaseMagnetOre.class, new ItemStack(SolItems.MAGNET_INGOT));
        oreSmeltingMap.put(BlockBaseManganeseOre.class, new ItemStack(SolItems.MANGANESE_INGOT));
        oreSmeltingMap.put(BlockBaseSulfurOre.class, new ItemStack(SolItems.SULFUR_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseLithiumOre.class, new ItemStack(SolItems.LITHIUM_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseVanadiumOre.class, new ItemStack(SolItems.VANADIUM_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseOsmiumOre.class, new ItemStack(SolItems.OSMIUM_INGOT, 1, 0));

        for (Block block : SolBlocks.Blocks) {
            if (oreSmeltingMap.containsKey(block.getClass())) {
                ItemStack output = oreSmeltingMap.get(block.getClass());
                GameRegistry.addSmelting(block, output, 1.0F);
            }
        }
    }

    public static void registerRocketRecipe(int tier, ItemStack noseCone, ItemStack plate, ItemStack booster,
                                            ItemStack fins, ItemStack engine, ItemStack previousRocket, ItemStack outputRocket) {
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
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ENGINE_BOOSTERS, 1, meta),
                        "ZYZ",
                        "ZWZ",
                        "XVX",
                        'V', airVent,
                        'W', new ItemStack(fuelCanisterPartial, 1, 1),
                        'X', currentPlate,
                        'Y', new ItemStack(Blocks.WOOL, 1, woolDataList[meta]),
                        'Z', currentMaterial
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);
            }

            if (tier >= 5 && gcCanister != null) {
                int targetMeta = meta - 1;

                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier);
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        " YV",
                        "XWX",
                        "XZX",
                        'V', Blocks.STONE_BUTTON,
                        'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate,
                        'Y', Items.FLINT_AND_STEEL,
                        'Z', airVent
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);

                ResourceLocation regNameAlt = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier + "_alt");
                IRecipe recipeAlt = new ShapedOreRecipe(regNameAlt, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        "VY ",
                        "XWX",
                        "XZX",
                        'V', Blocks.STONE_BUTTON,
                        'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate,
                        'Y', Items.FLINT_AND_STEEL,
                        'Z', airVent
                ).setRegistryName(regNameAlt);
                event.getRegistry().register(recipeAlt);
            }

            if (tier >= 5) {
                int targetMeta = meta - 1;

                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "nose_cone_t" + tier);
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.NOSE_CONES, 1, targetMeta),
                        " Y ",
                        " X ",
                        "X X",
                        'X', currentPlate,
                        'Y', Blocks.REDSTONE_TORCH
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);
            }

            ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_fins_t" + tier);
            IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_FINS, 1, meta),
                    " Y ",
                    "XYX",
                    "X X",
                    'X', currentPlate,
                    'Y', currentMaterial
            ).setRegistryName(regName);
            event.getRegistry().register(recipe);
        }
    }
}
