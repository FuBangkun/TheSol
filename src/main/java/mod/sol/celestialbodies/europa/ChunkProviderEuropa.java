package mod.sol.celestialbodies.europa;

import mod.sol.celestialbodies.ChunkProviderSolAdvanced;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;

import javax.annotation.Nonnull;

public class ChunkProviderEuropa extends ChunkProviderSolAdvanced {
    public ChunkProviderEuropa(World world, long seed) { super(world, seed); }
    @Override protected IBlockState getFillBlock() { return SolBlocks.EUROPA_ROCK.getDefaultState(); }
    @Override protected MapGenBaseMeta getCaveGenerator() { return new MapGenCavesEuropa(); }
    @Override
    protected void generateOcean(ChunkPrimer primer) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y <= 78; ++y) {
                    if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) {
                        primer.setBlockState(x, y, z, (y == 78) ? Blocks.PACKED_ICE.getDefaultState() : Blocks.WATER.getDefaultState());
                    }
                }
            }
        }
    }
    @Override
    protected void generateSurfaceFeatures(ChunkPrimer primer) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 127; ++y) {
                    if (primer.getBlockState(x, y, z) == SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState() && primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) {
                        primer.setBlockState(x, y, z, SolBlocks.EUROPA_SURFACE_ROCK.getDefaultState());
                        if (primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) primer.setBlockState(x, y + 1, z, Blocks.SNOW_LAYER.getDefaultState());
                    }
                }
            }
        }
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunkIn, int x, int z) {
    }
}