package org.mgga.sol.celestialbodies.sedna;

import net.minecraft.util.ResourceLocation;
import org.mgga.sol.celestialbodies.SkyProviderSolBase;

public class SkyProviderSedna extends SkyProviderSolBase {
    @Override
    protected ResourceLocation getOverworldTexture() {
        return null;
    }

    @Override
    protected boolean hasOverworldSprite() {
        return false;
    }

    @Override
    protected float getSunBlankSize() {
        return 3.0F;
    }

    @Override
    protected float getSunSize() {
        return 1.5F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 0;
    }
}
