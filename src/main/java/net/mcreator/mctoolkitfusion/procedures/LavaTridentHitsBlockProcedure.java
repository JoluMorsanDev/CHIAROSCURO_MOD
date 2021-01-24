package net.mcreator.mctoolkitfusion.procedures;

@MctoolkitFusionModElements.ModElement.Tag
public class LavaTridentHitsBlockProcedure extends MctoolkitFusionModElements.ModElement {

	public LavaTridentHitsBlockProcedure(MctoolkitFusionModElements instance) {
		super(instance, 215);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure LavaTridentHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure LavaTridentHitsBlock!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");

		if (world instanceof World && !world.isRemote()) {
			ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
					new ItemStack(LavaTridentItem.block, (int) (1)));
			entityToSpawn.setPickupDelay((int) 10);
			entityToSpawn.setNoDespawn();
			world.addEntity(entityToSpawn);
		}
		if (world instanceof World && !world.isRemote()) {
			((World) world).playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.return")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else {
			((World) world).playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.return")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
		}

	}

}
