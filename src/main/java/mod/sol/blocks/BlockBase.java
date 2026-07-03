package mod.sol.blocks;

import mod.sol.init.SolBlocks;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {
    public BlockBase(String name, Material material, String toolClass, int harvestLevel) {
        super(material);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setHarvestLevel(toolClass, harvestLevel);
        this.setCreativeTab(SolCreativeTabs.BLOCK_TAB);

        SolBlocks.Blocks.add(this);
        SolItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }
}
