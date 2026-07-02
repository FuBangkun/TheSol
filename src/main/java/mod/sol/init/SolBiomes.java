package mod.sol.init;

import mod.sol.celestialbodies.ariel.biome.BiomeFlatAriel;
import mod.sol.celestialbodies.europa.biome.BiomeGenMountainEuropa;
import mod.sol.celestialbodies.europa.biome.BiomeGenValleyEuropa;
import mod.sol.celestialbodies.europa.biome.BiomeGenFlatEuropa;
import mod.sol.celestialbodies.io.biome.BiomeAshLandIo;
import mod.sol.celestialbodies.io.biome.BiomeFlatIo;
import mod.sol.celestialbodies.io.biome.BiomeSulfurFieldIo;
import mod.sol.celestialbodies.mercury.biome.BiomeFlatMercury;
import mod.sol.celestialbodies.mimas.biome.BiomeFlatMimas;
import mod.sol.celestialbodies.pluto.biome.BiomeFlatPluto;
import mod.sol.celestialbodies.pluto.biome.BiomeSnowfieldPluto;
import mod.sol.celestialbodies.sedna.biome.BiomeFlatSedna;
import mod.sol.celestialbodies.titan.biome.BiomeGenFlatTitan;
import mod.sol.celestialbodies.titan.biome.BiomeGenTitanMountain;
import mod.sol.celestialbodies.titan.biome.BiomeGenTitanOcean;
import mod.sol.celestialbodies.triton.biome.BiomeFlatTriton;
import net.minecraft.world.biome.Biome;

public class SolBiomes {
    public static Biome ARIEL_FLAT;
    public static Biome EUROPA_FLAT;
    public static Biome EUROPA_MOUNTAIN;
    public static Biome EUROPA_VALLEY;
    public static Biome IO_FLAT;
    public static Biome IO_ASH_LAND;
    public static Biome IO_SULFUR_FIELD;
    public static Biome MERCURY_FLAT;
    public static Biome MIMAS_FLAT;
    public static Biome PLUTO_FLAT;
    public static Biome PLUTO_SNOWFIELD;
    public static Biome SEDNA_FLAT;
    public static Biome TITAN_FLAT;
    public static Biome TITAN_MOUNTAIN;
    public static Biome TITAN_OCEAN;
    public static Biome TRITON_FLAT;

    public static void init() {
        ARIEL_FLAT = new BiomeFlatAriel(new Biome.BiomeProperties("Ariel").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        EUROPA_FLAT = new BiomeGenFlatEuropa(new Biome.BiomeProperties("Europa Flat").setRainfall(0.0F).setRainDisabled().setBaseHeight(0.6F).setHeightVariation(0.4F).setTemperature(4.0F));
        EUROPA_MOUNTAIN = new BiomeGenMountainEuropa(new Biome.BiomeProperties("Europa Mountain").setRainfall(0.0F).setRainDisabled().setBaseHeight(1.7F).setHeightVariation(1.0F).setTemperature(4.0F));
        EUROPA_VALLEY = new BiomeGenValleyEuropa(new Biome.BiomeProperties("Europa Valley").setRainfall(0.0F).setRainDisabled().setBaseHeight(-0.4F).setHeightVariation(0.2F).setTemperature(4.0F));
        IO_FLAT = new BiomeFlatIo(new Biome.BiomeProperties("Io Flat").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        IO_ASH_LAND = new BiomeAshLandIo(new Biome.BiomeProperties("Io Ash Land").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        IO_SULFUR_FIELD = new BiomeSulfurFieldIo(new Biome.BiomeProperties("Io Sulfur Field").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        MERCURY_FLAT = new BiomeFlatMercury(new Biome.BiomeProperties("Mercury").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        MIMAS_FLAT = new BiomeFlatMimas(new Biome.BiomeProperties("Mimas").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        PLUTO_FLAT = new BiomeFlatPluto(new Biome.BiomeProperties("Pluto Flat").setBaseHeight(1.0F).setHeightVariation(0.4F).setRainfall(0.0F));
        PLUTO_SNOWFIELD = new BiomeSnowfieldPluto(new Biome.BiomeProperties("Pluto Snowfield").setBaseHeight(1.0F).setHeightVariation(0.4F).setRainfall(0.0F));
        SEDNA_FLAT = new BiomeFlatSedna(new Biome.BiomeProperties("Sedna").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
        TITAN_FLAT = new BiomeGenFlatTitan(new Biome.BiomeProperties("Titan Flat").setRainfall(0.0F).setRainDisabled().setBaseHeight(0.9F).setHeightVariation(0.4F).setTemperature(4.0F));
        TITAN_MOUNTAIN = new BiomeGenTitanMountain(new Biome.BiomeProperties("Titan Mountain").setRainfall(0.0F).setRainDisabled().setBaseHeight(3.5F).setHeightVariation(1.0F).setTemperature(4.0F));
        TITAN_OCEAN = new BiomeGenTitanOcean(new Biome.BiomeProperties("Titan Ocean").setRainfall(0.0F).setRainDisabled().setBaseHeight(-0.4F).setHeightVariation(0.2F).setTemperature(4.0F));
        TRITON_FLAT = new BiomeFlatTriton(new Biome.BiomeProperties("Triton").setBaseHeight(1.5F).setHeightVariation(0.4F).setRainfall(0.0F));
    }
}
