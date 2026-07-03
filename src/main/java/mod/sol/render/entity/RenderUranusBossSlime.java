package mod.sol.render.entity;

import mod.sol.Tags;
import mod.sol.entities.boss.EntityUranusBossSlime;
import mod.sol.render.layer.LayerUranusBossSlimeGel;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUranusBossSlime extends RenderBossBase<EntityUranusBossSlime> {
    private static final ResourceLocation TEX = new ResourceLocation(Tags.MOD_ID, "textures/entities/slime_boss.png");

    public RenderUranusBossSlime(RenderManager manager) {
        super(manager, new ModelSlime(16), 3.0F, 10.0F, TEX);
        this.addLayer(new LayerUranusBossSlimeGel(this));
    }

    @Override
    protected int getDeathTicks(EntityUranusBossSlime entity) {
        return entity.deathTicks;
    }
}