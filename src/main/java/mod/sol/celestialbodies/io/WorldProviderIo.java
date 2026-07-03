package mod.sol.celestialbodies.io;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import mod.sol.celestialbodies.BiomeProviderSol;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.celestialbodies.WorldProviderSolBase;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolPlanets;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class WorldProviderIo extends WorldProviderSolBase {
    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Io;
    }

    @Override
    public long getDayLength() {
        return 60000L;
    }

    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass() {
        BiomeAdaptive.setBodyMultiBiome(SolPlanets.moonIo);
        return BiomeProviderSol.class;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderIo.class;
    }

    @Override
    public float getGravity() {
        return 0.062F;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0.7D;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0.4D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.18F;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SolPlanets.moonIo;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasureUniversal.TABLE_JUPITER;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.IO_SURFACE_ROCK);
        return list;
    }
}
