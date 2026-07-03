package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.item.ItemHoe;

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
