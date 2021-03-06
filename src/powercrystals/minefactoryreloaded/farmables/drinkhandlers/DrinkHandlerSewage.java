package powercrystals.minefactoryreloaded.farmables.drinkhandlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraftforge.fluids.FluidStack;
import powercrystals.minefactoryreloaded.api.ILiquidDrinkHandler;

public class DrinkHandlerSewage implements ILiquidDrinkHandler {

	@Override
	public void onDrink(EntityLivingBase player, FluidStack fluid) {

		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 40 * 20, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.POISON, 40 * 20, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40 * 20, 0));
	}

}
