package beneficialblocks.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import beneficialblocks.BeneficialBlocks;
import beneficialblocks.help.RegisterHelper;

public class Recipies
{
	public static void makeItems()
	{
		GameRegistry.addRecipe(new ItemStack(BeneficialBlocks.hoseEnd), new Object[]
		{
			" I ", 
			"IPI", 
			" S ", 
			'I', Items.gold_nugget, 'P', Items.ender_pearl, 'S', Items.slime_ball 
		});
		
		GameRegistry.addRecipe(new ItemStack(BeneficialBlocks.hosePipe), new Object[]
		{
			"SLS", 
			"LBL", 
			"SLS", 
			'S', Items.string, 'L', Items.leather, 'B', Items.slime_ball 
		});
		
		GameRegistry.addRecipe(new ItemStack(BeneficialBlocks.hose), new Object[]
		{
			" RE", 
			" P ", 
			"ER ",
			'R', Items.redstone, 'E', BeneficialBlocks.hoseEnd, 'P', BeneficialBlocks.hosePipe
		});
	}
}
