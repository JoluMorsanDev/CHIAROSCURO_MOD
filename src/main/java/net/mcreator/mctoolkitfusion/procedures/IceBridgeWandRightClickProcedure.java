package net.mcreator.mctoolkitfusion.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.mctoolkitfusion.potion.IcyEffectPotion;
import net.mcreator.mctoolkitfusion.item.IceBridgeWandItem;
import net.mcreator.mctoolkitfusion.MctoolkitFusionModElements;
import net.mcreator.mctoolkitfusion.MctoolkitFusionMod;

import java.util.Random;
import java.util.Map;

@MctoolkitFusionModElements.ModElement.Tag
public class IceBridgeWandRightClickProcedure extends MctoolkitFusionModElements.ModElement {
	public IceBridgeWandRightClickProcedure(MctoolkitFusionModElements instance) {
		super(instance, 199);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MctoolkitFusionMod.LOGGER.warn("Failed to load dependency entity for procedure IceBridgeWandRightClick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(IceBridgeWandItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(IcyEffectPotion.potion, (int) 120, (int) 1));
			{
				ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
		}
	}
}
