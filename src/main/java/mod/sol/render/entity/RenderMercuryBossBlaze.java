package mod.sol.render.entity;

import mod.sol.Tags;
import mod.sol.entities.boss.EntityMercuryBossBlaze;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMercuryBossBlaze extends RenderBossBase<EntityMercuryBossBlaze> {
    private static final ResourceLocation TEX = new ResourceLocation(Tags.MOD_ID, "textures/entities/mercury_blaze_boss.png");

    public RenderMercuryBossBlaze(RenderManager manager) {
        super(manager, new ModelBlaze(), 2.0F, 4.0F, TEX);
    }

    @Override
    protected int getDeathTicks(EntityMercuryBossBlaze entity) {
        return entity.deathTicks;
    }
}