package mod.sol.celestialbodies.mercury;

import mod.sol.celestialbodies.ChunkProviderSolNoise;
import mod.sol.celestialbodies.RoomBossUniversal;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;

public class ChunkProviderMercury extends ChunkProviderSolNoise {
    public ChunkProviderMercury(World world, long seed) { super(world, seed); }
    @Override protected float getNoiseScale() { return 0.15F; }
    @Override protected IBlockState getTopBlock(int x, int y, int z, Biome b) { return SolBlocks.MERCURY_TURF.getDefaultState(); }
    @Override protected IBlockState getFillBlock(int x, int y, int z, Biome b) { return SolBlocks.MERCURY_DIRT.getDefaultState(); }
    @Override protected IBlockState getLowerBlock() { return SolBlocks.MERCURY_ROCK.getDefaultState(); }
    @Override protected MapGenBaseMeta createCaveGenerator() { return new MapGenCavesMercury(); }
    @Override protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.MERCURY_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}