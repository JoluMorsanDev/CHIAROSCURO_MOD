
package net.mcreator.mctoolkitfusion.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

import java.util.List;
import java.util.Collections;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulStoneBlock extends MctoolkitFusionModElements.ModElement {
	@ObjectHolder("mctoolkit_fusion:restless_soul_stone")
	public static final Block block = null;
	public RestlessSoulStoneBlock(MctoolkitFusionModElements instance) {
		super(instance, 186);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.NETHERRACK).hardnessAndResistance(1.5f, 3f).setLightLevel(s -> 0)
					.harvestLevel(0).harvestTool(ToolType.PICKAXE).slipperiness(0.8f));
			setRegistryName("restless_soul_stone");
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.BROWN;
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
