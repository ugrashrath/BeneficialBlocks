package beneficialblocks.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import beneficialblocks.BeneficialBlocks;
import beneficialblocks.setup.CreativeTabBB;

public class ItemGelidProiectum extends Item
{
	public ItemGelidProiectum()
	{	
		setUnlocalizedName("bbGelidProiectum");
		setTextureName("beneficialblocks:gelidProiectum");
		setCreativeTab(CreativeTabBB.BB_TAB);
		//setMaxDamage(10);
		setMaxStackSize(1);
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack iStack, World world, EntityPlayer player)
    {
		
		FluidStack fluid = null;
    	int index=0;
    	
    	//searching inventory for a fluid container
    	
        for(int i=0;i<player.inventory.getSizeInventory();i++)
        {
        	
        	ItemStack invoStack = player.inventory.getStackInSlot(i);
        	
        	if(invoStack == null)
			{
				continue;
			}
        	
			if(invoStack.getItem() instanceof IFluidContainerItem)
			{
				fluid = ((IFluidContainerItem)invoStack.getItem()).getFluid(invoStack);
				
				if(fluid == null)
				{
					continue;
				}
				
				//breaks if Fluid container doesn't contain water
				if(fluid != null && fluid.getFluid() != FluidRegistry.WATER)
				{
					continue;
				}
				
				//if tanks are stacked and need to be separated
				ItemStack newStack = invoStack.copy(); //Makes a copy with all the NBT data intact
				
				if(newStack.stackSize > 1)
				{
					if(player.inventory.getFirstEmptyStack() < 0) //check if inventory space is available for a split
					{
						continue;
					}
					newStack.stackSize = 1;
				}
				//simulated drain to see if enough fluid
				fluid = ((IFluidContainerItem)newStack.getItem()).drain(newStack, 250, false);
				
				if(fluid.amount >= 250)
				{
					index = i;
					break;
				}
			}
        }
        
        //BeneficialBlocks.bbLog.info("The tank is located at index: " + index);
        
		 //actually pulling liquid from container
        if(index >= 0 && fluid != null)
        {
        	int drain = 250;
            
            if(drain > 0)
            {
            	ItemStack invoStack = player.inventory.getStackInSlot(index);
            	
            	if(invoStack.getItem() instanceof IFluidContainerItem)
            	{
            		if(invoStack.stackSize > 1)
            		{
            			ItemStack drainStack = invoStack.copy();
            			invoStack.stackSize -= 1;
            			drainStack.stackSize = 1;
            			((IFluidContainerItem)invoStack.getItem()).drain(drainStack, drain, true);
            			player.inventory.addItemStackToInventory(drainStack); //We checked for space earlier
            		}
            		else
            		{
            			((IFluidContainerItem)invoStack.getItem()).drain(invoStack, drain, true);
            			
            		}
            		Items.snowball.onItemRightClick(new ItemStack(Items.snowball), world, player);
            	}
            }
        }
		
		return iStack;
    }
}
