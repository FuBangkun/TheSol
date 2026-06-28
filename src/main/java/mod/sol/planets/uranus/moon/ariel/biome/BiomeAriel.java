package mod.sol.planets.uranus.moon.ariel.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeAriel extends BiomeGenBaseGC {
    public static final Biome arielFlat = new BiomeFlatAriel(new BiomeProperties("Ariel").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeAriel(BiomeProperties properties) {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorAriel());
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}
