package net.minecraft.src;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;

public class InventoryDishes implements IInventory
{
    /** Inventory object corresponding to double chest lower part */
    private ItemStack[] dishes;
    
    private ContainerKitchen parent;

    public InventoryDishes(ContainerKitchen parent, ItemStack[] dishes)
    {
    	this.parent = parent;
        this.dishes = dishes;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 3;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.dishes";
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return dishes[par1];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
    	if(dishes[par1] != null)
    	{
            if (dishes[par1].stackSize <= par2)
            {
                ItemStack itemstack = dishes[par1];
                dishes[par1] = null;
                return itemstack;
            }

            ItemStack itemstack1 = dishes[par1].splitStack(par2);

            if (dishes[par1].stackSize == 0)
            {
            	dishes[par1] = null;
            }

            return itemstack1;
    	}
    	else
    	{
    		return null;
    	}
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (par1 < dishes.length)
        {
            return dishes[par1];
        }
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        if (par1 < dishes.length)
        {
            dishes[par1] = par2ItemStack;
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {			
    	String save = dishes[0]+"/"+dishes[1]+"/"+dishes[2];
    	String separator = System.getProperty("file.separator");
    	String worldname = Minecraft.getMinecraft().theWorld.getWorldInfo().getWorldName();
    	File saveFile = new File(Minecraft.getMinecraft().getMinecraftDir()+separator+"mods"+separator+"facepunch"+separator+worldname+"!"+parent.posX+"!"+parent.posY+"!"+parent.posZ+".dat");
    	try
    	{
			DataOutputStream os = new DataOutputStream(new FileOutputStream(saveFile));
			os.write(save.getBytes());
			os.close();
		}
    	catch (FileNotFoundException e) {}
    	catch (IOException e) {}
    	
    	parent.onCookMatrixChanged(parent.cookMatrix);
    }
    
    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    public void openChest() {}

    public void closeChest() {}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}
}
