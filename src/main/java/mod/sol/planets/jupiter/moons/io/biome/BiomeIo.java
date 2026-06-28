package mod.sol.planets.jupiter.moons.io.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class BiomeIo extends BiomeGenBaseGC {
    public static final Biome ioFlat = new BiomeFlatIo(new BiomeProperties("Io Flat").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
    public static final Biome ioAshLand = new BiomeAshLandIo(new BiomeProperties("Io Ash Land").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
    public static final Biome ioSulfurField = new BiomeSulfurFieldIo(new BiomeProperties("Io Sulfur Field").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));

    BiomeIo(BiomeProperties properties) {
        super(properties, true);
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorIo());
    }

}
