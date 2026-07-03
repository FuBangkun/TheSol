package mod.sol.celestialbodies.pluto;

import mod.sol.Tags;
import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

public class SkyProviderPluto extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Tags.MOD_ID, "textures/planets/charon.png");

    @Override
    protected ResourceLocation getOverworldTexture() {
        return OVERWORLD_TEXTURE;
    }

    @Override
    protected float getSunBlankSize() {
        return 3.0F;
    }

    @Override
    protected float getSunSize() {
        return 2.0F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 8.0F;
    }
}
