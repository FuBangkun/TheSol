package mod.sol.celestialbodies.ariel;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorAriel extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator ilmenitGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator lithiumGen;
    private final WorldGenerator surfaceCarbonGen;
    private final WorldGenerator tinGen;

    public BiomeDecoratorAriel() {
        this.aluminumGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 6, 0, true, SolBlocks.ARIEL_ROCK, 0);
        this.deshGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 6, 1, true, SolBlocks.ARIEL_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 6, 2, true, SolBlocks.ARIEL_ROCK, 0);
        this.ilmenitGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 6, 3, true, SolBlocks.ARIEL_ROCK, 0);
        this.lithiumGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 4, 4, true, SolBlocks.ARIEL_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.ARIEL_ORES, 6, 5, true, SolBlocks.ARIEL_ROCK, 0);
        this.surfaceCarbonGen = new WorldGenMinableMeta(SolBlocks.CARBON_DIOXIDE, 8, 0, true, SolBlocks.ARIEL_TURF, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.ARIEL_DIRT, 10, 0, true, SolBlocks.ARIEL_ROCK, 0);
    }

    @Override
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(18, this.aluminumGen, 0, 60);
        this.genStandardOre(16, this.deshGen, 0, 60);
        this.genStandardOre(16, this.ilmenitGen, 0, 60);
        this.genStandardOre(18, this.ironGen, 0, 60);
        this.genStandardOre(12, this.lithiumGen, 0, 60);
        this.genStandardOre(26, this.tinGen, 0, 60);
        this.genStandardOre(4, this.surfaceCarbonGen, 55, 70);
    }
}