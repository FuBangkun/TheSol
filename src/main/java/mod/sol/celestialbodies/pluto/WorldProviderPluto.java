package mod.sol.celestialbodies.pluto;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import mod.sol.celestialbodies.BiomeProviderSol;
import mod.sol.celestialbodies.WorldProviderSolBase;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolPlanets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class WorldProviderPluto extends WorldProviderSolBase {
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
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.PLUTO_SURFACE_ROCK);
        list.add(Blocks.SNOW);
        list.add(Blocks.SNOW_LAYER);
        return list;
    }
}
