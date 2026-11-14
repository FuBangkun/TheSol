package org.mgga.sol.items;

import net.minecraft.item.Item;
import org.mgga.sol.init.SolCreativeTabs;
import org.mgga.sol.init.SolItems;

public class ItemBase extends Item {
    public ItemBase(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }
}
