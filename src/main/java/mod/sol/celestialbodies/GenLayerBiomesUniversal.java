package mod.sol.celestialbodies;

import micdoodle8.mods.miccore.IntCache;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;

import javax.annotation.Nonnull;

public class GenLayerBiomesUniversal extends GenLayer {
    private final Biome[] biomes;

    public GenLayerBiomesUniversal(long seed, Biome[] biomes) {
        super(seed);
        this.biomes = biomes;
    }

    @Nonnull
    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);

        for (int k = 0; k < depth; ++k) {
            for (int i = 0; i < width; ++i) {
                initChunkSeed(x + i, z + k);
                dest[i + k * width] = Biome.getIdForBiome(biomes[nextInt(biomes.length)]);
            }
        }

        return dest;
    }
}