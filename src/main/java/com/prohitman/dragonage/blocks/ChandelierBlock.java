package com.prohitman.dragonage.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class ChandelierBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty IS_LIT = BlockStateProperties.LIT;

    public static final VoxelShape SHAPES = Stream.of(
            Block.makeCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D),
            Block.makeCuboidShape(0.59244, 0, 8.40615, 15.59244, 16, 8.40615),
            Block.makeCuboidShape(7.59244, 0, 0.59385, 7.59244, 16, 15.59385),
            Block.makeCuboidShape(11, 5, 2, 14, 6, 5),
            Block.makeCuboidShape(11.5, 6, 2.5, 13.5, 12, 4.5),
            Block.makeCuboidShape(11, 5, 11, 14, 6, 14), Block.makeCuboidShape(11.5, 6, 11.5, 13.5, 12, 13.5),
            Block.makeCuboidShape(2, 5, 11, 5, 6, 14), Block.makeCuboidShape(2.5, 6, 11.5, 4.5, 12, 13.5),
            Block.makeCuboidShape(2, 5, 2, 5, 6, 5), Block.makeCuboidShape(2.5, 6, 2.5, 4.5, 12, 4.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public ChandelierBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(IS_LIT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if(stack.getItem() == Items.FLINT_AND_STEEL && !state.get(IS_LIT) && !state.get(WATERLOGGED)){
            worldIn.setBlockState(pos,state.with(IS_LIT, Boolean.TRUE));
            worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            stack.damageItem(1, player, (playerIn) -> {
                playerIn.sendBreakAnimation(handIn);
            });
            return ActionResultType.SUCCESS;
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (state.get(IS_LIT)) {
            worldIn.setBlockState(pos, state.cycleValue(IS_LIT), 2);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorld iworld = context.getWorld();
        BlockPos blockpos = context.getPos();
        boolean flag = iworld.getFluidState(blockpos).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag).with(IS_LIT, !flag);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(IS_LIT)) {
            double d0 = 0.78D;
            double d1 = 0.9D;
            double d2 = 0.78D;
            worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, 0.0D, 0.0D, 0.0D);

            worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() - d0 + 1, pos.getY() + d1, pos.getZ() - d2 + 1, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() - d0 + 1, pos.getY() + d1, pos.getZ() - d2 + 1, 0.0D, 0.0D, 0.0D);

            worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() - d0 + 1, pos.getY() + d1, pos.getZ() + d2, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() - d0 + 1, pos.getY() + d1, pos.getZ() + d2, 0.0D, 0.0D, 0.0D);

            worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + d0, pos.getY() + d1, pos.getZ() - d2 + 1, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + d0, pos.getY() + d1, pos.getZ() - d2 + 1, 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            boolean flag = state.get(IS_LIT);
            if (flag) {
                if (!worldIn.isRemote()) {
                    worldIn.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }

            worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.TRUE).with(IS_LIT, Boolean.FALSE), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
            return true;
        } else {
            return false;
        }
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        if (!worldIn.isRemote && projectile.isBurning()) {
            Entity entity = projectile.getShooter();
            boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entity);
            if (flag && !state.get(IS_LIT) && !state.get(WATERLOGGED)) {
                BlockPos blockpos = hit.getPos();
                worldIn.setBlockState(blockpos, state.with(BlockStateProperties.LIT, Boolean.TRUE), 11);
            }
        }
    }

    public static boolean canBeLit(BlockState state) {
        return state.isInAndMatches(BlockTags.CAMPFIRES, (stateIn) -> stateIn.hasProperty(BlockStateProperties.WATERLOGGED) && stateIn.hasProperty(BlockStateProperties.LIT)) && !state.get(BlockStateProperties.WATERLOGGED) && !state.get(BlockStateProperties.LIT);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, IS_LIT);
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return Direction.DOWN == facing && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return hasEnoughSolidSide(worldIn, pos.up(), Direction.DOWN);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
