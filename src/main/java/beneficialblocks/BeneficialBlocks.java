package beneficialblocks;

import org.apache.logging.log4j.Logger;

import beneficialblocks.crafting.Recipies;
import beneficialblocks.handler.EntityEventHandler;
import beneficialblocks.integration.MFRItems;
import beneficialblocks.setup.ModItems;
import beneficialblocks.setup.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=Reference.MODID, name=Reference.NAME, version=Reference.VERSION, dependencies=Reference.DEPENDENCIES)
public class BeneficialBlocks
{
	
	public static Logger bbLog;
	EntityEventHandler handler = new EntityEventHandler();	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		bbLog = event.getModLog();
		
		bbLog.info("Beneficial Blocks starting to load.");
		
		ModItems.registerBlocks();
		ModItems.registerItems();
		
		Recipies.makeMaterials();
		Recipies.makeBlocks();
		Recipies.makeItems();

		MinecraftForge.EVENT_BUS.register(handler);
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		//bbLog.info("Beneficial Blocks initial loading.");
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//Attempts as cross-mod integration
		// process is less than hoped for
		MFRItems.init();
		
		bbLog.info("Beneficial Blocks done loading.");
	}
	
}
