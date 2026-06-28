package mod.sol.items.armor.sulfur;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import mod.sol.Tags;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorSulfur extends ItemArmor implements ISortableItem, IHasModel {
    private final ArmorMaterial material;

    public ItemArmorSulfur(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4) {
        super(par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
        SolItems.ITEMS.add(this);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (this.material == SolItems.ARMOR_SULFUR) {
            if (stack.getItem() == SolItems.SULFUR_HELMET) {
                return Tags.MOD_ID + ":textures/model/sulfur_1.png";
            } else if (stack.getItem() == SolItems.SULFUR_CHESTPLATE || stack.getItem() == SolItems.SULFUR_BOOTS) {
                return Tags.MOD_ID + ":textures/model/sulfur_2.png";
            } else if (stack.getItem() == SolItems.SULFUR_LEGGINGS) {
                return Tags.MOD_ID + ":textures/model/sulfur_3.png";
            }
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == SolItems.COMPRESSED_MANGANESE;
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
