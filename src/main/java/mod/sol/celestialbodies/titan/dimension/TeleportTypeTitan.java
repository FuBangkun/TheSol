package mod.sol.celestialbodies.titan.dimension;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntityLandingBalloons;
import mod.sol.celestialbodies.TeleportTypeUniversal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class TeleportTypeTitan extends TeleportTypeUniversal {
    @Override
    public boolean useParachute() {
        return false;
    }

    @Override
    public Vector3 getParaChestSpawnLocation(WorldServer world, EntityPlayerMP player, Random rand) {
        return null;
    }

    @Override
    public void onSpaceDimensionChanged(World newWorld, EntityPlayerMP player, boolean ridingAutoRocket) {
        if (!ridingAutoRocket && player != null) {
            GCPlayerStats stats = GCPlayerStats.get(player);

            if (stats.getTeleportCooldown() <= 0) {
                if (player.capabilities.isFlying) {
                    player.capabilities.isFlying = false;
                }

                EntityLandingBalloons lander = new EntityLandingBalloons(player);

                if (!newWorld.isRemote) {
                    boolean previous = CompatibilityManager.forceLoadChunks((WorldServer) newWorld);
                    lander.forceSpawn = true;
                    newWorld.spawnEntity(lander);
                    CompatibilityManager.forceLoadChunksEnd((WorldServer) newWorld, previous);
                }

                stats.setTeleportCooldown(10);
            }
        }
    }
}
