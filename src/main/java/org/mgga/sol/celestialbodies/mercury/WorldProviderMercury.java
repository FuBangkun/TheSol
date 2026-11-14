package org.mgga.sol.celestialbodies.mercury;

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

public class WorldProviderMercury extends WorldProviderSolBase {
    private static final List<Block> SURFACE_BLOCKS = Collections.singletonList(SolBlocks.MERCURY_TURF);

    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Mercury;
    }

    @Override
    public long getDayLength() {
        return 2112000L;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderMercury.class;
    }

    @Override
    public float getGravity() {
        return 0.060F;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0.7D;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 4.0D;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.36F;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SolPlanets.planetMercury;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasureUniversal.TABLE_MERCURY;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        return SURFACE_BLOCKS;
    }
}
