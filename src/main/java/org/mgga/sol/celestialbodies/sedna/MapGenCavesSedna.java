package org.mgga.sol.celestialbodies.sedna;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesSedna extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.SEDNA_SURFACE_ROCK,
                SolBlocks.SEDNA_TURF,
                SolBlocks.SEDNA_SUB_SURFACE_ROCK,
                SolBlocks.SEDNA_ROCK
        };
    }
}
