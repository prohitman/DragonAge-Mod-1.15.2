package com.prohitman.dragonage.tileentities;

import com.prohitman.dragonage.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UrnTileEntity extends TileEntity implements ITickableTileEntity{
    //public final int size;
    public int timer;
    public boolean requiresUpdate = true;
    private NonNullList<ItemStack> urnContents = NonNullList.withSize(9, ItemStack.EMPTY);

    public final IItemHandlerModifiable inventory;
    protected LazyOptional<IItemHandlerModifiable> handler;

    private UrnTileEntity(TileEntityType<?> urnType) {
        super(urnType);
        //this.size = 9;
        inventory = this.createHandler();
        handler = LazyOptional.of(() -> inventory);
    }

    public UrnTileEntity() {
        this(ModTileEntityTypes.URN_TILE_ENTITY_TYPE.get());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return this.handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public LazyOptional<IItemHandlerModifiable> getHandler() {
        return this.handler;
    }

    public IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    public IItemHandlerModifiable createHandler() {
        return new ItemStackHandler(this.urnContents.size()) {
            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                return super.extractItem(slot, amount, simulate);
            }
        };
    }

    public ItemStack getItemInSlot(int slot) {
        return this.handler.map(inventory -> inventory.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        ItemStack itemIn = stack.copy();
        stack.shrink(itemIn.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inventory -> inventory.insertItem(slot, itemIn, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack extractItem(int slot) {
        int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inventory -> inventory.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    public int getSize() {
        return this.urnContents.size();
    }

    public NonNullList<ItemStack> getItems() {
        return this.urnContents;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        ListNBT list = compound.getList("Items", 10);
        for (int x = 0; x < list.size(); ++x) {
            CompoundNBT nbt = list.getCompound(x);
            int r = nbt.getByte("Slot") & 255;
            this.handler.ifPresent(inventory -> {
                int invslots = inventory.getSlots();
                if (r >= 0 && r < invslots) {
                    inventory.setStackInSlot(r, ItemStack.read(nbt));
                }
            });
        }
        this.requiresUpdate = true;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ListNBT list = new ListNBT();
        int slots = inventory.getSlots();
        for (int x = 0; x < slots; ++x) {
            ItemStack stack = inventory.getStackInSlot(x);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putByte("Slot", (byte) x);
            stack.write(nbt);
            list.add(nbt);
        }

        compound.put("Items", list);
        return compound;
    }

    public void updateTile() {
        this.requestModelDataUpdate();
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.handleUpdateTag(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public void tick() {
        this.timer++;
        if (this.world != null) {
            if (this.requiresUpdate) {
                updateTile();
                this.requiresUpdate = false;
            }
        }
    }

}
