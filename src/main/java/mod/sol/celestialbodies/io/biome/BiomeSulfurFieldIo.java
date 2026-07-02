package mod.sol.celestialbodies.io.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import mod.sol.init.SolBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;

public class BiomeSulfurFieldIo extends BiomeGenBaseGC {
    public BiomeSulfurFieldIo(BiomeProperties properties) {
        super(properties, true);
        this.topBlock = SolBlocks.IO_SULFUR_BLOCK.getDefaultState();
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return getModdedBiomeDecorator(new BiomeDecoratorIo());
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
