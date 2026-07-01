package mod.sol.items;

import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.Tags;
import mod.sol.TheSol;
import mod.sol.init.SolItems;
import mod.sol.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ItemArmorBase extends ItemArmor implements ISortableItem, IHasModel {
    private final String textureName;
    private final Supplier<Item> repairIngredient;

    public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, Supplier<Item> repairIngredient) {
        super(material, 7, slot);
        this.textureName = name.toLowerCase();
        this.repairIngredient = repairIngredient;

        String slotName;
        switch (slot) {
            case HEAD:  slotName = "helmet"; break;
            case CHEST: slotName = "chestplate"; break;
            case LEGS:  slotName = "leggings"; break;
            case FEET:  slotName = "boots"; break;
            default:    slotName = slot.getName().toLowerCase(); break;
        }

        this.setTranslationKey(name + "_" + slotName);
        this.setRegistryName(name + "_" + slotName);
        SolItems.ITEMS.add(this);
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
        int layer = 2;
        if (slot == EntityEquipmentSlot.HEAD) {
            layer = 1;
        } else if (slot == EntityEquipmentSlot.LEGS) {
            layer = 3;
        }
        return Tags.MOD_ID + ":textures/model/" + this.textureName + "_" + layer + ".png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return TheSol.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == repairIngredient.get();
    }

    @Override
    public void registerModels() {
        TheSol.proxy.registerItemRenderer(this, 0, "inventory");
    }
}