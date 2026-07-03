package mod.sol.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public abstract class RenderBossBase<T extends EntityLiving> extends RenderLiving<T> {
    private final ResourceLocation texture;
    private final float scale;

    public RenderBossBase(RenderManager renderManager, ModelBase model, float shadowSize, float scale, ResourceLocation texture) {
        super(renderManager, model, shadowSize);
        this.scale = scale;
        this.texture = texture;
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T entity) {
        return this.texture;
    }

    @Override
    protected void preRenderCallback(@Nonnull T entity, float partialTicks) {
        GlStateManager.scale(this.scale, this.scale, this.scale);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int ticks = getDeathTicks(entity);
        double cur = Math.pow(ticks, 2) / 5.0;
        double prev = Math.pow(ticks - 1, 2) / 5.0;
        float rotation = (float) (cur + (cur - prev) * partialTicks);

        GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
    }

    protected abstract int getDeathTicks(T entity);
}