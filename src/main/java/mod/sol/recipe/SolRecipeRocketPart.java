package mod.sol.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import mod.sol.Tags;
import mod.sol.init.SolItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class SolRecipeRocketPart {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
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