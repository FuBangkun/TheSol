package mod.sol.celestialbodies.pluto;

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

public class ChunkProviderPluto extends ChunkProviderSolNoise {
    public ChunkProviderPluto(World world, long seed) {
        super(world, seed);
    }

    @Override
    protected float getNoiseScale() {
        return 0.5F;
    }

    @Override
    protected double getTerrainHeightModifier() {
        return 32.0;
    } // Pluto 比较崎岖

    @Override
    protected IBlockState getTopBlock(int x, int y, int z, Biome b) {
        return b.topBlock;
    }

    @Override
    protected IBlockState getFillBlock(int x, int y, int z, Biome b) {
        return SolBlocks.PLUTO_SUB_SURFACE_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getLowerBlock() {
        return SolBlocks.PLUTO_ROCK.getDefaultState();
    }

    @Override
    protected MapGenBaseMeta createCaveGenerator() {
        return new MapGenCavesPluto();
    }

    @Override
    protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.PLUTO_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}