package org.mgga.sol.celestialbodies.io;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesIo extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.IO_ASH_BLOCK,
                SolBlocks.IO_ROCK,
                SolBlocks.IO_SUB_SURFACE_ROCK,
                SolBlocks.IO_SULFUR_BLOCK,
                SolBlocks.IO_SURFACE_ROCK
        };
    }
}
