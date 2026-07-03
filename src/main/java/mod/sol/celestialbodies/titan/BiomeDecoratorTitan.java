package mod.sol.celestialbodies.titan;

import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import mod.sol.celestialbodies.BiomeDecoratorSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorTitan extends BiomeDecoratorSolBase {
    private final WorldGenerator dirtGen;
    private final WorldGenerator aluminumGen;
    private final WorldGenerator deshGen;
    private final WorldGenerator diamondGen;
    private final WorldGenerator ilmeniteGen;
    private final WorldGenerator maganeseGen;
    private final WorldGenerator tinGen;

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
    protected void generateCustomOres() {
        this.genStandardOre(20, this.dirtGen, 0, 200);
        this.genStandardOre(20, this.aluminumGen, 0, 100);
        this.genStandardOre(16, this.deshGen, 0, 80);
        this.genStandardOre(8, this.diamondGen, 0, 30);
        this.genStandardOre(22, this.ilmeniteGen, 0, 120);
        this.genStandardOre(14, this.maganeseGen, 0, 40);
        this.genStandardOre(20, this.tinGen, 0, 100);
    }
}
