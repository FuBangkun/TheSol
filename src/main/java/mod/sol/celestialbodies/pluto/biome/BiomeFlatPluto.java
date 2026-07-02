package mod.sol.celestialbodies.pluto.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.init.SolBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;

public class BiomeFlatPluto extends BiomeGenBaseGC {
    public BiomeFlatPluto(BiomeProperties properties) {
        super(properties, true);
        this.topBlock = SolBlocks.PLUTO_SURFACE_ROCK.getDefaultState();
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorPluto());
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
    }
}
