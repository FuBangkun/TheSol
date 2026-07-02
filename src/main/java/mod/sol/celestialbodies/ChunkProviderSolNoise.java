package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.EnumCraterSize;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import javax.annotation.Nonnull;
import java.util.Random;

public abstract class ChunkProviderSolNoise extends ChunkProviderSolBase {
    protected static final int MID_HEIGHT = 63;
    protected static final int CHUNK_SIZE_X = 16;
    protected static final int CHUNK_SIZE_Y = 128;
    protected static final int CHUNK_SIZE_Z = 16;
    protected static final int CRATER_PROB = 300;

    protected final NoiseModule noiseGen1, noiseGen2, noiseGen3, noiseGen4;
    protected final MapGenDungeon dungeonGenerator;
    protected final MapGenBaseMeta caveGenerator;
    protected Biome[] biomesForGeneration = {BiomeAdaptive.biomeDefault};

    public ChunkProviderSolNoise(World world, long seed) {
        super(world, seed);
        float scale = getNoiseScale();
        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, scale);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, scale);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, scale);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, scale);
        this.dungeonGenerator = createDungeonGenerator();
        this.caveGenerator = createCaveGenerator();
    }

    protected abstract float getNoiseScale();
    protected abstract MapGenDungeon createDungeonGenerator();
    protected abstract MapGenBaseMeta createCaveGenerator();

    // 获取各层方块，子类可根据 y 坐标或生物群系返回不同方块
    protected abstract IBlockState getTopBlock(int x, int y, int z, Biome biome);
    protected abstract IBlockState getFillBlock(int x, int y, int z, Biome biome);
    protected abstract IBlockState getLowerBlock();

    protected double getTerrainHeightModifier() { return 16.0; }

    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.010F);
        this.noiseGen3.setFrequency(0.0075F);

        for (int x = 0; x < CHUNK_SIZE_X; x++) {
            for (int z = 0; z < CHUNK_SIZE_Z; z++) {
                final double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * getTerrainHeightModifier();
                final double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * (getTerrainHeightModifier() * 3);
                double d3 = (this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1) * 4;

                double yDev = d3 < 0 ? d : (d3 > 1 ? d2 : d + (d2 - d) * d3);

                for (int y = 0; y < CHUNK_SIZE_Y; y++) {
                    if (y < MID_HEIGHT + yDev) {
                        primer.setBlockState(x, y, z, getLowerBlock());
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomes) {
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                Biome biome = (biomes == null) ? BiomeAdaptive.biomeDefault : biomes[x + z * 16];
                int var12 = (int) (this.noiseGen4.getNoise(x + chunkX * 16, z + chunkZ * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;

                for (int y = 127; y >= 0; --y) {
                    if (y <= this.rand.nextInt(5)) {
                        primer.setBlockState(x, y, z, Blocks.BEDROCK.getDefaultState());
                    } else {
                        IBlockState current = primer.getBlockState(x, y, z);
                        if (current.getBlock() == Blocks.AIR) {
                            var13 = -1;
                        } else if (current == getLowerBlock()) {
                            if (var13 == -1) {
                                var13 = var12;
                                if (y >= 19) {
                                    primer.setBlockState(x, y, z, getTopBlock(x, y, z, biome));
                                } else {
                                    primer.setBlockState(x, y, z, getFillBlock(x, y, z, biome));
                                }
                            } else if (var13 > 0) {
                                --var13;
                                primer.setBlockState(x, y, z, getFillBlock(x, y, z, biome));
                            }
                        }
                    }
                }
            }
        }
    }

    @Nonnull
    @Override
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);

        setBlocksInChunk(x, z, chunkprimer);
        createCraters(x, z, chunkprimer);
        replaceBlocksForBiome(x, z, chunkprimer, biomesForGeneration);

        this.caveGenerator.generate(this.world, x, z, chunkprimer);
        this.dungeonGenerator.generate(this.world, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();
        for (int i = 0; i < abyte.length; ++i) abyte[i] = (byte) Biome.getIdForBiome(biomesForGeneration[i]);
        chunk.generateSkylightMap();
        return chunk;
    }

    protected void createCraters(int chunkX, int chunkZ, ChunkPrimer primer) {
        for (int cx = chunkX - 2; cx <= chunkX + 2; cx++) {
            for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        if (Math.abs(randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * 16 + x, cz * 16 + z) / CRATER_PROB) {
                            Random random = new Random(cx * 16L + x + (cz * 16L + z) * 5000);
                            EnumCraterSize cSize = EnumCraterSize.sizeArray[random.nextInt(EnumCraterSize.sizeArray.length)];
                            int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
                            makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, primer);
                        }
                    }
                }
            }
        }
    }

    protected void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, ChunkPrimer primer) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double xDev = (craterX - (chunkX + x)) / (double)size;
                double zDev = (craterZ - (chunkZ + z)) / (double)size;
                double distSq = xDev * xDev + zDev * zDev;
                if (distSq < 1.0) {
                    double yDev = (5 - (distSq * distSq * 6));
                    int helper = 0;
                    for (int y = 127; y > 0; y--) {
                        if (primer.getBlockState(x, y, z).getBlock() != Blocks.AIR && helper <= yDev) {
                            primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
                            helper++;
                        }
                    }
                }
            }
        }
    }

    private double randFromPoint(int x, int z) {
        int n = x + z * 57;
        n = n << 13 ^ n;
        return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
    }

    @Override
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        BlockPos pos = new BlockPos(x * 16, 0, z * 16);
        this.world.getBiome(pos.add(16, 0, 16)).decorate(this.world, this.rand, pos);
        this.dungeonGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));
        onPopulateExtra(x, z, pos);
        BlockFalling.fallInstantly = false;
    }

    protected void onPopulateExtra(int x, int z, BlockPos pos) {}

    @Override
    public void recreateStructures(@Nonnull Chunk chunk, int x, int z) {
        this.dungeonGenerator.generate(this.world, x, z, null);
    }
}