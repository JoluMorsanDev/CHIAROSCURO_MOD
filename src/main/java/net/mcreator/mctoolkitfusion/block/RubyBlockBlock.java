
package net.mcreator.mctoolkitfusion.block;

import net.minecraft.block.material.Material;

@MctoolkitFusionModElements.ModElement.Tag
public class RubyBlockBlock extends MctoolkitFusionModElements.ModElement {

	@ObjectHolder("mctoolkit_fusion:ruby_block")
	public static final Block block = null;

	public RubyBlockBlock(MctoolkitFusionModElements instance) {
		super(instance, 202);

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

					Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5f, 10f).setLightLevel(s -> 0).harvestLevel(2)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("ruby_block");
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
