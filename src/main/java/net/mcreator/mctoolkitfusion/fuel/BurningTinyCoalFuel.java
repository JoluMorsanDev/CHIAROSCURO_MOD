
package net.mcreator.mctoolkitfusion.fuel;

@MctoolkitFusionModElements.ModElement.Tag
public class BurningTinyCoalFuel extends MctoolkitFusionModElements.ModElement {

	public BurningTinyCoalFuel(MctoolkitFusionModElements instance) {
		super(instance, 195);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(TinyCoalItem.block, (int) (1)).getItem())
			event.setBurnTime(200);
	}

}
