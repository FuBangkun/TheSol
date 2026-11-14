package mod.sol.celestialbodies.titan;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import mod.sol.Reference;
import mod.sol.celestialbodies.SkyDisplayLists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SkyProviderTitan extends IRenderHandler {
    private static final ResourceLocation overworldTexture = new ResourceLocation(Reference.MOD_ID, "textures/sky/planets/saturn.png");
    private static final ResourceLocation sunTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/planets/orbitalsun.png");
    private static final float[] SUN_AURA_COLOR = {1.0F, 194 / 255.0F, 180 / 255.0F, 0.3F};
    private final float sunSize;
    private final SkyDisplayLists skyLists;

    public SkyProviderTitan(IGalacticraftWorldProvider worldProvider) {
        this.sunSize = 120.0F * worldProvider.getSolarSize();
        this.skyLists = new SkyDisplayLists(6000, 35000, 130.0D, 150.0D);
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.disableRescaleNormal();
        Vec3d vec3 = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float f1 = (float) vec3.x;
        float f2 = (float) vec3.y;
        float f3 = (float) vec3.z;
        float f6;

        if (mc.gameSettings.anaglyph) {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        GL11.glColor3f(f1, f2, f3);
        Tessellator tessellator1 = Tessellator.getInstance();
        BufferBuilder worldRenderer1 = tessellator1.getBuffer();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(f1, f2, f3);
        GL11.glCallList(this.skyLists.skyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        float f7;
        float f8;
        float f9;
        float f10;

        float f18 = world.getStarBrightness(partialTicks);

        if (f18 > 0.0F) {
            GL11.glPushMatrix();
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-19.0F, 0, 1.0F, 0);
            GL11.glColor4f(f18, f18, f18, f18);
            GL11.glCallList(this.skyLists.starList);
            GL11.glPopMatrix();
        }

        float[] afloat = SUN_AURA_COLOR;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        f6 = afloat[0];
        f7 = afloat[1];
        f8 = afloat[2];
        float f11;

        if (mc.gameSettings.anaglyph) {
            f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
            f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
            f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
            f6 = f9;
            f7 = f10;
            f8 = f11;
        }

        f18 = 1.0F - f18;

        worldRenderer1.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        float r = f6 * f18;
        float g = f7 * f18;
        float b = f8 * f18;
        float a = afloat[3] * 2 / f18;
        worldRenderer1.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();
        r = afloat[0] * f18;
        g = afloat[1] * f18;
        b = afloat[2] * f18 / 20.0F;
        a = 0.0F;

        // Render sun aura
        f10 = 20.0F;
        worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
        worldRenderer1.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
        worldRenderer1.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
        worldRenderer1.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
        worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

        tessellator1.draw();
        worldRenderer1.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
        r = f6 * f18;
        g = f7 * f18;
        b = f8 * f18;
        a = afloat[3] * f18;
        worldRenderer1.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();
        r = afloat[0] * f18;
        g = afloat[1] * f18;
        b = afloat[2] * f18;
        a = 0.0F;

        // Render larger sun aura
        f10 = 80.0F;
        worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
        worldRenderer1.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
        worldRenderer1.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
        worldRenderer1.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
        worldRenderer1.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
        worldRenderer1.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
        GL11.glPushMatrix();
        f7 = 0.0F;
        f8 = 0.0F;
        f9 = 0.0F;
        GL11.glTranslatef(f7, f8, f9);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        // Render sun
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
        //Some blanking to conceal the stars
        f10 = this.sunSize / 3.5F;
        worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldRenderer1.pos(-f10, 99.9D, -f10).endVertex();
        worldRenderer1.pos(f10, 99.9D, -f10).endVertex();
        worldRenderer1.pos(f10, 99.9D, f10).endVertex();
        worldRenderer1.pos(-f10, 99.9D, f10).endVertex();
        tessellator1.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.1F);
        f10 = this.sunSize;
        mc.renderEngine.bindTexture(SkyProviderTitan.sunTexture);
        worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer1.pos(-f10, 100.0D, -f10).tex(0.0D, 0.0D).endVertex();
        worldRenderer1.pos(f10, 100.0D, -f10).tex(1.0D, 0.0D).endVertex();
        worldRenderer1.pos(f10, 100.0D, f10).tex(1.0D, 1.0D).endVertex();
        worldRenderer1.pos(-f10, 100.0D, f10).tex(0.0D, 1.0D).endVertex();
        tessellator1.draw();

        // Render earth
        f10 = 50.0F;
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(200F, 1.0F, 0.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(SkyProviderTitan.overworldTexture);
        worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer1.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
        worldRenderer1.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
        worldRenderer1.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
        worldRenderer1.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
        tessellator1.draw();

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        double d0 = mc.player.getPosition().getY() - world.getHorizon();

        if (d0 < 0.0D) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.skyLists.skyListDown);
            GL11.glPopMatrix();
            f8 = 1.0F;
            f9 = -((float) (d0 + 65.0D));
            f10 = -f8;
            worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer1.pos(-f8, f9, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f9, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f9, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f9, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f9, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f9, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f9, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f9, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(-f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, f8).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer1.pos(f8, f10, -f8).color(0, 0, 0, 1.0F).endVertex();
            tessellator1.draw();
        }

        if (world.provider.isSkyColored()) {
            GL11.glColor3f(f1 * 0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
        } else {
            GL11.glColor3f(f1, f2, f3);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (d0 - 16.0D)), 0.0F);
        GL11.glCallList(this.skyLists.skyListDown);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GlStateManager.enableRescaleNormal();
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GL11.glDepthMask(true);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void dispose() {
        this.skyLists.dispose();
    }
}
