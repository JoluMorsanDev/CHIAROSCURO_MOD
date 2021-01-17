package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModVariables;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessCursePotionExpiresProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessCursePotionExpiresProcedure(MctoolkitFusionModElements instance) {
		super(instance, 170);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure RestlessCursePotionExpires!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			boolean _setval = (boolean) (false);
			entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Restlessovelay = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
