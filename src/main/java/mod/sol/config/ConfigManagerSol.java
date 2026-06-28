package mod.sol.config;

import mod.sol.TheSol;
import mod.sol.Tags;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigManagerSol {
    public static Configuration config;

    public static boolean enableCustomGalaxymap = true;

    public static int dimensionidMercury = -(Tags.MOD_ID.hashCode() + 100);
    public static int dimensionidIo = -(Tags.MOD_ID.hashCode() + 501);
    public static int dimensionidEuropa = -(Tags.MOD_ID.hashCode() + 502);
    public static int dimensionidMimas = -(Tags.MOD_ID.hashCode() + 601);
    public static int dimensionidTitan = -(Tags.MOD_ID.hashCode() + 606);
    public static int dimensionidAriel = -(Tags.MOD_ID.hashCode() + 701);
    public static int dimensionidTriton = -(Tags.MOD_ID.hashCode() + 801);
    public static int dimensionidPluto = -(Tags.MOD_ID.hashCode() + 900);
    public static int dimensionidKuiperBelt = -(Tags.MOD_ID.hashCode() + 1000);
    public static int dimensionidSedna = -(Tags.MOD_ID.hashCode() + 1100);

    public static void init(File file) {
        config = new Configuration(file);
        String categoryDimensionids = "The Sol - Dimension IDs";
        String categoryMisc = "The Sol - Misc";
        config.addCustomCategoryComment(categoryDimensionids, "IDs for dimensions of the mod 'The Sol'");
        dimensionidMercury = config.getInt("Dimension ID for Mercury", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 100), -999999999, 999999999, "None");
        dimensionidIo = config.getInt("Dimension ID for Io", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 501), -999999999, 999999999, "None");
        dimensionidEuropa = config.getInt("Dimension ID for Europa", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 502), -999999999, 999999999, "None");
        dimensionidMimas = config.getInt("Dimension ID for Mimas", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 601), -999999999, 999999999, "None");
        dimensionidTitan = config.getInt("Dimension ID for Titan", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 606), -999999999, 999999999, "None");
        dimensionidAriel = config.getInt("Dimension ID for Ariel", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 701), -999999999, 999999999, "None");
        dimensionidTriton = config.getInt("Dimension ID for Triton", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 801), -999999999, 999999999, "None");
        dimensionidPluto = config.getInt("Dimension ID for Pluto", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 900), -999999999, 999999999, "None");
        dimensionidKuiperBelt = config.getInt("Dimension ID for Kuiper Belt", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 1000), -999999999, 999999999, "None");
        dimensionidSedna = config.getInt("Dimension ID for Sedna", categoryDimensionids, -(Tags.MOD_ID.hashCode() + 1100), -999999999, 999999999, "None");

        config.addCustomCategoryComment(categoryDimensionids, "IDs for dimensions of the mod 'The Sol'");
        enableCustomGalaxymap = config.getBoolean("Enable Custom Galaxymap?", categoryMisc, true, "If this config is true, it will override Galacticraft default Galaxymap");
        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event) {
        TheSol.configSol = new File(event.getModConfigurationDirectory() + "/" + Tags.MOD_ID);
        TheSol.configSol.mkdirs();
        init(new File(TheSol.configSol.getPath(), Tags.MOD_ID + ".conf"));
    }
}
