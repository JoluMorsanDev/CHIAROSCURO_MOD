package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModVariables;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessoverlayDisplayOverlayIngameProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessoverlayDisplayOverlayIngameProcedure(MctoolkitFusionModElements instance) {
		super(instance, 171);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure RestlessoverlayDisplayOverlayIngame!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MctoolkitFusionModVariables.PlayerVariables())).Restlessovelay) == (true))) {
			return (true);
		}
		return (false);
	}
}
