package com.prohitman.dragonage.entity;

import com.google.common.collect.Lists;
import com.prohitman.dragonage.init.ModEntityTypes;
import com.prohitman.dragonage.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class AcidBombEntity extends ProjectileItemEntity {
    public static final Predicate<LivingEntity> WATER_SENSITIVE = LivingEntity::isWaterSensitive;

    public AcidBombEntity(EntityType<? extends AcidBombEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public AcidBombEntity(World worldIn, LivingEntity livingEntityIn) {
        super(ModEntityTypes.ACID_BOMB_ENTITY.get(), livingEntityIn, worldIn);
    }

    public AcidBombEntity(World worldIn, double x, double y, double z) {
        super(ModEntityTypes.ACID_BOMB_ENTITY.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ModItems.ACID_BOMB.get();
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

    public void animateTick(World worldIn, BlockPos pos, Random rand) {
        float radius = 1.5F;
        for (float x = -radius; x < radius; x += 0.3f) {
            for (float z = -radius; z < radius; z += 0.3f) {
                if (x * x + z * z < radius * radius) {
                        double d0 = (double) pos.getX() + x + (rand.nextInt(9) / 5);
                        double d1 = pos.getY();
                        double d2 = (double) pos.getZ() + z + (rand.nextInt(9) / 5);
                        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                        worldIn.addParticle(ParticleTypes.ASH, d0, d1, d2, 0.0D, 0.0D, 0.0D);

                }
            }
        }

    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 1, 1, false);
        this.animateTick(this.world, this.getPosition(), this.rand);
        if (!this.world.isRemote) {
            Potion potion = Potions.POISON;
            List<EffectInstance> list = Lists.newArrayList();
            list.add(new EffectInstance(Effects.POISON, 1000, 2));
            list.add(new EffectInstance(Effects.WEAKNESS, 1000, 0));
            list.add(new EffectInstance(Effects.WITHER, 1000, 1));

            if (!list.isEmpty()) {
                this.func_213888_a(list, result.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult) result).getEntity() : null);

            }
            int i = potion.hasInstantEffect() ? 2007 : 2002;
            this.world.playEvent(i, this.getPosition(), PotionUtils.getPotionColor(potion));
            this.remove();
        }
    }

    private void func_213888_a(List<EffectInstance> p_213888_1_, @Nullable Entity p_213888_2_) {
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
