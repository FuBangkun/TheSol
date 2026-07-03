package mod.sol.render.entity;

import mod.sol.Tags;
import mod.sol.entities.boss.EntityBossMagmaCube;
import mod.sol.render.model.ModelBossMagmaCube;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBossMagmaCube extends RenderBossBase<EntityBossMagmaCube> {
    private static final ResourceLocation TEX = new ResourceLocation(Tags.MOD_ID, "textures/entities/magmacube.png");

    public RenderBossMagmaCube(RenderManager manager) {
        super(manager, new ModelBossMagmaCube(), 0.25F, 10.0F, TEX);
    }

    @Override
    protected int getDeathTicks(EntityBossMagmaCube entity) {
        return entity.deathTicks;
    }
}