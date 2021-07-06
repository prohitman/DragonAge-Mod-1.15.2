package com.prohitman.dragonage.entity;

import com.google.common.collect.Lists;
import com.prohitman.dragonage.init.ModEntityTypes;
import com.prohitman.dragonage.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class FireBombEntity extends ProjectileItemEntity {
    public FireBombEntity(EntityType<? extends FireBombEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public FireBombEntity(World worldIn, LivingEntity livingEntityIn) {
        super(ModEntityTypes.FIRE_BOMB_ENTITY.get(), livingEntityIn, worldIn);
    }

    public FireBombEntity(World worldIn, double x, double y, double z) {
        super(ModEntityTypes.FIRE_BOMB_ENTITY.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ModItems.FIRE_BOMB.get();
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity() {
        return 0.05F;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void animateParticles(World worldIn, BlockPos pos, Random rand) {
        float radius = 1.5F;
        for (float x = -radius; x < radius; x += 0.25f) {
            for (float z = -radius; z < radius; z += 0.25f) {
                if (x * x + z * z < radius * radius) {
                    double d0 = (double) pos.getX() + x + (rand.nextInt(9) / 5);
                    double d1 = pos.getY();
                    double d2 = (double) pos.getZ() + z + (rand.nextInt(9) / 5);
                    worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    //worldIn.addParticle(ParticleTypes.ASH, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);

                }
            }
        }
        //cool flying fiery circle
        /*int radius2 = 3;
        for(float a = 0; a < Math.PI * 2; a += Math.PI / 180){
            float x = (float) (Math.cos(a) * radius2);
            float z = (float) (Math.sin(a) * radius2);
            double d0 = (double) pos.getX() + x;
            double d1 = pos.getY();
            double d2 = (double) pos.getZ() + z;
            worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 2.0D, 0.0D, 2.0D);
            worldIn.addParticle(ParticleTypes.LANDING_LAVA, d0, d1, d2, 2.0D, 0.0D, 2.0D);

        }*/

        float radius2 = 1.25f;
        double speed = 0.5;
        for (float a = 0; a < Math.PI * 2; a += Math.PI / 180) {
            float x = (float) (Math.cos(a) * radius2);
            float z = (float) (Math.sin(a) * radius2);

            double particleXSpeed = Math.cos(a) * speed;
            double particleZSpeed = Math.sin(a) * speed;

            double d0 = (double) pos.getX() + x;
            double d1 = pos.getY();
            double d2 = (double) pos.getZ() + z;
            worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, particleXSpeed, 0.0D, particleZSpeed);
            //worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, particleXSpeed, 0.0D, particleZSpeed);
        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);

        if (!this.world.isRemote) {
            Potion potion = Potions.FIRE_RESISTANCE;
            List<EffectInstance> list = Lists.newArrayList();
            list.add(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1));

            if (!list.isEmpty()) {
                this.getAndEffectNearbyEntities(list, result.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult) result).getEntity() : null);
            }
            if(result instanceof BlockRayTraceResult){
                this.setBlockOnFire(this.getPosition(), ((BlockRayTraceResult)result).getFace());
            }
            int i = potion.hasInstantEffect() ? 2007 : 2002;
            this.world.playEvent(i, this.getPosition(), PotionUtils.getPotionColor(potion));
            this.remove();
        }

        if(this.world.isRemote){
            this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 1, 1, false);
            this.animateParticles(this.world, this.getPosition(), this.rand);
        }
    }

    public void setBlockOnFire(BlockPos blockpos, Direction direction) {

        BlockPos randompos = this.world.getBlockRandomPos(blockpos.getX() + 1, blockpos.getY(), blockpos.getZ() -1, 15);
        if (AbstractFireBlock.canLightBlock(world, randompos, direction)) {
            BlockState blockstate1 = AbstractFireBlock.getFireForPlacement(world, randompos);
            world.setBlockState(randompos, blockstate1, 11);
        }
    }

    private void getAndEffectNearbyEntities(List<EffectInstance> p_213888_1_, @Nullable Entity p_213888_2_) {
        AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(10.0D, 3.0D, 10.0D);
        List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for (LivingEntity livingentity : list) {
                if (livingentity.canBeHitWithPotion()) {
                    double d0 = this.getDistanceSq(livingentity);
                    if (d0 < 16.0D) {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                        if (livingentity == p_213888_2_) {
                            d1 = 1.0D;
                        }

                        for (EffectInstance effectinstance : p_213888_1_) {
                            Effect effect = effectinstance.getPotion();
                            if (effect.isInstant()) {
                                effect.affectEntity(this, this.getShooter(), livingentity, effectinstance.getAmplifier(), d1);
                                livingentity.setFire(1000);
                            } else {
                                int i = (int) (d1 * (double) effectinstance.getDuration() + 0.5D);
                                if (i > 20) {
                                    livingentity.addPotionEffect(new EffectInstance(effect, i, effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}
