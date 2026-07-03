package mod.sol.celestialbodies.pluto;

import mod.sol.celestialbodies.MapGenCavesSolBase;
import mod.sol.init.SolBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

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
