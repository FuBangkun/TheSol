package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import mod.sol.config.ConfigManager;
import mod.sol.planets.jupiter.moons.europa.dimension.WorldProviderEuropa;
import mod.sol.planets.jupiter.moons.io.dimension.WorldProviderIo;
import mod.sol.planets.kuiper_belt.dimension.WorldProviderKuiperBelt;
import mod.sol.planets.mercury.dimension.WorldProviderMercury;
import mod.sol.planets.pluto.dimension.WorldProviderPluto;
import mod.sol.planets.saturn.moons.mimas.dimension.WorldProviderMimas;
import mod.sol.planets.saturn.moons.titan.dimension.WorldProviderTitan;
import mod.sol.planets.sedna.dimension.WorldProviderSedna;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import net.minecraft.world.DimensionType;

public class SolDimensions {
    //planets
    public static DimensionType Mercury = GalacticraftRegistry.registerDimension("mercury", "_mercury", ConfigManager.dimensionIds.dimensionidMercury, WorldProviderMercury.class, false);
    public static DimensionType Pluto = GalacticraftRegistry.registerDimension("pluto", "_pluto", ConfigManager.dimensionIds.dimensionidPluto, WorldProviderPluto.class, false);
    public static DimensionType KuiperBelt = GalacticraftRegistry.registerDimension("kuiper_belt", "_kuiper_belt", ConfigManager.dimensionIds.dimensionidKuiperBelt, WorldProviderKuiperBelt.class, false);
    public static DimensionType Sedna = GalacticraftRegistry.registerDimension("sedna", "_sedna", ConfigManager.dimensionIds.dimensionidSedna, WorldProviderSedna.class, false);
    //moons
    public static DimensionType Io = GalacticraftRegistry.registerDimension("io", "_io", ConfigManager.dimensionIds.dimensionidIo, WorldProviderIo.class, false);
    public static DimensionType Europa = GalacticraftRegistry.registerDimension("europa", "_europa", ConfigManager.dimensionIds.dimensionidEuropa, WorldProviderEuropa.class, false);

    public static DimensionType Mimas = GalacticraftRegistry.registerDimension("mimas", "_mimas", ConfigManager.dimensionIds.dimensionidMimas, WorldProviderMimas.class, false);
    public static DimensionType Titan = GalacticraftRegistry.registerDimension("titan", "_titan", ConfigManager.dimensionIds.dimensionidTitan, WorldProviderTitan.class, false);

    public static DimensionType Ariel = GalacticraftRegistry.registerDimension("ariel", "_ariel", ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class, false);

    public static DimensionType Triton = GalacticraftRegistry.registerDimension("triton", "_triton", ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class, false);
}