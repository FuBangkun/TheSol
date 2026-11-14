package org.mgga.sol.celestialbodies.ariel;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import org.mgga.sol.celestialbodies.RoomTreasureUniversal;
import org.mgga.sol.celestialbodies.WorldProviderSolBase;
import org.mgga.sol.init.SolBlocks;
import org.mgga.sol.init.SolDimensions;
import org.mgga.sol.init.SolPlanets;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class WorldProviderAriel extends WorldProviderSolBase {
    private static final List<Block> SURFACE_BLOCKS = Collections.singletonList(SolBlocks.ARIEL_TURF);

    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Ariel;
    }

    @Override
    public long getDayLength() {
        return 192000L;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderAriel.class;
    }

    @Override
    public float getGravity() {
        return 0.062F;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0.85D;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0.33D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.18F;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SolPlanets.moonAriel;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasureUniversal.TABLE_URANUS;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        return SURFACE_BLOCKS;
    }
}
