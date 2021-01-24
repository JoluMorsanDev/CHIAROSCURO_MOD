package net.mcreator.mctoolkitfusion.procedures;

@MctoolkitFusionModElements.ModElement.Tag
public class LavaTridentHitsEntityProcedure extends MctoolkitFusionModElements.ModElement {

	public LavaTridentHitsEntityProcedure(MctoolkitFusionModElements instance) {
		super(instance, 216);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure LavaTridentHitsEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency sourceentity for procedure LavaTridentHitsEntity!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(FlammablePotion.potion, (int) 120, (int) 1));
		entity.setFire((int) 20);
		if (sourceentity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(LavaTridentItem.block, (int) (1));
			_setstack.setCount((int) 1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
		}

	}

}
