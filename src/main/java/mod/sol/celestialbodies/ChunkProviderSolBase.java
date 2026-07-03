package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public abstract class ChunkProviderSolBase extends ChunkProviderBase {
    protected final World world;
    protected final Random rand;

    public ChunkProviderSolBase(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
    }

    @Nonnull
    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(@Nonnull EnumCreatureType creatureType, @Nonnull BlockPos pos) {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }
}