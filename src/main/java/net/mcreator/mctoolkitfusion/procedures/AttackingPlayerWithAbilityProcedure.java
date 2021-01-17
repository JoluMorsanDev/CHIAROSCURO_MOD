package net.mcreator.mctoolkitfusion.procedures;

@MctoolkitFusionModElements.ModElement.Tag
public class AttackingPlayerWithAbilityProcedure extends MctoolkitFusionModElements.ModElement {

	public AttackingPlayerWithAbilityProcedure(MctoolkitFusionModElements instance) {
		super(instance, 167);

		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure AttackingPlayerWithAbility!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency sourceentity for procedure AttackingPlayerWithAbility!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency x for procedure AttackingPlayerWithAbility!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency y for procedure AttackingPlayerWithAbility!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency z for procedure AttackingPlayerWithAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure AttackingPlayerWithAbility!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if (((entity instanceof PlayerEntity) && (((entity.getPersistentData().getDouble("bud")) > 0) && (sourceentity.isNonBoss())))) {
			if (world instanceof ServerWorld) {
				Entity entityToSpawn = new RedRagerEntity.CustomEntity(RedRagerEntity.entity, (World) world);
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

				world.addEntity(entityToSpawn);
			}
			sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
			if ((sourceentity.isAlive())) {
				sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
				if ((sourceentity.isAlive())) {
					sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
					if ((sourceentity.isAlive())) {
						sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
						if ((sourceentity.isAlive())) {
							sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
							if ((sourceentity.isAlive())) {
								sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
								if ((sourceentity.isAlive())) {
									sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) (entity.getPersistentData().getDouble("bud")));
									if ((sourceentity.isAlive())) {
										sourceentity.attackEntityFrom(DamageSource.MAGIC, (float) Double.POSITIVE_INFINITY);
									}
								}
							}
						}
					}
				}
			}
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if ((entityiterator instanceof RedRagerEntity.CustomEntity)) {
						entityiterator.attackEntityFrom(DamageSource.GENERIC, (float) Double.POSITIVE_INFINITY);
					}
				}
			}
			entity.getPersistentData().putDouble("bud", ((entity.getPersistentData().getDouble("bud")) - 1));
		}

	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			Entity imediatesourceentity = event.getSource().getImmediateSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("imediatesourceentity", imediatesourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

}
