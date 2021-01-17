package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.world.GameType;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModVariables;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessCurseOnPotionActiveTickProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessCurseOnPotionActiveTickProcedure(MctoolkitFusionModElements instance) {
		super(instance, 169);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure RestlessCurseOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MctoolkitFusionModVariables.PlayerVariables())).NetherEvilness) < 100)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 2, (int) 0, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 2, (int) 0, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 2, (int) 2, (false), (false)));
			if (((new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.SURVIVAL;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.SURVIVAL;
					}
					return false;
				}
			}.checkGamemode(entity)) || (new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.ADVENTURE;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.ADVENTURE;
					}
					return false;
				}
			}.checkGamemode(entity)))) {
				{
					boolean _setval = (boolean) (true);
					entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Restlessovelay = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, (int) 2, (int) 1, (false), (false)));
	}
}
