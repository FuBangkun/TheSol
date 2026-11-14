package org.mgga.sol.celestialbodies.triton;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.mgga.sol.celestialbodies.ChunkProviderSolNoise;
import org.mgga.sol.celestialbodies.RoomBossUniversal;
import org.mgga.sol.celestialbodies.RoomTreasureUniversal;
import org.mgga.sol.init.SolBlocks;

public class ChunkProviderTriton extends ChunkProviderSolNoise {
    public ChunkProviderTriton(World world, long seed) {
        super(world, seed);
    }

    @Override
    protected float getNoiseScale() {
        return 0.20F;
    }

    @Override
    protected IBlockState getTopBlock(int x, int y, int z, Biome b) {
        if (y >= rand.nextInt(4) + 64) return SolBlocks.TRITON_SOFT_ROCK.getDefaultState();
        if (y >= rand.nextInt(6) + 56) return SolBlocks.TRITON_SURFACE_ROCK.getDefaultState();
        return SolBlocks.TRITON_TURF.getDefaultState();
    }

    @Override
    protected IBlockState getFillBlock(int x, int y, int z, Biome b) {
        return SolBlocks.TRITON_DIRT.getDefaultState();
    }

    @Override
    protected IBlockState getLowerBlock() {
        return SolBlocks.TRITON_ROCK.getDefaultState();
    }

    @Override
    protected MapGenBaseMeta createCaveGenerator() {
        return new MapGenCavesTriton();
    }

    @Override
    protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.NEPTUNE_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}