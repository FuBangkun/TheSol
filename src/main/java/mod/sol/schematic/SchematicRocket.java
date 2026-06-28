package mod.sol.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicPage;
import mod.sol.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchematicRocket extends SchematicPage {

    private final int tier;
    private final Item schematicItem;
    private final int meta;

    @FunctionalInterface
    public interface GuiFactory {
        GuiScreen create(InventoryPlayer inv, BlockPos pos);
    }

    @FunctionalInterface
    public interface ContainerFactory {
        Container create(InventoryPlayer inv, BlockPos pos);
    }

    private final GuiFactory guiFactory;
    private final ContainerFactory containerFactory;

    public SchematicRocket(int tier, Item schematicItem, int meta, GuiFactory guiFactory, ContainerFactory containerFactory) {
        this.tier = tier;
        this.schematicItem = schematicItem;
        this.meta = meta;
        this.guiFactory = guiFactory;
        this.containerFactory = containerFactory;
    }

    @Override
    public int getPageID() {
        return (tier + 3) + Reference.MOD_ID.hashCode();
    }

    @Override
    public int getGuiID() {
        return (tier - 3) + Reference.MOD_ID.hashCode();
    }

    @Override
    public ItemStack getRequiredItem() {
        return new ItemStack(schematicItem, 1, meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos) {
        return guiFactory.create(player.inventory, pos);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, BlockPos pos) {
        return containerFactory.create(player.inventory, pos);
    }
}
