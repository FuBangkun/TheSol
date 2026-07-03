package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public abstract class BiomeSol extends BiomeGenBaseGC {
    private final BiomeDictionary.Type[] biomeTypes;

    public BiomeSol(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock, BiomeDictionary.Type... biomeTypes) {
        super(properties, true);
        this.biomeTypes = (biomeTypes.length == 0)
                ? new BiomeDictionary.Type[]{BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD}
                : biomeTypes;

        if (topBlock != null) this.topBlock = topBlock;
        if (fillerBlock != null) this.fillerBlock = fillerBlock;
    }

    public BiomeSol(BiomeProperties properties, BiomeDictionary.Type... biomeTypes) {
        this(properties, null, null, biomeTypes);
    }

    @Override
    public void registerTypes(Biome b) {
        if (this.biomeTypes != null && this.biomeTypes.length > 0) {
            BiomeDictionary.addTypes(b, this.biomeTypes);
        }
    }
}