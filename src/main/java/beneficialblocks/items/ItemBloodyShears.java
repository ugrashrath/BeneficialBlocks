package beneficialblocks.items;

import beneficialblocks.setup.CreativeTabBB;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;

public class ItemBloodyShears extends ItemShears
{
	
	public ItemBloodyShears()
	{	
		setUnlocalizedName("bbBloodyShears");
		setTextureName("beneficialblocks:bloodyShears");
		setCreativeTab(CreativeTabBB.BB_TAB);
		setMaxDamage(42);
		setMaxStackSize(1);
	}
	
	public void interactChicken()
	{
		
	}
	
	public void interactCow()
	{
		
	}
	
	public void interactVillager()
	{
		
	}
}
