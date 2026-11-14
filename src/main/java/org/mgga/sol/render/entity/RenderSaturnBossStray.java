package org.mgga.sol.render.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.mgga.sol.Reference;
import org.mgga.sol.entities.boss.EntitySaturnBossStray;
import org.mgga.sol.render.layer.LayerHeldItemSaturnBossSkeleton;
import org.mgga.sol.render.model.ModelSaturnBossStray;

@SideOnly(Side.CLIENT)
public class RenderSaturnBossStray extends RenderBossBase<EntitySaturnBossStray> {
    private static final ResourceLocation TEX = new ResourceLocation(Reference.MOD_ID, "textures/entities/strayboss.png");

    public RenderSaturnBossStray(RenderManager manager) {
        super(manager, new ModelSaturnBossStray(), 0.9F, 1.2F, TEX);
        this.addLayer(new LayerHeldItemSaturnBossSkeleton(this));
    }

    @Override
    protected int getDeathTicks(EntitySaturnBossStray entity) {
        return entity.deathTicks;
    }
}