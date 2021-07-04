package com.prohitman.dragonage.tileentities;

import com.prohitman.dragonage.blocks.UrnBlock;
import com.prohitman.dragonage.init.ModBlocks;
import com.prohitman.dragonage.init.ModTileEntityTypes;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DDUrnTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> urnContents = NonNullList.withSize(9, ItemStack.EMPTY);
    private int numPlayersUsing;

    private DDUrnTileEntity(TileEntityType<?> urnType) {
        super(urnType);
    }

    public DDUrnTileEntity() {
        this(ModTileEntityTypes.URN_TILE_ENTITY_TYPE.get());
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.urnContents);
        }

        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.urnContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.urnContents);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return 9;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.urnContents;
    }

    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.urnContents = itemsIn;
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.urn");
    }

    protected Container createMenu(int id, PlayerInventory player) {
        return ChestContainer.createGeneric9X1(id, player);
    }

    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            BlockState blockstate = this.getBlockState();
            boolean flag = blockstate.get(UrnBlock.PROPERTY_OPEN);
            if (!flag) {
                this.playSound(blockstate, SoundEvents.BLOCK_BARREL_OPEN);
                this.setOpenProperty(blockstate, true);
            }

            this.scheduleTick();
        }

    }

    private void scheduleTick() {
        this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
    }

    public void urnTick() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        this.numPlayersUsing = ChestTileEntity.calculatePlayersUsing(this.world, this, i, j, k);
        if (this.numPlayersUsing > 0) {
            this.scheduleTick();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!blockstate.matchesBlock(ModBlocks.URN.get())) {
                this.remove();
                return;
            }

            boolean flag = blockstate.get(UrnBlock.PROPERTY_OPEN);
            if (flag) {
                this.playSound(blockstate, SoundEvents.BLOCK_BARREL_CLOSE);
                this.setOpenProperty(blockstate, false);
            }
        }

    }

    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
        }

    }

    private void setOpenProperty(BlockState state, boolean open) {
        this.world.setBlockState(this.getPos(), state.with(UrnBlock.PROPERTY_OPEN, Boolean.valueOf(open)), 3);
    }

    private void playSound(BlockState state, SoundEvent sound) {
        double d0 = (double)this.pos.getX() + 0.5D;
        double d1 = (double)this.pos.getY() + 0.5D;
        double d2 = (double)this.pos.getZ() + 0.5D;
        this.world.playSound(null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }
}
