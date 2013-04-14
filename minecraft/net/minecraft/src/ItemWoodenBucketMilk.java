package net.minecraft.src;

public class ItemWoodenBucketMilk extends Item
{
    public ItemWoodenBucketMilk(int par1)
    {
        super(par1);
        setMaxStackSize(16);
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		int freeSlot = -1;

        boolean stackFlag = false;
    	for(int a = 1; a < 16; a++)
    	{
    		if(par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.woodenBucketEmpty, a))) {stackFlag = true; break;}
    	}
    	if(par1ItemStack.stackSize == 1)
    	{
        	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketEmpty.itemID) && stackFlag)
        	{
                par3EntityPlayer.clearActivePotions();
        		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketEmpty, 1), 1, 46, false);
        		return null;
        	}
        	else
        	{
                par3EntityPlayer.clearActivePotions();
        		return new ItemStack(Item.woodenBucketEmpty, 1);
        	}
    	}
    	else
    	{
        	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketEmpty.itemID) && stackFlag)
        	{
        		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketEmpty, 1), 1, 46, false);
        	}
        	else
        	{
        		for(int a = 0; a < par3EntityPlayer.inventory.getSizeInventory(); a++)
        		{
                    par3EntityPlayer.clearActivePotions();
        			if(par3EntityPlayer.inventory.getStackInSlot(a) == null) {freeSlot = a; break;}
        		}
        		if(freeSlot == -1) return par1ItemStack;
                if (!par2World.isRemote && freeSlot != -1)
                {
                    par3EntityPlayer.clearActivePotions();
                }
        		par3EntityPlayer.inventory.setInventorySlotContents(freeSlot, new ItemStack(Item.woodenBucketEmpty, 1));
        	}
    		return new ItemStack(par1ItemStack.getItem(), par1ItemStack.stackSize-1);
    	}
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
