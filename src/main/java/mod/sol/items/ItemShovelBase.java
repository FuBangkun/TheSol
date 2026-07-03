package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.item.ItemSpade;

public class ItemShovelBase extends ItemSpade implements ISortableItem {
    public ItemShovelBase(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);

        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.TOOLS;
    }
}
