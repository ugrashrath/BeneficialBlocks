package beneficialblocks.integration;

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
		
		if(Loader.isModLoaded("MineFactoryReloaded"))
		{
			FMLLog.info("BB: MFR is available");
			
			try {
				mushroomsoupBucket = GameRegistry.findItemStack("MineFactoryReloaded", "bucket.mushroomsoup", 1).getItem();
				milkBottle = GameRegistry.findItemStack("MinefactoryReloaded", "milkbottle", 1).getItem();
				milk = GameRegistry.findBlock("MineFactoryReloaded", "milk.still");
				FMLLog.info("BB: " + milk.toString());
			}
			catch(NullPointerException e)
			{
				FMLLog.info("BB: One of the items didn't load correctly");
			}

		}
		
	}	
}
