package mod.sol.celestialbodies.io;

import mod.sol.celestialbodies.ChunkProviderSolNoise;
import mod.sol.celestialbodies.RoomBossUniversal;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;

public class ChunkProviderIo extends ChunkProviderSolNoise {
    public ChunkProviderIo(World world, long seed) { super(world, seed); }
    @Override protected float getNoiseScale() { return 0.125F; }
    @Override protected IBlockState getTopBlock(int x, int y, int z, Biome b) { return b.topBlock; }
    @Override protected IBlockState getFillBlock(int x, int y, int z, Biome b) { return SolBlocks.IO_SUB_SURFACE_ROCK.getDefaultState(); }
    @Override protected IBlockState getLowerBlock() { return SolBlocks.IO_ROCK.getDefaultState(); }
    @Override protected MapGenBaseMeta createCaveGenerator() { return new MapGenCavesIo(); }
    @Override protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.JUPITER_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
    @Override
    protected void onPopulateExtra(int x, int z, BlockPos pos) {
        if (this.rand.nextInt(3) == 0) {
            int ix = this.rand.nextInt(16) + 8, iy = this.rand.nextInt(248) + 8, iz = this.rand.nextInt(16) + 8;
            (new MapGenLavaLakesIo()).generate(this.world, this.rand, pos.add(ix, iy, iz));
        }
    }
}