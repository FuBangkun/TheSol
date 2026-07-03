package mod.sol.celestialbodies.sedna;

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

public class ChunkProviderSedna extends ChunkProviderSolNoise {
    public ChunkProviderSedna(World world, long seed) {
        super(world, seed);
    }

    @Override
    protected float getNoiseScale() {
        return 0.25F;
    }

    @Override
    protected IBlockState getTopBlock(int x, int y, int z, Biome b) {
        return (y >= rand.nextInt(8) + 52) ? SolBlocks.SEDNA_SURFACE_ROCK.getDefaultState() : SolBlocks.SEDNA_TURF.getDefaultState();
    }

    @Override
    protected IBlockState getFillBlock(int x, int y, int z, Biome b) {
        return SolBlocks.SEDNA_SUB_SURFACE_ROCK.getDefaultState();
    }

    @Override
    protected IBlockState getLowerBlock() {
        return SolBlocks.SEDNA_ROCK.getDefaultState();
    }

    @Override
    protected MapGenBaseMeta createCaveGenerator() {
        return new MapGenCavesSedna();
    }

    @Override
    protected MapGenDungeon createDungeonGenerator() {
        return new MapGenDungeon(new DungeonConfiguration(SolBlocks.SEDNA_DUNGEON_BRICK.getDefaultState(), 25, 8, 16, 5, 6, RoomBossUniversal.class, RoomTreasureUniversal.class));
    }
}