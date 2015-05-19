package beneficialblocks;

import beneficialblocks.help.Reference;
import beneficialblocks.help.RegisterHelper;
import beneficialblocks.items.ItemHose;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=Reference.MODID, version=Reference.VERSION)
public class BeneficialBlocks
{
	
	//blocks
	
	//materials
	
	//tools
	public static Item hose;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		hose = new ItemHose();
		RegisterHelper.RegisterItem(hose);
		
	}
	
}
