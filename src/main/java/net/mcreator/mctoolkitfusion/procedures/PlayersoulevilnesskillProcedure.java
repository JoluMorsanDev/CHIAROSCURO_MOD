package net.mcreator.mctoolkitfusion.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.entity.SpritghastEntity;
import net.mcreator.mctoolkitfusion.entity.SpritEntity;
import net.mcreator.mctoolkitfusion.entity.SoulEntity;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModVariables;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;
import java.util.HashMap;

@MctoolkitFusionModElements.ModElement.Tag
public class PlayersoulevilnesskillProcedure extends MctoolkitFusionModElements.ModElement {
	public PlayersoulevilnesskillProcedure(MctoolkitFusionModElements instance) {
		super(instance, 153);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure Playersoulevilnesskill!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Playersoulevilnesskill!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof PlayerEntity) && (entity instanceof SoulEntity.CustomEntity))
				|| (((sourceentity instanceof PlayerEntity) && (entity instanceof SpritghastEntity.CustomEntity))
						|| (((sourceentity instanceof PlayerEntity) && (entity instanceof SpritEntity.CustomEntity))
								|| ((sourceentity instanceof PlayerEntity) && (entity instanceof GhastEntity)))))) {
			{
				double _setval = (double) (((entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MctoolkitFusionModVariables.PlayerVariables())).NetherEvilness) + 1);
				entity.getCapability(MctoolkitFusionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.NetherEvilness = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
