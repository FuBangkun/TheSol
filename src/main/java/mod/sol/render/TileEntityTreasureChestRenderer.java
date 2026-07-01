package mod.sol.render;

import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChest;
import mod.sol.Tags;
import mod.sol.tile.TileEntityTreasureChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityTreasureChestRenderer extends TileEntitySpecialRenderer<TileEntityTreasureChest> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[11];

    static {
        for (int i = 4; i <= 10; i++) {
            TEXTURES[i] = new ResourceLocation(Tags.MOD_ID, "textures/model/treasure_t" + i + ".png");
        }
    }

    private final ModelTreasureChest chestModel = new ModelTreasureChest();

    @Override
    public void render(TileEntityTreasureChest chest, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        int meta = !chest.hasWorld() ? 0 : chest.getBlockMetadata();

        int tier = chest.getTier();
        ResourceLocation texture = (tier >= 4 && tier <= 10) ? TEXTURES[tier] : TEXTURES[4];
        this.bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

        short angle = 0;
        if (meta == 2) angle = 180;
        if (meta == 4) angle = 90;
        if (meta == 5) angle = -90;

        GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        float lidAngle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTicks;
        lidAngle = 1.0F - lidAngle;
        lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;

        this.chestModel.chestLid.rotateAngleX = -(lidAngle * (float) Math.PI / 4.0F);
        this.chestModel.renderAll(!chest.locked);

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}