package mod.sol.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

public class SolRecipeSmelting {

    public static void registryRecipe() {
        GameRegistry.addSmelting(SolItems.SULFUR_SHARD, new ItemStack(SolItems.SULFUR_INGOT, 1, 0), 1F);

        Map<Class<? extends Block>, ItemStack> oreSmeltingMap = new HashMap<>();

        oreSmeltingMap.put(BlockBaseAluminumOre.class, new ItemStack(GCItems.basicItem, 1, 5));
        oreSmeltingMap.put(BlockBaseCopperOre.class,   new ItemStack(GCItems.basicItem, 1, 3));
        oreSmeltingMap.put(BlockBaseTinOre.class,      new ItemStack(GCItems.basicItem, 1, 4));
        oreSmeltingMap.put(BlockBaseSiliconOre.class,  new ItemStack(GCItems.basicItem, 1, 2));
        oreSmeltingMap.put(BlockBaseIronOre.class,     new ItemStack(Items.IRON_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseDiamondOre.class,  new ItemStack(Items.DIAMOND, 2, 0));
        oreSmeltingMap.put(BlockBaseDeshOre.class,     new ItemStack(MarsItems.marsItemBasic, 1, 2));
        oreSmeltingMap.put(BlockBaseIlmeniteOre.class, new ItemStack(AsteroidsItems.basicItem, 1, 0));

        oreSmeltingMap.put(BlockBaseMagnesiumOre.class, new ItemStack(SolItems.MAGNESIUM_INGOT));
        oreSmeltingMap.put(BlockBaseMagnetOre.class,    new ItemStack(SolItems.MAGNET_INGOT));
        oreSmeltingMap.put(BlockBaseManganeseOre.class, new ItemStack(SolItems.MANGANESE_INGOT));
        oreSmeltingMap.put(BlockBaseSulfurOre.class,    new ItemStack(SolItems.SULFUR_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseLithiumOre.class,   new ItemStack(SolItems.LITHIUM_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseVanadiumOre.class,  new ItemStack(SolItems.VANADIUM_INGOT, 1, 0));
        oreSmeltingMap.put(BlockBaseOsmiumOre.class,    new ItemStack(SolItems.OSMIUM_INGOT, 1, 0));

        for (Block block : SolBlocks.Blocks) {
            if (oreSmeltingMap.containsKey(block.getClass())) {
                ItemStack output = oreSmeltingMap.get(block.getClass());
                GameRegistry.addSmelting(block, output, 1.0F);
            }
        }
    }
}