package org.mgga.sol.celestialbodies;

import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Random;

/**
 * Star field and sky dome display lists shared by the mod's sky renderers.
 *
 * <p>An instance owns three consecutive display list ids and must be disposed when the
 * world it belongs to is unloaded, otherwise the driver resources are leaked every time
 * a planet dimension is entered.
 */
@SideOnly(Side.CLIENT)
public final class SkyDisplayLists {
    public final int starList;
    public final int skyList;
    public final int skyListDown;

    private final int firstList;
    private boolean disposed;

    public SkyDisplayLists(int starCount, int moreStarsCount, double moreStarsDistanceBase, double moreStarsDistanceRange) {
        this.firstList = GLAllocation.generateDisplayLists(3);
        this.starList = this.firstList;
        this.skyList = this.firstList + 1;
        this.skyListDown = this.firstList + 2;

        GL11.glPushMatrix();
        GL11.glNewList(this.starList, GL11.GL_COMPILE);
        renderStars(starCount, moreStarsCount, moreStarsDistanceBase, moreStarsDistanceRange);
        GL11.glEndList();
        GL11.glPopMatrix();

        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        final byte cellSize = 64;
        final int cellCount = 256 / cellSize + 2;

        GL11.glNewList(this.skyList, GL11.GL_COMPILE);
        float y = 16F;
        for (int x = -cellSize * cellCount; x <= cellSize * cellCount; x += cellSize) {
            for (int z = -cellSize * cellCount; z <= cellSize * cellCount; z += cellSize) {
                buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
                buffer.pos(x, y, z).endVertex();
                buffer.pos(x + cellSize, y, z).endVertex();
                buffer.pos(x + cellSize, y, z + cellSize).endVertex();
                buffer.pos(x, y, z + cellSize).endVertex();
                tessellator.draw();
            }
        }
        GL11.glEndList();

        GL11.glNewList(this.skyListDown, GL11.GL_COMPILE);
        y = -16F;
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        for (int x = -cellSize * cellCount; x <= cellSize * cellCount; x += cellSize) {
            for (int z = -cellSize * cellCount; z <= cellSize * cellCount; z += cellSize) {
                buffer.pos(x + cellSize, y, z).endVertex();
                buffer.pos(x, y, z).endVertex();
                buffer.pos(x, y, z + cellSize).endVertex();
                buffer.pos(x + cellSize, y, z + cellSize).endVertex();
            }
        }
        tessellator.draw();
        GL11.glEndList();
    }

    private static void renderStars(int starCount, int moreStarsCount, double moreStarsDistanceBase, double moreStarsDistanceRange) {
        final Random rand = new Random(10842L);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

        final int count = ConfigManagerCore.moreStars ? moreStarsCount : starCount;

        for (int i = 0; i < count; ++i) {
            double x = rand.nextFloat() * 2.0F - 1.0F;
            double y = rand.nextFloat() * 2.0F - 1.0F;
            double z = rand.nextFloat() * 2.0F - 1.0F;
            final double size = 0.15F + rand.nextFloat() * 0.1F;
            double length = x * x + y * y + z * z;

            if (length >= 1.0D || length <= 0.01D) {
                continue;
            }

            length = 1.0D / Math.sqrt(length);
            x *= length;
            y *= length;
            z *= length;

            final double px = x * (ConfigManagerCore.moreStars ? rand.nextDouble() * moreStarsDistanceRange + moreStarsDistanceBase : 100.0D);
            final double py = y * (ConfigManagerCore.moreStars ? rand.nextDouble() * moreStarsDistanceRange + moreStarsDistanceBase : 100.0D);
            final double pz = z * (ConfigManagerCore.moreStars ? rand.nextDouble() * moreStarsDistanceRange + moreStarsDistanceBase : 100.0D);
            final double azimuth = Math.atan2(x, z);
            final double sinAzimuth = Math.sin(azimuth);
            final double cosAzimuth = Math.cos(azimuth);
            final double elevation = Math.atan2(Math.sqrt(x * x + z * z), y);
            final double sinElevation = Math.sin(elevation);
            final double cosElevation = Math.cos(elevation);
            final double rotation = rand.nextDouble() * Math.PI * 2.0D;
            final double sinRotation = Math.sin(rotation);
            final double cosRotation = Math.cos(rotation);

            for (int vertex = 0; vertex < 4; ++vertex) {
                final double offsetA = ((vertex & 2) - 1) * size;
                final double offsetB = ((vertex + 1 & 2) - 1) * size;
                final double rotatedA = offsetA * cosRotation - offsetB * sinRotation;
                final double rotatedB = offsetB * cosRotation + offsetA * sinRotation;
                final double tiltedA = rotatedA * sinElevation;
                final double tiltedB = -rotatedA * cosElevation;
                final double finalX = tiltedB * sinAzimuth - rotatedB * cosAzimuth;
                final double finalZ = rotatedB * sinAzimuth + tiltedB * cosAzimuth;
                buffer.pos(px + finalX, py + tiltedA, pz + finalZ).endVertex();
            }
        }

        tessellator.draw();
    }

    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        GLAllocation.deleteDisplayLists(this.firstList, 3);
    }
}
