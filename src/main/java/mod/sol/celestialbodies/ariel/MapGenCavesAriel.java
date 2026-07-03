package mod.sol.celestialbodies.ariel;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;

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
