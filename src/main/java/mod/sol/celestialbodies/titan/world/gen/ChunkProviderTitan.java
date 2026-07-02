package mod.sol.celestialbodies.titan.world.gen;

import mod.sol.celestialbodies.ChunkProviderSolAdvanced;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;

import javax.annotation.Nonnull;

public class ChunkProviderTitan extends ChunkProviderSolAdvanced {
    public ChunkProviderTitan(World world, long seed) { super(world, seed); }
    @Override protected IBlockState getFillBlock() { return SolBlocks.TITAN_ROCK.getDefaultState(); }
    @Override protected MapGenBaseMeta getCaveGenerator() { return new MapGenCavesTitan(); }
    @Override
    protected void generateOcean(ChunkPrimer primer) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y <= 62; ++y) {
                    if (primer.getBlockState(x, y, z) == Blocks.AIR.getDefaultState()) {
                        primer.setBlockState(x, y, z, SolBlocks.METHANE_FLUID_BLOCK.getDefaultState());
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
                    if (primer.getBlockState(x, y, z) == SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState() && primer.getBlockState(x, y + 1, z) == Blocks.AIR.getDefaultState()) {
                        primer.setBlockState(x, y, z, SolBlocks.TITAN_SURFACE_ROCK.getDefaultState());
                    }
                }
            }
        }
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunkIn, int x, int z) {
    }
}