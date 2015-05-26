package beneficialblocks.setup;

import javax.swing.Icon;

import beneficialblocks.entity.TileEntityHopperChest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelper
{
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}
	
	public static void RegisterItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
}
