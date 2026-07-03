package mod.sol.inventory;

import micdoodle8.mods.galacticraft.core.inventory.SlotRocketBenchResult;
import mod.sol.util.RecipeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

public class ContainerSchematicRocket extends Container {
    private final World world;
    private final int tier;
    public InventorySchematicRocket craftMatrix = new InventorySchematicRocket(this);
    public IInventory craftResult = new InventoryCraftResult();

    public ContainerSchematicRocket(InventoryPlayer par1InventoryPlayer, BlockPos pos, int tier) {
        final int change = 27;
        this.tier = tier;
        this.world = par1InventoryPlayer.player.world;
        this.addSlotToContainer(new SlotRocketBenchResult(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 142, 18 + 69 + change));
        int var6;
        int var7;

        // Cone
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 1, 48, -8 + change, pos, par1InventoryPlayer.player, tier));

        // Body
        for (var6 = 0; var6 < 5; ++var6) {
            this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 2 + var6, 39, -6 + var6 * 18 + 16 + change, pos, par1InventoryPlayer.player, tier));
        }

        // Body Right
        for (var6 = 0; var6 < 5; ++var6) {
            this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 7 + var6, 57, -6 + var6 * 18 + 16 + change, pos, par1InventoryPlayer.player, tier));
        }

        // Left fins
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 12, 21, 64 + change, pos, par1InventoryPlayer.player, tier));
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 13, 21, 82 + change, pos, par1InventoryPlayer.player, tier));
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 14, 21, 100 + change, pos, par1InventoryPlayer.player, tier));

        // Engine
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 15, 48, 100 + change, pos, par1InventoryPlayer.player, tier));

        // Right fins
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 16, 75, 64 + change, pos, par1InventoryPlayer.player, tier));
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 17, 75, 82 + change, pos, par1InventoryPlayer.player, tier));
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 18, 75, 100 + change, pos, par1InventoryPlayer.player, tier));

        // Addons
        for (int var8 = 0; var8 < 3; var8++) {
            this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 19 + var8, 93 + var8 * 26, -15 + change, pos, par1InventoryPlayer.player, tier));
        }

        // Player inv:
        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 9; ++var7) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 129 + var6 * 18 + change));
            }
        }

        for (var6 = 0; var6 < 9; ++var6) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 18 + 169 + change));
        }

        // input rocket
        this.addSlotToContainer(new SlotSchematicRocket(this.craftMatrix, 22, 145, -15 + change + change, pos, par1InventoryPlayer.player, tier));

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onContainerClosed(@Nonnull EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);

        if (!this.world.isRemote) {
            for (int var2 = 1; var2 < this.craftMatrix.getSizeInventory(); ++var2) {
                final ItemStack var3 = this.craftMatrix.removeStackFromSlot(var2);

                if (!var3.isEmpty()) {
                    par1EntityPlayer.entityDropItem(var3, 0.0F);
                }
            }
        }
    }

    @Override
    public void onCraftMatrixChanged(@Nonnull IInventory par1IInventory) {
        this.craftResult.setInventorySlotContents(0, RecipeUtil.findMatchingSpaceshipRecipe(this.craftMatrix, tier));
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer par1EntityPlayer) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = ItemStack.EMPTY;
        final Slot var3 = this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack()) {
            final ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 <= 21) {
                if (!this.mergeItemStack(var4, 22, 58, false)) {
                    return ItemStack.EMPTY;
                }

                var3.onSlotChange(var4, var2);
            } else {
                boolean valid = false;
                for (int i = 1; i < 19; i++) {
                    Slot testSlot = this.inventorySlots.get(i);
                    if (!testSlot.getHasStack() && testSlot.isItemValid(var2)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    if (this.mergeOneItemTestInvalid(var4, 1, 19)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    boolean foundChest = false;
                    for (ItemStack woodChest : OreDictionary.getOres("chestWood")) {
                        if (var2.getItem() == woodChest.getItem()) {
                            foundChest = true;
                            break;
                        }
                    }
                    if (foundChest) {
                        if (this.mergeOneItemTestInvalid(var4, 19, 22)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (par1 >= 22 && par1 < 49) {
                        if (!this.mergeItemStack(var4, 49, 58, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (par1 >= 49 && par1 < 58) {
                        if (!this.mergeItemStack(var4, 22, 49, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(var4, 22, 58, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (var4.isEmpty()) {
                var3.putStack(ItemStack.EMPTY);
            } else {
                var3.onSlotChanged();
            }

            if (var4.getCount() == var2.getCount()) {
                return ItemStack.EMPTY;
            }

            var3.onTake(par1EntityPlayer, var4);
        }

        return var2;
    }

    protected boolean mergeOneItemTestInvalid(ItemStack par1ItemStack, int par2, int par3) {
        boolean flag1 = false;
        if (!par1ItemStack.isEmpty()) {
            Slot slot;
            ItemStack slotStack;

            for (int k = par2; k < par3; k++) {
                slot = this.inventorySlots.get(k);
                slotStack = slot.getStack();

                if (slotStack.isEmpty() && slot.isItemValid(par1ItemStack)) {
                    ItemStack stackOneItem = par1ItemStack.copy();
                    stackOneItem.setCount(1);
                    par1ItemStack.shrink(1);
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag1 = true;
                    break;
                }
            }
        }

        return !flag1;
    }
}
