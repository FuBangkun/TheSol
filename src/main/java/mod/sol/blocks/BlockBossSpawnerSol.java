package mod.sol.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockBossSpawner;
import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockBossSpawnerSol extends BlockBossSpawner implements IHasModel {
    private final Supplier<TileEntity> tileEntitySupplier;

    public BlockBossSpawnerSol(String name, Supplier<TileEntity> tileEntitySupplier) {
        super(name);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.tileEntitySupplier = tileEntitySupplier;

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return tileEntitySupplier.get();
    }

    @Nonnull
    @Override
    public Block setCreativeTab(@Nonnull CreativeTabs tab) {
        return null;
    }

    @Nonnull
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
    public boolean isPassable(@Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}