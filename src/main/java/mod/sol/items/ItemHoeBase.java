package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHoeBase extends ItemHoe implements ISortableItem, IHasModel {
    public ItemHoeBase(ToolMaterial par2EnumToolMaterial) {
        super(par2EnumToolMaterial);

        SolItems.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
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
