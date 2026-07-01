package mod.sol;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderMoon;
import micdoodle8.mods.galacticraft.core.entities.*;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.venus.ConfigManagerVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.TeleportTypeVenus;
import micdoodle8.mods.galacticraft.planets.venus.dimension.WorldProviderVenus;
import micdoodle8.mods.galacticraft.planets.venus.world.gen.BiomeVenus;
import mod.sol.api.galaxy.DwarfPlanet;
import mod.sol.api.galaxy.GasGiant;
import mod.sol.client.gui.container.GuiSchematicRocket;
import mod.sol.client.gui.screen.SolCelestialSelection;
import mod.sol.config.ConfigManager;
import mod.sol.entities.EntityHugeFireball;
import mod.sol.entities.EntityTierRocket;
import mod.sol.entities.boss.*;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolDimensions;
import mod.sol.init.SolItems;
import mod.sol.init.SolOreDict;
import mod.sol.inventory.ContainerSchematicRocket;
import mod.sol.items.ItemSchematic;
import mod.sol.planets.jupiter.moons.europa.biome.BiomeEuropa;
import mod.sol.planets.jupiter.moons.europa.dimension.TeleportTypeEuropa;
import mod.sol.planets.jupiter.moons.europa.dimension.WorldProviderEuropa;
import mod.sol.planets.jupiter.moons.io.biome.BiomeIo;
import mod.sol.planets.jupiter.moons.io.dimension.TeleportTypeIo;
import mod.sol.planets.jupiter.moons.io.dimension.WorldProviderIo;
import mod.sol.planets.mercury.biome.BiomeMercury;
import mod.sol.planets.mercury.dimension.TeleportTypeMercury;
import mod.sol.planets.mercury.dimension.WorldProviderMercury;
import mod.sol.planets.neptune.triton.biome.BiomeTriton;
import mod.sol.planets.neptune.triton.dimension.TeleportTypeTriton;
import mod.sol.planets.neptune.triton.dimension.WorldProviderTriton;
import mod.sol.planets.pluto.biome.BiomePluto;
import mod.sol.planets.pluto.dimension.TeleportTypePluto;
import mod.sol.planets.pluto.dimension.WorldProviderPluto;
import mod.sol.planets.saturn.moons.mimas.biome.BiomeMimas;
import mod.sol.planets.saturn.moons.mimas.dimension.TeleportTypeMimas;
import mod.sol.planets.saturn.moons.mimas.dimension.WorldProviderMimas;
import mod.sol.planets.saturn.moons.titan.biome.BiomeTitan;
import mod.sol.planets.saturn.moons.titan.dimension.TeleportTypeTitan;
import mod.sol.planets.saturn.moons.titan.dimension.WorldProviderTitan;
import mod.sol.planets.sedna.biome.BiomeSedna;
import mod.sol.planets.sedna.dimension.TeleportTypeSedna;
import mod.sol.planets.sedna.dimension.WorldProviderSedna;
import mod.sol.planets.uranus.moon.ariel.biome.BiomeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.TeleportTypeAriel;
import mod.sol.planets.uranus.moon.ariel.dimension.WorldProviderAriel;
import mod.sol.proxy.CommonProxy;
import mod.sol.recipe.BaseRocketRecipeManager;
import mod.sol.recipe.SolRecipeCompressor;
import mod.sol.recipe.SolRecipeSmelting;
import mod.sol.render.RenderRocketBase;
import mod.sol.render.entity.*;
import mod.sol.render.tile.*;
import mod.sol.schematic.SchematicRocket;
import mod.sol.tile.*;
import mod.sol.util.RocketModelUtil;
import mod.sol.util.handler.SolEventHandlerClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, dependencies = "required-after:galacticraftcore")
public class TheSol {
    public static final CreativeTabs ITEM_TAB = new CreativeTabs("sol_items") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SolItems.ENGINE_BOOSTERS, 1, 0);
        }
    };
    public static final CreativeTabs BLOCK_TAB = new CreativeTabs("sol_blocks") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SolBlocks.MERCURY_DUNGEON_BRICK);
        }
    };
    // SolarSystem
    // GCCore
    public static Planet planetVenus;
    //    public static Planet planetMars;
    // Planets
    public static Planet planetMercury;
    public static DwarfPlanet planetCeres;
    public static DwarfPlanet planetPluto;
    public static Planet planetKuiperBelt;
    public static DwarfPlanet planetHaumea;
    public static DwarfPlanet planetEris;
    public static DwarfPlanet planetMakemake;
    public static DwarfPlanet planetSedna;
    public static Planet planetOortCloud;
    // Fake
    public static GasGiant planetJupiter;
    public static GasGiant planetSaturn;
    public static GasGiant planetUranus;
    public static GasGiant planetNeptune;
    // Moons
    // Mars
    public static Moon moonPhobos;
    public static Moon moonDeimos;
    // Jupiter
    public static Moon moonIo;
    public static Moon moonEuropa;
    public static Moon moonGanymede;
    public static Moon moonCallisto;
    // Saturn
    public static Moon moonRingsOfSaturn;
    public static Moon moonMimas;
    public static Moon moonEnceladus;
    public static Moon moonTethys;
    public static Moon moonDione;
    public static Moon moonRhea;
    public static Moon moonTitan;
    // Uranus
    public static Moon moonAriel;
    public static Moon moonUmbriel;
    public static Moon moonTitania;
    public static Moon moonOberon;
    // Neptune
    public static Moon moonTriton;
    // Pluto
    public static Moon moonCharon;
    @Instance
    public static TheSol instance;
    @SidedProxy(clientSide = "mod.sol.proxy.ClientProxy", serverSide = "mod.sol.proxy.CommonProxy")
    public static CommonProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        SolDimensions.Mercury = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidMercury);

        SolDimensions.Io = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidIo);
        SolDimensions.Europa = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidEuropa);

        SolDimensions.Mimas = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidMimas);
        SolDimensions.Titan = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidTitan);

        SolDimensions.Ariel = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidAriel);

        SolDimensions.Triton = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidTriton);

        SolDimensions.Pluto = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidPluto);
        SolDimensions.KuiperBelt = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidKuiperBelt);

        SolDimensions.Sedna = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidSedna);
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public static void postInitClient(FMLPostInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier4.class, new TileEntityTreasureTier4ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier5.class, new TileEntityTreasureTier5ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier6.class, new TileEntityTreasureTier6ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier7.class, new TileEntityTreasureTier7ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier8.class, new TileEntityTreasureTier8ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier9.class, new TileEntityTreasureTier9ChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChestTier10.class, new TileEntityTreasureTier10ChestRenderer());
    }

    public static void registerNonMobEntity(Class<? extends Entity> var0, String var1, int trackingDistance, int updateFreq, boolean sendVel) {
        ResourceLocation registryName = new ResourceLocation(Tags.MOD_ID, var1);
        EntityRegistry.registerModEntity(registryName, var0, var1, GCCoreUtil.nextInternalID(), TheSol.instance, trackingDistance, updateFreq, sendVel);
    }

    public static void registerEntityCreature(Class<? extends Entity> clazz, String name, int back, int fore) {
        TheSol.registerNonMobEntity(clazz, name, 80, 3, true);
        int nextEggID = GCCoreUtil.getNextValidID();
        if (nextEggID < 65536) {
            ResourceLocation resourcelocation = new ResourceLocation(Tags.MOD_ID, name);
            EntityList.ENTITY_EGGS.put(resourcelocation, new EntityList.EntityEggInfo(resourcelocation, back, fore));
        }
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {
        OBJLoaderGC.instance.addDomain(Tags.MOD_ID);
        RenderingRegistry.registerEntityRenderingHandler(EntityTierRocket.class, RenderRocketBase::new);
        MinecraftForge.EVENT_BUS.register(this);

        RenderingRegistry.registerEntityRenderingHandler(EntityHugeFireball.class, (RenderManager manager) -> new RenderHugeFireball(manager, 1));

        RenderingRegistry.registerEntityRenderingHandler(EntityMercuryBossBlaze.class, RenderMercuryBossBlaze::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJupiterBossGhast.class, RenderJupiterBossGhast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySaturnBossStray.class, RenderSaturnBossStray::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUranusBossSlime.class, RenderUranusBossSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNeptuneBossSpider.class, RenderNeptuneBossSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossSilverfish.class, RenderBossSilverfish::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossMagmaCube.class, RenderBossMagmaCube::new);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        TheSol.planetVenus = (Planet) new Planet("venus").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(2.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.75F, 0.75F)).setRelativeOrbitTime(0.61527929901423877327491785323111F);
        TheSol.planetVenus.setBiomeInfo(BiomeVenus.venusFlat, BiomeVenus.venusMountain, BiomeVenus.venusValley);
        TheSol.planetVenus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/venus.png"));
        TheSol.planetVenus.setDimensionInfo(ConfigManagerVenus.dimensionIDVenus, WorldProviderVenus.class).setTierRequired(3);
        TheSol.planetVenus.setAtmosphere(new AtmosphereInfo(false, true, true, 5.0F, 0.3F, 54.0F));
        TheSol.planetVenus.atmosphereComponent(EnumAtmosphericGas.CO2).atmosphereComponent(EnumAtmosphericGas.NITROGEN);
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetVenus.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetVenus.addChecklistKeys("equip_oxygen_suit", "equip_shield_controller", "thermal_padding_t2");
        GalaxyRegistry.registerPlanet(TheSol.planetVenus);
        GalacticraftRegistry.registerTeleportType(WorldProviderVenus.class, new TeleportTypeVenus());

        // Fake Planets
        TheSol.planetJupiter = (GasGiant) new GasGiant("jupiter").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        TheSol.planetJupiter.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.0F, 2.0F)).setRelativeOrbitTime(11.861993428258488499452354874042F);
        TheSol.planetJupiter.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png"));

        TheSol.planetSaturn = (GasGiant) new GasGiant("saturn").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        TheSol.planetSaturn.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(5.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.25F, 2.25F)).setRelativeOrbitTime(29.463307776560788608981380065717F);
        TheSol.planetSaturn.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn.png"));

        TheSol.planetUranus = (GasGiant) new GasGiant("uranus").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        TheSol.planetUranus.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.38F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.5F, 2.5F)).setRelativeOrbitTime(84.063526834611171960569550930997F);
        TheSol.planetUranus.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus.png"));

        TheSol.planetNeptune = (GasGiant) new GasGiant("neptune").setParentSolarSystem(GalacticraftCore.solarSystemSol);
        TheSol.planetNeptune.setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.0F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(2.75F, 2.75F)).setRelativeOrbitTime(164.84118291347207009857612267251F);
        TheSol.planetNeptune.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/neptune.png"));
        // Override
        AsteroidsModule.planetAsteroids.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.65F, 1.65F));

        // Planets
        // Mercury
        TheSol.planetMercury = (Planet) new Planet("mercury").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(0.5F, 0.5F)).setRelativeOrbitTime(0.24096385542168674698795180722892F);
        TheSol.planetMercury.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/mercury.png"));
        TheSol.planetMercury.setAtmosphere(new AtmosphereInfo(false, false, false, 5.0F, 0.0F, 0.0F));
        TheSol.planetMercury.setRelativeSize(0.4312F);
        TheSol.planetMercury.setDimensionInfo(ConfigManager.dimensionIds.dimensionidMercury, WorldProviderMercury.class).setTierRequired(3).setBiomeInfo(BiomeMercury.mercuryFlat);
        TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetMercury.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetMercury.setDimensionSuffix("_mercury");
        // Ceres
        TheSol.planetCeres = (DwarfPlanet) new DwarfPlanet("ceres").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.48F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.5F, 1.5F)).setRelativeOrbitTime(5.2433153256534542F);
        TheSol.planetCeres.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/ceres.png"));
        TheSol.planetCeres.setAtmosphere(new AtmosphereInfo(false, false, false, -1.7F, 0.0F, 0.0F));
        TheSol.planetCeres.setRelativeSize(0.1294F);
        TheSol.planetCeres.setDimensionSuffix("_ceres");
        // Pluto
        TheSol.planetPluto = (DwarfPlanet) new DwarfPlanet("pluto").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(3.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.0F, 3.0F)).setRelativeOrbitTime(5.2433153256534542F);
        TheSol.planetPluto.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/pluto.png"));
        TheSol.planetPluto.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        TheSol.planetPluto.setRelativeSize(0.1294F);
        TheSol.planetPluto.setDimensionInfo(ConfigManager.dimensionIds.dimensionidPluto, WorldProviderPluto.class).setTierRequired(8);
        TheSol.planetPluto.setBiomeInfo(BiomePluto.plutoFlat, BiomePluto.plutoSnowfield);
        TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetPluto.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetPluto.setDimensionSuffix("_pluto");
        // Haumea
        TheSol.planetHaumea = (DwarfPlanet) new DwarfPlanet("haumea").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(3.92F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.65F, 3.65F)).setRelativeOrbitTime(15.1415926F);
        TheSol.planetHaumea.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/haumea.png"));
        TheSol.planetHaumea.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        TheSol.planetHaumea.setRelativeSize(0.0294F);
        TheSol.planetHaumea.setDimensionSuffix("_haumea");
        // Eris
        TheSol.planetEris = (DwarfPlanet) new DwarfPlanet("eris").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(2.42F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.4F, 3.4F)).setRelativeOrbitTime(16.1415926F);
        TheSol.planetEris.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/eris.png"));
        TheSol.planetEris.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        TheSol.planetEris.setRelativeSize(0.0294F);
        TheSol.planetEris.setDimensionSuffix("_eris");
        // Makemake
        TheSol.planetMakemake = (DwarfPlanet) new DwarfPlanet("makemake").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(255.0F, 0.0F, 0.0F).setPhaseShift(9.81F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.8F, 3.8F)).setRelativeOrbitTime(11.1415926F);
        TheSol.planetMakemake.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/makemake.png"));
        TheSol.planetMakemake.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        TheSol.planetMakemake.setRelativeSize(0.0294F);
        TheSol.planetMakemake.setDimensionSuffix("_makemake");
        // Kuiperbelt
        TheSol.planetKuiperBelt = (Planet) new Planet("kuiper_belt").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(3.55F, 3.55F)).setRelativeOrbitTime(90.0F);
        TheSol.planetKuiperBelt.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/kuiper_belt.png"));
        TheSol.planetKuiperBelt.setDimensionSuffix("_kuiper_belt");
        // Sedna
        TheSol.planetSedna = (DwarfPlanet) new DwarfPlanet("sedna").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(14.421412354F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5F, 5F)).setRelativeOrbitTime(39.143442132456F);
        TheSol.planetSedna.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/sedna.png"));
        TheSol.planetSedna.setAtmosphere(new AtmosphereInfo(false, false, false, -6.0F, 0.0F, 0.0F));
        TheSol.planetSedna.setRelativeSize(0.1294F);
        TheSol.planetSedna.setDimensionInfo(ConfigManager.dimensionIds.dimensionidSedna, WorldProviderSedna.class).setTierRequired(9);
        TheSol.planetSedna.setBiomeInfo(BiomeSedna.sednaFlat);
        TheSol.planetSedna.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.planetSedna.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.planetSedna.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.planetSedna.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.planetSedna.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.planetSedna.setDimensionSuffix("_sedna");
        // Oortcloud
        TheSol.planetOortCloud = (Planet) new Planet("oort_cloud").setParentSolarSystem(GalacticraftCore.solarSystemSol).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) (Math.random() * (2 * Math.PI))).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(7.5F, 7.5F)).setRelativeOrbitTime(90.0F);
        TheSol.planetOortCloud.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/oort_cloud.png"));
        TheSol.planetOortCloud.setDimensionSuffix("_oort_cloud");
        // Moons
        // Phobos
        TheSol.moonPhobos = (Moon) new Moon("phobos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(1.45F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12F, 12F)).setRelativeOrbitTime(20.0F);
        TheSol.moonPhobos.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/phobos.png"));
        TheSol.moonPhobos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonPhobos.setRelativeSize(0.4312F);
        TheSol.moonPhobos.setDimensionSuffix("_phobos");
        // Deimos
        TheSol.moonDeimos = (Moon) new Moon("deimos").setParentPlanet(MarsModule.planetMars).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift(0.25F).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(20F, 20F)).setRelativeOrbitTime(45.68F);
        TheSol.moonDeimos.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/deimos.png"));
        TheSol.moonDeimos.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonDeimos.setRelativeSize(0.4312F);
        TheSol.moonDeimos.setDimensionSuffix("_deimos");
        // Io
        TheSol.moonIo = (Moon) new Moon("io").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
        TheSol.moonIo.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/io.png"));
        TheSol.moonIo.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
        TheSol.moonIo.setRelativeSize(0.4312F);
        TheSol.moonIo.setBiomeInfo(BiomeIo.ioFlat, BiomeIo.ioAshLand, BiomeIo.ioSulfurField);
        TheSol.moonIo.setDimensionInfo(ConfigManager.dimensionIds.dimensionidIo, WorldProviderIo.class).setTierRequired(4);
        TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
        TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
        TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
        TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
        TheSol.moonIo.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 10, 1, 4));
        TheSol.moonIo.setDimensionSuffix("_io");
        // Europa
        TheSol.moonEuropa = (Moon) new Moon("europa").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        TheSol.moonEuropa.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/europa.png"));
        TheSol.moonEuropa.setAtmosphere(new AtmosphereInfo(false, false, false, -2.0F, 0.0F, 0.0F));
        TheSol.moonEuropa.setRelativeSize(0.4312F);
        TheSol.moonEuropa.setBiomeInfo(BiomeEuropa.europaFlat, BiomeEuropa.europaMountain, BiomeEuropa.europaValley);
        TheSol.moonEuropa.setDimensionInfo(ConfigManager.dimensionIds.dimensionidEuropa, WorldProviderEuropa.class).setTierRequired(4);
        TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        TheSol.moonEuropa.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        TheSol.moonEuropa.setDimensionSuffix("_europa");
        // Ganymede
        TheSol.moonGanymede = (Moon) new Moon("ganymede").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
        TheSol.moonGanymede.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/ganymede.png"));
        TheSol.moonGanymede.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonGanymede.setRelativeSize(0.4312F);
        TheSol.moonGanymede.setDimensionSuffix("_ganymede");
        // Callisto
        TheSol.moonCallisto = (Moon) new Moon("callisto").setParentPlanet(TheSol.planetJupiter).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
        TheSol.moonCallisto.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/callisto.png"));
        TheSol.moonCallisto.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonCallisto.setRelativeSize(0.4312F);
        TheSol.moonCallisto.setDimensionSuffix("_callisto");
        // Saturn
        // RingsOfSaturn
        TheSol.moonRingsOfSaturn = (Moon) new Moon("rings_of_saturn").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(5.0F, 5.0F)).setRelativeOrbitTime(90.0F);
        TheSol.moonRingsOfSaturn.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/rings_of_saturn.png"));
        TheSol.moonRingsOfSaturn.setRelativeSize(0.4312F);
        TheSol.moonRingsOfSaturn.setDimensionSuffix("_rings_of_saturn");
        // Mimas
        TheSol.moonMimas = (Moon) new Moon("mimas").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(9.375F);
        TheSol.moonMimas.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.0F, 0.0F));
        TheSol.moonMimas.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/mimas.png"));
        TheSol.moonMimas.setRelativeSize(0.4312F);
        TheSol.moonMimas.setBiomeInfo(BiomeMimas.mimasFlat);
        TheSol.moonMimas.setDimensionInfo(ConfigManager.dimensionIds.dimensionidMimas, WorldProviderMimas.class).setTierRequired(5);
        TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        TheSol.moonMimas.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        TheSol.moonMimas.setDimensionSuffix("_mimas");
        // Enceladus
        TheSol.moonEnceladus = (Moon) new Moon("enceladus").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(13.70218F);
        TheSol.moonEnceladus.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/enceladus.png"));
        TheSol.moonEnceladus.setRelativeSize(0.4312F);
        TheSol.moonEnceladus.setDimensionSuffix("_enceladus");
        // Tethys
        TheSol.moonTethys = (Moon) new Moon("tethys").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(16.5F, 16.5F)).setRelativeOrbitTime(18.87802F);
        TheSol.moonTethys.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/tethys.png"));
        TheSol.moonTethys.setRelativeSize(0.4312F);
        TheSol.moonTethys.setDimensionSuffix("_tethys");
        // Dione
        TheSol.moonDione = (Moon) new Moon("dione").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(27.36915F);
        TheSol.moonDione.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/dione.png"));
        TheSol.moonDione.setRelativeSize(0.4312F);
        TheSol.moonDione.setDimensionSuffix("_dione");
        // Rhea
        TheSol.moonRhea = (Moon) new Moon("rhea").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(23.5F, 23.5F)).setRelativeOrbitTime(45.18212F);
        TheSol.moonRhea.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/rhea.png"));
        TheSol.moonRhea.setRelativeSize(0.4312F);
        TheSol.moonRhea.setDimensionSuffix("_rhea");
        // Titan
        TheSol.moonTitan = (Moon) new Moon("titan").setParentPlanet(TheSol.planetSaturn).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(26.5F, 26.5F)).setRelativeOrbitTime(159.45F);
        TheSol.moonTitan.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/titan.png"));
        TheSol.moonTitan.setAtmosphere(new AtmosphereInfo(false, false, false, -3.0F, 0.5F, 0.3F));
        TheSol.moonTitan.atmosphereComponent(EnumAtmosphericGas.METHANE);
        TheSol.moonTitan.setRelativeSize(0.4312F);
        TheSol.moonTitan.setBiomeInfo(BiomeTitan.titanFlat, BiomeTitan.titanMountain, BiomeTitan.titanOcean);
        TheSol.moonTitan.setDimensionInfo(ConfigManager.dimensionIds.dimensionidTitan, WorldProviderTitan.class).setTierRequired(5);
        TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        TheSol.moonTitan.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        TheSol.moonTitan.setDimensionSuffix("_titan");
        // Uranus
        // Ariel
        TheSol.moonAriel = (Moon) new Moon("ariel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(9.0F, 9.0F)).setRelativeOrbitTime(17.69F);
        TheSol.moonAriel.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/ariel.png"));
        TheSol.moonAriel.setRelativeSize(0.4312F);
        TheSol.moonAriel.setBiomeInfo(BiomeAriel.arielFlat);
        TheSol.moonAriel.setDimensionInfo(ConfigManager.dimensionIds.dimensionidAriel, WorldProviderAriel.class).setTierRequired(6);
        TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        TheSol.moonAriel.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        TheSol.moonAriel.setDimensionSuffix("_ariel");
        // Umbriel
        TheSol.moonUmbriel = (Moon) new Moon("umbriel").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        TheSol.moonUmbriel.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/umbriel.png"));
        TheSol.moonUmbriel.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
        TheSol.moonUmbriel.setRelativeSize(0.4312F);
        TheSol.moonUmbriel.setDimensionSuffix("_umbriel");
        // Titania
        TheSol.moonTitania = (Moon) new Moon("titania").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(19.5F, 19.5F)).setRelativeOrbitTime(71.54F);
        TheSol.moonTitania.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/titania.png"));
        TheSol.moonTitania.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonTitania.setRelativeSize(0.4312F);
        TheSol.moonTitania.setDimensionSuffix("_titania");
        // Oberon
        TheSol.moonOberon = (Moon) new Moon("oberon").setParentPlanet(TheSol.planetUranus).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(25.5F, 25.5F)).setRelativeOrbitTime(166.89F);
        TheSol.moonOberon.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/oberon.png"));
        TheSol.moonOberon.setAtmosphere(new AtmosphereInfo(false, false, false, -1.0F, 0.0F, 0.0F));
        TheSol.moonOberon.setRelativeSize(0.4312F);
        TheSol.moonOberon.setDimensionSuffix("_oberon");
        // neptune
        // triton
        TheSol.moonTriton = (Moon) new Moon("triton").setParentPlanet(TheSol.planetNeptune).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(12.5F, 12.5F)).setRelativeOrbitTime(35.51F);
        TheSol.moonTriton.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/triton.png"));
        TheSol.moonTriton.setAtmosphere(new AtmosphereInfo(false, false, false, -2.5F, 0.0F, 0.0F));
        TheSol.moonTriton.setBiomeInfo(BiomeTriton.tritonFlat);
        TheSol.moonTriton.setDimensionInfo(ConfigManager.dimensionIds.dimensionidTriton, WorldProviderTriton.class).setTierRequired(7);
        TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedZombie.class, 10, 2, 3));
        TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSpider.class, 10, 2, 3));
        TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedSkeleton.class, 10, 2, 3));
        TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedCreeper.class, 10, 2, 3));
        TheSol.moonTriton.addMobInfo(new SpawnListEntry(EntityEvolvedEnderman.class, 12, 1, 4));
        TheSol.moonTriton.setRelativeSize(0.4312F);
        TheSol.moonTriton.setDimensionSuffix("_triton");
        // pluto
        // charon
        TheSol.moonCharon = (Moon) new Moon("charon").setParentPlanet(TheSol.planetPluto).setRingColorRGB(0.1F, 0.9F, 0.6F).setPhaseShift((float) Math.PI).setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(13.5F, 13.5F)).setRelativeOrbitTime(63.87230F);
        TheSol.moonCharon.setBodyIcon(new ResourceLocation(Tags.MOD_ID, "textures/planets/charon.png"));
        TheSol.moonCharon.setRelativeSize(7.0F);
        TheSol.moonCharon.setDimensionSuffix("_charon");
        // register
        // misc
        GalacticraftRegistry.registerRocketGui(WorldProviderMoon.class, new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/moon_rocket_gui.png"));
        // planets
        GalaxyRegistry.registerPlanet(TheSol.planetMercury);
        GalacticraftRegistry.registerTeleportType(WorldProviderMercury.class, new TeleportTypeMercury());
        GalacticraftRegistry.registerRocketGui(WorldProviderMercury.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/mercury_rocket_gui.png"));

        GalaxyRegistry.registerPlanet(TheSol.planetCeres);

        GalaxyRegistry.registerPlanet(TheSol.planetPluto);
        GalacticraftRegistry.registerTeleportType(WorldProviderPluto.class, new TeleportTypePluto());
        GalacticraftRegistry.registerRocketGui(WorldProviderPluto.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/pluto_rocket_gui.png"));

        GalaxyRegistry.registerPlanet(TheSol.planetHaumea);

        GalaxyRegistry.registerPlanet(TheSol.planetMakemake);

        GalaxyRegistry.registerPlanet(TheSol.planetEris);

        GalaxyRegistry.registerPlanet(TheSol.planetKuiperBelt);

        GalaxyRegistry.registerPlanet(TheSol.planetSedna);
        GalacticraftRegistry.registerTeleportType(WorldProviderSedna.class, new TeleportTypeSedna());

        GalaxyRegistry.registerPlanet(TheSol.planetOortCloud);
        // moons
        GalaxyRegistry.registerMoon(TheSol.moonPhobos);

        GalaxyRegistry.registerMoon(TheSol.moonDeimos);

        GalaxyRegistry.registerMoon(TheSol.moonIo);
        GalacticraftRegistry.registerTeleportType(WorldProviderIo.class, new TeleportTypeIo());
        GalacticraftRegistry.registerRocketGui(WorldProviderIo.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/io_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonEuropa);
        GalacticraftRegistry.registerTeleportType(WorldProviderEuropa.class, new TeleportTypeEuropa());
        GalacticraftRegistry.registerRocketGui(WorldProviderEuropa.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/europa_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonGanymede);

        GalaxyRegistry.registerMoon(TheSol.moonCallisto);

        GalaxyRegistry.registerMoon(TheSol.moonRingsOfSaturn);

        GalaxyRegistry.registerMoon(TheSol.moonMimas);
        GalacticraftRegistry.registerTeleportType(WorldProviderMimas.class, new TeleportTypeMimas());
        GalacticraftRegistry.registerRocketGui(WorldProviderMimas.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/mimas_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonEnceladus);

        GalaxyRegistry.registerMoon(TheSol.moonTethys);

        GalaxyRegistry.registerMoon(TheSol.moonDione);

        GalaxyRegistry.registerMoon(TheSol.moonRhea);

        GalaxyRegistry.registerMoon(TheSol.moonTitan);
        GalacticraftRegistry.registerTeleportType(WorldProviderTitan.class, new TeleportTypeTitan());
        GalacticraftRegistry.registerRocketGui(WorldProviderTitan.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/titan_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonAriel);
        GalacticraftRegistry.registerTeleportType(WorldProviderAriel.class, new TeleportTypeAriel());
        GalacticraftRegistry.registerRocketGui(WorldProviderAriel.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/ariel_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonUmbriel);

        GalaxyRegistry.registerMoon(TheSol.moonOberon);

        GalaxyRegistry.registerMoon(TheSol.moonTriton);
        GalacticraftRegistry.registerTeleportType(WorldProviderTriton.class, new TeleportTypeTriton());
        GalacticraftRegistry.registerRocketGui(WorldProviderTriton.class, new ResourceLocation(Tags.MOD_ID, "textures/gui/rocketgui/triton_rocket_gui.png"));

        GalaxyRegistry.registerMoon(TheSol.moonCharon);
        // fake planets
        GalaxyRegistry.registerPlanet(TheSol.planetJupiter);
        GalaxyRegistry.registerPlanet(TheSol.planetSaturn);
        GalaxyRegistry.registerPlanet(TheSol.planetUranus);
        GalaxyRegistry.registerPlanet(TheSol.planetNeptune);
        // override
        //GalaxyRegistry.registerPlanet(TheSol.planetAsteroids);
        GalaxyRegistry.registerPlanet(AsteroidsModule.planetAsteroids);
        // planets

        // moons

        // rockets
        TheSol.registerNonMobEntity(EntityHugeFireball.class, "fireball_huge", 150, 1, false);

        TheSol.registerNonMobEntity(EntityTierRocket.class, "rocket", 150, 1, false);
        // schematic
        Item schematicItem = SolItems.SCHEMATICS;
        ItemSchematic schematicClass = (ItemSchematic) schematicItem;

        Item[] tierRockets = {
                SolItems.ROCKET_T4,
                SolItems.ROCKET_T5,
                SolItems.ROCKET_T6,
                SolItems.ROCKET_T7,
                SolItems.ROCKET_T8,
                SolItems.ROCKET_T9
        };

        for (int tier = 4; tier <= 9; tier++) {
            final int t = tier;
            final int meta = schematicClass.getMetaFromTier(tier);
            Item rocketItem = tierRockets[t - 4];

            SchematicRegistry.registerSchematicRecipe(new SchematicRocket(
                    t,
                    schematicItem,
                    meta,
                    (inv, pos) -> new GuiSchematicRocket(inv, pos, t, rocketItem),
                    (inv, pos) -> new ContainerSchematicRocket(inv, pos, t)
            ));
            ItemSchematic.registerSchematicItems(new ItemStack(schematicItem, 1, meta));

            if (tier == 4) {
                BaseRocketRecipeManager.registerRocketRecipes(
                        4,
                        new ItemStack(AsteroidsItems.heavyNoseCone),
                        new ItemStack(SolItems.REINFORCED_PLATES, 1, 0),
                        new ItemStack(SolItems.ENGINE_BOOSTERS, 1, 0),
                        new ItemStack(SolItems.ROCKET_FINS, 1, 0),
                        new ItemStack(AsteroidsItems.basicItem, 1, 1),
                        new ItemStack(AsteroidsItems.tier3Rocket, 1, 0),
                        new ItemStack(SolItems.ROCKET_T4, 1, 0)
                );
            } else {
                int k = tier - 5;
                BaseRocketRecipeManager.registerRocketRecipes(
                        tier,
                        new ItemStack(SolItems.NOSE_CONES, 1, k),
                        new ItemStack(SolItems.REINFORCED_PLATES, 1, k + 1),
                        new ItemStack(SolItems.ENGINE_BOOSTERS, 1, k + 1),
                        new ItemStack(SolItems.ROCKET_FINS, 1, k + 1),
                        new ItemStack(SolItems.ROCKET_ENGINES, 1, k),
                        new ItemStack(tierRockets[k], 1, 0),
                        new ItemStack(tierRockets[k + 1], 1, 0)
                );
            }
        }
        // skyRegistry
        MinecraftForge.EVENT_BUS.register(new SolEventHandlerClient.TickHandlerClient());
        // Recipe
        SolRecipeCompressor.registryRecipe();
        SolRecipeSmelting.registryRecipe();
        // oreDict
        SolOreDict.registerOres();
        // chest
        TileEntity.register("sol:tier_4_treasure_chest", TileEntityTreasureChestTier4.class);
        TileEntity.register("sol:tier_5_treasure_chest", TileEntityTreasureChestTier5.class);
        TileEntity.register("sol:tier_6_treasure_chest", TileEntityTreasureChestTier6.class);
        TileEntity.register("sol:tier_7_treasure_chest", TileEntityTreasureChestTier7.class);
        TileEntity.register("sol:tier_8_treasure_chest", TileEntityTreasureChestTier8.class);
        TileEntity.register("sol:tier_9_treasure_chest", TileEntityTreasureChestTier9.class);
        TileEntity.register("sol:tier_10_treasure_chest", TileEntityTreasureChestTier10.class);
        for (int tier = 4; tier <= 10; tier++) {
            int meta = tier - 4;
            GalacticraftRegistry.addDungeonLoot(tier, new ItemStack(SolItems.SCHEMATICS, 1, meta));
        }
        // entity
        TheSol.registerEntityCreature(EntityMercuryBossBlaze.class, "mercury_blaze_boss", ColorUtil.to32BitColor(255, 63, 0, 0), ColorUtil.to32BitColor(255, 220, 0, 0));
        TheSol.registerEntityCreature(EntityJupiterBossGhast.class, "jupiter_ghast_boss", ColorUtil.to32BitColor(255, 127, 0, 0), ColorUtil.to32BitColor(255, 0, 0, 0));
        TheSol.registerEntityCreature(EntitySaturnBossStray.class, "saturn_stray_boss", ColorUtil.to32BitColor(255, 225, 255, 225), ColorUtil.to32BitColor(255, 0, 15, 255));
        TheSol.registerEntityCreature(EntityUranusBossSlime.class, "uranus_slime_boss", ColorUtil.to32BitColor(255, 5, 31, 127), ColorUtil.to32BitColor(255, 0, 0, 255));
        TheSol.registerEntityCreature(EntityNeptuneBossSpider.class, "neptune_spider_boss", ColorUtil.to32BitColor(255, 4, 4, 4), ColorUtil.to32BitColor(255, 127, 0, 16));
        TheSol.registerEntityCreature(EntityBossSilverfish.class, "silverfish_boss", ColorUtil.to32BitColor(255, 64, 64, 64), ColorUtil.to32BitColor(255, 127, 127, 127));
        TheSol.registerEntityCreature(EntityBossMagmaCube.class, "magmacube_boss", ColorUtil.to32BitColor(255, 127, 31, 31), ColorUtil.to32BitColor(255, 255, 127, 127));
        // dungeon
        TileEntity.register("sol:mercury_dungeon_spawner", TileEntityDungeonSpawnerMercury.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_MERCURY);
        TileEntity.register("sol:jupiter_dungeon_spawner", TileEntityDungeonSpawnerJupiter.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_JUPITER);
        TileEntity.register("sol:saturn_dungeon_spawner", TileEntityDungeonSpawnerSaturn.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_SATURN);
        TileEntity.register("sol:uranus_dungeon_spawner", TileEntityDungeonSpawnerUranus.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_URANUS);
        TileEntity.register("sol:neptune_dungeon_spawner", TileEntityDungeonSpawnerNeptune.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_NEPTUNE);
        TileEntity.register("sol:pluto_dungeon_spawner", TileEntityDungeonSpawnerPluto.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_PLUTO);
        TileEntity.register("sol:sedna_dungeon_spawner", TileEntityDungeonSpawnerSedna.class);
        GCBlocks.hiddenBlocks.add(SolBlocks.BOSS_SPAWNER_SEDNA);

        MinecraftForge.EVENT_BUS.register(new TheSol());
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void initClient(FMLInitializationEvent event) {
        for (int tier = 4; tier <= 10; tier++) {
            ItemSchematic.registerTextures(tier);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if (((event.getGui() instanceof GuiCelestialSelection))) {
            if (ConfigManager.misc.enableCustomGalaxymap) {
                if (event.getGui().getClass().getName().equalsIgnoreCase("asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection"))
                    System.err.println("Please disable Asmodeuscore's Galaxymap in configs/AsmodeusCore/core.conf");
                if (GameSettings.isKeyDown(micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.galaxyMap)) {
                    event.setGui(new SolCelestialSelection(true, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
                } else {
                    event.setGui(new SolCelestialSelection(false, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBakeEvent(ModelBakeEvent event) {
        for (int i = 4; i <= 9; i++) {
            RocketModelUtil.replaceModel(event, i);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void loadTextures(TextureStitchEvent.Pre event) {
        for (int i = 4; i <= 9; i++) {
            event.getMap().registerSprite(new ResourceLocation(Tags.MOD_ID, "rockets/tier" + i + "rocket"));
        }
    }
}