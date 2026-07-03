package mod.sol.celestialbodies.io;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

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
