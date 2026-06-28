package mod.sol.planets.saturn.moons.mimas.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.planets.saturn.moons.mimas.world.gen.ChunkProviderMimas;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class BiomeMimas extends BiomeGenBaseGC {
    public static final Biome mimasFlat = new BiomeFlatMimas(new BiomeProperties("Mimas").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeMimas(BiomeProperties properties) {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorMimas());
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.fillerBlock = ChunkProviderMimas.BLOCK_LOWER;
        this.topBlock = ChunkProviderMimas.BLOCK_TOP;
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}
