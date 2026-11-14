package org.mgga.sol.celestialbodies.europa;

import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.util.ResourceLocation;
import org.mgga.sol.celestialbodies.SkyProviderSolBase;

public class SkyProviderEuropa extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/jupiter.png");

    @Override
    protected ResourceLocation getOverworldTexture() {
        return OVERWORLD_TEXTURE;
    }

    @Override
    protected float getSunSize() {
        return 8.0F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 40.0F;
    }
}
