package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BiomeDecoratorSolBase extends BiomeDecorator {
    protected World world;
    protected Random randomGenerator;

    @Override
    public void decorate(@Nonnull World worldIn, @Nonnull Random random, @Nonnull Biome biome, @Nonnull BlockPos pos) {
        if (this.world != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.world = worldIn;
            this.randomGenerator = random;
            this.chunkPos = pos;
            MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.world, this.randomGenerator, this.chunkPos));

            this.generateCustomOres();

            MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.world, this.randomGenerator, this.chunkPos));

            this.world = null;
            this.randomGenerator = null;
        }
    }

    protected void genStandardOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY) {
        for (int i = 0; i < amountPerChunk; ++i) {
            BlockPos blockpos = this.chunkPos.add(
                    this.randomGenerator.nextInt(16),
                    this.randomGenerator.nextInt(maxY - minY) + minY,
                    this.randomGenerator.nextInt(16)
            );
            worldGenerator.generate(this.world, this.randomGenerator, blockpos);
        }
    }

    protected abstract void generateCustomOres();
}