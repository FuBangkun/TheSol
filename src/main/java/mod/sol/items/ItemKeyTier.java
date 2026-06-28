package mod.sol.items;

import micdoodle8.mods.galacticraft.api.item.IKeyItem;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.Tags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKeyTier extends ItemMetadataBase implements IKeyItem, ISortableItem {
    public ItemKeyTier() {
        super("key", "t4", "t5", "t6", "t7", "t8", "t9", "t10");
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return ClientProxyCore.galacticraftItem;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
    }

    @Override
    public int getTier(ItemStack keyStack) {
        return keyStack.getItemDamage() + 4;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.KEYS;
    }

    @Override
    public void registerModels() {
        for (int meta = 0; meta <= maxMeta; meta++) {
            int tier = meta + 4;

            String modelName = "key_t" + tier;

            net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(
                    this,
                    meta,
                    new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                            new net.minecraft.util.ResourceLocation(Tags.MOD_ID, modelName),
                            "inventory"
                    )
            );
        }
    }
}