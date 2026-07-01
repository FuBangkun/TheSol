package mod.sol.planets.neptune.triton.biome;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.init.SolBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

public class BiomeDecoratorTriton extends BiomeDecorator {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator ilmenitGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator lithiumGen;
    private final WorldGenerator magnesiumGen;
    private final WorldGenerator magnetGen;
    private final WorldGenerator siliconGen;
    private final WorldGenerator tinGen;
    private World world;
    private Random randomGenerator;

    public BiomeDecoratorTriton() {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 0, true, SolBlocks.TRITON_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 1, true, SolBlocks.TRITON_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 2, true, SolBlocks.TRITON_ROCK, 0);
        this.ilmenitGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 3, true, SolBlocks.TRITON_ROCK, 0);
        this.lithiumGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 4, 4, true, SolBlocks.TRITON_ROCK, 0);
        this.magnesiumGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 5, true, SolBlocks.TRITON_ROCK, 0);
        this.magnetGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 5, 6, true, SolBlocks.TRITON_ROCK, 0);
        this.siliconGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 7, true, SolBlocks.TRITON_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.TRITON_ORES, 6, 8, true, SolBlocks.TRITON_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.TRITON_DIRT, 10, 0, true, SolBlocks.TRITON_ROCK, 0);
    }

    @Override
    public void decorate(World worldIn, Random random, Biome p_180292_3_, BlockPos pos) {
        if (this.world != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.world = worldIn;
            this.randomGenerator = random;
            this.chunkPos = pos;
            this.generateMercury();
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

    private void generateMercury() {
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.world, this.randomGenerator, chunkPos));
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(18, this.aluminumGen, 0, 60);
        this.genStandardOre(16, this.deshGen, 0, 60);
        this.genStandardOre(16, this.ilmenitGen, 0, 60);
        this.genStandardOre(18, this.ironGen, 0, 60);
        this.genStandardOre(12, this.lithiumGen, 0, 60);
        this.genStandardOre(16, this.magnesiumGen, 0, 60);
        this.genStandardOre(14, this.magnetGen, 0, 60);
        this.genStandardOre(26, this.siliconGen, 0, 60);
        this.genStandardOre(26, this.tinGen, 0, 60);
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.world, this.randomGenerator, chunkPos));
    }
}
