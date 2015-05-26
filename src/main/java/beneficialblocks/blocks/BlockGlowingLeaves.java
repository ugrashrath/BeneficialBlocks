package beneficialblocks.blocks;

import java.util.Random;

import beneficialblocks.setup.CreativeTabBB;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGlowingLeaves extends Block
{
	public BlockGlowingLeaves()
	{
		super(Material.leaves);
		setBlockName("bbGlowingLeaves");
		setBlockTextureName("beneficialblocks:GlowingLeaves");
		setCreativeTab(CreativeTabBB.BB_TAB);
		setStepSound(soundTypeGlass);
		setLightLevel(1.0F);
		setHardness(0.3F);
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public int quantityDropped(Random r)
    {
        return 4;
    }

    public Item getItemDropped(int i1, Random r, int i2)
    {
        return Items.glowstone_dust;
    }
}