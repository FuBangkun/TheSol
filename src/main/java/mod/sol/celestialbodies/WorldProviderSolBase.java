<<<<<<<< HEAD:src/main/java/mod/sol/celestialbodies/WorldProviderSolBase.java
package mod.sol.celestialbodies;
========
package mod.sol.celestialbodies.mimas;
>>>>>>>> origin/main:src/main/java/mod/sol/celestialbodies/mimas/WorldProviderMimas.java

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
<<<<<<<< HEAD:src/main/java/mod/sol/celestialbodies/WorldProviderSolBase.java
========
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.celestialbodies.RoomTreasureUniversal;
import mod.sol.init.SolPlanets;
>>>>>>>> origin/main:src/main/java/mod/sol/celestialbodies/mimas/WorldProviderMimas.java
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class WorldProviderSolBase extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel {

    @Nonnull
    @Override
    public abstract DimensionType getDimensionType();

    @Override
    public Vector3 getFogColor() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public Vector3 getSkyColor() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean hasSunset() {
        return false;
    }

    @Override
    public abstract long getDayLength();

    @Override
    public abstract Class<? extends IChunkGenerator> getChunkProviderClass();

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        final float var2 = this.world.getCelestialAngle(par1);
        float var3 = 1.0F - (MathHelper.cos(var2 * Constants.twoPI) * 2.0F + 0.25F);
        if (var3 < 0.0F) var3 = 0.0F;
        if (var3 > 1.0F) var3 = 1.0F;
        return var3 * var3 * 0.5F + 0.3F;
    }

    @Override
    public boolean isSkyColored() {
        return false;
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

    @Override
    public boolean canRespawnHere() {
        if (EventHandlerGC.bedActivated) {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public abstract float getGravity();

    @Override
    public abstract double getFuelUsageMultiplier();

    @Override
    public abstract double getSolarEnergyMultiplier();

    @Override
    public boolean canSpaceshipTierPass(int tier) {
        return tier >= this.getCelestialBody().getTierRequirement();
    }

    @Override
    public abstract float getFallDamageModifier();

    @Override
    public abstract CelestialBody getCelestialBody();

    @Override
    public int getDungeonSpacing() {
        return 704;
    }

    @Override
    public abstract ResourceLocation getDungeonChestType();

    @Override
    public abstract List<Block> getSurfaceBlocks();
}
