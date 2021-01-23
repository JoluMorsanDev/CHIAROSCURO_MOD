
package net.mcreator.mctoolkitfusion.item;

@MctoolkitFusionModElements.ModElement.Tag
public class RubyItem extends MctoolkitFusionModElements.ModElement {

	@ObjectHolder("mctoolkit_fusion:ruby")
	public static final Item block = null;

	public RubyItem(MctoolkitFusionModElements instance) {
		super(instance, 200);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("ruby");
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
