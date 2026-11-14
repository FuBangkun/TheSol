package org.mgga.sol.config;

import net.minecraftforge.common.config.Config;
import org.mgga.sol.Reference;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class ConfigManager {
    @Config.Comment("Misc settings")
    public static final Misc misc = new Misc();

    @Config.Comment("Dimension IDs")
    public static final DimensionIds dimensionIds = new DimensionIds();

    public static class Misc {
        @Config.Comment("If this is true, it will override Galacticraft default Galaxymap")
        public boolean enableCustomGalaxymap = true;
    }

    public static class DimensionIds {
        private static final int BASE = -(Reference.MOD_ID.hashCode());

        public int dimensionidMercury = BASE - 100;
        public int dimensionidIo = BASE - 501;
        public int dimensionidEuropa = BASE - 502;
        public int dimensionidMimas = BASE - 601;
        public int dimensionidTitan = BASE - 606;
        public int dimensionidAriel = BASE - 701;
        public int dimensionidTriton = BASE - 801;
        public int dimensionidPluto = BASE - 900;
        public int dimensionidSedna = BASE - 1100;
    }
}