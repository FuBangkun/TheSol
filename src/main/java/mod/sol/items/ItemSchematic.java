package mod.sol.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import mod.sol.Tags;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemSchematic extends ItemMetadataBase implements ISchematicItem, ISortableItem {
    public ItemSchematic() {
        super("schematic", "t4", "t5", "t6", "t7", "t8", "t9");
    }

    public static void registerSchematicItems(ItemStack schematicStack) {
        SchematicRegistry.registerSchematicItem(schematicStack);
    }

    @SideOnly(Side.CLIENT)
    public static void registerTextures(int tier) {
        SchematicRegistry.registerTexture(new ResourceLocation(Tags.MOD_ID, "textures/items/schematic_t" + tier + ".png"));
    }

    public int getMetaFromTier(int tier) {
        return tier - 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        int tier = stack.getMetadata() + 4;
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
    protected String getModelSuffix(int meta) {
        return "t" + (meta + 4);
    }
}