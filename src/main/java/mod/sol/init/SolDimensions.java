package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import mod.sol.config.ConfigManager;
import mod.sol.celestialbodies.europa.WorldProviderEuropa;
import mod.sol.celestialbodies.io.WorldProviderIo;
import mod.sol.celestialbodies.mercury.WorldProviderMercury;
import mod.sol.celestialbodies.pluto.WorldProviderPluto;
import mod.sol.celestialbodies.mimas.WorldProviderMimas;
import mod.sol.celestialbodies.titan.WorldProviderTitan;
import mod.sol.celestialbodies.sedna.WorldProviderSedna;
import mod.sol.celestialbodies.ariel.WorldProviderAriel;
import net.minecraft.world.DimensionType;

public class SolDimensions {
    //planets
    public static DimensionType Mercury = GalacticraftRegistry.registerDimension("mercury", "_mercury", ConfigManager.dimensionIds.dimensionidMercury, WorldProviderMercury.class, false);
    public static DimensionType Pluto = GalacticraftRegistry.registerDimension("pluto", "_pluto", ConfigManager.dimensionIds.dimensionidPluto, WorldProviderPluto.class, false);
    public static DimensionType Sedna = GalacticraftRegistry.registerDimension("sedna", "_sedna", ConfigManager.dimensionIds.dimensionidSedna, WorldProviderSedna.class, false);
    //moons
    public static DimensionType Io = GalacticraftRegistry.registerDimension("io", "_io", ConfigManager.dimensionIds.dimensionidIo, WorldProviderIo.class, false);
    public static DimensionType Europa = GalacticraftRegistry.registerDimension("europa", "_europa", ConfigManager.dimensionIds.dimensionidEuropa, WorldProviderEuropa.class, false);

    public static DimensionType Mimas = GalacticraftRegistry.registerDimension("mimas", "_mimas", ConfigManager.dimensionIds.dimensionidMimas, WorldProviderMimas.class, false);
    public static DimensionType Titan = GalacticraftRegistry.registerDimension("titan", "_titan", ConfigManager.dimensionIds.dimensionidTitan, WorldProviderTitan.class, false);

    public static DimensionType Ariel = GalacticraftRegistry.registerDimension("ariel", "_ariel", ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class, false);

    public static DimensionType Triton = GalacticraftRegistry.registerDimension("triton", "_triton", ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class, false);
}