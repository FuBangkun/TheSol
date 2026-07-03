package mod.sol.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

public class InventorySchematicRocket implements IInventory {
    private final NonNullList<ItemStack> stacks;
    private final Container eventHandler;

    public InventorySchematicRocket(Container par1Container) {
        this.stacks = NonNullList.withSize(23, ItemStack.EMPTY);
        this.eventHandler = par1Container;
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int par1) {
        return par1 >= this.getSizeInventory() ? ItemStack.EMPTY : this.stacks.get(par1);
    }

    @Nonnull
    @Override
    public String getName() {
        return "container.crafting";
    }

    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack oldstack = ItemStackHelper.getAndRemove(this.stacks, index);
        if (!oldstack.isEmpty()) {
            this.markDirty();
            this.eventHandler.onCraftMatrixChanged(this);
        }
        return oldstack;
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.stacks, index, count);

        if (!itemstack.isEmpty()) {
            this.markDirty();
            this.eventHandler.onCraftMatrixChanged(this);
        }

        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.stacks.set(index, stack);
        this.markDirty();
        this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer par1EntityPlayer) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int i, @Nonnull ItemStack itemstack) {
        return false;
    }

    //We don't use these because we use forge containers
    @Override
    public void openInventory(@Nonnull EntityPlayer player) {
    }

    //We don't use these because we use forge containers
    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }
}
