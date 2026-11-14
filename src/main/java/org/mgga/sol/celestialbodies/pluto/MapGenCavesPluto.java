package org.mgga.sol.celestialbodies.pluto;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesPluto extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.PLUTO_SURFACE_ROCK,
                SolBlocks.PLUTO_SUB_SURFACE_ROCK,
                SolBlocks.PLUTO_ROCK,
                Blocks.SNOW,
                Blocks.SNOW_LAYER
        };
    }
}
