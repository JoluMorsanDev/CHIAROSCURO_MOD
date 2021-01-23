package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.potion.FlammablePotion;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class FlamablePotionFoodEatenProcedure extends MctoolkitFusionModElements.ModElement {
	public FlamablePotionFoodEatenProcedure(MctoolkitFusionModElements instance) {
		super(instance, 180);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure FlamablePotionFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(FlammablePotion.potion, (int) 2400, (int) 1));
	}
}
