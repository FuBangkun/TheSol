package mod.sol.planets.pluto.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class BiomePluto extends BiomeGenBaseGC {
    public static final Biome plutoFlat = new BiomeFlatPluto(new BiomeProperties("Pluto Flat").setBaseHeight(1.0F).setHeightVariation(0.4F).setRainfall(0.0F));
    public static final Biome plutoSnowfield = new BiomeSnowfieldPluto(new BiomeProperties("Pluto Snowfield").setBaseHeight(1.0F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomePluto(BiomeProperties properties) {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorPluto());
    }

}
