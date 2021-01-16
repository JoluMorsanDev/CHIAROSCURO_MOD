package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class DeathWishOnPotionActiveTickProcedure extends MctoolkitFusionModElements.ModElement {
	public DeathWishOnPotionActiveTickProcedure(MctoolkitFusionModElements instance) {
		super(instance, 166);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure DeathWishOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency x for procedure DeathWishOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency y for procedure DeathWishOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency z for procedure DeathWishOnPotionActiveTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure DeathWishOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = (double) (-10);
		found = (boolean) (false);
		for (int index0 = 0; index0 < (int) (20); index0++) {
			sy = (double) (-10);
			for (int index1 = 0; index1 < (int) (20); index1++) {
				sz = (double) (-10);
				for (int index2 = 0; index2 < (int) (20); index2++) {
					if ((((((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getBlock() == Blocks.LAVA
							.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getBlock() == Blocks.LAVA
									.getDefaultState().getBlock()))
							|| ((((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getBlock() == Blocks.FIRE
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
											.getBlock() == Blocks.SOUL_FIRE.getDefaultState().getBlock()))
									|| (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
											.getBlock() == Blocks.SWEET_BERRY_BUSH.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
													.getBlock() == Blocks.WITHER_ROSE.getDefaultState().getBlock()))))
							|| ((((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
									.getBlock() == Blocks.CAMPFIRE.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
											.getBlock() == Blocks.SOUL_CAMPFIRE.getDefaultState().getBlock()))
									|| (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz)))))
											.getBlock() == Blocks.CACTUS.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (x + (sx)), (int) ((y + (sy)) - 1), (int) (z + (sz)))))
													.getBlock() == Blocks.MAGMA_BLOCK.getDefaultState().getBlock()))))) {
						found = (boolean) (true);
					}
					if ((found)) {
						break;
					}
					sz = (double) ((sz) + 1);
				}
				if ((found)) {
					break;
				}
				sy = (double) ((sy) + 1);
			}
			if ((found)) {
				break;
			}
			sx = (double) ((sx) + 1);
		}
		if ((found)) {
			entity.setMotion((((sx) / 10) + 0.5), (((sy) / 10) + 0.5), (((sz) / 10) + 0.5));
		}
	}
}
