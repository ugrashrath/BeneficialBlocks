package beneficialblocks.blocks;

import beneficialblocks.setup.CreativeTabBB;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHopperChest extends BlockContainer
{	
	
	public static IIcon topIcon;
	public static IIcon botIcon;
	public static IIcon sideIcon;
	
	public BlockHopperChest()
	{
		super(Material.iron);
		setBlockName("bbHopperChest");
		//setBlockTextureName("beneficialblocks:HopperChest");
		setCreativeTab(CreativeTabBB.BB_TAB);
		setHarvestLevel("pickaxe", 2);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		topIcon = icon.registerIcon("beneficialblocks:HCtop");
		botIcon = icon.registerIcon("beneficialblocks:HCbot");
		sideIcon = icon.registerIcon("beneficialblocks:HCside");
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		switch(side)
		{
			case 0: return botIcon;
			case 1: return topIcon;
			//case 2: case 3 : case 4: case 5: return sideIcon;
			
		}
		return topIcon;
	}
	
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return true;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}

}
