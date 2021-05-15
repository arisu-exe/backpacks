package io.github.falOut015.backpacks.inventory.container;

import io.github.falOut015.backpacks.inventory.KnapsackInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class KnapsackContainer extends Container {
    private final IInventory knapsackInventory;
    public CompoundNBT compoundNBT;

    public KnapsackContainer(int windowId, PlayerInventory player) {
        this(windowId, player, new KnapsackInventory(player.player, ItemStack.EMPTY));
    }
    public KnapsackContainer(int id, PlayerInventory playerInventoryIn, IInventory knapsackInventory) {
        super(ContainersBackpacks.KNAPSACK.get(), id);
        checkContainerSize(knapsackInventory, 18);
        this.knapsackInventory = knapsackInventory;
        this.compoundNBT = new CompoundNBT();
        knapsackInventory.startOpen(playerInventoryIn.player);

        for(int j = 0; j < 2; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(knapsackInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventoryIn, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 - 36));
            }
        }
        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventoryIn, i1, 8 + i1 * 18, 125));
        }

        // Slot that holds knapsack is greyed out and red when hovered over.
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return this.knapsackInventory.stillValid(playerIn);
    }
    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 18) {
                if (!this.moveItemStackTo(itemstack1, 18, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 18, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
        }
        return itemstack;
    }
    @Override
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);

        this.compoundNBT = ((KnapsackInventory) this.knapsackInventory).write(this.compoundNBT);
        ((KnapsackInventory) this.knapsackInventory).itemStack.setTag(this.compoundNBT);
        ((KnapsackInventory) this.knapsackInventory).itemStack.getTag().putBoolean("open", false);

        this.knapsackInventory.stopOpen(playerIn);
    }
    public IInventory getKnapsackInventory() {
        return this.knapsackInventory;
    }
}