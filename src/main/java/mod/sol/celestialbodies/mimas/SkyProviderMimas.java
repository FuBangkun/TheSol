package mod.sol.celestialbodies.mimas;

import micdoodle8.mods.galacticraft.core.Constants;
import mod.sol.celestialbodies.SkyProviderSolBase;
import net.minecraft.util.ResourceLocation;

public class SkyProviderMimas extends SkyProviderSolBase {
    private static final ResourceLocation OVERWORLD_TEXTURE = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn.png");

    @Override
    protected ResourceLocation getOverworldTexture() {
        return OVERWORLD_TEXTURE;
    }

    @Override
    protected float getSunSize() {
        return 6.0F;
    }

    @Override
    protected float getOverworldSpriteSize() {
        return 70.0F;
    }
}
