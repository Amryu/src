package net.minecraft.src;

import java.util.Random;

public class ItemWoodenBucket extends Item
{
    /** field for checking if the bucket has been filled. */
    private int isFull;

    public ItemWoodenBucket(int par1, int par2)
    {
        super(par1);
        maxStackSize = 16;
        isFull = par2;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        float f = 1.0F;
        double d = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)f;
        double d1 = (par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)par3EntityPlayer.yOffset;
        double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)f;
        boolean flag = isFull == 0;
        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);

        if (movingobjectposition == null)
        {
            return par1ItemStack;
        }

        if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;

            if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
            {
                return par1ItemStack;
            }

            if (isFull == 0)
            {
                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.getBlockMaterial(i, j, k) == Material.water && par2World.getBlockMetadata(i, j, k) == 0)
                {
                    par2World.setBlockToAir(i, j, k);

                    if (par3EntityPlayer.capabilities.isCreativeMode)
                    {
                        return par1ItemStack;
                    }
                    else
                    {
                    	boolean stackFlag = false;
                    	for(int a = 1; a < 16; a++)
                    	{
                    		if(par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.woodenBucketWater, a))) {stackFlag = true; break;}
                    	}
                    	if(par1ItemStack.stackSize == 1)
                    	{
                        	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketWater.itemID) && stackFlag)
                        	{
                        		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketWater, 1), 1, 46, false);
                        		return null;
                        	}
                        	else
                        	{
                        		return new ItemStack(Item.woodenBucketWater, 1);
                        	}
                    	}
                    	else
                    	{
                        	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketWater.itemID) && stackFlag)
                        	{
                        		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketWater, 1), 1, 46, false);
                        	}
                        	else
                        	{
                        		int freeSlot = -1;
                        		for(int a = 0; a < par3EntityPlayer.inventory.getSizeInventory(); a++)
                        		{
                        			if(par3EntityPlayer.inventory.getStackInSlot(a) == null) {freeSlot = a; break;}
                        		}
                        		if(freeSlot == -1) return par1ItemStack;
                        		par3EntityPlayer.inventory.setInventorySlotContents(freeSlot, new ItemStack(Item.woodenBucketWater, 1));
                        	}
                    		return new ItemStack(par1ItemStack.getItem(), par1ItemStack.stackSize-1);
                    	}
                    }
                }
            }
            else
            {
                if (isFull < 0)
                {
                    return new ItemStack(Item.woodenBucketEmpty);
                }

                if (movingobjectposition.sideHit == 0)
                {
                    j--;
                }

                if (movingobjectposition.sideHit == 1)
                {
                    j++;
                }

                if (movingobjectposition.sideHit == 2)
                {
                    k--;
                }

                if (movingobjectposition.sideHit == 3)
                {
                    k++;
                }

                if (movingobjectposition.sideHit == 4)
                {
                    i--;
                }

                if (movingobjectposition.sideHit == 5)
                {
                    i++;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.isAirBlock(i, j, k) || !par2World.getBlockMaterial(i, j, k).isSolid())
                {
                    if (par2World.provider.isHellWorld && isFull == Block.waterMoving.blockID)
                    {
                        par2World.playSoundEffect(d + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (par2World.rand.nextFloat() - par2World.rand.nextFloat()) * 0.8F);

                        for (int l = 0; l < 8; l++)
                        {
                            par2World.spawnParticle("largesmoke", (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                        }
                    }
                    else
                    {
                        par2World.setBlockMetadataWithNotify(i, j, k, isFull, 0);
                    }

                    if (par3EntityPlayer.capabilities.isCreativeMode)
                    {
                        return par1ItemStack;
                    }
                    else
                    {
                    	boolean stackFlag = false;
                    	for(int a = 1; a < 16; a++)
                    	{
                    		if(par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.woodenBucketEmpty, a))) {stackFlag = true; break;}
                    	}
                    	if(par1ItemStack.stackSize == 1)
                    	{
                        	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketEmpty.itemID) && stackFlag)
                        	{
                        		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketEmpty, 1), 1, 46, false);
                        		return null;
                        	}
                        	else
                        	{
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
                        		int freeSlot = -1;
                        		for(int a = 0; a < par3EntityPlayer.inventory.getSizeInventory(); a++)
                        		{
                        			if(par3EntityPlayer.inventory.getStackInSlot(a) == null) {freeSlot = a; break;}
                        		}
                        		if(freeSlot == -1) return par1ItemStack;
                        		par3EntityPlayer.inventory.setInventorySlotContents(freeSlot, new ItemStack(Item.woodenBucketEmpty, 1));
                        	}
                    		return new ItemStack(par1ItemStack.getItem(), par1ItemStack.stackSize-1);
                    	}
                    }
                }
            }
        }
        else if (isFull == 0 && (movingobjectposition.entityHit instanceof EntityCow))
        {
        	boolean stackFlag = false;
            for(int a = 1; a < 16; a++)
            {
            	if(par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.woodenBucketMilk, a))) {stackFlag = true; break;}
            }
        	if(par1ItemStack.stackSize == 1)
        	{
            	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketMilk.itemID) && stackFlag)
            	{
            		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketMilk, 1), 1, 46, false);
            		return null;
            	}
            	else
            	{
            		return new ItemStack(Item.woodenBucketMilk, 1);
            	}
        	}
        	else
        	{
            	if(par3EntityPlayer.inventory.hasItem(Item.woodenBucketMilk.itemID) && stackFlag)
            	{
            		par3EntityPlayer.inventoryContainer.mergeItemStack(new ItemStack(Item.woodenBucketMilk, 1), 1, 46, false);
            	}
            	else
            	{
            		int freeSlot = -1;
            		for(int a = 0; a < par3EntityPlayer.inventory.getSizeInventory(); a++)
            		{
            			if(par3EntityPlayer.inventory.getStackInSlot(a) == null) {freeSlot = a; break;}
            		}
            		if(freeSlot == -1) return par1ItemStack;
            		par3EntityPlayer.inventory.setInventorySlotContents(freeSlot, new ItemStack(Item.woodenBucketMilk, 1));
            	}
        		return new ItemStack(par1ItemStack.getItem(), par1ItemStack.stackSize-1);
        	}
        }

        return par1ItemStack;
    }
}
