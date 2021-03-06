package powercrystals.minefactoryreloaded.modhelpers.forestry;

import forestry.api.arboriculture.ITreeRoot;
import forestry.api.genetics.AlleleManager;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableStandard;

public class ForestrySapling extends PlantableStandard implements IFactoryFertilizable
{
	private ITreeRoot root;

	public ForestrySapling(Item item, Block block)
	{
		super(item, block, WILDCARD, null);
		root = (ITreeRoot)AlleleManager.alleleRegistry.getSpeciesRoot("rootTrees");
		_plantedBlock = new ReplacementBlock((Block)null) {
			@Override
			public boolean replaceBlock(World world, BlockPos pos, ItemStack stack) {
				return root.plantSapling(world, root.getMember(stack), null, pos);
			}
		};
	}

	@Override
	public Block getPlant()
	{
		return _block;
	}

	@Override
	public boolean canBePlantedHere(World world, BlockPos pos, ItemStack stack)
	{
		if (!world.isAirBlock(pos))
			return false;

		return root.getMember(stack).canStay(world, pos);
	}

	@Override
	public boolean canFertilize(World world, BlockPos pos, FertilizerType fertilizerType)
	{
		return true;
	}

	@Override
	public boolean fertilize(World world, Random rand, BlockPos pos, FertilizerType fertilizerType)
	{
		Block block = world.getBlockState(pos).getBlock();
		root.getTree(world, pos).getTreeGenerator(world, pos, true).generate(world, rand, pos);
		return world.getBlockState(pos).getBlock() != block;
	}
}
