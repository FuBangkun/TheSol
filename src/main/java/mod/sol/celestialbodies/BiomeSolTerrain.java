package mod.sol.celestialbodies;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BiomeSolTerrain extends BiomeSol {
    protected final IBlockState customStoneBlock;
    protected final IBlockState customGravelBlock;

    public BiomeSolTerrain(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock, IBlockState stoneBlock, IBlockState gravelBlock, BiomeDictionary.Type... biomeTypes) {
        super(properties, topBlock, fillerBlock, biomeTypes);

        this.customStoneBlock = stoneBlock;
        this.customGravelBlock = gravelBlock != null ? gravelBlock : stoneBlock;
    }

    @Override
    public float getSpawningChance() {
        return 0.01F;
    }

    @Override
    public void genTerrainBlocks(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = worldIn.getSeaLevel();
        IBlockState currentTop = this.topBlock;
        IBlockState currentFiller = this.fillerBlock;

        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int localX = x & 15;
        int localZ = z & 15;

        for (int y = 255; y >= 0; --y) {
            if (y <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(localX, y, localZ, Blocks.BEDROCK.getDefaultState());
                continue;
            }

            IBlockState state = chunkPrimerIn.getBlockState(localX, y, localZ);

            if (state.getMaterial() == Material.AIR) {
                j = -1;
                continue;
            }

            if (state.getBlock() == customStoneBlock.getBlock()
                    || state.getBlock() == fillerBlock.getBlock()
                    || state.getBlock() == topBlock.getBlock()) {

                if (j == -1) {

                    if (k <= 0) {
                        currentTop = null;
                        currentFiller = customStoneBlock;
                    } else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
                        currentTop = this.topBlock;
                        currentFiller = this.fillerBlock;
                    }

                    j = k;

                    if (y >= seaLevel - 1) {
                        chunkPrimerIn.setBlockState(localX, y, localZ, currentTop);
                    } else if (y < seaLevel - 7 - k) {
                        currentTop = null;
                        currentFiller = customStoneBlock;
                        chunkPrimerIn.setBlockState(localX, y, localZ, customGravelBlock);
                    } else {
                        chunkPrimerIn.setBlockState(localX, y, localZ, currentFiller);
                    }

                } else if (j > 0) {
                    --j;
                    chunkPrimerIn.setBlockState(localX, y, localZ, currentFiller);
                }
            }
        }
    }
}