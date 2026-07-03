package mod.sol.celestialbodies.sedna;

import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

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
