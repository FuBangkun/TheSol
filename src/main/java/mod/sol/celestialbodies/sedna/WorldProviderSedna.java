package mod.sol.celestialbodies.sedna;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
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

public class WorldProviderSedna extends WorldProviderSolBase {
    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Sedna;
    }

    @Override
    public long getDayLength() {
        return 2112000L;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderSedna.class;
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
        return SolPlanets.planetSedna;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasure.MOONCHEST;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.SEDNA_SURFACE_ROCK);
        return list;
    }
}
