package net.minecraft.src;

class SlotEquip extends Slot
{
    /**
     * The equip type that can be placed on that slot, it uses the same values of equipType field on Itemequip.
     */
    final int equipType;

    /**
     * The parent class of this clot, ContainerPlayer, Slotequip is a Anon inner class.
     */
    final ContainerPlayer parent;

    SlotEquip(ContainerPlayer par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5, int par6)
    {
        super(par2IInventory, par3, par4, par5);
        this.parent = par1ContainerPlayer;
        this.equipType = par6;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of equip slots)
     */
    public int getSlotStackLimit()
    {
        return 1;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the equip slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return par1ItemStack == null ? false : (par1ItemStack.getItem() instanceof ItemEquip ? ((ItemEquip)par1ItemStack.getItem()).equipType == this.equipType : (par1ItemStack.getItem().itemID != Block.pumpkin.blockID && par1ItemStack.getItem().itemID != Item.skull.itemID ? false : this.equipType == 0));
    }

    /**
     * Returns the icon index on items.png that is used as background image of the slot.
     */
    public Icon getBackgroundIconIndex()
    {
        return null;
    }
}
