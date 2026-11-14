package org.mgga.sol.celestialbodies.mimas;

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

public class ChunkProviderMimas extends ChunkProviderSolNoise {
    public ChunkProviderMimas(World world, long seed) {
        super(world, seed);
    }

    @Override
    protected float getNoiseScale() {
        return 0.0425F;
    }

    @Override
    protected IBlockState getTopBlock(int x, int y, int z, Biome b) {
        return SolBlocks.MIMAS_TURF.getDefaultState();
    }

    @Override
    protected IBlockState getFillBlock(int x, int y, int z, Biome b) {
        return SolBlocks.MIMAS_DIRT.getDefaultState();
    }

    @Override
    protected IBlockState getLowerBlock() {
        return SolBlocks.MIMAS_ROCK.getDefaultState();
    }

    @Override
    protected MapGenBaseMeta createCaveGenerator() {
        return new MapGenCavesMimas();
    }

    @Override
    protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.MERCURY_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}