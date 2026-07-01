package mod.sol.blocks;

import mod.sol.init.SolBlocks;
import mod.sol.init.SolItems;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidBase extends BlockFluidClassic {

    public BlockFluidBase(String name, Fluid fluid, Material material) {
        super(fluid, material);
        this.setRegistryName(name);
        this.setTranslationKey(name);

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
