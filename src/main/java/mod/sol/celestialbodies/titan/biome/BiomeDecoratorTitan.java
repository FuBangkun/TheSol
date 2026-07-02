package mod.sol.celestialbodies.titan.biome;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.init.SolBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeDecoratorTitan extends BiomeDecorator {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator diamondGen;
    private final WorldGenerator ilmeniteGen;
    private final WorldGenerator maganeseGen;
    private final WorldGenerator tinGen;
    private World world;
    private Random randomGenerator;

    public BiomeDecoratorTitan() {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 6, 0, true, SolBlocks.TITAN_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 6, 1, true, SolBlocks.TITAN_ROCK, 0);
        this.diamondGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 6, 2, true, SolBlocks.TITAN_ROCK, 0);
        this.ilmeniteGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 6, 3, true, SolBlocks.TITAN_ROCK, 0);
        this.maganeseGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 4, 4, true, SolBlocks.TITAN_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.TITAN_ORES, 6, 5, true, SolBlocks.TITAN_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.TITAN_SUB_SURFACE_ROCK, 10, 0, true, SolBlocks.TITAN_ROCK, 0);
    }

    @Override
    public void decorate(@Nonnull World worldIn, @Nonnull Random random, @Nonnull Biome p_180292_3_, @Nonnull BlockPos pos) {
        if (this.world != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.world = worldIn;
            this.randomGenerator = random;
            this.chunkPos = pos;
            this.generateIo();
            this.world = null;
            this.randomGenerator = null;
        }
    }

    private void genStandardOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY) {
        for (int var5 = 0; var5 < amountPerChunk; ++var5) {
            BlockPos blockpos = this.chunkPos.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(maxY - minY) + minY, this.randomGenerator.nextInt(16));
            worldGenerator.generate(this.world, this.randomGenerator, blockpos);
        }
    }

    private void generateIo() {
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.world, this.randomGenerator, chunkPos));
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(20, this.aluminumGen, 0, 100);
        this.genStandardOre(16, this.deshGen, 0, 80);
        this.genStandardOre(8, this.diamondGen, 0, 30);
        this.genStandardOre(22, this.ilmeniteGen, 0, 120);
        this.genStandardOre(14, this.maganeseGen, 0, 40);
        this.genStandardOre(20, this.tinGen, 0, 100);
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.world, this.randomGenerator, chunkPos));
    }
}
