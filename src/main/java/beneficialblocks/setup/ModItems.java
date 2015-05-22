package beneficialblocks.setup;

import net.minecraft.item.Item;
import beneficialblocks.setup.RegisterHelper;
import beneficialblocks.items.ItemBloodyShears;
import beneficialblocks.items.ItemHose;
import beneficialblocks.items.ItemHoseEnd;
import beneficialblocks.items.ItemHosePipe;
import beneficialblocks.items.ItemHoseReverse;

public class ModItems
{
	//materials
	public static Item hoseEnd;
	public static Item hosePipe;
	
	//tools
	public static Item hose;
	public static Item hoseReverse;
	public static Item bloodyShears;
	
	public static void registerBlocks()
	{
		hose = new ItemHose();
		RegisterHelper.RegisterItem(hose);
		
		hoseReverse = new ItemHoseReverse();
		RegisterHelper.RegisterItem(hoseReverse);
		
		hoseEnd = new ItemHoseEnd();
		RegisterHelper.RegisterItem(hoseEnd);
		
		hosePipe = new ItemHosePipe();
		RegisterHelper.RegisterItem(hosePipe);
		
		bloodyShears = new ItemBloodyShears();
		RegisterHelper.RegisterItem(bloodyShears);
	}
}