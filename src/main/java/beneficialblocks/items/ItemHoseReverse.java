package beneficialblocks.items;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import beneficialblocks.BeneficialBlocks;
import beneficialblocks.setup.CreativeTabBB;
import beneficialblocks.setup.ModItems;

public class ItemHoseReverse extends ItemHose
{
	public ItemHoseReverse()
	{	
		setUnlocalizedName("bbHoseReverse");
		setTextureName("beneficialblocks:hoseReverse");
		setCreativeTab(CreativeTabBB.BB_TAB);
		setMaxDamage(10);
		setMaxStackSize(1);
	}
	
	/**
	 * Using item damage to show cooldown left on hose.
	 * 
	 */
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld)
	{
		if(stack.getItemDamage() > 0)
		{
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
		else if(stack.getItemDamage() < 0)
		{
			stack.setItemDamage(0);
		}
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack iStack, World world, EntityPlayer player)
    {
		
		//built-in item delay
		if(iStack.getItemDamage() > 0)
		{
			return iStack;
		}
		else
		{
			iStack.setItemDamage(this.getMaxDamage());
		}
		
		//starting reverse hose code
		
    	MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
    	
    	if(mop == null)
    	{
    		//switch modes on right click with nothing targetted
    		if(player.isSneaking())
    		{
    			return new ItemStack(ModItems.hose);
    		}
    		
    		return iStack;
    	}
    	
    	TileEntity tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
        Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
        int meta = world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
        
        boolean isTank = false;
    	
    	if(tile instanceof IFluidTank || tile instanceof IFluidHandler)
    	{
    		isTank = true;
    	}
    	else
    	{
    		ForgeDirection dirOff = ForgeDirection.getOrientation(mop.sideHit);
    		mop.blockX += dirOff.offsetX;
    		mop.blockY += dirOff.offsetY;
    		mop.blockZ += dirOff.offsetZ;
    		
    		tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
            block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            meta = world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
            
            if(!block.getMaterial().isReplaceable())
            {
            	return iStack;
            }
    	}
    	
    	FluidStack fluid = null;
    	int index=0;
        
        for(int i=0;i<player.inventory.getSizeInventory();i++)
        {
        	
        	ItemStack invoStack = player.inventory.getStackInSlot(i);
        	
        	if(invoStack == null)
			{
				continue;
			}
        	
			if(invoStack.getItem() instanceof IFluidContainerItem)
			{
				ItemStack newStack = invoStack.copy(); //Makes a copy with all the NBT data intact
				
				if(newStack.stackSize > 1)
				{
					if(player.inventory.getFirstEmptyStack() < 0) //check if inventory space is available for a split
					{
						continue;
					}
					newStack.stackSize = 1;
				}
				
				fluid = ((IFluidContainerItem)newStack.getItem()).drain(newStack, 1000, false);
				
				if(fluid != null && (isTank || (fluid.getFluid().getBlock() != null && fluid.amount >= 1000)))
				{
					if(isTank)
					{
						if(tile instanceof IFluidTank)
						{
							if(((IFluidTank)tile).fill(fluid, false) <= 0)
							{
								continue;
							}
						}
						else if(tile instanceof IFluidHandler)
						{
							if(((IFluidHandler)tile).fill(ForgeDirection.getOrientation(mop.sideHit), fluid, false) <= 0)
							{
								continue;
							}
						}
						else
						{
							continue;
						}
					}
					
					index = i;
					break;
				}
			}
			else if(invoStack.getItem() == Items.lava_bucket)
			{
				index = i;
				fluid = new FluidStack(FluidRegistry.LAVA, 1000);
				break;
			}
			else if(invoStack.getItem() == Items.water_bucket)
			{
				index = i;
				fluid = new FluidStack(FluidRegistry.WATER, 1000);
				break;
			}
			
        }
        
        //BeneficialBlocks.bbLog.info("Index: " + index);
        
        if(index >= 0 && fluid != null)
        {
        	int fill = 0;
        	
            if(isTank)
            {
            	if(tile instanceof IFluidTank)
            	{
            		fill = ((IFluidTank)tile).fill(fluid, true);
            	}
            	else if(tile instanceof IFluidHandler)
            	{
            		fill = ((IFluidHandler)tile).fill(ForgeDirection.getOrientation(mop.sideHit), fluid, true);
            		fluid.getFluid().getBlock().onNeighborBlockChange(world, mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
            	}
            }
            else
            {
            	world.setBlock(mop.blockX, mop.blockY, mop.blockZ, fluid.getFluid().getBlock());
            	fill = 1000;
            }
            
            if(fill > 0)
            {
            	ItemStack invoStack = player.inventory.getStackInSlot(index);
            	
            	if(invoStack.getItem() instanceof IFluidContainerItem)
            	{
            		if(invoStack.stackSize > 1)
            		{
            			ItemStack drainStack = invoStack.copy();
            			invoStack.stackSize -= 1;
            			drainStack.stackSize = 1;
            			((IFluidContainerItem)invoStack.getItem()).drain(drainStack, fill, true);
            			player.inventory.addItemStackToInventory(drainStack); //We checked for space earlier
            			
            		}
            		else
            		{
            			((IFluidContainerItem)invoStack.getItem()).drain(invoStack, fill, true);
            		}
            	}
            	else if(invoStack.getItem() == Items.lava_bucket || invoStack.getItem() == Items.water_bucket)
            	{
            		player.inventory.setInventorySlotContents(index, new ItemStack(Items.bucket)); //Filled buckets should have stack size of 1
            	}
            	else
            	{
            		player.inventory.setInventorySlotContents(index, null); //Make sure no exploits
            	}
            }
        }
        
    	return iStack;
    }
}
