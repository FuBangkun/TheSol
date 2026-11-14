package org.mgga.sol.celestialbodies.europa;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

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
