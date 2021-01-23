
package net.mcreator.mctoolkitfusion;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlesssoulcoalrecipeBrewingRecipe extends MctoolkitFusionModElements.ModElement {

	public RestlesssoulcoalrecipeBrewingRecipe(MctoolkitFusionModElements instance) {
		super(instance, 179);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(SoulCoalItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(RestlessSoulCrystalShardItem.block, (int) (1))),
				new ItemStack(RestlessSoulCoalItem.block, (int) (1)));
	}

}