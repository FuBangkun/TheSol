package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import javax.annotation.Nonnull;

public abstract class ChunkProviderSolAdvanced extends ChunkProviderSolBase {
    protected final WorldType worldType;
    protected final Gradient noiseGenSmooth1;
    protected final double[] terrainCalcs = new double[825];
    protected final float[] parabolicField = new float[25];
    protected NoiseGeneratorOctaves noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6;
    protected NoiseGeneratorPerlin noiseGen4;
    protected Biome[] biomesForGeneration;
    protected double[] stoneNoise = new double[256];
    protected double[] oct1, oct2, oct3, oct4;

    public ChunkProviderSolAdvanced(World world, long seed) {
        super(world, (long) (seed * 2.23412422));
        this.worldType = world.getWorldInfo().getTerrainType();
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGenSmooth1 = new Gradient(this.rand.nextLong(), 4, 0.25F);

        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                this.parabolicField[i + 2 + (j + 2) * 5] = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
            }
        }
    }

    protected abstract IBlockState getFillBlock();

    protected abstract MapGenBaseMeta getCaveGenerator();

    protected abstract void generateOcean(ChunkPrimer primer);

    protected abstract void generateSurfaceFeatures(ChunkPrimer primer);

    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
        this.noiseGenSmooth1.setFrequency(0.015F);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.createLandPerBiome(chunkX * 4, chunkZ * 4);

        for (int i = 0; i < 4; ++i) {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2) {
                    double d0 = 0.125D;
                    double d1 = this.terrainCalcs[i1 + i2];
                    double d2 = this.terrainCalcs[j1 + i2];
                    double d3 = this.terrainCalcs[k1 + i2];
                    double d4 = this.terrainCalcs[l1 + i2];
                    double d5 = (this.terrainCalcs[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.terrainCalcs[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.terrainCalcs[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.terrainCalcs[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2) {
                                if ((lvt_45_1_ += d16) > this.noiseGenSmooth1.getNoise(chunkX * 16 + (i * 4 + k2), chunkZ * 16 + (l * 4 + l2)) * 20.0) {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, getFillBlock());
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    protected void createLandPerBiome(int x, int z) {
        this.oct4 = this.noiseGen6.generateNoiseOctaves(this.oct4, x, z, 5, 5, 2000.0, 2000.0, 0.5);
        this.oct1 = this.noiseGen3.generateNoiseOctaves(this.oct1, x, 0, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.oct2 = this.noiseGen1.generateNoiseOctaves(this.oct2, x, 0, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.oct3 = this.noiseGen2.generateNoiseOctaves(this.oct3, x, 0, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 5; ++l) {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biomegenbase = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -i1; j1 <= i1; ++j1) {
                    for (int k1 = -i1; k1 <= i1; ++k1) {
                        Biome biomegenbase1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = biomegenbase1.getBaseHeight();
                        float f6 = biomegenbase1.getHeightVariation();

                        if (this.worldType == WorldType.AMPLIFIED && f5 > 0.0F) {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.parabolicField[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.oct4[j] / 4000.0D;

                if (d7 < 0.0D) {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D) {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D) {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                } else {
                    if (d7 > 1.0D) {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = f3;
                double d9 = f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * 8.5 / 8.0D;
                double d0 = 8.5 + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1) {
                    double d1 = ((double) l1 - d0) * 12.0 * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }

                    double d3 = this.oct3[i] / 1024.0;
                    double d5 = d3 - d1;

                    if (l1 > 29) {
                        double d6 = (float) (l1 - 29) / 3.0F;
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.terrainCalcs[i] = d5;
                    ++i;
                }
            }
        }
    }

    @Nonnull
    @Override
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed((long) x * 646447291020L + (long) z * 143121534752L);
        ChunkPrimer primer = new ChunkPrimer();
        setBlocksInChunk(x, z, primer);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);

        // Replace blocks for biome
        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen4.getRegion(this.stoneNoise, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                biomesForGeneration[j + i * 16].genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j, stoneNoise[j + i * 16]);
            }
        }

        getCaveGenerator().generate(this.world, x, z, primer);
        generateSurfaceFeatures(primer);
        generateOcean(primer);

        Chunk chunk = new Chunk(this.world, primer, x, z);
        byte[] abyte = chunk.getBiomeArray();
        for (int i = 0; i < abyte.length; ++i) abyte[i] = (byte) Biome.getIdForBiome(biomesForGeneration[i]);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        int i = x * 16, j = z * 16;
        BlockPos pos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(pos.add(16, 0, 16));
        biome.decorate(this.world, this.rand, pos);
        WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
        BlockFalling.fallInstantly = false;
    }
}