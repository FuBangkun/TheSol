package mod.sol.celestialbodies.pluto.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;

public class BiomeSnowfieldPluto extends BiomeGenBaseGC {
    public BiomeSnowfieldPluto(BiomeProperties properties) {
        super(properties, true);
        this.topBlock = Blocks.SNOW.getDefaultState();
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorPluto());
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
