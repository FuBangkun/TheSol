package mod.sol.render.entity;

import mod.sol.Tags;
import mod.sol.entities.boss.EntitySaturnBossStray;
import mod.sol.render.layer.LayerHeldItemSaturnBossSkeleton;
import mod.sol.render.model.ModelSaturnBossStray;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSaturnBossStray extends RenderBossBase<EntitySaturnBossStray> {
    private static final ResourceLocation TEX = new ResourceLocation(Tags.MOD_ID, "textures/entities/strayboss.png");

    public RenderSaturnBossStray(RenderManager manager) {
        super(manager, new ModelSaturnBossStray(), 0.9F, 1.2F, TEX);
        this.addLayer(new LayerHeldItemSaturnBossSkeleton(this));
    }

    @Override
    protected int getDeathTicks(EntitySaturnBossStray entity) {
        return entity.deathTicks;
    }
}