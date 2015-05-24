package beneficialblocks.items;

import beneficialblocks.integration.MFRItems;
import beneficialblocks.setup.CreativeTabBB;
import beneficialblocks.setup.ModItems;
import beneficialblocks.setup.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemHose extends Item
{

	public ItemHose()
	{	
		setUnlocalizedName("bbHose");
		setTextureName("beneficialblocks:hose");
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
		
		//starting hose code
		
    	MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
    	
    	if(mop == null)
    	{
    		//switch modes on right click with nothing targetted
    		if(player.isSneaking())
    		{
    			return new ItemStack(ModItems.hoseReverse);
    		}
    		
    		return iStack;
    	}
        	
    	TileEntity tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
    	Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
    	int meta = world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
    	
    	//Changes hose to hoseReverse
    	if(player.isSneaking() && block == Blocks.grass)
    	{
    		return new ItemStack(ModItems.hoseReverse);
    	}
    	
    	Fluid fluid = FluidRegistry.lookupFluidForBlock(block);
    	int amount = 1000;
    	boolean isTank = false;
       
    	// pulls fluids from fluid tanks
    	if(tile != null && tile instanceof IFluidTank)
    	{
    		isTank = true;
    		FluidStack fStack = ((IFluidTank)tile).drain(1000, false);
    	   
    		if(fStack != null)
    		{
    			fluid = fStack.getFluid();
    			amount = fStack.amount;
    		}
    	} // pull fluids from fluid handlers 
    	else if(tile != null && tile instanceof IFluidHandler)
    	{
    		isTank = true;
    		FluidStack fStack = ((IFluidHandler)tile).drain(ForgeDirection.getOrientation(mop.sideHit), 1000, false);
    	   
    		if(fStack != null)
    		{
    			fluid = fStack.getFluid();
    			amount = fStack.amount;
    		}
    	}
       
    	int fullAmount = amount;
       
    	// find places to put the liquids, tanks, drums, buckets, bottles, etc.
    	if(fluid != null)
    	{
    		for(int i=0;i<player.inventory.getSizeInventory();i++)
    		{
    			ItemStack invoStack = player.inventory.getStackInSlot(i);
    		   
    			if(invoStack == null)
    			{
    				continue;
    			}
    		   
    			if(invoStack.getItem() == Items.bucket && meta == 0 && amount>= 1000)
    			{
    			   
    				if(block == Blocks.water || block == Blocks.flowing_water || fluid == FluidRegistry.WATER)
    				{
    					if(invoStack.stackSize <= 1)
    					{
    						player.inventory.setInventorySlotContents(i, new ItemStack(Items.water_bucket));
    						amount = 0;
    						break;
    					}
    					else if (player.inventory.addItemStackToInventory(new ItemStack(Items.water_bucket)))
    					{
    						invoStack.stackSize--;
    						player.inventory.setInventorySlotContents(i, invoStack);
    						amount = 0;
    						break;
    					}
    				}
    				else if(block == Blocks.lava || block == Blocks.flowing_lava || fluid == FluidRegistry.LAVA)
    				{
    					if(invoStack.stackSize <= 1)
    					{
    						player.inventory.setInventorySlotContents(i, new ItemStack(Items.lava_bucket));
    						amount = 0;
    						break;
    					}
    					else if (player.inventory.addItemStackToInventory(new ItemStack(Items.lava_bucket)))
    					{
    						invoStack.stackSize--;
    						player.inventory.setInventorySlotContents(i, invoStack);
    						amount = 0;
    						break;
    					}/*
    					else if(block == MFRItems.milk)
    					{
    						if(invoStack.stackSize <= 1)
    						{
    							player.inventory.setInventorySlotContents(i, new ItemStack(Items.milk_bucket));
    							amount = 0;
    							break;
    						}
    						else if (player.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
    						{
    							invoStack.stackSize--;
    							player.inventory.setInventorySlotContents(i, invoStack);
    							amount = 0;
    							break;
    						}   
    					}*/                		   
    				}
    			}
    			else if(invoStack.getItem() == Items.glass_bottle && block == Blocks.water && meta == 0 && amount >= 250)
    			{
    				if(invoStack.stackSize <= 1)
    				{
    					player.inventory.setInventorySlotContents(i, new ItemStack(Items.potionitem));
    					amount -=250;
    				}
    				else if(player.inventory.addItemStackToInventory(new ItemStack(Items.potionitem)))
    				{
    					invoStack.stackSize--;
    					player.inventory.setInventorySlotContents(i, invoStack);
    					amount -= 250;
    				}
    			} //Fills tanks while pulling individual tanks
    			else if(invoStack.getItem() instanceof IFluidContainerItem)
    			{
    				if(invoStack.stackSize > 1)
    				{
    					ItemStack newStack = invoStack.copy(); //Makes a copy with all the NBT data intact
    					newStack.stackSize = 1; //We only want one of this item
    					int tmpDrain = ((IFluidContainerItem)newStack.getItem()).fill(newStack, new FluidStack(fluid, amount), true);
				   
    					if(player.inventory.addItemStackToInventory(newStack)) //Successfully added this item. Remove from stack and apply deduction
    					{
    						amount -= tmpDrain;
    						invoStack.stackSize -= 1;
    					}
    				}
    				else
    				{
    					amount -= ((IFluidContainerItem)invoStack.getItem()).fill(invoStack, new FluidStack(fluid, amount), true);
    				}
			   
    				player.inventory.setInventorySlotContents(i, invoStack);
    			}
    		   
    			if(amount <=0)
    			{
    				break;
    			}
    		}
    	}
       
    	if(amount < fullAmount)
    	{
    		if(isTank)
    		{
    			if(tile instanceof IFluidTank)
    			{
    				((IFluidTank)tile).drain(fullAmount - amount, true); 
    			}
    			else if(tile instanceof IFluidHandler)
    			{
    				((IFluidHandler)tile).drain(ForgeDirection.getOrientation(mop.sideHit), fullAmount - amount, true);
    			}        		   
    		}
    		else
    		{
    			world.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
    		}       	   
    	}
    return iStack;
	}
}
