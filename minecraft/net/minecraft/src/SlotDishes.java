package net.minecraft.src;

class SlotDishes extends Slot
{
	private int dishType;
	ContainerKitchen cont;
	
    SlotDishes(IInventory par2IInventory, int par3, int par4, int par5, int par6, ContainerKitchen cont)
    {
        super(par2IInventory, par3, par4, par5);
        dishType = par6;
        this.cont = cont;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 64;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
    	switch(dishType) {
    	case 0:
    		return 	par1ItemStack.itemID == Item.bowlEmpty.itemID ||
    				par1ItemStack.itemID == Item.silverBowl.itemID ||
    				par1ItemStack.itemID == Item.goldenBowl.itemID ? true : false;
    	case 1:
    		return 	par1ItemStack.itemID == Item.woodenPlate.itemID ||
    				par1ItemStack.itemID == Item.silverPlate.itemID ||
    				par1ItemStack.itemID == Item.goldenPlate.itemID 	? true : false;
    	case 2:
    		return 	par1ItemStack.itemID == Item.glass.itemID ? true : false;
    	default:
    		return false;
    	}
    }

//    /**
//     * Returns the icon index on items.png that is used as background image of the slot.
//     */
//    public Icon getBackgroundIconIndex()
//    {
//        return par1IconRegister.registerIcon("workbench_side");
//    }
}
