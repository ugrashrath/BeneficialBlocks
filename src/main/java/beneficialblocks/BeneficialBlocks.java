package beneficialblocks;

import beneficialblocks.help.MyEventHandler;
import beneficialblocks.help.Reference;
import beneficialblocks.help.RegisterHelper;
import beneficialblocks.items.ItemHose;
import beneficialblocks.items.ItemHoseEnd;
import beneficialblocks.items.ItemHosePipe;
import beneficialblocks.items.ItemHoseReverse;
import beneficialblocks.items.Recipies;
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

@Mod(modid=Reference.MODID, version=Reference.VERSION)
public class BeneficialBlocks
{
	
	//blocks
	
	//materials
	public static Item hoseEnd;
	public static Item hosePipe;
	
	//tools
	public static Item hose;
	public static Item hoseReverse;
	
	MyEventHandler handler = new MyEventHandler();	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		hose = new ItemHose();
		RegisterHelper.RegisterItem(hose);
		
		hoseReverse = new ItemHoseReverse();
		RegisterHelper.RegisterItem(hoseReverse);
		
		hoseEnd = new ItemHoseEnd();
		RegisterHelper.RegisterItem(hoseEnd);
		
		hosePipe = new ItemHosePipe();
		RegisterHelper.RegisterItem(hosePipe);
		
		Recipies.makeItems();
		
		MinecraftForge.EVENT_BUS.register(handler);
		
	}
	
}
