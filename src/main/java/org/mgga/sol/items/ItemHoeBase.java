package org.mgga.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.item.ItemHoe;
import org.mgga.sol.init.SolCreativeTabs;
import org.mgga.sol.init.SolItems;

public class ItemHoeBase extends ItemHoe implements ISortableItem {
    public ItemHoeBase(ToolMaterial material) {
        super(material);

        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);
        SolItems.ITEMS.add(this);
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.TOOLS;
    }
}
