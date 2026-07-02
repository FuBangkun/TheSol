package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.item.ItemHoe;

public class ItemHoeBase extends ItemHoe implements ISortableItem, IHasModel {
    public ItemHoeBase(ToolMaterial material) {
        super(material);

        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);
        SolItems.ITEMS.add(this);
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.TOOLS;
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
