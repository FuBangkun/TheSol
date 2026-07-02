package mod.sol.celestialbodies.mimas.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.celestialbodies.mimas.world.gen.ChunkProviderMimas;
import mod.sol.init.SolBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeFlatMimas extends BiomeGenBaseGC {
    public BiomeFlatMimas(BiomeProperties properties) {
        super(properties, true);
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorMimas());
    }

    @Override
    public void genTerrainBlocks(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.topBlock = SolBlocks.MIMAS_TURF.getDefaultState();
        this.fillerBlock = SolBlocks.MIMAS_ROCK.getDefaultState();
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
