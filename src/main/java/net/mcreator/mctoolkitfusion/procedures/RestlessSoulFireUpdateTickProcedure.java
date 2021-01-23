package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.mctoolkitfusion.block.WeatheredCutCopperBlock;
import net.mcreator.mctoolkitfusion.block.WeatheredCopperBlockBlock;
import net.mcreator.mctoolkitfusion.block.WaxedCopperBlockBlock;
import net.mcreator.mctoolkitfusion.block.SemiWeatheredCutCopperBlock;
import net.mcreator.mctoolkitfusion.block.SemiWeatheredCopperBlockBlock;
import net.mcreator.mctoolkitfusion.block.RestlessSoulSoilBlock;
import net.mcreator.mctoolkitfusion.block.RestlessSoulSandBlock;
import net.mcreator.mctoolkitfusion.block.RestlessSoulFireBlock;
import net.mcreator.mctoolkitfusion.block.LightlyWeatheredCutCopperBlock;
import net.mcreator.mctoolkitfusion.block.LightlyWeatheredCopperBlockBlock;
import net.mcreator.mctoolkitfusion.block.CutCooperBlock;
import net.mcreator.mctoolkitfusion.block.CopperFireBlock;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulFireUpdateTickProcedure extends MctoolkitFusionModElements.ModElement {
	public RestlessSoulFireUpdateTickProcedure(MctoolkitFusionModElements instance) {
		super(instance, 177);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency x for procedure RestlessSoulFireUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency y for procedure RestlessSoulFireUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency z for procedure RestlessSoulFireUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency world for procedure RestlessSoulFireUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == RestlessSoulSandBlock.block.getDefaultState()
				.getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == RestlessSoulSoilBlock.block.getDefaultState()
						.getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), RestlessSoulFireBlock.block.getDefaultState(), 3);
		} else if ((((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == LightlyWeatheredCutCopperBlock.block
				.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == LightlyWeatheredCopperBlockBlock.block
						.getDefaultState().getBlock()))
				|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SemiWeatheredCutCopperBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == SemiWeatheredCopperBlockBlock.block
								.getDefaultState().getBlock())))
				|| ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == WeatheredCutCopperBlock.block
						.getDefaultState().getBlock())
						|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == WeatheredCopperBlockBlock.block
								.getDefaultState().getBlock()))
						|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CutCooperBlock.block.getDefaultState()
								.getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == WaxedCopperBlockBlock.block
										.getDefaultState().getBlock()))))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CopperFireBlock.block.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
