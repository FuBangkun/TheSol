package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeBase extends ItemPickaxe implements ISortableItem, IHasModel {
    public ItemPickaxeBase(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);

        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
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
