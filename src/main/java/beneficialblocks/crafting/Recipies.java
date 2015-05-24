package beneficialblocks.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import beneficialblocks.BeneficialBlocks;
import beneficialblocks.setup.ModItems;
import beneficialblocks.setup.RegisterHelper;

public class Recipies
{
	public static void makeMaterials()
	{
		GameRegistry.addRecipe(new ItemStack(ModItems.hoseEnd), new Object[]
		{
			" I ", 
			"IPI", 
			" S ", 
			'I', Items.gold_ingot, 'P', Items.ender_pearl, 'S', Items.lead 
		});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.hosePipe), new Object[]
		{
			"SLS", 
			"LBL", 
			"SLS", 
			'S', Blocks.wool, 'L', Items.leather, 'B', Items.slime_ball 
		});
	}

	public static void makeItems()
	{
		
		GameRegistry.addRecipe(new ItemStack(ModItems.hose), new Object[]
		{
			" RE", 
			"SPS", 
			"ER ",
			'R', Items.redstone, 'E', ModItems.hoseEnd, 'P', ModItems.hosePipe, 'S', Items.string
		});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.bloodyShears), new Object[]
		{
			" NP", 
			"NRN", 
			"BN ",
			'N', Items.netherbrick, 'R', Items.blaze_rod, 'P', Items.blaze_powder, 'B', Blocks.iron_block
		});
	}
		
	public static void makeBlocks()
	{
		
	}
}
