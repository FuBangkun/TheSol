package mod.sol.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;

public class SolCreativeTabs {
    public static final CreativeTabs ITEM_TAB = new CreativeTabs("sol_items") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SolItems.ENGINE_BOOSTERS, 1, 0);
        }
    };

    public static final CreativeTabs BLOCK_TAB = new CreativeTabs("sol_blocks") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SolBlocks.MERCURY_DUNGEON_BRICK);
        }
    };
}