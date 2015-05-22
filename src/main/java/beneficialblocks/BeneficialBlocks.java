package beneficialblocks;

import beneficialblocks.crafting.Recipies;
import beneficialblocks.handler.EntityEventHandler;
import beneficialblocks.integration.MFRItems;
import beneficialblocks.setup.ModItems;
import beneficialblocks.setup.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
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
	
	EntityEventHandler handler = new EntityEventHandler();	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
		ModItems.registerBlocks();
		
		Recipies.makeMaterials();
		Recipies.makeItems();
		
		//Attempts as cross-mod integration
		// process is less than hoped for
		MFRItems.init();
		
		MinecraftForge.EVENT_BUS.register(handler);
		
	}
	
	public void Init()
	{
		
	}
	
	public void postInit()
	{
		
	}
	
}
