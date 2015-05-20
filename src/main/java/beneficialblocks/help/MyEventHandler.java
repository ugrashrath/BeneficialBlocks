package beneficialblocks.help;

import beneficialblocks.BeneficialBlocks;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MyEventHandler
{
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent ev)
	{
		InventoryPlayer inv = ev.entityPlayer.inventory;
		
		//get milk from cows
		if(ev.target instanceof EntityCow && inv.getCurrentItem().getItem() == BeneficialBlocks.hose)
		{
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack invoStack = inv.getStackInSlot(i);
				
				if(invoStack == null)
				{
					continue;
				}
				
				if(invoStack.getItem() == Items.bucket)
				{
					if(invoStack.stackSize <= 1)
					{
						inv.setInventorySlotContents(i, new ItemStack(Items.milk_bucket));
					}
					else if (inv.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
					{
						invoStack.stackSize--;
						inv.setInventorySlotContents(i, invoStack);
					}
				}
			}
		}
		//get mushroom stew from mooshroom
		if(ev.target instanceof EntityMooshroom && inv.getCurrentItem().getItem() == BeneficialBlocks.hose)
		{
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack invoStack = inv.getStackInSlot(i);
				
				if(invoStack == null)
				{
					continue;
				}
				
				if(invoStack.getItem() == Items.bowl)
				{
					if(invoStack.stackSize <= 1)
					{
						inv.setInventorySlotContents(i, new ItemStack(Items.mushroom_stew));
					}
					else if (inv.addItemStackToInventory(new ItemStack(Items.mushroom_stew)))
					{
						invoStack.stackSize--;
						inv.setInventorySlotContents(i, invoStack);
					}
				}
			}
		}
	}
}
