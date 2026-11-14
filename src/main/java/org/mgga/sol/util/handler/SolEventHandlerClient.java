package org.mgga.sol.util.handler;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.mgga.sol.celestialbodies.SkyProviderSolBase;
import org.mgga.sol.celestialbodies.ariel.SkyProviderAriel;
import org.mgga.sol.celestialbodies.ariel.WorldProviderAriel;
import org.mgga.sol.celestialbodies.europa.SkyProviderEuropa;
import org.mgga.sol.celestialbodies.europa.WorldProviderEuropa;
import org.mgga.sol.celestialbodies.io.SkyProviderIo;
import org.mgga.sol.celestialbodies.io.WorldProviderIo;
import org.mgga.sol.celestialbodies.mercury.SkyProviderMercury;
import org.mgga.sol.celestialbodies.mercury.WorldProviderMercury;
import org.mgga.sol.celestialbodies.mimas.SkyProviderMimas;
import org.mgga.sol.celestialbodies.mimas.WorldProviderMimas;
import org.mgga.sol.celestialbodies.pluto.SkyProviderPluto;
import org.mgga.sol.celestialbodies.pluto.WorldProviderPluto;
import org.mgga.sol.celestialbodies.sedna.SkyProviderSedna;
import org.mgga.sol.celestialbodies.sedna.WorldProviderSedna;
import org.mgga.sol.celestialbodies.titan.SkyProviderTitan;
import org.mgga.sol.celestialbodies.titan.WorldProviderTitan;
import org.mgga.sol.celestialbodies.triton.SkyProviderTriton;
import org.mgga.sol.celestialbodies.triton.WorldProviderTriton;
import org.mgga.sol.init.SolPlanets;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class SolEventHandlerClient {
    public static class TickHandlerClient {
        private static final Map<Class<?>, Function<WorldProvider, IRenderHandler>> SKY_RENDERERS = new LinkedHashMap<>();

        static {
            SKY_RENDERERS.put(WorldProviderMercury.class, provider -> new SkyProviderMercury((IGalacticraftWorldProvider) provider));
            SKY_RENDERERS.put(WorldProviderIo.class, provider -> new SkyProviderIo());
            SKY_RENDERERS.put(WorldProviderEuropa.class, provider -> new SkyProviderEuropa());
            SKY_RENDERERS.put(WorldProviderMimas.class, provider -> new SkyProviderMimas());
            SKY_RENDERERS.put(WorldProviderTitan.class, provider -> new SkyProviderTitan((IGalacticraftWorldProvider) provider));
            SKY_RENDERERS.put(WorldProviderAriel.class, provider -> new SkyProviderAriel());
            SKY_RENDERERS.put(WorldProviderTriton.class, provider -> new SkyProviderTriton());
            SKY_RENDERERS.put(WorldProviderPluto.class, provider -> new SkyProviderPluto());
            SKY_RENDERERS.put(WorldProviderSedna.class, provider -> new SkyProviderSedna());
        }

        private WorldClient lastWorld;

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onClientTick(ClientTickEvent event) {
            if (event.phase != TickEvent.Phase.END) {
                return;
            }

            final WorldClient world = FMLClientHandler.instance().getClient().world;
            if (world == this.lastWorld) {
                return;
            }

            this.lastWorld = world;
            if (world != null) {
                this.configureSky(world.provider);
            }
        }

        @SideOnly(Side.CLIENT)
        private void configureSky(WorldProvider provider) {
            for (Map.Entry<Class<?>, Function<WorldProvider, IRenderHandler>> entry : SKY_RENDERERS.entrySet()) {
                if (!entry.getKey().isInstance(provider)) {
                    continue;
                }

                if (provider.getSkyRenderer() == null) {
                    provider.setSkyRenderer(entry.getValue().apply(provider));
                }

                if (provider.getCloudRenderer() == null) {
                    provider.setCloudRenderer(new CloudRenderer());
                }

                return;
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onWorldUnload(WorldEvent.Unload event) {
            final World world = event.getWorld();
            if (!world.isRemote) {
                return;
            }

            if (world == this.lastWorld) {
                this.lastWorld = null;
            }

            final IRenderHandler skyRenderer = world.provider.getSkyRenderer();
            if (skyRenderer instanceof SkyProviderSolBase) {
                ((SkyProviderSolBase) skyRenderer).dispose();
            } else if (skyRenderer instanceof SkyProviderMercury) {
                ((SkyProviderMercury) skyRenderer).dispose();
            } else if (skyRenderer instanceof SkyProviderTitan) {
                ((SkyProviderTitan) skyRenderer).dispose();
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {
            CelestialBody body = renderEvent.celestialBody;
            if (body.equals(SolPlanets.planetCeres) || body.equals(SolPlanets.planetPluto) || body.equals(SolPlanets.planetSedna) || body.equals(SolPlanets.planetHaumea) || body.equals(SolPlanets.planetMakemake) || body.equals(SolPlanets.planetEris)) {
                renderEvent.setCanceled(true);
            } else if (body.equals(GalacticraftCore.planetOverworld)) {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection) {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.0F, 0.7F, 0.0F, alpha / 2.0F);
                } else {
                    GL11.glColor4f(0.1F, 0.3F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 68.0F;
                float max = 82.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.0F, 0.7F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            } else if (body.equals(SolPlanets.planetKuiperBelt)) {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection) {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 2.0F);
                } else {
                    GL11.glColor4f(0.3F, 0.1F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 66.0F;
                float max = 84.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            } else if (body.equals(SolPlanets.planetOortCloud)) {
                float alpha = 1.0F;
                GuiScreen screen = FMLClientHandler.instance().getClient().currentScreen;
                if (screen instanceof GuiCelestialSelection) {
                    alpha = ((GuiCelestialSelection) screen).getAlpha(body);
                    GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 2.0F);
                } else {
                    GL11.glColor4f(0.3F, 0.1F, 0.1F, 1.0F);
                }
                renderEvent.setCanceled(true);
                GL11.glBegin(GL11.GL_LINE_LOOP);

                final float theta = Constants.twoPI / 90;
                final float cos = MathHelper.cos(theta);
                final float sin = MathHelper.sin(theta);

                float min = 62.0F;
                float max = 90.0F;

                float x = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y = 0;

                float temp;
                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glBegin(GL11.GL_LINE_LOOP);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                }

                GL11.glEnd();
                GL11.glColor4f(0.7F, 0.0F, 0.0F, alpha / 10.0F);
                GL11.glBegin(GL11.GL_QUADS);

                x = min * body.getRelativeDistanceFromCenter().unScaledDistance;
                y = 0;
                float x2 = max * body.getRelativeDistanceFromCenter().unScaledDistance;
                float y2 = 0;

                for (int i = 0; i < 90; i++) {
                    GL11.glVertex2f(x2, y2);
                    GL11.glVertex2f(x, y);

                    temp = x;
                    x = cos * x - sin * y;
                    y = sin * temp + cos * y;
                    temp = x2;
                    x2 = cos * x2 - sin * y2;
                    y2 = sin * temp + cos * y2;

                    GL11.glVertex2f(x, y);
                    GL11.glVertex2f(x2, y2);
                }

                GL11.glEnd();
            } else if (body.equals(SolPlanets.moonRingsOfSaturn)) {
                renderEvent.setCanceled(true);
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onBodyRender(CelestialBodyRenderEvent.Pre renderEvent) {
            CelestialBody body = renderEvent.celestialBody;
            if (body.equals(SolPlanets.planetKuiperBelt)) {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            } else if (body.equals(SolPlanets.planetOortCloud)) {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            } else if (body.equals(SolPlanets.moonRingsOfSaturn)) {
                GL11.glRotatef(ClientUtil.getClientTimeTotal() / 10.0F % 360, 0, 0, 1);
            }
        }

        @SubscribeEvent
        public void onRenderPlanetPost(CelestialBodyRenderEvent.Post event) {
            if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection) {
                if (event.celestialBody == SolPlanets.planetSaturn) {
                    FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxyCore.saturnRingTexture);
                    float size = ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
                    ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 32, 32);
                } else if (event.celestialBody == SolPlanets.planetUranus) {
                    FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxyCore.uranusRingTexture);
                    float size = ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).getWidthForCelestialBody(event.celestialBody) / 6.0F;
                    ((GuiCelestialSelection) FMLClientHandler.instance().getClient().currentScreen).drawTexturedModalRect(-1.75F * size, -7.0F * size, 3.5F * size, 14.0F * size, 0, 0, 7, 28, false, false, 32, 32);
                }
            }
        }
    }
}
