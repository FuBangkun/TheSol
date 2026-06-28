package mod.sol.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.TheSol;
import mod.sol.util.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSchematicTier extends ItemMetadataBase implements ISchematicItem, ISortableItem {
    public ItemSchematicTier(String registryName) {
        super(registryName, "t4", "t5", "t6", "t7", "t8", "t9");
    }

    public int getTier(int meta) {
        return meta + 4;
    }

    public int getMetaFromTier(int tier) {
        return tier - 4;
    }

    public static void registerSchematicItems(ItemStack schematicStack) {
        SchematicRegistry.registerSchematicItem(schematicStack);
    }

    @SideOnly(value = Side.CLIENT)
    public static void registerTextures(int tier) {
        SchematicRegistry.registerTexture(new ResourceLocation(Reference.MOD_ID, "textures/items/schematic_t" + tier + ".png"));
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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int tier = getTier(stack.getItemDamage());
        tooltip.add(I18n.format("item.schematic.tier" + tier + ".name"));
    }

    @Override
    protected boolean useSubNamesInTranslation() {
        return false;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.SCHEMATIC;
    }

    @Override
    public void registerModels() {
        for (int meta = 0; meta <= maxMeta; meta++) {
            int tier = getTier(meta);

            String modelName = "schematic_t" + tier;

            net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(
                    this,
                    meta,
                    new net.minecraft.client.renderer.block.model.ModelResourceLocation(
                            new net.minecraft.util.ResourceLocation(mod.sol.util.Reference.MOD_ID, modelName),
                            "inventory"
                    )
            );
        }
    }
}