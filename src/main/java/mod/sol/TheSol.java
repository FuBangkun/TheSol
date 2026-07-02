package mod.sol;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import mod.sol.client.gui.GuiSelestialSelection;
import mod.sol.config.ConfigManager;
import mod.sol.entities.EntityHugeFireball;
import mod.sol.entities.EntityTierRocket;
import mod.sol.entities.boss.*;
import mod.sol.init.*;
import mod.sol.items.ItemSchematic;
import mod.sol.proxy.CommonProxy;
import mod.sol.render.RenderRocketBase;
import mod.sol.render.TileEntityTreasureChestRenderer;
import mod.sol.render.entity.*;
import mod.sol.tile.*;
import mod.sol.util.RocketModelUtil;
import mod.sol.util.handler.SolEventHandlerClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
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
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, dependencies = "required-after:galacticraftcore")
public class TheSol {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

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
        SolDimensions.Sedna = WorldUtil.getDimensionTypeById(ConfigManager.dimensionIds.dimensionidSedna);
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public static void postInitClient(FMLPostInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChest.class, new TileEntityTreasureChestRenderer());
    }

    public static void registerNonMobEntity(Class<? extends Entity> var0, String var1, int trackingDistance, int updateFreq, boolean sendVel) {
        ResourceLocation registryName = new ResourceLocation(Tags.MOD_ID, var1);
        EntityRegistry.registerModEntity(registryName, var0, var1, micdoodle8.mods.galacticraft.core.util.GCCoreUtil.nextInternalID(), TheSol.instance, trackingDistance, updateFreq, sendVel);
    }

    public static void registerEntityCreature(Class<? extends Entity> clazz, String name, int back, int fore) {
        TheSol.registerNonMobEntity(clazz, name, 80, 3, true);
        int nextEggID = micdoodle8.mods.galacticraft.core.util.GCCoreUtil.getNextValidID();
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

        RenderingRegistry.registerEntityRenderingHandler(EntityHugeFireball.class, manager -> new RenderHugeFireball(manager, 1));

        RenderingRegistry.registerEntityRenderingHandler(EntityMercuryBossBlaze.class, RenderMercuryBossBlaze::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJupiterBossGhast.class, RenderJupiterBossGhast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySaturnBossStray.class, RenderSaturnBossStray::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUranusBossSlime.class, RenderUranusBossSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNeptuneBossSpider.class, RenderNeptuneBossSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossSilverfish.class, RenderBossSilverfish::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossMagmaCube.class, RenderBossMagmaCube::new);

        SolBiomes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        SolPlanets.registerCelestialBodies();
        SolRegistries.register();
        MinecraftForge.EVENT_BUS.register(new SolEventHandlerClient.TickHandlerClient());
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
                    event.setGui(new GuiSelestialSelection(true, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
                } else {
                    event.setGui(new GuiSelestialSelection(false, ((GuiCelestialSelection) event.getGui()).possibleBodies, ((GuiCelestialSelection) event.getGui()).canCreateStations));
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

    @SubscribeEvent
    public static void registerRocketPartRecipe(RegistryEvent.Register<IRecipe> event) {
        Item airVent = GCItems.oxygenVent;
        Item fuelCanisterPartial = GCItems.fuelCanister;
        Item gcCanister = GCItems.canister;

        Object[] tierMaterials = new Object[]{
                new OreIngredient("compressedTitanium"),
                new ItemStack(SolItems.COMPRESSED_SULFUR),
                new ItemStack(SolItems.COMPRESSED_MANGANESE),
                new ItemStack(SolItems.COMPRESSED_LITHIUM),
                new ItemStack(SolItems.COMPRESSED_MAGNESIUM),
                new ItemStack(SolItems.COMPRESSED_VANADIUM)
        };

        int[] woolDataList = new int[]{3, 3, 13, 10, 1, 9};

        for (int meta = 0; meta < 6; meta++) {
            int tier = meta + 4;

            ItemStack currentPlate = new ItemStack(SolItems.REINFORCED_PLATES, 1, meta);
            Object currentMaterial = tierMaterials[meta];

            if (airVent != null && fuelCanisterPartial != null) {
                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "engine_booster_t" + tier);
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ENGINE_BOOSTERS, 1, meta),
                        "ZYZ",
                        "ZWZ",
                        "XVX",
                        'V', airVent,
                        'W', new ItemStack(fuelCanisterPartial, 1, 1),
                        'X', currentPlate,
                        'Y', new ItemStack(Blocks.WOOL, 1, woolDataList[meta]),
                        'Z', currentMaterial
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);
            }

            if (tier >= 5 && gcCanister != null) {
                int targetMeta = meta - 1;

                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier);
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        " YV",
                        "XWX",
                        "XZX",
                        'V', Blocks.STONE_BUTTON,
                        'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate,
                        'Y', Items.FLINT_AND_STEEL,
                        'Z', airVent
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);

                ResourceLocation regNameAlt = new ResourceLocation(Tags.MOD_ID, "rocket_engine_t" + tier + "_alt");
                IRecipe recipeAlt = new ShapedOreRecipe(regNameAlt, new ItemStack(SolItems.ROCKET_ENGINES, 1, targetMeta),
                        "VY ",
                        "XWX",
                        "XZX",
                        'V', Blocks.STONE_BUTTON,
                        'W', new ItemStack(gcCanister, 1, 32767),
                        'X', currentPlate,
                        'Y', Items.FLINT_AND_STEEL,
                        'Z', airVent
                ).setRegistryName(regNameAlt);
                event.getRegistry().register(recipeAlt);
            }

            if (tier >= 5) {
                int targetMeta = meta - 1;

                ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "nose_cone_t" + tier);
                IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.NOSE_CONES, 1, targetMeta),
                        " Y ",
                        " X ",
                        "X X",
                        'X', currentPlate,
                        'Y', Blocks.REDSTONE_TORCH
                ).setRegistryName(regName);
                event.getRegistry().register(recipe);
            }

            ResourceLocation regName = new ResourceLocation(Tags.MOD_ID, "rocket_fins_t" + tier);
            IRecipe recipe = new ShapedOreRecipe(regName, new ItemStack(SolItems.ROCKET_FINS, 1, meta),
                    " Y ",
                    "XYX",
                    "X X",
                    'X', currentPlate,
                    'Y', currentMaterial
            ).setRegistryName(regName);
            event.getRegistry().register(recipe);
        }
    }
}
