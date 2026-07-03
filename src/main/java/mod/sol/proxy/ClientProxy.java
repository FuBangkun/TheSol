package mod.sol.proxy;

import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import mod.sol.Tags;
import mod.sol.client.gui.GuiSelestialSelection;
import mod.sol.config.ConfigManager;
import mod.sol.entities.EntityHugeFireball;
import mod.sol.entities.EntityTierRocket;
import mod.sol.entities.boss.*;
import mod.sol.init.SolBiomes;
import mod.sol.items.ItemSchematic;
import mod.sol.render.RenderRocketBase;
import mod.sol.render.TileEntityTreasureChestRenderer;
import mod.sol.render.entity.*;
import mod.sol.tile.TileEntityTreasureChest;
import mod.sol.util.RocketModelUtil;
import mod.sol.util.handler.SolEventHandlerClient;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
        OBJLoaderGC.instance.addDomain(Tags.MOD_ID);

        RenderingRegistry.registerEntityRenderingHandler(EntityTierRocket.class, RenderRocketBase::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHugeFireball.class, manager -> new RenderFireball(manager, 2.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMercuryBossBlaze.class, RenderMercuryBossBlaze::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJupiterBossGhast.class, RenderJupiterBossGhast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySaturnBossStray.class, RenderSaturnBossStray::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityUranusBossSlime.class, RenderUranusBossSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNeptuneBossSpider.class, RenderNeptuneBossSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossSilverfish.class, RenderBossSilverfish::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBossMagmaCube.class, RenderBossMagmaCube::new);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new SolEventHandlerClient.TickHandlerClient());

        for (int tier = 4; tier <= 10; tier++) {
            ItemSchematic.registerTextures(tier);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreasureChest.class, new TileEntityTreasureChestRenderer());
    }

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiCelestialSelection) {
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
    public void onModelBakeEvent(ModelBakeEvent event) {
        for (int i = 4; i <= 9; i++) {
            RocketModelUtil.replaceModel(event, i);
        }
    }

    @SubscribeEvent
    public void loadTextures(TextureStitchEvent.Pre event) {
        for (int i = 4; i <= 9; i++) {
            event.getMap().registerSprite(new ResourceLocation(Tags.MOD_ID, "rockets/tier" + i + "rocket"));
        }
    }
}