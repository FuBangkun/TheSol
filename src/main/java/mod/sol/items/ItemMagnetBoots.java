package mod.sol.items;

import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.Tags;
import mod.sol.init.SolCreativeTabs;
import mod.sol.init.SolItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

public class ItemMagnetBoots extends ItemArmor implements IArmorGravity, ISortableItem {
    private final ArmorMaterial material;

    public ItemMagnetBoots(String name, ArmorMaterial materialIn, int renderIndexIn) {
        super(materialIn, renderIndexIn, EntityEquipmentSlot.FEET);
        this.material = materialIn;
        this.setCreativeTab(SolCreativeTabs.ITEM_TAB);
        this.setRegistryName(name);
        this.setTranslationKey(name);

        SolItems.ITEMS.add(this);
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
        if (this.material == ArmorMaterial.IRON) {
            if (stack.getItem() == SolItems.MAGNET_BOOTS) {
                return Tags.MOD_ID + ":textures/model/magnet_boots.png";
            }
        }
        return null;
    }

    private int getGravityOverride(EntityPlayer p) {
        Material material0 = p.world.getBlockState(new BlockPos(p.posX, (int) p.posY, p.posZ)).getMaterial();
        Material material1 = p.world.getBlockState(new BlockPos(p.posX, (int) p.posY - 1, p.posZ)).getMaterial();
        Material material2 = p.world.getBlockState(new BlockPos(p.posX, (int) p.posY - 2, p.posZ)).getMaterial();

        boolean hasIron = material0 == Material.IRON || material0 == Material.ANVIL || material1 == Material.IRON || material1 == Material.ANVIL || material2 == Material.IRON || material2 == Material.ANVIL;

        if (!hasIron) {
            return 0;
        }

        return p.world.provider instanceof WorldProviderSpaceStation ? -1000 : 1000;
    }

    @Override
    public int gravityOverrideIfLow(EntityPlayer p) {
        return getGravityOverride(p);
    }

    @Override
    public int gravityOverrideIfHigh(EntityPlayer p) {
        return getGravityOverride(p);
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == SolItems.MAGNET_INGOT;
    }
}
