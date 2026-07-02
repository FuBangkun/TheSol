package mod.sol.celestialbodies;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerUniversal extends GenLayer {
    public GenLayerUniversal(long seed) {
        super(seed);
    }

    public static GenLayer[] createWorld(long seed, Biome[] biomes) {
        GenLayer biomeLayer = new GenLayerBiomesUniversal(seed, biomes);

        // Apply zoom layers
        biomeLayer = new GenLayerZoom(1000L, biomeLayer);
        biomeLayer = new GenLayerZoom(1001L, biomeLayer);
        biomeLayer = new GenLayerZoom(1002L, biomeLayer);
        biomeLayer = new GenLayerZoom(1003L, biomeLayer);

        GenLayer voronoiZoomLayer = new GenLayerVoronoiZoom(10L, biomeLayer);

        biomeLayer.initWorldGenSeed(seed);
        voronoiZoomLayer.initWorldGenSeed(seed);

        return new GenLayer[]{biomeLayer, voronoiZoomLayer};
    }
}