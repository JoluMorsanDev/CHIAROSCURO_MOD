package net.mcreator.mctoolkitfusion.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.mctoolkitfusion.block.SoulstoneBlock;
import net.mcreator.mctoolkitfusion.block.SoulCrystalOreBlock;
import net.mcreator.mctoolkitfusion.block.SoulCoalOreBlock;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;
import java.util.HashMap;

@MctoolkitFusionModElements.ModElement.Tag
public class ColorFireProcedure extends MctoolkitFusionModElements.ModElement {
	public ColorFireProcedure(MctoolkitFusionModElements instance) {
		super(instance, 64);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency x for procedure ColorFire!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency y for procedure ColorFire!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency z for procedure ColorFire!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure ColorFire!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulstoneBlock.block.getDefaultState().getBlock())
				|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulCoalOreBlock.block.getDefaultState()
						.getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulCrystalOreBlock.block
								.getDefaultState().getBlock())))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 0), (int) z))).getBlock() == Blocks.FIRE.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.SOUL_SOIL.getDefaultState(), 3);
				if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulstoneBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), SoulstoneBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulCoalOreBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), SoulCoalOreBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SoulCrystalOreBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), SoulCrystalOreBlock.block.getDefaultState(), 3);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
		Entity entity = event.getEntity();
		IWorld world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", event.getPos().getX());
		dependencies.put("y", event.getPos().getY());
		dependencies.put("z", event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
