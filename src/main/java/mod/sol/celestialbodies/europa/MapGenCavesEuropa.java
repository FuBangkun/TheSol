package mod.sol.celestialbodies.europa;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class MapGenCavesEuropa extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.EUROPA_ROCK,
                SolBlocks.EUROPA_SUB_SURFACE_ROCK,
                SolBlocks.EUROPA_SURFACE_ROCK,
                Blocks.PACKED_ICE,
                Blocks.SNOW_LAYER,
                Blocks.WATER
        };
    }

    @Override
    protected int getLiquidLevel() {
        return 78;
    }
}
