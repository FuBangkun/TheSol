package mod.sol.celestialbodies.europa.biome;

import mod.sol.init.SolBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeGenMountainEuropa extends BiomeEuropa {
    public BiomeGenMountainEuropa(BiomeProperties properties) {
        super(properties);
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }

    @Override
    public void genTerrainBlocks(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull ChunkPrimer chunkPrimerIn, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
        this.topBlock = SolBlocks.EUROPA_SURFACE_ROCK.getDefaultState();
        this.fillerBlock = SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState();
        super.generateBiomeTerrainVenus(worldIn, rand, chunkPrimerIn, p_180622_4_, p_180622_5_, p_180622_6_);
    }
}
