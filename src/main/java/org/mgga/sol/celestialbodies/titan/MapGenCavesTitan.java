package org.mgga.sol.celestialbodies.titan;

import net.minecraft.block.Block;
import org.mgga.sol.celestialbodies.MapGenCavesSolBase;
import org.mgga.sol.init.SolBlocks;

public class MapGenCavesTitan extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.TITAN_ROCK,
                SolBlocks.TITAN_SUB_SURFACE_ROCK,
                SolBlocks.TITAN_SURFACE_ROCK
        };
    }

    @Override
    protected int getLiquidLevel() {
        return 62;
    }

    @Override
    protected net.minecraft.block.state.IBlockState getLiquidBlock() {
        return SolBlocks.METHANE_FLUID_BLOCK.getDefaultState();
    }
}
