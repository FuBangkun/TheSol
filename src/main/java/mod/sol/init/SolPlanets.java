package mod.sol.init;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.*;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.venus.ConfigManagerVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.WorldProviderVenus;
import micdoodle8.mods.galacticraft.planets.venus.world.gen.BiomeVenus;
import mod.sol.Tags;
import mod.sol.api.galaxy.DwarfPlanet;
import mod.sol.api.galaxy.GasGiant;
import mod.sol.celestialbodies.TeleportTypeUniversal;
import mod.sol.celestialbodies.ariel.WorldProviderAriel;
import mod.sol.celestialbodies.europa.WorldProviderEuropa;
import mod.sol.celestialbodies.io.WorldProviderIo;
import mod.sol.celestialbodies.mercury.WorldProviderMercury;
import mod.sol.celestialbodies.mimas.WorldProviderMimas;
import mod.sol.celestialbodies.pluto.WorldProviderPluto;
import mod.sol.celestialbodies.sedna.WorldProviderSedna;
import mod.sol.celestialbodies.titan.TeleportTypeTitan;
import mod.sol.celestialbodies.titan.WorldProviderTitan;
import mod.sol.celestialbodies.triton.WorldProviderTriton;
import mod.sol.config.ConfigManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.SpawnListEntry;

public class SolPlanets {
    // Planets
    public static Planet planetVenus;
    public static Planet planetMercury;
    public static DwarfPlanet planetCeres;
    public static DwarfPlanet planetPluto;
    public static DwarfPlanet planetKuiperBelt;
    public static DwarfPlanet planetHaumea;
    public static DwarfPlanet planetEris;
    public static DwarfPlanet planetMakemake;
    public static DwarfPlanet planetSedna;
    public static Planet planetOortCloud;
    // Gas giants (no dimension, rendered as planetary bodies)
    public static GasGiant planetJupiter;
    public static GasGiant planetSaturn;
    public static GasGiant planetUranus;
    public static GasGiant planetNeptune;
    // Moons — Mars
    public static Moon moonPhobos;
    public static Moon moonDeimos;
    // Moons — Jupiter
    public static Moon moonIo;
    public static Moon moonEuropa;
    public static Moon moonGanymede;
    public static Moon moonCallisto;
    // Moons — Saturn
    public static Moon moonRingsOfSaturn;
    public static Moon moonMimas;
    public static Moon moonEnceladus;
    public static Moon moonTethys;
    public static Moon moonDione;
    public static Moon moonRhea;
    public static Moon moonTitan;
    // Moons — Uranus
    public static Moon moonAriel;
    public static Moon moonUmbriel;
    public static Moon moonTitania;
    public static Moon moonOberon;
    // Moons — Neptune
    public static Moon moonTriton;
    // Moons — Pluto
    public static Moon moonCharon;
    
    public static void registerCelestialBodies() {
        // === Venus (overrides Galacticraft's Venus) ===
        planetVenus = (Planet) new Planet("venus").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(2.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.75F, 0.75F)).setRelativeOrbitTime(0.61527929901423877327491785323111F);
        planetVenus.setBiomeInfo(BiomeVenus.venusFlat, BiomeVenus.venusMountain, BiomeVenus.venusValley);
        planetVenus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/venus.png"));
        planetVenus.setDimensionInfo(ConfigManagerVenus.dimensionIDVenus, WorldProviderVenus.class).setTierRequired(3);
        planetVenus.setAtmosphere(new AtmosphereInfo(false, true, true, 5.0F, 0.3F, 54.0F));
        planetVenus.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        addDefaultEvolvedMobs(planetVenus, false);
        planetVenus.addChecklistKeys("equip_oxygen_suit", "equip_shield_controller", "thermal_padding_t2");
        GalaxyRegistry.registerPlanet(planetVenus);
        GalacticraftRegistry.registerTeleportType(WorldProviderVenus.class, new TeleportTypeVenus());

        // === Gas Giants (fake planets, no dimension) ===
        // Jupiter
        planetJupiter = (GasGiant) new GasGiant("jupiter").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        planetJupiter.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.0F, 2.0F)).setRelativeOrbitTime(11.861993428258488499452354874042F);
        planetJupiter.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png"));

        // Saturn
        planetSaturn = (GasGiant) new GasGiant("saturn").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        planetSaturn.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(5.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.25F, 2.25F)).setRelativeOrbitTime(29.463307776560788608981380065717F);
        planetSaturn.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn.png"));

        // Uranus
        planetUranus = (GasGiant) new GasGiant("uranus").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        planetUranus.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.38F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.5F, 2.5F)).setRelativeOrbitTime(84.063526834611171960569550930997F);
        planetUranus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus.png"));

        // Neptune
        planetNeptune = (GasGiant) new GasGiant("neptune").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        planetNeptune.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.75F, 2.75F)).setRelativeOrbitTime(164.84118291347207009857612267251F);
        planetNeptune.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/neptune.png"));

        // Override asteroid belt position
        AsteroidsModule.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.65F, 1.65F));

        // === Planets ===
        // Mercury
        planetMercury = (Planet) new Planet("mercury").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.5F, 0.5F)).setRelativeOrbitTime(0.24096385542168674698795180722892F);
        planetMercury.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mercury.png"));
        planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
        planetMercury.setRelativeSize(0.4312F);
        planetMercury.setDimensionInfo(ConfigManager.dimensionIds.dimensionidMercury, WorldProviderMercury.class).setTierRequired(3).setBiomeInfo(SolBiomes.MERCURY_FLAT);
        addDefaultEvolvedMobs(planetMercury, false);
        planetMercury.setDimensionSuffix("_mercury");

        // Ceres (dwarf)
        planetCeres = (DwarfPlanet) new DwarfPlanet("ceres").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.48F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.5F, 1.5F)).setRelativeOrbitTime(5.2433153256534542F);
        planetCeres.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/ceres.png"));
        planetCeres.setAtmosphere(new AtmosphereInfo(false, false, false, -1.7F, 0.0F, 0.0F));
        planetCeres.setRelativeSize(0.1294F);
        planetCeres.setDimensionSuffix("_ceres");

        // Pluto (dwarf)
        planetPluto = (DwarfPlanet) new DwarfPlanet("pluto").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(3.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.0F, 3.0F)).setRelativeOrbitTime(5.2433153256534542F);
        planetPluto.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/pluto.png"));
        planetPluto.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        planetPluto.setRelativeSize(0.1294F);
        planetPluto.setDimensionInfo(ConfigManager.dimensionIds.dimensionidPluto, WorldProviderPluto.class).setTierRequired(8);
        planetPluto.setBiomeInfo(SolBiomes.PLUTO_FLAT, SolBiomes.PLUTO_SNOWFIELD);
        addDefaultEvolvedMobs(planetPluto, false);
        planetPluto.setDimensionSuffix("_pluto");

        // Haumea (dwarf)
        planetHaumea = (DwarfPlanet) new DwarfPlanet("haumea").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(3.92F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.65F, 3.65F)).setRelativeOrbitTime(15.1415926F);
        planetHaumea.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/haumea.png"));
        planetHaumea.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        planetHaumea.setRelativeSize(0.0294F);
        planetHaumea.setDimensionSuffix("_haumea");

        // Eris (dwarf)
        planetEris = (DwarfPlanet) new DwarfPlanet("eris").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.42F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.4F, 3.4F)).setRelativeOrbitTime(16.1415926F);
        planetEris.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/eris.png"));
        planetEris.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        planetEris.setRelativeSize(0.0294F);
        planetEris.setDimensionSuffix("_eris");

        // Kuiperbelt (dwarf)
        planetKuiperBelt = (DwarfPlanet) new DwarfPlanet("kuiper_belt").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.55F, 3.55F)).setRelativeOrbitTime(90.0F);
        planetKuiperBelt.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/kuiper_belt.png"));
        planetKuiperBelt.setDimensionSuffix("_kuiper_belt");

        // Makemake (dwarf)
        planetMakemake = (DwarfPlanet) new DwarfPlanet("makemake").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(9.81F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.8F, 3.8F)).setRelativeOrbitTime(11.1415926F);
        planetMakemake.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/makemake.png"));
        planetMakemake.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        planetMakemake.setRelativeSize(0.0294F);
        planetMakemake.setDimensionSuffix("_makemake");

        // Sedna (dwarf)
        planetSedna = (DwarfPlanet) new DwarfPlanet("sedna").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(14.421412354F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5F, 5F)).setRelativeOrbitTime(39.143442132456F);
        planetSedna.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/sedna.png"));
        planetSedna.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        planetSedna.setRelativeSize(0.1294F);
        planetSedna.setDimensionInfo(ConfigManager.dimensionIds.dimensionidSedna, WorldProviderSedna.class).setTierRequired(9);
        planetSedna.setBiomeInfo(SolBiomes.SEDNA_FLAT);
        addDefaultEvolvedMobs(planetSedna, false);
        planetSedna.setDimensionSuffix("_sedna");

        // Oort Cloud
        planetOortCloud = (Planet) new Planet("oort_cloud").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(7.5F, 7.5F)).setRelativeOrbitTime(90.0F);
        planetOortCloud.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/oort_cloud.png"));
        planetOortCloud.setDimensionSuffix("_oort_cloud");

        // === Moons ===
        // Phobos (Mars)
        moonPhobos = (Moon) new Moon("phobos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12F, 12F)).setRelativeOrbitTime(20.0F);
        moonPhobos.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/phobos.png"));
        moonPhobos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonPhobos.setRelativeSize(0.4312F);
        moonPhobos.setDimensionSuffix("_phobos");

        // Deimos (Mars)
        moonDeimos = (Moon) new Moon("deimos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(0.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(20F, 20F)).setRelativeOrbitTime(45.68F);
        moonDeimos.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/deimos.png"));
        moonDeimos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonDeimos.setRelativeSize(0.4312F);
        moonDeimos.setDimensionSuffix("_deimos");

        // Io (Jupiter)
        moonIo = (Moon) new Moon("io").setParentPlanet(planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
        moonIo.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/io.png"));
        moonIo.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
        moonIo.setRelativeSize(0.4312F);
        moonIo.setBiomeInfo(SolBiomes.IO_FLAT, SolBiomes.IO_ASH_LAND, SolBiomes.IO_SULFUR_FIELD);
        moonIo.setDimensionInfo(ConfigManager.dimensionIds.dimensionidIo, WorldProviderIo.class).setTierRequired(4);
        moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        moonIo.setDimensionSuffix("_io");

        // Europa (Jupiter)
        moonEuropa = (Moon) new Moon("europa").setParentPlanet(planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        moonEuropa.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/europa.png"));
        moonEuropa.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
        moonEuropa.setRelativeSize(0.4312F);
        moonEuropa.setBiomeInfo(SolBiomes.EUROPA_FLAT, SolBiomes.EUROPA_MOUNTAIN, SolBiomes.EUROPA_VALLEY);
        moonEuropa.setDimensionInfo(ConfigManager.dimensionIds.dimensionidEuropa, WorldProviderEuropa.class).setTierRequired(4);
        addDefaultEvolvedMobs(moonEuropa, true);
        moonEuropa.setDimensionSuffix("_europa");

        // Ganymede (Jupiter)
        moonGanymede = (Moon) new Moon("ganymede").setParentPlanet(planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
        moonGanymede.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/ganymede.png"));
        moonGanymede.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonGanymede.setRelativeSize(0.4312F);
        moonGanymede.setDimensionSuffix("_ganymede");

        // Callisto (Jupiter)
        moonCallisto = (Moon) new Moon("callisto").setParentPlanet(planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
        moonCallisto.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/callisto.png"));
        moonCallisto.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonCallisto.setRelativeSize(0.4312F);
        moonCallisto.setDimensionSuffix("_callisto");

        // Rings of Saturn (Saturn)
        moonRingsOfSaturn = (Moon) new Moon("rings_of_saturn").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5.0F, 5.0F)).setRelativeOrbitTime(90.0F);
        moonRingsOfSaturn.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/rings_of_saturn.png"));
        moonRingsOfSaturn.setRelativeSize(0.4312F);
        moonRingsOfSaturn.setDimensionSuffix("_rings_of_saturn");

        // Mimas (Saturn)
        moonMimas = (Moon) new Moon("mimas").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(9.375F);
        moonMimas.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.0F, 0.0F));
        moonMimas.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/mimas.png"));
        moonMimas.setRelativeSize(0.4312F);
        moonMimas.setBiomeInfo(SolBiomes.MIMAS_FLAT);
        moonMimas.setDimensionInfo(ConfigManager.dimensionIds.dimensionidMimas, WorldProviderMimas.class).setTierRequired(5);
        addDefaultEvolvedMobs(moonMimas, true);
        moonMimas.setDimensionSuffix("_mimas");

        // Enceladus (Saturn)
        moonEnceladus = (Moon) new Moon("enceladus").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(13.70218F);
        moonEnceladus.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/enceladus.png"));
        moonEnceladus.setRelativeSize(0.4312F);
        moonEnceladus.setDimensionSuffix("_enceladus");

        // Tethys (Saturn)
        moonTethys = (Moon) new Moon("tethys").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(16.5F, 16.5F)).setRelativeOrbitTime(18.87802F);
        moonTethys.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/tethys.png"));
        moonTethys.setRelativeSize(0.4312F);
        moonTethys.setDimensionSuffix("_tethys");

        // Dione (Saturn)
        moonDione = (Moon) new Moon("dione").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(27.36915F);
        moonDione.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/dione.png"));
        moonDione.setRelativeSize(0.4312F);
        moonDione.setDimensionSuffix("_dione");

        // Rhea (Saturn)
        moonRhea = (Moon) new Moon("rhea").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(23.5F, 23.5F)).setRelativeOrbitTime(45.18212F);
        moonRhea.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/rhea.png"));
        moonRhea.setRelativeSize(0.4312F);
        moonRhea.setDimensionSuffix("_rhea");

        // Titan (Saturn)
        moonTitan = (Moon) new Moon("titan").setParentPlanet(planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(26.5F, 26.5F)).setRelativeOrbitTime(159.45F);
        moonTitan.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/titan.png"));
        moonTitan.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.5F, 0.3F));
        moonTitan.atmosphereComponent(EnumAtmosphericGas.METHANE);
        moonTitan.setRelativeSize(0.4312F);
        moonTitan.setBiomeInfo(SolBiomes.TITAN_FLAT, SolBiomes.TITAN_MOUNTAIN, SolBiomes.TITAN_OCEAN);
        moonTitan.setDimensionInfo(ConfigManager.dimensionIds.dimensionidTitan, WorldProviderTitan.class).setTierRequired(5);
        addDefaultEvolvedMobs(moonTitan, true);
        moonTitan.setDimensionSuffix("_titan");

        // Ariel (Uranus)
        moonAriel = (Moon) new Moon("ariel").setParentPlanet(planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
        moonAriel.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/ariel.png"));
        moonAriel.setRelativeSize(0.4312F);
        moonAriel.setBiomeInfo(SolBiomes.ARIEL_FLAT);
        moonAriel.setDimensionInfo(ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class).setTierRequired(6);
        addDefaultEvolvedMobs(moonAriel, true);
        moonAriel.setDimensionSuffix("_ariel");

        // Umbriel (Uranus)
        moonUmbriel = (Moon) new Moon("umbriel").setParentPlanet(planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        moonUmbriel.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/umbriel.png"));
        moonUmbriel.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
        moonUmbriel.setRelativeSize(0.4312F);
        moonUmbriel.setDimensionSuffix("_umbriel");

        // Titania (Uranus)
        moonTitania = (Moon) new Moon("titania").setParentPlanet(planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
        moonTitania.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/titania.png"));
        moonTitania.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonTitania.setRelativeSize(0.4312F);
        moonTitania.setDimensionSuffix("_titania");

        // Oberon (Uranus)
        moonOberon = (Moon) new Moon("oberon").setParentPlanet(planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
        moonOberon.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/oberon.png"));
        moonOberon.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        moonOberon.setRelativeSize(0.4312F);
        moonOberon.setDimensionSuffix("_oberon");

        // Triton (Neptune)
        moonTriton = (Moon) new Moon("triton").setParentPlanet(planetNeptune).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        moonTriton.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/triton.png"));
        moonTriton.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
        moonTriton.setBiomeInfo(SolBiomes.TRITON_FLAT);
        moonTriton.setDimensionInfo(ConfigManager.dimensionIds.dimensionidTriton, WorldProviderTriton.class).setTierRequired(7);
        moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        moonTriton.setRelativeSize(0.4312F);
        moonTriton.setDimensionSuffix("_triton");

        // Charon (Pluto)
        moonCharon = (Moon) new Moon("charon").setParentPlanet(planetPluto).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(13.5F, 13.5F)).setRelativeOrbitTime(63.87230F);
        moonCharon.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/charon.png"));
        moonCharon.setRelativeSize(7.0F);
        moonCharon.setDimensionSuffix("_charon");

        // === Register all with GalaxyRegistry ===
        // Misc — override vanilla moon rocket GUI
        GalacticraftRegistry.registerRocketGui(micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon.class, new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/moon_rocket_gui.png"));

        // Planets
        GalaxyRegistry.registerPlanet(planetMercury);
        GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/mercury_rocket_gui.png"));

        GalaxyRegistry.registerPlanet(planetCeres);
        GalaxyRegistry.registerPlanet(planetPluto);
        GalacticraftRegistry.registerTeleportType(WorldProviderPluto.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderPluto.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/pluto_rocket_gui.png"));

        GalaxyRegistry.registerPlanet(planetHaumea);
        GalaxyRegistry.registerPlanet(planetMakemake);
        GalaxyRegistry.registerPlanet(planetKuiperBelt);
        GalaxyRegistry.registerPlanet(planetEris);
        GalaxyRegistry.registerPlanet(planetSedna);
        GalacticraftRegistry.registerTeleportType(WorldProviderSedna.class, new TeleportTypeUniversal());
        GalaxyRegistry.registerPlanet(planetOortCloud);

        // Moons
        GalaxyRegistry.registerMoon(moonPhobos);
        GalaxyRegistry.registerMoon(moonDeimos);

        GalaxyRegistry.registerMoon(moonIo);
        GalacticraftRegistry.registerTeleportType(WorldProviderIo.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderIo.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/io_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonEuropa);
        GalacticraftRegistry.registerTeleportType(WorldProviderEuropa.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderEuropa.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/europa_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonGanymede);
        GalaxyRegistry.registerMoon(moonCallisto);
        GalaxyRegistry.registerMoon(moonRingsOfSaturn);

        GalaxyRegistry.registerMoon(moonMimas);
        GalacticraftRegistry.registerTeleportType(WorldProviderMimas.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderMimas.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/mimas_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonEnceladus);
        GalaxyRegistry.registerMoon(moonTethys);
        GalaxyRegistry.registerMoon(moonDione);
        GalaxyRegistry.registerMoon(moonRhea);

        GalaxyRegistry.registerMoon(moonTitan);
        GalacticraftRegistry.registerTeleportType(WorldProviderTitan.class, new TeleportTypeTitan());
        GalacticraftRegistry.registerRocketGui(WorldProviderTitan.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/titan_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonAriel);
        GalacticraftRegistry.registerTeleportType(WorldProviderAriel.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderAriel.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/ariel_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonUmbriel);
        GalaxyRegistry.registerMoon(moonOberon);

        GalaxyRegistry.registerMoon(moonTriton);
        GalacticraftRegistry.registerTeleportType(WorldProviderTriton.class, new TeleportTypeUniversal());
        GalacticraftRegistry.registerRocketGui(WorldProviderTriton.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/triton_rocket_gui.png"));

        GalaxyRegistry.registerMoon(moonCharon);

        // Fake planets (gas giants, rendered as planetary bodies)
        GalaxyRegistry.registerPlanet(planetJupiter);
        GalaxyRegistry.registerPlanet(planetSaturn);
        GalaxyRegistry.registerPlanet(planetUranus);
        GalaxyRegistry.registerPlanet(planetNeptune);

        // Asteroid belt (Galacticraft's native)
        GalaxyRegistry.registerPlanet(AsteroidsModule.planetAsteroids);
    }

    private static void addDefaultEvolvedMobs(CelestialBody body, boolean includeHeavy) {
        int weight = includeHeavy ? 10 : 8;
        int endermanWeight = includeHeavy ? 12 : 10;

        body.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, weight, 2, 3));
        body.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, weight, 2, 3));
        body.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, weight, 2, 3));
        body.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, weight, 2, 3));
        body.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, endermanWeight, 1, 4));
    }
}
