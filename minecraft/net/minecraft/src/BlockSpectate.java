package net.minecraft.src;

import java.util.Random;

public class BlockSpectate extends BlockContainer
{
    public BlockSpectate(int par1, Material par3Material)
    {
        super(par1, par3Material);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    public void onBlockRemoval(World par1World, int par2, int par3, int par4) {

    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    protected boolean func_50074_q()
    {
        return true;
    }

	public TileEntity getBlockEntity()
	{
		return null;
	}

	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
}
