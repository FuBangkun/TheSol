package mod.sol.celestialbodies.ariel;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import mod.sol.celestialbodies.ChunkProviderSolNoise;
import mod.sol.celestialbodies.RoomBossUniversal;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.init.SolBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ChunkProviderAriel extends ChunkProviderSolNoise {
    public ChunkProviderAriel(World world, long seed) {
        super(world, seed);
    }

    @Override
    protected float getNoiseScale() {
        return 0.15F;
    }

    @Override
    protected IBlockState getTopBlock(int x, int y, int z, Biome b) {
        return SolBlocks.ARIEL_TURF.getDefaultState();
    }

    @Override
    protected IBlockState getFillBlock(int x, int y, int z, Biome b) {
        return SolBlocks.ARIEL_DIRT.getDefaultState();
    }

    @Override
    protected IBlockState getLowerBlock() {
        return SolBlocks.ARIEL_ROCK.getDefaultState();
    }

    @Override
    protected MapGenBaseMeta createCaveGenerator() {
        return new MapGenCavesAriel();
    }

    @Override
    protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.URANUS_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}