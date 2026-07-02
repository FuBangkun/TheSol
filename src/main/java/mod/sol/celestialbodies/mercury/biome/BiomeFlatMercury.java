package mod.sol.celestialbodies.mercury.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.init.SolBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeFlatMercury extends BiomeGenBaseGC {
    public BiomeFlatMercury(BiomeProperties properties) {
        super(properties, true);
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorMercury());
    }

    @Override
    public void genTerrainBlocks(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.topBlock = SolBlocks.MERCURY_TURF.getDefaultState();
        this.fillerBlock = SolBlocks.MERCURY_ROCK.getDefaultState();
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
