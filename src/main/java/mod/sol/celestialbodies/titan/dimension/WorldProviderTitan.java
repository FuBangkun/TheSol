package mod.sol.celestialbodies.titan.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.celestialbodies.titan.biome.BiomeProviderTitan;
import mod.sol.celestialbodies.titan.world.gen.ChunkProviderTitan;
import mod.sol.init.SolPlanets;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class WorldProviderTitan extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel {
    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return SolDimensions.Titan;
    }

    @Override
    public Vector3 getFogColor() {
        float night = this.getStarBrightness(1.0F);
        float day = 1.0F - this.getStarBrightness(1.0F);
        float dayColR = 203.0F / 255.0F;
        float dayColG = 147.0F / 255.0F;
        float dayColB = 0.0F / 255.0F;
        float nightColR = 131.0F / 255.0F;
        float nightColG = 108.0F / 255.0F;
        float nightColB = 42.0F / 255.0F;
        return new Vector3(dayColR * day + nightColR * night,
                dayColG * day + nightColG * night,
                dayColB * day + nightColB * night);
    }

    @Override
    public Vector3 getSkyColor() {
        float night = this.getStarBrightness(1.0F);
        float day = 1.0F - this.getStarBrightness(1.0F);
        float dayColR = 1.0f;
        float dayColG = 207.0F / 255.0F;
        float dayColB = 81.0F / 255.0F;
        float nightColR = 118.0F / 255.0F;
        float nightColG = 89.0F / 255.0F;
        float nightColB = 18.0F / 255.0F;
        return new Vector3(dayColR * day + nightColR * night,
                dayColG * day + nightColG * night,
                dayColB * day + nightColB * night);
    }

    @Override
    public boolean hasSunset() {
        return false;
    }

    @Override
    public long getDayLength() {
        return 28000L;
    }

    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass() {
        BiomeAdaptive.setBodyMultiBiome(SolPlanets.moonTitan);
        return BiomeProviderTitan.class;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return ChunkProviderTitan.class;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        final float var2 = this.world.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * Constants.twoPI) * 2.0F + 0.25F);

        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F + 0.3F;
    }

    @Override
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public double getHorizon() {
        return 44.0D;
    }

    @Override
    public int getAverageGroundLevel() {
        return 68;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    //Overriding  so that beds do not explode on Moon
    @Override
    public boolean canRespawnHere() {
        if (EventHandlerGC.bedActivated) {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public float getGravity() {
        return 0.058F;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0.8D;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0.36;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier) {
        return tier >= this.getCelestialBody().getTierRequirement();
    }

    @Override
    public float getFallDamageModifier() {
        return 0.38F;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return SolPlanets.moonTitan;
    }

    @Override
    public int getDungeonSpacing() {
        return 704;
    }

    @Override
    public ResourceLocation getDungeonChestType() {
        return RoomTreasureUniversal.TABLE_SATURN;
    }

    @Override
    public List<Block> getSurfaceBlocks() {
        List<Block> list = new LinkedList<>();
        list.add(SolBlocks.TITAN_SURFACE_ROCK);
        return list;
    }
}
