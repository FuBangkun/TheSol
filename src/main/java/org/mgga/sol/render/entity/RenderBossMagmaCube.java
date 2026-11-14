package org.mgga.sol.render.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.mgga.sol.Reference;
import org.mgga.sol.entities.boss.EntityBossMagmaCube;
import org.mgga.sol.render.model.ModelBossMagmaCube;

@SideOnly(Side.CLIENT)
public class RenderBossMagmaCube extends RenderBossBase<EntityBossMagmaCube> {
    private static final ResourceLocation TEX = new ResourceLocation(Reference.MOD_ID, "textures/entities/magmacube.png");

    public RenderBossMagmaCube(RenderManager manager) {
        super(manager, new ModelBossMagmaCube(), 0.25F, 10.0F, TEX);
    }

    @Override
    protected int getDeathTicks(EntityBossMagmaCube entity) {
        return entity.deathTicks;
    }
}