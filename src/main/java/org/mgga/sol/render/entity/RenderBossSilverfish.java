package org.mgga.sol.render.entity;

import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.mgga.sol.Reference;
import org.mgga.sol.entities.boss.EntityBossSilverfish;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderBossSilverfish extends RenderBossBase<EntityBossSilverfish> {
    private static final ResourceLocation TEX = new ResourceLocation(Reference.MOD_ID, "textures/entities/silverfish.png");

    public RenderBossSilverfish(RenderManager manager) {
        super(manager, new ModelSilverfish(), 2.4F, 8.0F, TEX);
    }

    @Override
    protected int getDeathTicks(EntityBossSilverfish entity) {
        return entity.deathTicks;
    }

    @Override
    protected float getDeathMaxRotation(@Nonnull EntityBossSilverfish entity) {
        return 180.0F;
    }
}