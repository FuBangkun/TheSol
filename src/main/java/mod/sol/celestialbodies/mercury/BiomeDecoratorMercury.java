package mod.sol.celestialbodies.mercury;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorMercury extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator ilmenitGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator siliconGen;
    private final WorldGenerator tinGen;

    public BiomeDecoratorMercury() {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 0, true, SolBlocks.MERCURY_ROCK, 0);
        this.copperGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 1, true, SolBlocks.MERCURY_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 2, true, SolBlocks.MERCURY_ROCK, 0);
        this.ilmenitGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 3, true, SolBlocks.MERCURY_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 4, true, SolBlocks.MERCURY_ROCK, 0);
        this.siliconGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 5, true, SolBlocks.MERCURY_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.MERCURY_ORES, 8, 6, true, SolBlocks.MERCURY_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.MERCURY_DIRT, 10, 0, true, SolBlocks.MERCURY_ROCK, 0);
    }

    @Override
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(26, this.aluminumGen, 0, 60);
        this.genStandardOre(26, this.copperGen, 0, 60);
        this.genStandardOre(26, this.deshGen, 0, 60);
        this.genStandardOre(26, this.ilmenitGen, 0, 60);
        this.genStandardOre(26, this.ironGen, 0, 60);
        this.genStandardOre(26, this.siliconGen, 0, 60);
        this.genStandardOre(26, this.tinGen, 0, 60);
    }
}
