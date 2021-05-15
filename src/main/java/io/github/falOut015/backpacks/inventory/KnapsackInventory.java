package io.github.falOut015.backpacks.inventory;

import io.github.falOut015.backpacks.inventory.container.KnapsackContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class KnapsackInventory implements INamedContainerProvider, IInventory {
    private NonNullList<ItemStack> knapsackContents = NonNullList.withSize(18, ItemStack.EMPTY);
    private int timesChanged;
    public ItemStack itemStack;

    public KnapsackInventory(PlayerEntity player, ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void read(CompoundNBT compound) {
        this.loadFromNbt(compound);
    }
    public CompoundNBT write(CompoundNBT compound) {
        return this.saveToNbt(compound);
    }
    public void loadFromNbt(CompoundNBT compound) {
        this.knapsackContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!compound.getList("Items", 10).isEmpty())
            ItemStackHelper.loadAllItems(compound, this.knapsackContents);
    }
    public CompoundNBT saveToNbt(CompoundNBT compound) {
        ItemStackHelper.saveAllItems(compound, this.knapsackContents, false);
        return compound;
    }
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        if(this.itemStack.hasTag()) {
            read(this.itemStack.getTag());
        }
        return new KnapsackContainer(id, playerInventory, this);
    }
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.knapsack");
    }
    @Override
    public void clearContent() {
        this.getItems().clear();
    }
    @Override
    public int getContainerSize() {
        return 18;
    }
    @Override
    public boolean isEmpty() {
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int index) {
        List<ItemStack> list = null;
        if (index < this.getItems().size())
            list = this.getItems();
        return list == null ? ItemStack.EMPTY : list.get(index);
    }
    @Override
    public ItemStack removeItem(int index, int count) {
        List<ItemStack> list = null;
        if (index < this.getItems().size())
            list = this.getItems();
        return list != null && !list.get(index).isEmpty() ? ItemStackHelper.removeItem(list, index, count) : ItemStack.EMPTY;
    }
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        NonNullList<ItemStack> nonnulllist = null;
        if (index < this.getItems().size())
            nonnulllist = this.getItems();
        if (nonnulllist != null && !nonnulllist.get(index).isEmpty()) {
            ItemStack itemstack = nonnulllist.get(index);
            nonnulllist.set(index, ItemStack.EMPTY);
            return itemstack;
        } else
            return ItemStack.EMPTY;
    }
    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> nonnulllist = null;
        if (index < this.getItems().size())
            nonnulllist = this.getItems();
        if (nonnulllist != null)
            nonnulllist.set(index, stack);
    }
    @Override
    public void setChanged() {
        ++this.timesChanged;
    }
    @Override
    public boolean stillValid(PlayerEntity player) {
        return !player.level.isClientSide;
    }
    public NonNullList<ItemStack> getItems() {
        return this.knapsackContents;
    }
    @OnlyIn(Dist.CLIENT)
    public int getTimesChanged() {
        return this.timesChanged;
    }
}