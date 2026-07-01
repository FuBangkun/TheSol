package mod.sol.blocks;

import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.item.ItemBlock;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBaseDiamondOre extends Block implements IHasModel {
    public BlockBaseDiamondOre(String name, Material material, String toolClass, int harvestLevel, CreativeTabs tab) {
        super(material);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setHarvestLevel(toolClass, harvestLevel);
        this.setCreativeTab(tab);

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(Items.DIAMOND, 3, 0));
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
