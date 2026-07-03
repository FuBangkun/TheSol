package mod.sol.celestialbodies.mimas;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

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
