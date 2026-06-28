package mod.sol.render;

import com.google.common.collect.ImmutableList;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import mod.sol.entities.EntityRocketBase;
import mod.sol.util.RocketModelUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderRocketBase<T extends EntityRocketBase> extends Render<T> {

    public RenderRocketBase(RenderManager manager) {
        super(manager);
        this.shadowSize = 2F;
    }

    private static IBakedModel getRocketModel(int tier) {
        return RocketModelUtil.bakeRocket(tier, ImmutableList.of("Boosters", "Rocket"));
    }

    private static IBakedModel getConeModel(int tier) {
        return RocketModelUtil.bakeRocket(tier, ImmutableList.of("NoseCone"));
    }

    private static IBakedModel getCubeModel(int tier) {
        return RocketModelUtil.bakeRocket(tier, ImmutableList.of("Cube"));
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        int tier = entity.getRocketTier();
        IBakedModel rocketModel = getRocketModel(tier);
        IBakedModel coneModel = getConeModel(tier);
        IBakedModel cubeModel = getCubeModel(tier);

        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks + 180;

        GlStateManager.disableRescaleNormal();
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x, (float) y, (float) z);

        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-pitch, 0.0F, 0.0F, 1.0F);

        GlStateManager.translate(0.0F, entity.getRenderOffsetY(), 0.0F);

        float rollAmplitude = entity.rollAmplitude / 3 - partialTicks;

        if (rollAmplitude > 0.0F) {
            float i = entity.getLaunched() ? (5 - MathHelper.floor((float) entity.timeUntilLaunch / 85)) / 10F : 0.3F;

            float rot = MathHelper.sin(rollAmplitude) * rollAmplitude * i * partialTicks;

            GlStateManager.rotate(rot, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(rot, 1.0F, 0.0F, 1.0F);
        }

        bindEntityTexture(entity);

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.scale(0.8F, 0.8F, 0.8F);

        // ---------------- main ----------------
        ClientUtil.drawBakedModel(rocketModel);

        // ---------------- cone ----------------
        Vector3 teamColor = ClientUtil.updateTeamColor(
                PlayerUtil.getName(FMLClientHandler.instance().getClient().player),
                true
        );

        if (teamColor != null) {
            int color = ColorUtil.to32BitColor(
                    255,
                    (int) (teamColor.floatZ() * 255),
                    (int) (teamColor.floatY() * 255),
                    (int) (teamColor.floatX() * 255)
            );

            GlStateManager.disableTexture2D();
            ClientUtil.drawBakedModelColored(coneModel, color);
        } else {
            ClientUtil.drawBakedModel(coneModel);
        }

        // ---------------- cube ----------------
        GlStateManager.disableLighting();

        boolean red = ((entity.ticksExisted >> 3) & 1) == 0;

        int color = ColorUtil.to32BitColor(
                255,
                0,
                red ? 0 : 255,
                red ? 255 : 0
        );

        ClientUtil.drawBakedModelColored(cubeModel, color);

        // ---------------- restore ----------------
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.color(1F, 1F, 1F);

        GlStateManager.popMatrix();

        RenderHelper.enableStandardItemLighting();
    }

    @Override
    public boolean shouldRender(T rocket, @Nonnull ICamera camera, double camX, double camY, double camZ) {
        AxisAlignedBB box = rocket.getEntityBoundingBox().grow(0.5D, 0, 0.5D);

        return rocket.isInRangeToRender3d(camX, camY, camZ) && camera.isBoundingBoxInFrustum(box);
    }
}