package com.prohitman.dragonage.containers;


import com.prohitman.dragonage.init.ModBlocks;
import com.prohitman.dragonage.init.ModContainerTypes;
import com.prohitman.dragonage.tileentities.UrnTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class UrnContainer extends Container {

    public UrnTileEntity urnTileEntity;
    private IWorldPosCallable canInteractWithCallable;

    public UrnContainer(final int windowId, final PlayerInventory playerInventory,
                        final UrnTileEntity tileEntity) {
        super(ModContainerTypes.URN_CONTAINER.get(), windowId);
        this.urnTileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        int i = (1 - 4) * 18;

        final int slotSize = 18;

        // Hotbar
        for (int column = 0; column < 9; ++column) {
            addSlot(new Slot(playerInventory, column, 8 + column * slotSize, 161 + i));
        }

        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * slotSize, 103 + row * slotSize + i));
            }
        }

        // Your inventory
        for (int column = 0; column < 9; ++column) {
            addSlot(new SlotItemHandler(tileEntity.getInventory(), column, 8 + column * slotSize, 18));
        }

    }

    public UrnContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static UrnTileEntity getTileEntity(final PlayerInventory playerInventory,
                                               final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof UrnTileEntity) {
            return (UrnTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModBlocks.URN.get());
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 9) {
                if (!this.mergeItemStack(itemstack1, 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
