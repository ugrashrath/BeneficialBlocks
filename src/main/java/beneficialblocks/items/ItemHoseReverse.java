package beneficialblocks.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import beneficialblocks.setup.CreativeTabBB;

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
		
		//starting hose code
		
    	MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
    	
    	TileEntity tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
        Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
        
        for(int i=0;i<player.inventory.getSizeInventory();i++)
        {
        	ItemStack invoStack = player.inventory.getStackInSlot(i);
        }
    	
    	return iStack;
    }
}
