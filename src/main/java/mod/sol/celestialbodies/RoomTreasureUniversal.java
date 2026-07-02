package mod.sol.celestialbodies;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.tile.TileEntityTreasureChest;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.DungeonConfiguration;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.RoomTreasure;
import mod.sol.Tags;
import mod.sol.blocks.BlockTreasureChest;
import mod.sol.init.SolBlocks;
import mod.sol.util.EnumSolPlanet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class RoomTreasureUniversal extends RoomTreasure {
    public static final ResourceLocation TABLE_MERCURY = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_4"));
    public static final ResourceLocation TABLE_JUPITER = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_5"));
    public static final ResourceLocation TABLE_SATURN  = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_6"));
    public static final ResourceLocation TABLE_URANUS  = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_7"));
    public static final ResourceLocation TABLE_NEPTUNE = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_8"));
    public static final ResourceLocation TABLE_PLUTO   = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_9"));
    public static final ResourceLocation TABLE_SEDNA   = LootTableList.register(new ResourceLocation(Tags.MOD_ID, "dungeon_tier_10"));

    @SuppressWarnings("unused")
    public RoomTreasureUniversal() {
    }

    @SuppressWarnings("unused")
    public RoomTreasureUniversal(DungeonConfiguration configuration, Random rand, int blockPosX, int blockPosZ, EnumFacing entranceDir) {
        super(configuration, rand, blockPosX, blockPosZ, rand.nextInt(4) + 6, configuration.getRoomHeight(), rand.nextInt(4) + 6, entranceDir);
    }

    @SuppressWarnings("unused")
    public RoomTreasureUniversal(DungeonConfiguration configuration, Random rand, int blockPosX, int blockPosZ, int sizeX, int sizeY, int sizeZ, EnumFacing entranceDir) {
        super(configuration, rand, blockPosX, blockPosZ, sizeX, sizeY, sizeZ, entranceDir);
    }

    private EnumSolPlanet getPlanetType() {
        if (this.configuration == null) return EnumSolPlanet.JUPITER;
        Block brick = this.configuration.getBrickBlock().getBlock();

        if (brick == SolBlocks.JUPITER_DUNGEON_BRICK) return EnumSolPlanet.JUPITER;
        if (brick == SolBlocks.MERCURY_DUNGEON_BRICK) return EnumSolPlanet.MERCURY;
        if (brick == SolBlocks.NEPTUNE_DUNGEON_BRICK) return EnumSolPlanet.NEPTUNE;
        if (brick == SolBlocks.PLUTO_DUNGEON_BRICK)   return EnumSolPlanet.PLUTO;
        if (brick == SolBlocks.SATURN_DUNGEON_BRICK)  return EnumSolPlanet.SATURN;
        if (brick == SolBlocks.SEDNA_DUNGEON_BRICK)   return EnumSolPlanet.SEDNA;
        if (brick == SolBlocks.URANUS_DUNGEON_BRICK)  return EnumSolPlanet.URANUS;

        return EnumSolPlanet.JUPITER;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random random, StructureBoundingBox boundingBox) {
        EnumSolPlanet planetType = getPlanetType();

        for (int i = 0; i <= this.sizeX; i++) {
            for (int j = 0; j <= this.sizeY; j++) {
                for (int k = 0; k <= this.sizeZ; k++) {
                    if (i == 0 || i == this.sizeX || j == 0 || j == this.sizeY || k == 0 || k == this.sizeZ) {
                        boolean placeBlock = true;
                        if (getDirection().getAxis() == EnumFacing.Axis.Z) {
                            int start = (this.boundingBox.maxX - this.boundingBox.minX) / 2 - 1;
                            int end = (this.boundingBox.maxX - this.boundingBox.minX) / 2 + 1;
                            if (i > start && i <= end && j < 3 && j > 0) {
                                if (getDirection() == EnumFacing.SOUTH && k == 0) placeBlock = false;
                                else if (getDirection() == EnumFacing.NORTH && k == this.sizeZ) placeBlock = false;
                            }
                        } else {
                            int start = (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - 1;
                            int end = (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 + 1;
                            if (k > start && k <= end && j < 3 && j > 0) {
                                if (getDirection() == EnumFacing.EAST && i == 0) placeBlock = false;
                                else if (getDirection() == EnumFacing.WEST && i == this.sizeX) placeBlock = false;
                            }
                        }
                        if (placeBlock) {
                            this.setBlockState(worldIn, this.configuration.getBrickBlock(), i, j, k, boundingBox);
                        } else {
                            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                        }
                    } else if ((i == 1 && k == 1) || (i == 1 && k == this.sizeZ - 1) || (i == this.sizeX - 1 && k == 1) || (i == this.sizeX - 1 && k == this.sizeZ - 1)) {
                        this.setBlockState(worldIn, Blocks.GLOWSTONE.getDefaultState(), i, j, k, boundingBox);
                    } else if (i == this.sizeX / 2 && j == 1 && k == this.sizeZ / 2) {
                        BlockPos blockpos = new BlockPos(this.getXWithOffset(i, k), this.getYWithOffset(j), this.getZWithOffset(i, k));
                        if (boundingBox.isVecInside(blockpos)) {
                            IBlockState chestState = getChestBlockState(planetType);
                            worldIn.setBlockState(blockpos, chestState, 2);

                            TileEntityTreasureChest treasureChest = (TileEntityTreasureChest) worldIn.getTileEntity(blockpos);
                            if (treasureChest != null) {
                                ResourceLocation chesttype = getLootTable(planetType);
                                if (worldIn.provider instanceof IGalacticraftWorldProvider) {
                                    chesttype = ((IGalacticraftWorldProvider) worldIn.provider).getDungeonChestType();
                                }
                                treasureChest.setLootTable(chesttype, random.nextLong());
                            }
                        }
                    } else {
                        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                    }
                }
            }
        }
        return true;
    }

    private IBlockState getChestBlockState(EnumSolPlanet type) {
        EnumFacing face = this.getDirection().getOpposite();
        switch (type) {
            case MERCURY: return SolBlocks.TREASURE_CHEST_T4.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case JUPITER: return SolBlocks.TREASURE_CHEST_T5.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case SATURN:  return SolBlocks.TREASURE_CHEST_T6.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case URANUS:  return SolBlocks.TREASURE_CHEST_T7.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case NEPTUNE: return SolBlocks.TREASURE_CHEST_T8.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case PLUTO:   return SolBlocks.TREASURE_CHEST_T9.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            case SEDNA:   return SolBlocks.TREASURE_CHEST_T10.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
            default:      return SolBlocks.TREASURE_CHEST_T5.getDefaultState().withProperty(BlockTreasureChest.FACING, face);
        }
    }

    private ResourceLocation getLootTable(EnumSolPlanet type) {
        switch (type) {
            case MERCURY: return TABLE_MERCURY;
            case JUPITER: return TABLE_JUPITER;
            case SATURN:  return TABLE_SATURN;
            case URANUS:  return TABLE_URANUS;
            case NEPTUNE: return TABLE_NEPTUNE;
            case PLUTO:   return TABLE_PLUTO;
            case SEDNA:   return TABLE_SEDNA;
            default:      return TABLE_JUPITER;
        }
    }
}