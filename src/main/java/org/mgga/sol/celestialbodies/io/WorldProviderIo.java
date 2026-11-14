package org.mgga.sol.celestialbodies.io;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import org.mgga.sol.celestialbodies.BiomeProviderSol;
import org.mgga.sol.celestialbodies.RoomTreasureUniversal;
import org.mgga.sol.celestialbodies.WorldProviderSolBase;
import org.mgga.sol.init.SolBlocks;
import org.mgga.sol.init.SolDimensions;
import org.mgga.sol.init.SolPlanets;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class WorldProviderIo extends WorldProviderSolBase {
    private static final List<Block> SURFACE_BLOCKS = Collections.singletonList(SolBlocks.IO_SURFACE_ROCK);

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
        return SURFACE_BLOCKS;
    }
}
