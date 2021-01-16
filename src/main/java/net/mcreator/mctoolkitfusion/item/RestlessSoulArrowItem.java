
package net.mcreator.mctoolkitfusion.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulArrowItem extends MctoolkitFusionModElements.ModElement {
	@ObjectHolder("mctoolkit_fusion:restless_soul_arrow")
	public static final Item block = null;
	public RestlessSoulArrowItem(MctoolkitFusionModElements instance) {
		super(instance, 160);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("restless_soul_arrow");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
