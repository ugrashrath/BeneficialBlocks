package beneficialblocks.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import beneficialblocks.setup.CreativeTabBB;

public class ItemIgnisProiectum extends Item
{
	public ItemIgnisProiectum()
	{	
		setUnlocalizedName("bbIgnisProiectum");
		setTextureName("beneficialblocks:IgnisProiectum");
		setCreativeTab(CreativeTabBB.BB_TAB);
		//setMaxDamage();
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
				if(fluid != null && fluid.getFluid() != FluidRegistry.LAVA)
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
            	MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
            	
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
            		
            		double motionX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4F);
            		double motionZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4F);
            		double motionY = (double)(-MathHelper.sin((player.rotationPitch + 0.0F) / 180.0F * (float)Math.PI) * 0.4F);
            		player.posY += 2;
            		
            		if(!world.isRemote)
            		{
            			world.spawnEntityInWorld(new EntitySmallFireball(world, player, motionX, motionY, motionZ));
            		}
            		//player.posX, player.posY+2, player.posZ
        			
        			//world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Items.fire_charge)));
            	}
            }
        }
		
		return iStack;
    }
}
