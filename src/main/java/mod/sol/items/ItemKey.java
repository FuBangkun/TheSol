package mod.sol.items;

import micdoodle8.mods.galacticraft.api.item.IKeyItem;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.item.ItemStack;

public class ItemKey extends ItemMetadataBase implements IKeyItem, ISortableItem {
    public ItemKey() {
        super("key", "t4", "t5", "t6", "t7", "t8", "t9", "t10");
        this.setMaxStackSize(1);
    }

    @Override
    public int getTier(ItemStack keyStack) {
        return keyStack.getMetadata() + 4;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.KEYS;
    }

    @Override
    public String getModelSuffix(int meta) {
        return "t" + (meta + 4);
    }
}