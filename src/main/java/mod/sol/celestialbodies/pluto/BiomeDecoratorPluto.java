package mod.sol.celestialbodies.pluto;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorPluto extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator copperGen;
    private final WorldGenerator ironGen;
    private final WorldGenerator tinGen;
    private final WorldGenerator vanadiumGen;

    public BiomeDecoratorPluto() {
        this.copperGen = new WorldGenMinableMeta(SolBlocks.PLUTO_ORES, 6, 0, true, SolBlocks.PLUTO_ROCK, 0);
        this.ironGen = new WorldGenMinableMeta(SolBlocks.PLUTO_ORES, 6, 1, true, SolBlocks.PLUTO_ROCK, 0);
        this.tinGen = new WorldGenMinableMeta(SolBlocks.PLUTO_ORES, 6, 2, true, SolBlocks.PLUTO_ROCK, 0);
        this.vanadiumGen = new WorldGenMinableMeta(SolBlocks.PLUTO_ORES, 4, 3, true, SolBlocks.PLUTO_ROCK, 0);
        this.dirtGen = new WorldGenMinableMeta(SolBlocks.PLUTO_SUB_SURFACE_ROCK, 10, 0, true, SolBlocks.PLUTO_ROCK, 0);
    }

    @Override
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(16, this.copperGen, 0, 60);
        this.genStandardOre(14, this.ironGen, 0, 60);
        this.genStandardOre(16, this.tinGen, 0, 60);
        this.genStandardOre(14, this.vanadiumGen, 0, 60);
    }
}
