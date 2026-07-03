package mod.sol.celestialbodies.triton;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

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
