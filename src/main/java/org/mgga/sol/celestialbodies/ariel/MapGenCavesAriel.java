package org.mgga.sol.celestialbodies.ariel;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesAriel extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.ARIEL_TURF,
                SolBlocks.ARIEL_DIRT,
                SolBlocks.ARIEL_ROCK
        };
    }
}
