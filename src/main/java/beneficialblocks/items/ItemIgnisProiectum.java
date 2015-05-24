package beneficialblocks.items;

import net.minecraft.item.Item;
import beneficialblocks.setup.CreativeTabBB;

public class ItemIgnisProiectum extends Item
{
	public ItemIgnisProiectum()
	{	
		setUnlocalizedName("bbIgnisProiectum");
		setTextureName("beneficialblocks:ignisProiectum");
		setCreativeTab(CreativeTabBB.BB_TAB);
		//setMaxDamage();
		setMaxStackSize(1);
	}
}
