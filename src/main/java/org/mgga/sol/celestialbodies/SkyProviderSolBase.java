package org.mgga.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public abstract class SkyProviderSolBase extends IRenderHandler {

    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/planets/orbitalsun.png");

    protected final SkyDisplayLists skyLists;

    public SkyProviderSolBase() {
        this.skyLists = new SkyDisplayLists(6000, 20000, 150.0D, 100.0D);
    }

    protected abstract ResourceLocation getOverworldTexture();

    protected abstract float getSunSize();

    protected abstract float getOverworldSpriteSize();

    protected float getSunBlankSize() {
        return getSunSize();
    }

    protected boolean hasOverworldSprite() {
        return true;
    }

    protected ResourceLocation getSunTexture() {
        return SUN_TEXTURE;
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.disableRescaleNormal();
        GL11.glColor3f(1F, 1F, 1F);
        final Tessellator var23 = Tessellator.getInstance();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(0, 0, 0);
        GL11.glCallList(this.skyLists.skyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();

        float var10;
        float var11;
        float var12;

        float var20 = 0;

        if (world.provider instanceof WorldProviderSpace) {
            var20 = world.provider.getStarBrightness(partialTicks);
        }

        if (var20 > 0.0F) {
            GL11.glPushMatrix();
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-19.0F, 0, 1.0F, 0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var20);
            GL11.glCallList(this.skyLists.starList);
            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 5F);
        GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
        float blankSize = getSunBlankSize() / 3.5F;
        BufferBuilder worldRenderer = var23.getBuffer();
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldRenderer.pos(-blankSize, 99.9D, -blankSize).endVertex();
        worldRenderer.pos(blankSize, 99.9D, -blankSize).endVertex();
        worldRenderer.pos(blankSize, 99.9D, blankSize).endVertex();
        worldRenderer.pos(-blankSize, 99.9D, blankSize).endVertex();
        var23.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float sunSize = getSunSize();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(getSunTexture());
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(-sunSize, 100.0D, -sunSize).tex(0.0D, 0.0D).endVertex();
        worldRenderer.pos(sunSize, 100.0D, -sunSize).tex(1.0D, 0.0D).endVertex();
        worldRenderer.pos(sunSize, 100.0D, sunSize).tex(1.0D, 1.0D).endVertex();
        worldRenderer.pos(-sunSize, 100.0D, sunSize).tex(0.0D, 1.0D).endVertex();
        var23.draw();

        GL11.glPopMatrix();

        if (hasOverworldSprite()) {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_BLEND);

            float spriteSize = getOverworldSpriteSize();
            final float earthRotation = (float) (world.getSpawnPoint().getZ() - mc.player.posZ) * 0.01F;
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            GL11.glRotatef(earthRotation, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(200F, 1.0F, 0.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(getOverworldTexture());

            world.getMoonPhase();
            worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.pos(-spriteSize, -100.0D, spriteSize).tex(0D, 1.0D).endVertex();
            worldRenderer.pos(spriteSize, -100.0D, spriteSize).tex(1.0D, 1.0D).endVertex();
            worldRenderer.pos(spriteSize, -100.0D, -spriteSize).tex(1.0D, 0D).endVertex();
            worldRenderer.pos(-spriteSize, -100.0D, -spriteSize).tex(0D, 0D).endVertex();
            var23.draw();

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glPopMatrix();
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        final double var25 = mc.player.getPosition().getY() - world.getHorizon();

        if (var25 < 0.0D) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.skyLists.skyListDown);
            GL11.glPopMatrix();
            var10 = 1.0F;
            var11 = -((float) (var25 + 65.0D));
            var12 = -var10;
            worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            worldRenderer.pos(-var10, var11, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var11, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var11, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var11, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var11, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var11, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var11, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var11, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(-var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, var10).color(0, 0, 0, 1.0F).endVertex();
            worldRenderer.pos(var10, var12, -var10).color(0, 0, 0, 1.0F).endVertex();
            var23.draw();
        }

        GL11.glColor3f(70F / 256F, 70F / 256F, 70F / 256F);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (var25 - 16.0D)), 0.0F);
        GL11.glCallList(this.skyLists.skyListDown);
        GL11.glPopMatrix();
        GlStateManager.enableRescaleNormal();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void dispose() {
        this.skyLists.dispose();
    }
}
