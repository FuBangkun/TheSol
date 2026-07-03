package mod.sol.celestialbodies.sedna;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

public class MapGenCavesSedna extends MapGenCavesSolBase {
    @Override
    protected Block[] getBreakableBlocks() {
        return new Block[]{
                SolBlocks.SEDNA_SURFACE_ROCK,
                SolBlocks.SEDNA_TURF,
                SolBlocks.SEDNA_SUB_SURFACE_ROCK,
                SolBlocks.SEDNA_ROCK
        };
    }
}
