package mod.sol.planets.pluto.biome;

import mod.sol.init.SolBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeFlatPluto extends BiomePluto {
    public BiomeFlatPluto(BiomeProperties properties) {
        super(properties);
        this.topBlock = SolBlocks.PLUTO_SURFACE_ROCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
