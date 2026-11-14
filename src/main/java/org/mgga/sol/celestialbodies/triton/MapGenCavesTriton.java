package org.mgga.sol.celestialbodies.triton;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesTriton extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.TRITON_SOFT_ROCK,
                SolBlocks.TRITON_TURF,
                SolBlocks.TRITON_SURFACE_ROCK,
                SolBlocks.TRITON_ROCK,
                SolBlocks.TRITON_DIRT
        };
    }
}
