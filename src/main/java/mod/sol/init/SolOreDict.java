package mod.sol.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SolOreDict {
    public static void registerOres() {
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
}
