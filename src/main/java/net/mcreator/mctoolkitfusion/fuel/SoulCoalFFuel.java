
package net.mcreator.mctoolkitfusion.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.mctoolkitfusion.item.SoulCoalItem;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

@MctoolkitFusionModElements.ModElement.Tag
public class SoulCoalFFuel extends MctoolkitFusionModElements.ModElement {
	public SoulCoalFFuel(MctoolkitFusionModElements instance) {
		super(instance, 55);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(SoulCoalItem.block, (int) (1)).getItem())
			event.setBurnTime(1200);
	}
}
