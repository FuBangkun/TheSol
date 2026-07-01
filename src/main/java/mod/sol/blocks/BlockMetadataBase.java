package mod.sol.blocks;

import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockMetadataBase extends Block implements IHasModel {

    protected final String[] subNames;
    protected final int maxMeta;

    public BlockMetadataBase(String registryName, Material material, String... subNames) {
        super(material);

        this.subNames = subNames;
        this.maxMeta = subNames.length - 1;

        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);
        this.setCreativeTab(TheSol.BLOCK_TAB);

        this.setDefaultState(this.blockState.getBaseState());

        SolBlocks.Blocks.add(this);
    }

    // =========================
    // metadata ↔ state
    // =========================

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getBlock().getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    // =========================
    // 创造模式子方块
    // =========================

    @Override
    public void getSubBlocks(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.is(tab)) {
            for (int meta = 0; meta <= maxMeta; meta++) {
                items.add(new ItemStack(this, 1, meta));
            }
        }
    }

    // =========================
    // BlockState（1.12.2 必须）
    // =========================

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);
    }

    // =========================
    // 模型注册
    // =========================

    @Override
    public void registerModels() {
        for (int meta = 0; meta <= maxMeta; meta++) {
            String modelName = this.getRegistryName().getPath() + "_" + getModelSuffix(meta);
            TheSol.proxy.registerItemRenderer(ItemStack.getItemFromBlock(this), meta, modelName);
        }
    }

    protected String getModelSuffix(int meta) {
        if (meta >= 0 && meta < subNames.length) {
            return subNames[meta];
        }
        return "default";
    }

    // =========================
    // tooltip / 命名扩展（可选）
    // =========================

    public String getSubName(int meta) {
        if (meta >= 0 && meta < subNames.length) {
            return subNames[meta];
        }
        return "default";
    }
}