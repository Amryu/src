package net.minecraft.src;

public class ShapedRecipes implements IRecipe
{
    /** How many horizontal slots this recipe is wide. */
    private int recipeWidth;

    /** How many vertical slots this recipe uses. */
    private int recipeHeight;

    /** Is a array of ItemStack that composes the recipe. */
    private ItemStack[] recipeItems;

    /** Is the ItemStack that you get when craft the recipe. */
    private ItemStack recipeOutput;

    /** Is the itemID of the output item that you get when craft the recipe. */
    public final int recipeOutputItemID;
    private boolean field_92101_f = false;
    
    private int neededDishes = -1;
    private int subType = 0;

    public ShapedRecipes(int par1, int par2, ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack)
    {
        this.recipeOutputItemID = par4ItemStack.itemID;
        this.recipeWidth = par1;
        this.recipeHeight = par2;
        this.recipeItems = par3ArrayOfItemStack;
        this.recipeOutput = par4ItemStack;
    }
    
    public ShapedRecipes(int par1, int par2, ItemStack par3ArrayOfItemStack[], ItemStack par4ItemStack, int neededDishes, int subType)
    {
        recipeOutputItemID = par4ItemStack.itemID;
        recipeWidth = par1;
        recipeHeight = par2;
        recipeItems = par3ArrayOfItemStack;
        recipeOutput = par4ItemStack;
        this.neededDishes = neededDishes;
        this.subType = subType;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
    {
        for (int var3 = 0; var3 <= 3 - this.recipeWidth; ++var3)
        {
            for (int var4 = 0; var4 <= 3 - this.recipeHeight; ++var4)
            {
                if (this.checkMatch(par1InventoryCrafting, var3, var4, true))
                {
                    return true;
                }

                if (this.checkMatch(par1InventoryCrafting, var3, var4, false))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4)
    {
        for (int var5 = 0; var5 < 3; ++var5)
        {
            for (int var6 = 0; var6 < 3; ++var6)
            {
                int var7 = var5 - par2;
                int var8 = var6 - par3;
                ItemStack var9 = null;

                if (var7 >= 0 && var8 >= 0 && var7 < this.recipeWidth && var8 < this.recipeHeight)
                {
                    if (par4)
                    {
                        var9 = this.recipeItems[this.recipeWidth - var7 - 1 + var8 * this.recipeWidth];
                    }
                    else
                    {
                        var9 = this.recipeItems[var7 + var8 * this.recipeWidth];
                    }
                }

                ItemStack var10 = par1InventoryCrafting.getStackInRowAndColumn(var5, var6);

                if (var10 != null || var9 != null)
                {
                    if (var10 == null && var9 != null || var10 != null && var9 == null)
                    {
                        return false;
                    }

                    if (var9.itemID != var10.itemID)
                    {
                        return false;
                    }

                    if (var9.getItemDamage() != 32767 && var9.getItemDamage() != var10.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /**
     * Checks if the region of a cooking inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCooking par1InventoryCooking, int par2, int par3, boolean par4)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int k = i - par2;
                int l = j - par3;
                ItemStack itemstack = null;

                if (k >= 0 && l >= 0 && k < recipeWidth && l < recipeHeight)
                {
                    if (par4)
                    {
                        itemstack = recipeItems[(recipeWidth - k - 1) + l * recipeWidth];
                    }
                    else
                    {
                        itemstack = recipeItems[k + l * recipeWidth];
                    }
                }

                ItemStack itemstack1 = par1InventoryCooking.getStackInRowAndColumn(i, j);

                if (itemstack1 == null && itemstack == null)
                {
                    continue;
                }

                if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                {
                    return false;
                }

                if (itemstack.itemID != itemstack1.itemID)
                {
                    return false;
                }

                if (itemstack.getItemDamage() != -1 && itemstack.getItemDamage() != itemstack1.getItemDamage())
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
    {
        ItemStack var2 = this.getRecipeOutput().copy();

        if (this.field_92101_f)
        {
            for (int var3 = 0; var3 < par1InventoryCrafting.getSizeInventory(); ++var3)
            {
                ItemStack var4 = par1InventoryCrafting.getStackInSlot(var3);

                if (var4 != null && var4.hasTagCompound())
                {
                    var2.setTagCompound((NBTTagCompound)var4.stackTagCompound.copy());
                }
            }
        }

        return var2;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeWidth * this.recipeHeight;
    }

    public ShapedRecipes func_92100_c()
    {
        this.field_92101_f = true;
        return this;
    }

	public boolean matches(InventoryCooking var1, World var2) {
        for (int i = 0; i <= 3 - recipeWidth; i++)
        {
            for (int j = 0; j <= 3 - recipeHeight; j++)
            {
                if (checkMatch(var1, i, j, true))
                {
                    return true;
                }

                if (checkMatch(var1, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
	}

	public ItemStack getCookingResult(InventoryCooking inventorycooking) {
		return new ItemStack(recipeOutput.itemID, recipeOutput.stackSize, recipeOutput.getItemDamage());
	}

	public int getNeededDishes() {
		return neededDishes;
	}

	public int getSubType() {
		return subType;
	}
}
