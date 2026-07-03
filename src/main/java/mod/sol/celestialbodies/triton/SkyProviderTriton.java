package mod.sol.celestialbodies.triton;

import micdoodle8.mods.galacticraft.core.Constants;
import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

public class SkyProviderTriton extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/neptune.png");

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
        return 2.5F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 52.0F;
    }
}
