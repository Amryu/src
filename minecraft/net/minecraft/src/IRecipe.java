package net.minecraft.src;

public interface IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCrafting var1, World var2);
    
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCooking var1, World var2);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCrafting var1);
    
    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCookingResult(InventoryCooking var1);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();
    
    int getNeededDishes();
    
    int getSubType();

    ItemStack getRecipeOutput();
}
