package mod.sol.celestialbodies.europa;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorEuropa extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator ironGen;

    public BiomeDecoratorEuropa() {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.EUROPA_ORES, 6, 0, true, SolBlocks.EUROPA_ROCK, 0);
        this.copperGen = new WorldGenMinableMeta(SolBlocks.EUROPA_ORES, 6, 1, true, SolBlocks.EUROPA_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.EUROPA_ORES, 6, 2, true, SolBlocks.EUROPA_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.EUROPA_SUB_SURFACE_ROCK, 10, 0, true, SolBlocks.EUROPA_ROCK, 0);
    }

    @Override
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(18, this.aluminumGen, 0, 60);
        this.genStandardOre(18, this.copperGen, 0, 60);
        this.genStandardOre(20, this.ironGen, 0, 60);
    }
}