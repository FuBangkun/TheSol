package org.mgga.sol.celestialbodies.pluto;

import net.minecraft.util.ResourceLocation;
import org.mgga.sol.Reference;
import org.mgga.sol.celestialbodies.SkyProviderSolBase;

public class SkyProviderPluto extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/planets/charon.png");

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
