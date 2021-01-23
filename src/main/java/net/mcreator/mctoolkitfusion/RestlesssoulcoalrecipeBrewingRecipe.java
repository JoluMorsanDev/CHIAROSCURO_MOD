
package net.mcreator.mctoolkitfusion;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;

import net.mcreator.mctoolkitfusion.item.SoulCoalItem;
import net.mcreator.mctoolkitfusion.item.RestlessSoulCrystalShardItem;
import net.mcreator.mctoolkitfusion.item.RestlessSoulCoalItem;

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
