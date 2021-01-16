
package net.mcreator.mctoolkitfusion.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.mctoolkitfusion.item.RestlessSoulCoalItem;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;

@MctoolkitFusionModElements.ModElement.Tag
public class RestlessSoulCoalFFuel extends MctoolkitFusionModElements.ModElement {
	public RestlessSoulCoalFFuel(MctoolkitFusionModElements instance) {
		super(instance, 146);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(RestlessSoulCoalItem.block, (int) (1)).getItem())
			event.setBurnTime(1700);
	}
}
