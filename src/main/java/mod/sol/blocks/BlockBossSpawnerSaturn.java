package mod.sol.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockBossSpawner;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.item.ItemBlock;
import mod.sol.tile.TileEntityDungeonSpawnerSaturn;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBossSpawnerSaturn extends BlockBossSpawner implements IHasModel {
    public BlockBossSpawnerSaturn(String assetName) {
        super(assetName);
        this.setTranslationKey(assetName);
        this.setRegistryName(assetName);

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDungeonSpawnerSaturn();
    }

    @Override
    public Block setCreativeTab(CreativeTabs tab) {
        return null;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
