package mod.sol.celestialbodies.mimas;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorMimas extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator maganeseGen;
    private final WorldGenerator tinGen;

    public BiomeDecoratorMimas() {
        this.copperGen = new WorldGenMinableMeta(SolBlocks.MIMAS_ORES, 8, 0, true, SolBlocks.MIMAS_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.MIMAS_ORES, 8, 1, true, SolBlocks.MIMAS_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.MIMAS_ORES, 8, 2, true, SolBlocks.MIMAS_ROCK, 0);
        this.maganeseGen = new WorldGenMinableMeta(SolBlocks.MIMAS_ORES, 6, 3, true, SolBlocks.MIMAS_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.MIMAS_ORES, 8, 4, true, SolBlocks.MIMAS_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.MIMAS_DIRT, 10, 0, true, SolBlocks.MIMAS_ROCK, 0);
    }

    @Override
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(14, this.copperGen, 0, 60);
        this.genStandardOre(12, this.deshGen, 0, 60);
        this.genStandardOre(14, this.ironGen, 0, 60);
        this.genStandardOre(8, this.maganeseGen, 0, 60);
        this.genStandardOre(13, this.tinGen, 0, 60);
    }
}
