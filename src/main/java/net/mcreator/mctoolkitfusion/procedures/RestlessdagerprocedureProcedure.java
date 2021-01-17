package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModVariables;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessdagerprocedureProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessdagerprocedureProcedure(MctoolkitFusionModElements instance) {
		super(instance, 152);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure Restlessdagerprocedure!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Restlessdagerprocedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MctoolkitFusionModVariables.PlayerVariables())).NetherEvilness) <= 5)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 90, (int) 2, (false), (false)));
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 90, (int) 2, (false), (false)));
		} else {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 90, (int) 2, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 90, (int) 2, (false), (false)));
		}
	}
}
