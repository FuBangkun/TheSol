package mod.sol.items;

import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }
}
