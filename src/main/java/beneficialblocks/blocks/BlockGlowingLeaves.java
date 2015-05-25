package beneficialblocks.blocks;

import beneficialblocks.setup.CreativeTabBB;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGlowingLeaves extends Block
{
	public BlockGlowingLeaves()
	{
		super(Material.leaves);
		setBlockName("bbGlowingLeaves");
		setBlockTextureName("beneficialblocks:GlowingLeaves");
		setCreativeTab(CreativeTabBB.BB_TAB);
		setStepSound(soundTypeGlass);
		this.setLightLevel(1.0F);
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }
}