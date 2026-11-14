package org.mgga.sol.render.entity;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.mgga.sol.Reference;
import org.mgga.sol.entities.boss.EntityJupiterBossGhast;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderJupiterBossGhast extends RenderBossBase<EntityJupiterBossGhast> {
    private static final ResourceLocation TEX_NORMAL = new ResourceLocation(Reference.MOD_ID, "textures/entities/ghast/boss_ghast.png");
    private static final ResourceLocation TEX_SHOOT = new ResourceLocation(Reference.MOD_ID, "textures/entities/ghast/boss_ghast_shooting.png");

    public RenderJupiterBossGhast(RenderManager manager) {
        super(manager, new ModelGhast(), 1.0F, 9.0F, TEX_NORMAL);
    }

    @Override
    protected int getDeathTicks(EntityJupiterBossGhast entity) {
        return entity.deathTicks;
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityJupiterBossGhast entity) {
        return entity.isAttacking() ? TEX_SHOOT : TEX_NORMAL;
    }
}