
package net.mcreator.mctoolkitfusion;

@MctoolkitFusionModElements.ModElement.Tag
public class FlmablepotionrecipeBrewingRecipe extends MctoolkitFusionModElements.ModElement {

	public FlmablepotionrecipeBrewingRecipe(MctoolkitFusionModElements instance) {
		super(instance, 181);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(Items.POTION, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.FIRE_CHARGE, (int) (1))), new ItemStack(FlamablePotionItem.block, (int) (1)));
	}

}