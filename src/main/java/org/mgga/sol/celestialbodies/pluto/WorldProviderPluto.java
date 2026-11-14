package org.mgga.sol.celestialbodies.pluto;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import org.mgga.sol.celestialbodies.BiomeProviderSol;
import org.mgga.sol.celestialbodies.WorldProviderSolBase;
import org.mgga.sol.init.SolBlocks;
import org.mgga.sol.init.SolDimensions;
import org.mgga.sol.init.SolPlanets;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class WorldProviderPluto extends WorldProviderSolBase {
    private static final List<Block> SURFACE_BLOCKS = Arrays.asList(SolBlocks.PLUTO_SURFACE_ROCK, Blocks.SNOW, Blocks.SNOW_LAYER);

    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Pluto;
    }

    @Override
    public long getDayLength() {
        return 2112000L;
    }

    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass() {
        BiomeAdaptive.setBodyMultiBiome(SolPlanets.planetPluto);
        return BiomeProviderSol.class;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderPluto.class;
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
        return 0.26D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.18F;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SolPlanets.planetPluto;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasure.MOONCHEST;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        return SURFACE_BLOCKS;
    }
}
