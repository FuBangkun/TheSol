package mod.sol.util;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import micdoodle8.mods.galacticraft.core.client.model.OBJLoaderGC;
import mod.sol.Tags;
import mod.sol.render.model.ItemModelRocket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.model.TRSRTransformation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RocketModelUtil {
    private static final List<String> BASE_GROUPS = ImmutableList.of("Boosters", "Cube", "NoseCone", "Rocket");
    // tier -> sprite function cache
    private static final Map<Integer, Function<ResourceLocation, TextureAtlasSprite>> SPRITE_CACHE = new HashMap<>();
    // cache RAW baked model ONLY
    private static final Map<String, IBakedModel> RAW_CACHE = new HashMap<>();
    private static OBJModel cachedModel;

    private static OBJModel getModel() {
        if (cachedModel != null) return cachedModel;

        try {
            cachedModel = (OBJModel) OBJLoaderGC.instance.loadModel(new ResourceLocation(Tags.MOD_ID, "rocket.obj"));
            return cachedModel;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load rocket.obj", e);
        }
    }

    // -----------------------
    // sprite cache
    // -----------------------
    public static Function<ResourceLocation, TextureAtlasSprite> spriteFunction(int tier) {
        return SPRITE_CACHE.computeIfAbsent(tier, t ->
                location -> Minecraft.getMinecraft()
                        .getTextureMapBlocks()
                        .getAtlasSprite(getTexture(location, t).toString())
        );
    }

    // -----------------------
    // texture rule
    // -----------------------
    public static ResourceLocation getTexture(ResourceLocation location, int tier) {
        return new ResourceLocation(
                location.getNamespace(),
                location.getPath().replace("tierrocket", "tier" + tier + "rocket")
        );
    }

    // -----------------------
    // RAW bake (cached)
    // -----------------------
    public static IBakedModel bakeRocket(int tier, List<String> groups) {
        String key = tier + ":" + groups.toString();

        return RAW_CACHE.computeIfAbsent(key, k -> {
            OBJModel model = getModel();

            return model.bake(
                    new OBJModel.OBJState(groups, false, TRSRTransformation.identity()),
                    DefaultVertexFormats.ITEM,
                    spriteFunction(tier)
            );
        });
    }

    // -----------------------
    // ModelBakeEvent hook
    // -----------------------
    public static void replaceModel(ModelBakeEvent event, int tier) {
        RAW_CACHE.clear();
        SPRITE_CACHE.clear();

        IBakedModel raw = bakeRocket(tier, BASE_GROUPS);
        IBakedModel wrapped = new ItemModelRocket(raw);
        ModelResourceLocation mrl = new ModelResourceLocation(Tags.MOD_ID + ":rocket_t" + tier, "inventory");
        event.getModelRegistry().putObject(mrl, wrapped);
    }
}