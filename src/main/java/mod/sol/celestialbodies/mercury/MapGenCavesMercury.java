package mod.sol.celestialbodies.mercury;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

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
