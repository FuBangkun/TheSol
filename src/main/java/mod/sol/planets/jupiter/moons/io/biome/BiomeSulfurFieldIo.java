package mod.sol.planets.jupiter.moons.io.biome;

import mod.sol.init.SolBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeSulfurFieldIo extends BiomeIo {
    public BiomeSulfurFieldIo(BiomeProperties properties) {
        super(properties);
        this.topBlock = SolBlocks.IO_SULFUR_BLOCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome b) {
        BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
    }
}
