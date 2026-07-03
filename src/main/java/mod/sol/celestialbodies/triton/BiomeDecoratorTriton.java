package mod.sol.celestialbodies.triton;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorTriton extends BiomeDecoratorSolBase {
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
    protected void generateCustomOres() {
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
    }
}
