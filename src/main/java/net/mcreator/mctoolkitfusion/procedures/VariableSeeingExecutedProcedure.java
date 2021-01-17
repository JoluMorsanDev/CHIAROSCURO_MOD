package net.mcreator.mctoolkitfusion.procedures;

@MctoolkitFusionModElements.ModElement.Tag
public class VariableSeeingExecutedProcedure extends MctoolkitFusionModElements.ModElement {

	public VariableSeeingExecutedProcedure(MctoolkitFusionModElements instance) {
		super(instance, 168);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure VariableSeeingExecuted!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
					(("soul evilness: ") + "" + (((entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MctoolkitFusionModVariables.PlayerVariables())).Soulevilness)))),
					(false));
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("Buddies: ") + "" + ((entity.getPersistentData().getDouble("bud"))))),
					(false));
		}

	}

}
