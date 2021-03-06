package powercrystals.minefactoryreloaded.item.syringe;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.render.ModelHelper;
import powercrystals.minefactoryreloaded.setup.MFRThings;

public class ItemSyringeGrowth extends ItemSyringe
{
	public ItemSyringeGrowth()
	{
		setUnlocalizedName("mfr.syringe.growth");
		setContainerItem(MFRThings.syringeEmptyItem);
		setRegistryName(MineFactoryReloadedCore.modId, "syringe_growth");
	}

	@Override
	public boolean canInject(World world, EntityLivingBase entity, ItemStack syringe)
	{
		return (entity instanceof EntityAgeable && ((EntityAgeable)entity).getGrowingAge() < 0) || entity instanceof EntityZombie;
	}
	
	@Override
	public boolean inject(World world, EntityLivingBase entity, ItemStack syringe)
	{
		if(entity instanceof EntityAgeable)
		{
			((EntityAgeable)entity).setGrowingAge(0);
		}
		else
		{
			EntityGiantZombie e = new EntityGiantZombie(world);
			e.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
			world.spawnEntityInWorld(e);
			entity.setDead();
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {

		ModelHelper.registerModel(MFRThings.syringeGrowthItem, "syringe", "variant=growth");
	}
}
