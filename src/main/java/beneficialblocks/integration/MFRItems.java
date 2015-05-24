package beneficialblocks.integration;

import beneficialblocks.BeneficialBlocks;
import beneficialblocks.integration.ModLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFRItems
{
	public static Item milkBottle;
	public static Item mushroomsoupBucket;
	public static Block milk;
	
	public static void init()
	{
		
		if(ModLoader.MFR.isLoaded())
		{
			BeneficialBlocks.bbLog.info("MFR is available");
			
			try {
				mushroomsoupBucket 	= GameRegistry.findItemStack("MineFactoryReloaded", "bucket.mushroomsoup", 1).getItem();
				milkBottle 			= GameRegistry.findItemStack("MineFactoryReloaded", "milkbottle", 1).getItem();
				//milk = GameRegistry.findBlock("MineFactoryReloaded", "milk.still");
				//BeneficialBlocks.bbLog.info("BB: " + milk.toString());
				BeneficialBlocks.bbLog.info("MFR items loaded.");
			}
			catch(NullPointerException e)
			{
				BeneficialBlocks.bbLog.info("Error: MFR items didn't load correctly.");
			}

		}
		
	}	
}
