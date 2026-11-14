package org.mgga.sol.celestialbodies.mimas;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesMimas extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.MIMAS_TURF,
                SolBlocks.MIMAS_DIRT,
                SolBlocks.MIMAS_ROCK
        };
    }
}
