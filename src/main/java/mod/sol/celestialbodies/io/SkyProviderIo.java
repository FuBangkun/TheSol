package mod.sol.celestialbodies.io;

import micdoodle8.mods.galacticraft.core.Constants;
import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

public class SkyProviderIo extends SkyProviderSolBase {
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
        return 50.0F;
    }
}
