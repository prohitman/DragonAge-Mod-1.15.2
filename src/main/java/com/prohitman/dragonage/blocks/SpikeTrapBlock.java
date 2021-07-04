package com.prohitman.dragonage.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlockStructureHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SpikeTrapBlock extends Block {
    public static final BooleanProperty EXTENDED = BlockStateProperties.EXTENDED;

    public SpikeTrapBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(EXTENDED, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(EXTENDED, Boolean.valueOf(false));
    }

    private boolean shouldBeExtended(World worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (worldIn.isSidePowered(pos.offset(direction), direction)) {
                return true;
            }
        }

        if (worldIn.isSidePowered(pos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockpos = pos.up();

            for(Direction direction1 : Direction.values()) {
                if (direction1 != Direction.DOWN && worldIn.isSidePowered(blockpos.offset(direction1), direction1)) {
                    return true;
                }
            }

            return false;
        }
    }
}

