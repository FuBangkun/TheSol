package mod.sol.celestialbodies.ariel;

import micdoodle8.mods.galacticraft.core.Constants;
import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

public class SkyProviderAriel extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus.png");

    @Override
    protected ResourceLocation getOverworldTexture() {
        return OVERWORLD_TEXTURE;
    }

    @Override
    protected float getSunSize() {
        return 3.0F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 65.0F;
    }
}
