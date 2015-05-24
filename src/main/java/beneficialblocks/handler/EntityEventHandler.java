package beneficialblocks.handler;

import beneficialblocks.BeneficialBlocks;
import beneficialblocks.integration.MFRItems;
import beneficialblocks.setup.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler
{
	public static DamageSource bloodyShears = (new DamageSource("bloody shears")).setDamageBypassesArmor();
		
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent ev)
	{
		InventoryPlayer inv = ev.entityPlayer.inventory;
		EntityPlayer p = ev.entityPlayer;
		World w = ev.target.worldObj;
		
		if(inv.getCurrentItem() == null)
		{
			return;
		}
		
		/*
		 * 
		 * Extracts Mushroom Stew from Mooshrooms provided there are empty bowls
		 * 
		 * */
		if(ev.target instanceof EntityMooshroom && inv.getCurrentItem().getItem() == ModItems.hose)
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
				else if(invoStack.getItem() == Items.bucket)
				{
					if(invoStack.stackSize <= 1)
					{
						inv.setInventorySlotContents(i, new ItemStack(MFRItems.mushroomsoupBucket));
					}
					else if(inv.addItemStackToInventory(new ItemStack(MFRItems.mushroomsoupBucket)))
					{
						invoStack.stackSize--;
						inv.setInventorySlotContents(i, invoStack);
					}
				}
			}
		}
		else
		/*
		 * 
		 * Extracts Milk from Cows provided there are empty buckets
		 * 
		 * */
		if(ev.target instanceof EntityCow && inv.getCurrentItem().getItem() == ModItems.hose)
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
						BeneficialBlocks.bbLog.info("Index of no stack: " + i);
						break;
					}
					else if (inv.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
					{
						invoStack.stackSize--;
						inv.setInventorySlotContents(i, invoStack);
						BeneficialBlocks.bbLog.info("Index of stack: " + i);
						break;
					}
				}
				else if(invoStack.getItem() == Items.glass_bottle)
				{
					if(invoStack.stackSize <= 1)
					{
						inv.setInventorySlotContents(i, new ItemStack(MFRItems.milkBottle));
					}
					else if (inv.addItemStackToInventory(new ItemStack(MFRItems.milkBottle)))
					{
						invoStack.stackSize--;
						inv.setInventorySlotContents(i, invoStack);
					}
				}
			}
		}
		
		/*
		 * 
		 * Extracts Emerald(s) from Villagers provided they have enough health
		 * 
		 * */
		if(ev.target instanceof EntityVillager && p.isSneaking() && inv.getCurrentItem().getItem() == ModItems.bloodyShears)
		{
			if(((EntityLiving)ev.target).getHealth()>= 10)
			{
				ev.target.attackEntityFrom(bloodyShears, 10);
				if(!w.isRemote)
				{
					w.spawnEntityInWorld(new EntityItem(w, ev.target.posX, ev.target.posY, ev.target.posZ, new ItemStack(Items.emerald)));
				}
				inv.getCurrentItem().setItemDamage(inv.getCurrentItem().getItemDamage()+1);
			}
		}
		
		/*
		 * 
		 * Extracts Feather(s) from Chickens provided they have enough health
		 * 
		 * */
		if(ev.target instanceof EntityChicken && p.isSneaking() && inv.getCurrentItem().getItem() == ModItems.bloodyShears)
		{
			if(((EntityLiving)ev.target).getHealth()>= 2)
			{
				ev.target.attackEntityFrom(bloodyShears, 2);
				if(!w.isRemote)
				{
					w.spawnEntityInWorld(new EntityItem(w, ev.target.posX, ev.target.posY, ev.target.posZ, new ItemStack(Items.feather)));
				}
				inv.getCurrentItem().setItemDamage(inv.getCurrentItem().getItemDamage()+1);					
			}
		}
		
		/*
		 * 
		 * Extracts Leather from Cows
		 * 
		 * */
		if(ev.target instanceof EntityCow && p.isSneaking() && inv.getCurrentItem().getItem() == ModItems.bloodyShears)
		{
			if(((EntityLiving)ev.target).getHealth()>= 10)
			{
				ev.target.attackEntityFrom(bloodyShears, 10);
				if(!w.isRemote)
				{
					w.spawnEntityInWorld(new EntityItem(w, ev.target.posX, ev.target.posY, ev.target.posZ, new ItemStack(Items.leather, 2)));
				}
				inv.getCurrentItem().setItemDamage(inv.getCurrentItem().getItemDamage()+1);				
			}
		}
		
		/*
		 * 
		 * Extracts TNT from Creeper
		 * 
		 * */
		if(ev.target instanceof EntityCreeper && p.isSneaking() && inv.getCurrentItem().getItem() == ModItems.bloodyShears)
		{
			if(((EntityLiving)ev.target).getHealth()>= 10)
			{
				ev.target.attackEntityFrom(bloodyShears, 10);
				if(!w.isRemote)
				{
					w.spawnEntityInWorld(new EntityItem(w, ev.target.posX, ev.target.posY, ev.target.posZ, new ItemStack(Blocks.tnt)));
				}
				inv.getCurrentItem().setItemDamage(inv.getCurrentItem().getItemDamage()+1);				
			}
		}
	}
}
