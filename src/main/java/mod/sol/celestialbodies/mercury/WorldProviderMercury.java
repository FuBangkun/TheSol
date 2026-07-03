package mod.sol.celestialbodies.mercury;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.celestialbodies.WorldProviderSolBase;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolPlanets;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class WorldProviderMercury extends WorldProviderSolBase {
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
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.MERCURY_TURF);
        return list;
    }
}
