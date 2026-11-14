package org.mgga.sol.render.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.mgga.sol.Reference;
import org.mgga.sol.entities.boss.EntityUranusBossSlime;
import org.mgga.sol.render.layer.LayerUranusBossSlimeGel;

@SideOnly(Side.CLIENT)
public class RenderUranusBossSlime extends RenderBossBase<EntityUranusBossSlime> {
    private static final ResourceLocation TEX = new ResourceLocation(Reference.MOD_ID, "textures/entities/slime_boss.png");

    public RenderUranusBossSlime(RenderManager manager) {
        super(manager, new ModelSlime(16), 3.0F, 10.0F, TEX);
        this.addLayer(new LayerUranusBossSlimeGel(this));
    }

    @Override
    protected int getDeathTicks(EntityUranusBossSlime entity) {
        return entity.deathTicks;
    }
}