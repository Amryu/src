package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapelessRecipes implements IRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    /** Is a List of ItemStack that composes the recipe. */
    private final List recipeItems;
    
    public int neededDishes = -1;
    public int subType = 0;

    public ShapelessRecipes(ItemStack par1ItemStack, List par2List)
    {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
    }
    
    public ShapelessRecipes(ItemStack par1ItemStack, List par2List, int neededDishes, int subType)
    {
        recipeOutput = par1ItemStack;
        recipeItems = par2List;
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
        ArrayList var3 = new ArrayList(this.recipeItems);

        for (int var4 = 0; var4 < 3; ++var4)
        {
            for (int var5 = 0; var5 < 3; ++var5)
            {
                ItemStack var6 = par1InventoryCrafting.getStackInRowAndColumn(var5, var4);

                if (var6 != null)
                {
                    boolean var7 = false;
                    Iterator var8 = var3.iterator();

                    while (var8.hasNext())
                    {
                        ItemStack var9 = (ItemStack)var8.next();

                        if (var6.itemID == var9.itemID && (var9.getItemDamage() == 32767 || var6.getItemDamage() == var9.getItemDamage()))
                        {
                            var7 = true;
                            var3.remove(var9);
                            break;
                        }
                    }

                    if (!var7)
                    {
                        return false;
                    }
                }
            }
        }

        return var3.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
    {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

	public boolean matches(InventoryCooking var1, World var2) {
		ArrayList arraylist = new ArrayList(recipeItems);
        int i = 0;

        do
        {
            if (i >= 3)
            {
                break;
            }

            for (int j = 0; j < 3; j++)
            {
                ItemStack itemstack = var1.getStackInRowAndColumn(j, i);

                if (itemstack == null)
                {
                    continue;
                }

                boolean flag = false;
                Iterator iterator = arraylist.iterator();

                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }

                    ItemStack itemstack1 = (ItemStack)iterator.next();

                    if (itemstack.itemID != itemstack1.itemID || itemstack1.getItemDamage() != -1 && itemstack.getItemDamage() != itemstack1.getItemDamage())
                    {
                        continue;
                    }

                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                }
                while (true);

                if (!flag)
                {
                    return false;
                }
            }

            i++;
        }
        while (true);

        return arraylist.isEmpty();
	}

	public ItemStack getCookingResult(InventoryCooking inventorycooking) {
		return recipeOutput.copy();
	}

	public int getNeededDishes() {
		return neededDishes;
	}

	public int getSubType() {
		return subType;
	}
}
