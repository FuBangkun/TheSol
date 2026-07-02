package mod.sol.items;

import mod.sol.TheSol;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
