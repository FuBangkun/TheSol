package mod.sol.items;

import lombok.Getter;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class ItemMetadataBase extends Item {
    protected final String[] subNames;
    @Getter
    protected final int maxMeta;

    public ItemMetadataBase(String registryName, String... subNames) {
        this.subNames = subNames;
        this.maxMeta = subNames.length - 1;

        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);
        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);

        SolItems.ITEMS.add(this);
    }

    public String getModelSuffix(int meta) {
        if (meta >= 0 && meta < subNames.length) {
            return subNames[meta];
        }
        return "default";
    }

    @Nonnull
    @Override
    public String getTranslationKey(@Nonnull ItemStack stack) {
        if (useSubNamesInTranslation()) {
            int meta = stack.getMetadata();
            if (meta >= 0 && meta < subNames.length) {
                return super.getTranslationKey() + "." + subNames[meta];
            }
        }
        return super.getTranslationKey();
    }

    protected boolean useSubNamesInTranslation() {
        return true;
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int meta = 0; meta <= maxMeta; meta++) {
                items.add(new ItemStack(this, 1, meta));
            }
        }
    }
}