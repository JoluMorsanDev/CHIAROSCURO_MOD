
package net.mcreator.mctoolkitfusion.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

import java.util.List;
import java.util.Collections;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulSandBlock extends MctoolkitFusionModElements.ModElement {
	@ObjectHolder("mctoolkit_fusion:restless_soul_sand")
	public static final Block block = null;
	public RestlessSoulSandBlock(MctoolkitFusionModElements instance) {
		super(instance, 143);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.EARTH).sound(SoundType.SOUL_SAND).hardnessAndResistance(0.6f, 0.5f).setLightLevel(s -> 0)
					.harvestLevel(0).harvestTool(ToolType.SHOVEL));
			setRegistryName("restless_soul_sand");
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