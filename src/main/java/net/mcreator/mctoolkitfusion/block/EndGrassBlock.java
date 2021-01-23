
package net.mcreator.mctoolkitfusion.block;

import net.minecraft.block.material.Material;

@MctoolkitFusionModElements.ModElement.Tag
public class EndGrassBlock extends MctoolkitFusionModElements.ModElement {

	@ObjectHolder("mctoolkit_fusion:end_grass")
	public static final Block block = null;

	public EndGrassBlock(MctoolkitFusionModElements instance) {
		super(instance, 192);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ROCK).sound(SoundType.WET_GRASS).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0)
							.harvestLevel(1).harvestTool(ToolType.PICKAXE));

			setRegistryName("end_grass");
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.PURPLE;
		}

		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

	}

}
