package beneficialblocks.help;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabBB
{

	public static final CreativeTabs BB_TAB = new CreativeTabs("bbTab")
	{
		@Override
		public Item getTabIconItem()
		{
			return Items.written_book;
		}
	};
	
}
