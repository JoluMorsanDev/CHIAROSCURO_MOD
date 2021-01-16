package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.potion.FlammablePotion;
import net.mcreator.mctoolkitfusion.potion.DeathWishPotion;
import net.mcreator.mctoolkitfusion.particle.SoulParticleParticle;
import net.mcreator.mctoolkitfusion.entity.RedRagerEntity;
import net.mcreator.mctoolkitfusion.entity.PsycheWarperEntity;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;
import java.util.Collections;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulBowBulletHitsLivingEntityProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessSoulBowBulletHitsLivingEntityProcedure(MctoolkitFusionModElements instance) {
		super(instance, 164);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure RestlessSoulBowBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency sourceentity for procedure RestlessSoulBowBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure RestlessSoulBowBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		IWorld world = (IWorld) dependencies.get("world");
		double NumberCounter = 0;
		if (((sourceentity.getPersistentData().getBoolean("HasPower")) == (true))) {
			if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("Blaze"))) {
				if (world instanceof ServerWorld) {
					Entity entityToSpawn = new FireballEntity(EntityType.FIREBALL, (World) world);
					entityToSpawn.setLocationAndAngles((sourceentity.getPosX()), ((sourceentity.getPosY()) + 2), (sourceentity.getPosZ()), (float) 0,
							(float) 0);
					entityToSpawn.setRenderYawOffset((float) 0);
					entityToSpawn.setMotion((((entity.getPosX()) - (sourceentity.getPosX())) / 2.5),
							(((entity.getPosY()) - (sourceentity.getPosY())) / 2.5), (((entity.getPosZ()) - (sourceentity.getPosZ())) / 2.5));
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			} else {
				if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("Hoglin"))) {
					entity.setMotion((((entity.getPosX()) - (sourceentity.getPosX())) / 5), (((entity.getPosY()) - (sourceentity.getPosY())) / 2.5),
							(((entity.getPosZ()) - (sourceentity.getPosZ())) / 5));
				} else {
					if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("Strider"))) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(FlammablePotion.potion, (int) 960, (int) 1));
					} else {
						if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("WitherSkeleton"))) {
							if (entity instanceof LivingEntity)
								((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 240, (int) 1));
						} else {
							if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("PsycheWarper"))) {
								if (entity instanceof LivingEntity)
									((LivingEntity) entity).addPotionEffect(new EffectInstance(DeathWishPotion.potion, (int) 240, (int) 1));
							} else {
								if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("MagmaCube"))) {
									entity.setMotion((entity.getMotion().getX()), 2, (entity.getMotion().getZ()));
								} else {
									if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("RedRager"))) {
										if ((!((entity instanceof PlayerEntity) || (!(entity.isNonBoss()))))) {
											if ((entity instanceof TameableEntity) && (sourceentity instanceof PlayerEntity)) {
												((TameableEntity) entity).setTamed(true);
												((TameableEntity) entity).setTamedBy((PlayerEntity) sourceentity);
											}
										}
									} else {
										if ((((sourceentity.getPersistentData().getString("RestlessBowAbility"))).equals("Enderman"))) {
											{
												Entity _ent = entity;
												_ent.setPositionAndUpdate((sourceentity.getPosX()), (sourceentity.getPosY()),
														(sourceentity.getPosZ()));
												if (_ent instanceof ServerPlayerEntity) {
													((ServerPlayerEntity) _ent).connection.setPlayerLocation((sourceentity.getPosX()),
															(sourceentity.getPosY()), (sourceentity.getPosZ()), _ent.rotationYaw, _ent.rotationPitch,
															Collections.emptySet());
												}
											}
											entity.rotationYaw = (float) ((sourceentity.rotationYaw));
											entity.setRenderYawOffset(entity.rotationYaw);
											entity.prevRotationYaw = entity.rotationYaw;
											if (entity instanceof LivingEntity) {
												((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
												((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
												((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
											}
											entity.rotationPitch = (float) ((sourceentity.rotationPitch));
											{
												Entity _ent = sourceentity;
												_ent.setPositionAndUpdate((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()));
												if (_ent instanceof ServerPlayerEntity) {
													((ServerPlayerEntity) _ent).connection.setPlayerLocation((entity.getPosX()), (entity.getPosY()),
															(entity.getPosZ()), _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
												}
											}
											sourceentity.rotationYaw = (float) ((entity.rotationYaw));
											entity.setRenderYawOffset(entity.rotationYaw);
											entity.prevRotationYaw = entity.rotationYaw;
											if (entity instanceof LivingEntity) {
												((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
												((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
												((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
											}
											sourceentity.rotationPitch = (float) ((entity.rotationPitch));
										}
									}
								}
							}
						}
					}
				}
			}
			NumberCounter = (double) 0;
			sourceentity.getPersistentData().putBoolean("HasPower", (false));
			sourceentity.getPersistentData().putString("RestlessBowAbility", "None");
		} else {
			if (((entity instanceof BlazeEntity) || (entity instanceof GhastEntity))) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Blaze");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if (((entity instanceof HoglinEntity) || (entity instanceof ZoglinEntity))) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Hoglin");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof RedRagerEntity.CustomEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "RedRager");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof StriderEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Strider");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof SkeletonEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Skeleton");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof WitherSkeletonEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "WitherSkeleton");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof MagmaCubeEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "MagmaCube");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if (((entity instanceof PiglinEntity) || ((entity instanceof PiglinBruteEntity) || (entity instanceof ZombifiedPiglinEntity)))) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Piglin");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof PsycheWarperEntity.CustomEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "PsycheWarper");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			if ((entity instanceof EndermanEntity)) {
				sourceentity.getPersistentData().putString("RestlessBowAbility", "Enderman");
				sourceentity.getPersistentData().putBoolean("HasPower", (true));
			}
			world.addParticle(SoulParticleParticle.particle, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
					(((sourceentity.getPosX()) - (entity.getPosX())) / 2.5), (((sourceentity.getPosY()) - (entity.getPosY())) / 2.5),
					(((sourceentity.getPosZ()) - (entity.getPosZ())) / 2.5));
		}
	}
}
