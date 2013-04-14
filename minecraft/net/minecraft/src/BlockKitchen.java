package net.minecraft.src;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;

public class BlockKitchen extends Block
{
    private Icon field_94385_a;
	private Icon field_94384_b;

	protected BlockKitchen(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? this.field_94385_a : (par1 == 0 ? Block.planks.getBlockTextureFromSide(par1) : (par1 != 2 && par1 != 4 ? this.blockIcon : this.field_94384_b));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("workbench_side");
        this.field_94385_a = par1IconRegister.registerIcon("workbench_top");
        this.field_94384_b = par1IconRegister.registerIcon("workbench_front");
    }
    
    /**
     * Called whenever the block is removed.
     */
    public void onBlockRemoval(World world, int i, int j, int k)
    {
    	String separator = System.getProperty("file.separator");
		File file = new File(Minecraft.getMinecraftDir()+separator+"mods"+separator+"facepunch"+separator+Minecraft.getMinecraft().theWorld.worldInfo.getWorldName()+"!"+i+"!"+j+"!"+k+".dat");
		DataInputStream is = null;
		
		if(file.exists()) {
	    	String dishData = "";
	    	try
	    	{
				is = new DataInputStream(new FileInputStream(file));
				dishData = is.readLine();
				is.close();
			}
	    	catch (FileNotFoundException e) {e.printStackTrace();}
	    	catch (IOException e) {e.printStackTrace();}
	    	
	    	dishData = dishData.replace("@0", "");
	    	String[] dishText = dishData.split("/");
	    	ItemStack[] dishes = new ItemStack[3];
	    	
	        
	        for(int a = 0; a < 3; a++) {
	        	if(!dishText[a].equals("null")) {
	        		try {
		        		int num = new Integer(dishText[a].split("x")[0]);
		        		Item id = null;
		        		if(dishText[a].split("x")[1].equals("item.bowl")) id = Item.bowlEmpty;
		        		if(dishText[a].split("x")[1].equals("item.silverBowl")) id = Item.silverBowl;
		        		if(dishText[a].split("x")[1].equals("item.goldenBowl")) id = Item.goldenBowl;
		        		if(dishText[a].split("x")[1].equals("item.woodenPlate")) id = Item.woodenPlate;
		        		if(dishText[a].split("x")[1].equals("item.silverPlate")) id = Item.silverPlate;
		        		if(dishText[a].split("x")[1].equals("item.goldenPlate")) id = Item.goldenPlate;
		        		if(dishText[a].split("x")[1].equals("item.glass")) id = Item.glass;
		        		EntityItem entityitem = new EntityItem(world, i, j, k, new ItemStack(id, num));
		        		Random rand = new Random();
		                float f = 0.1F;

		                float f2 = rand.nextFloat() * 0.5F;
		                float f4 = rand.nextFloat() * (float)Math.PI * 2.0F;
		                entityitem.motionX = -MathHelper.sin(f4) * f2;
		                entityitem.motionZ = MathHelper.cos(f4) * f2;
		                entityitem.motionY = 0.20000000298023224D;
		                
		                if(world.blockExists(i, j, k)) {
		                	Minecraft.getMinecraft().thePlayer.joinEntityItemWithWorld(entityitem);
		                	Minecraft.getMinecraft().thePlayer.addStat(StatList.dropStat, 1);
		                }
	        		} catch (NumberFormatException e) {}
	        	}
	        }
			file.delete();
		}
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            par5EntityPlayer.displayGUIKitchen(par2, par3, par4);
            return true;
        }
    }
}
