package mod.sol.render.entity;

import mod.sol.Tags;
import mod.sol.entities.boss.EntityNeptuneBossSpider;
import mod.sol.render.layer.LayerNeptuneBossSpiderEye;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderNeptuneBossSpider<T extends EntityNeptuneBossSpider> extends RenderBossBase<T> {
    private static final ResourceLocation TEX = new ResourceLocation(Tags.MOD_ID, "textures/entities/spider.png");

    public RenderNeptuneBossSpider(RenderManager manager) {
        super(manager, new ModelSpider(), 3.0F, 3.0F, TEX);
        this.addLayer(new LayerNeptuneBossSpiderEye<>(this));
    }

    @Override
    protected int getDeathTicks(T entity) {
        return entity.deathTicks;
    }

    @Override
    protected float getDeathMaxRotation(@Nonnull T entity) {
        return 180.0F;
    }
}