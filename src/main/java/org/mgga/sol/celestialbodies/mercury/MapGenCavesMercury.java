package org.mgga.sol.celestialbodies.mercury;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesMercury extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.MERCURY_TURF,
                SolBlocks.MERCURY_DIRT,
                SolBlocks.MERCURY_ROCK
        };
    }
}
