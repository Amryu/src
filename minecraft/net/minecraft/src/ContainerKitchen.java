package net.minecraft.src;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.minecraft.client.Minecraft;

public class ContainerKitchen extends Container
{
    /** The crafting matrix inventory (3x3). */
    public InventoryCooking cookMatrix;
    public InventoryDishes invDishes;
    public IInventory cookResult;
    private World worldObj;
    public int posX;
    public int posY;
    public int posZ;
    
    public SlotCooking cookSlot;

    public ContainerKitchen(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
    	String separator = System.getProperty("file.separator");
    	String worldname = Minecraft.getMinecraft().theWorld.getWorldInfo().getWorldName();
    	File saveFile = new File(Minecraft.getMinecraft().getMinecraftDir()+separator+"mods"+separator+"facepunch"+separator+worldname+"!"+par3+"!"+par4+"!"+par5+".dat");
    	String dishData = "";
    	ItemStack[] dishes = new ItemStack[3];
    	
    	if(saveFile.exists()) {
	    	try
	    	{
				DataInputStream is = new DataInputStream(new FileInputStream(saveFile));
				dishData = is.readLine();
				is.close();
			}
	    	catch (FileNotFoundException e) {}
	    	catch (IOException e) {}
	    	
	    	dishData = dishData.replace("@0", "");
	    	String[] dishText = dishData.split("/");
	        
	        for(int i = 0; i < 3; i++) {
	        	if(!dishText[i].equals("null")) {
	        		try {
		        		int num = new Integer(dishText[i].split("x")[0]);
		        		Item id = null;
		        		if(dishText[i].split("x")[1].equals("item.bowl")) id = Item.bowlEmpty;
		        		if(dishText[i].split("x")[1].equals("item.silverBowl")) id = Item.silverBowl;
		        		if(dishText[i].split("x")[1].equals("item.goldenBowl")) id = Item.goldenBowl;
		        		if(dishText[i].split("x")[1].equals("item.woodenPlate")) id = Item.woodenPlate;
		        		if(dishText[i].split("x")[1].equals("item.silverPlate")) id = Item.silverPlate;
		        		if(dishText[i].split("x")[1].equals("item.goldenPlate")) id = Item.goldenPlate;
		        		if(dishText[i].split("x")[1].equals("item.glass")) id = Item.glass;
		        		dishes[i] = new ItemStack(id, num);
	        		} catch (NumberFormatException e) {}
	        	}
	        }
    	}
        cookMatrix = new InventoryCooking(this, 3, 3);
        invDishes = new InventoryDishes(this, dishes);
        cookResult = new InventoryCookResult(this);
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        cookSlot = new SlotCooking(par1InventoryPlayer.player, cookMatrix, cookResult, invDishes, 0, 148, 35);
        
        addSlotToContainer(cookSlot);

        for (int i = 0; i < 3; i++)
        {
            for (int l = 0; l < 3; l++)
            {
            	addSlotToContainer(new Slot(cookMatrix, l + i * 3, 8 + l * 18, 17 + i * 18));
            }
        }

        for (int j = 0; j < 3; j++)
        {
            for (int i1 = 0; i1 < 9; i1++)
            {
            	addSlotToContainer(new Slot(par1InventoryPlayer, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));
            }
        }

        for (int k = 0; k < 9; k++)
        {
        	addSlotToContainer(new Slot(par1InventoryPlayer, k, 8 + k * 18, 142));
        }
        
        addSlotToContainer(new SlotDishes(invDishes, 0, 94, 12, 0, this));
        addSlotToContainer(new SlotDishes(invDishes, 1, 94, 35, 1, this));
        addSlotToContainer(new SlotDishes(invDishes, 2, 94, 58, 2, this));

        onCookMatrixChanged(cookMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCookMatrixChanged(IInventory par1IInventory)
    {
    	if(CookingManager.getInstance().findMatchingRecipe(cookMatrix, invDishes, worldObj) != null)
    	{
    		cookResult.setInventorySlotContents(0, (ItemStack)CookingManager.getInstance().findMatchingRecipe(cookMatrix, invDishes, worldObj)[0]);
    	}
    	else
    	{
    		cookResult.setInventorySlotContents(0, null);
    	}
    }
    
    public void removeIngredients()
    {
    	Object[] recipeData = CookingManager.getInstance().findMatchingRecipe(cookMatrix, invDishes, worldObj);
    	
    	try
    	{
	    	if(recipeData[0] != null)
	    	{
	    		try
	    		{
	    			if(new Integer(recipeData[1].toString()) >= 0)
	    			{
	    				invDishes.decrStackSize(new Integer(recipeData[1].toString()), 1);
	    			}
	    		} catch (NullPointerException e) {}
	    	}
    	} catch (NullPointerException e) {}
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onKitchenGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onKitchenGuiClosed(par1EntityPlayer);

        if (!this.worldObj.isRemote)
        {
            for (int var2 = 0; var2 < 9; ++var2)
            {
                ItemStack var3 = this.cookMatrix.getStackInSlotOnClosing(var2);

                if (var3 != null)
                {
                    par1EntityPlayer.dropPlayerItem(var3);
                }
            }
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != Block.kitchen.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(var5, 10, 46, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(var5, 37, 46, false))
                {
                    return null;
                }
            }
            else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(var5, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 10, 46, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return par2Slot.inventory != this.cookResult && super.func_94530_a(par1ItemStack, par2Slot);
    }
}
