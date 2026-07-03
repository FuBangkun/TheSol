package mod.sol.init;

import mod.sol.celestialbodies.BiomeSol;
import mod.sol.celestialbodies.BiomeSolTerrain;
import mod.sol.celestialbodies.ariel.BiomeDecoratorAriel;
import mod.sol.celestialbodies.europa.BiomeDecoratorEuropa;
import mod.sol.celestialbodies.io.BiomeDecoratorIo;
import mod.sol.celestialbodies.mercury.BiomeDecoratorMercury;
import mod.sol.celestialbodies.mimas.BiomeDecoratorMimas;
import mod.sol.celestialbodies.pluto.BiomeDecoratorPluto;
import mod.sol.celestialbodies.sedna.BiomeDecoratorSedna;
import mod.sol.celestialbodies.titan.BiomeDecoratorTitan;
import mod.sol.celestialbodies.triton.BiomeDecoratorTriton;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;

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

    private static Biome.BiomeProperties createProperties(String name, float baseHeight) {
        return new Biome.BiomeProperties(name).setBaseHeight(baseHeight).setHeightVariation(0.4F).setRainfall(0.0F);
    }

    public static void init() {
        // ==================== ARIEL ====================
        ARIEL_FLAT = new BiomeSol(createProperties("Ariel", 1.5F)) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorAriel());
            }
        };

        // ==================== EUROPA ====================
        EUROPA_FLAT = new BiomeSolTerrain(
                new Biome.BiomeProperties("Europa Flat").setRainfall(0.0F).setRainDisabled().setBaseHeight(0.6F).setHeightVariation(0.4F).setTemperature(4.0F),
                SolBlocks.EUROPA_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_ROCK.getDefaultState(),
                null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorEuropa());
            }
        };
        EUROPA_MOUNTAIN = new BiomeSolTerrain(
                new Biome.BiomeProperties("Europa Mountain").setRainfall(0.0F).setRainDisabled().setBaseHeight(1.7F).setHeightVariation(1.0F).setTemperature(4.0F),
                SolBlocks.EUROPA_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_ROCK.getDefaultState(),
                null,
                BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorEuropa());
            }
        };
        EUROPA_VALLEY = new BiomeSolTerrain(
                new Biome.BiomeProperties("Europa Valley").setRainfall(0.0F).setRainDisabled().setBaseHeight(-0.4F).setHeightVariation(0.2F).setTemperature(4.0F),
                SolBlocks.EUROPA_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.EUROPA_ROCK.getDefaultState(),
                SolBlocks.EUROPA_SUB_SURFACE_ROCK.getDefaultState(), // Valley 有 gravel 参数
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorEuropa());
            }
        };

        // ==================== IO ====================
        IO_FLAT = new BiomeSol(
                createProperties("Io Flat", 1.5F),
                SolBlocks.IO_SURFACE_ROCK.getDefaultState(), null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorIo());
            }
        };
        IO_ASH_LAND = new BiomeSol(
                createProperties("Io Ash Land", 1.5F),
                SolBlocks.IO_ASH_BLOCK.getDefaultState(), null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorIo());
            }
        };
        IO_SULFUR_FIELD = new BiomeSol(
                createProperties("Io Sulfur Field", 1.5F),
                SolBlocks.IO_SULFUR_BLOCK.getDefaultState(), null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorIo());
            }
        };

        // ==================== MERCURY ====================
        MERCURY_FLAT = new BiomeSol(createProperties("Mercury", 1.5F), SolBlocks.MERCURY_TURF.getDefaultState(), SolBlocks.MERCURY_ROCK.getDefaultState()) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorMercury());
            }
        };

        // ==================== MIMAS ====================
        MIMAS_FLAT = new BiomeSol(createProperties("Mimas", 1.5F), SolBlocks.MIMAS_TURF.getDefaultState(), SolBlocks.MIMAS_ROCK.getDefaultState()) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorMimas());
            }
        };

        // ==================== PLUTO ====================
        PLUTO_FLAT = new BiomeSol(createProperties("Pluto Flat", 1.0F), SolBlocks.PLUTO_SURFACE_ROCK.getDefaultState(), null) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorPluto());
            }
        };
        PLUTO_SNOWFIELD = new BiomeSol(
                createProperties("Pluto Snowfield", 1.0F),
                net.minecraft.init.Blocks.SNOW.getDefaultState(), null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorPluto());
            }
        };

        // ==================== SEDNA ====================
        SEDNA_FLAT = new BiomeSol(createProperties("Sedna", 1.5F)) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorSedna());
            }
        };

        // ==================== TITAN ====================
        TITAN_FLAT = new BiomeSolTerrain(
                new Biome.BiomeProperties("Titan Flat").setRainfall(0.0F).setRainDisabled().setBaseHeight(0.9F).setHeightVariation(0.4F).setTemperature(4.0F),
                SolBlocks.TITAN_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_ROCK.getDefaultState(),
                null,
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorTitan());
            }
        };
        TITAN_MOUNTAIN = new BiomeSolTerrain(
                new Biome.BiomeProperties("Titan Mountain").setRainfall(0.0F).setRainDisabled().setBaseHeight(3.5F).setHeightVariation(1.0F).setTemperature(4.0F),
                SolBlocks.TITAN_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_ROCK.getDefaultState(),
                null,
                BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorTitan());
            }
        };
        TITAN_OCEAN = new BiomeSolTerrain(
                new Biome.BiomeProperties("Titan Ocean").setRainfall(0.0F).setRainDisabled().setBaseHeight(-0.4F).setHeightVariation(0.2F).setTemperature(4.0F),
                SolBlocks.TITAN_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState(),
                SolBlocks.TITAN_ROCK.getDefaultState(),
                SolBlocks.TITAN_SUB_SURFACE_ROCK.getDefaultState(), // Ocean 有 gravel 参数
                BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY
        ) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorTitan());
            }
        };

        // ==================== TRITON ====================
        TRITON_FLAT = new BiomeSol(createProperties("Triton", 1.5F)) {
            @Nonnull
            @Override
            public BiomeDecorator createBiomeDecorator() {
                return getModdedBiomeDecorator(new BiomeDecoratorTriton());
            }
        };
    }
}
