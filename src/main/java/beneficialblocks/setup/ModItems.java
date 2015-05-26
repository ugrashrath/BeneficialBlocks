package beneficialblocks.setup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import beneficialblocks.setup.RegisterHelper;
import beneficialblocks.blocks.BlockGlowingLeaves;
import beneficialblocks.blocks.BlockHopperChest;
import beneficialblocks.entity.TileEntityHopperChest;
import beneficialblocks.items.ItemBloodyShears;
import beneficialblocks.items.ItemGelidProiectum;
import beneficialblocks.items.ItemHose;
import beneficialblocks.items.ItemHoseEnd;
import beneficialblocks.items.ItemHosePipe;
import beneficialblocks.items.ItemHoseReverse;
import beneficialblocks.items.ItemIgnisProiectum;

public class ModItems
{
	//materials
	public static Item hoseEnd;
	public static Item hosePipe;
	
	//blocks
	public static BlockHopperChest hopperChest;
	public static BlockGlowingLeaves glowingLeaves;
	
	//tools
	public static Item hose;
	public static Item hoseReverse;
	public static Item bloodyShears;
	public static Item gelidProiectum;
	public static Item ignisProiectum;
	
	public static void registerBlocks()
	{
		hopperChest = new BlockHopperChest();
		RegisterHelper.registerBlock(hopperChest);
		
		glowingLeaves = new BlockGlowingLeaves();
		RegisterHelper.registerBlock(glowingLeaves);
		
		GameRegistry.registerTileEntity(TileEntityHopperChest.class, Reference.MODID);
	}
	
	public static void registerItems()
	{
		hose = new ItemHose();
		RegisterHelper.RegisterItem(hose);
		
		hoseReverse = new ItemHoseReverse();
		RegisterHelper.RegisterItem(hoseReverse);
		
		hoseEnd = new ItemHoseEnd();
		RegisterHelper.RegisterItem(hoseEnd);
		
		hosePipe = new ItemHosePipe();
		RegisterHelper.RegisterItem(hosePipe);
		
		bloodyShears = new ItemBloodyShears();
		RegisterHelper.RegisterItem(bloodyShears);
		
		gelidProiectum = new ItemGelidProiectum();
		RegisterHelper.RegisterItem(gelidProiectum);
		
		ignisProiectum = new ItemIgnisProiectum();
		RegisterHelper.RegisterItem(ignisProiectum);
	}
}