package mod.sol.planets.pluto.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeSnowfieldPluto extends BiomePluto {
    public BiomeSnowfieldPluto(BiomeProperties properties) {
        super(properties);
        this.topBlock = Blocks.SNOW.getDefaultState();
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
