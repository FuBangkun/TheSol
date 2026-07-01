package mod.sol.blocks;

import mod.sol.TheSol;
import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class BlockBaseOre extends Block implements IHasModel {
    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 15);
    private final List<OreVariant> variants;

    public BlockBaseOre(String registryName, List<OreVariant> variants) {
        super(Material.ROCK);
        this.variants = variants;

        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);
        this.setCreativeTab(TheSol.BLOCK_TAB);
        this.setHardness(3.5F);

        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlockSubtypes(this).setRegistryName(registryName));
    }

    @Override
    public int getHarvestLevel(@Nonnull IBlockState state) {
        int meta = state.getValue(TYPE);
        if (meta < variants.size()) {
            return variants.get(meta).harvestLevel;
        }
        return 0;
    }

    @Nonnull
    @Override
    public String getHarvestTool(@Nonnull IBlockState state) {
        return "pickaxe";
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune) {
        int meta = state.getValue(TYPE);
        if (meta < variants.size()) {
            List<ItemStack> customDrops = variants.get(meta).customDrops;
            if (customDrops != null && !customDrops.isEmpty()) {
                for (ItemStack stack : customDrops) {
                    drops.add(stack.copy());
                }
                return;
            }
        }
        drops.add(new ItemStack(this, 1, meta));
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }

    @Override
    public void getSubBlocks(@Nonnull CreativeTabs itemIn, @Nonnull NonNullList<ItemStack> items) {
        if (this.getCreativeTab() == itemIn) {
            for (int i = 0; i < variants.size(); i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        ResourceLocation registryName = this.getRegistryName();

        if (registryName != null) {
            for (int i = 0; i < variants.size(); i++) {
                ModelResourceLocation itemMRL = new ModelResourceLocation(registryName, "type=" + i);
                ModelLoader.setCustomModelResourceLocation(item, i, itemMRL);
            }
        }
    }

    public static class OreVariant {
        public final String name;
        public final int harvestLevel;
        public List<ItemStack> customDrops = null;

        public OreVariant(String name, int harvestLevel) {
            this.name = name;
            this.harvestLevel = harvestLevel;
        }

        public OreVariant(String name, int harvestLevel, ItemStack drop) {
            this(name, harvestLevel);
            if (drop != null && !drop.isEmpty()) {
                this.customDrops = Collections.singletonList(drop);
            }
        }

        public OreVariant(String name, int harvestLevel, List<ItemStack> drops) {
            this(name, harvestLevel);
            this.customDrops = drops;
        }
    }

    private static class ItemBlockSubtypes extends ItemBlock {
        private final BlockBaseOre oreBlock;

        public ItemBlockSubtypes(BlockBaseOre block) {
            super(block);
            this.oreBlock = block;
            this.setMaxDamage(0);
            this.setHasSubtypes(true);
        }

        @Override
        public int getMetadata(int damage) {
            return damage;
        }

        @Nonnull
        @Override
        public String getTranslationKey(@Nonnull ItemStack stack) {
            int meta = stack.getMetadata();
            if (meta < oreBlock.variants.size()) {
                return super.getTranslationKey() + "_" + oreBlock.variants.get(meta).name;
            }
            return super.getTranslationKey();
        }
    }
}