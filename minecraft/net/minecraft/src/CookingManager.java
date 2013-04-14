package net.minecraft.src;

import java.io.PrintStream;
import java.util.*;

public class CookingManager
{
    /** The static instance of this class */
    private static final CookingManager instance = new CookingManager();

    /** A list of all the recipes added */
    private List recipes;

    /**
     * Returns the static instance of this class
     */
    public static final CookingManager getInstance()
    {
        return instance;
    }

    private CookingManager()
    {
        setRecipes(new ArrayList());
        // Kuchen
        addRecipe(new ItemStack(Item.cake, 1), new Object[]
                {
                    "AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E',
                    Item.egg
                }, -1, 0);
        
        // Kürbissuppe
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupWooden, 1), new Object[]
                {
                    Item.bucketWater, Block.pumpkin
                }, 0, 0);
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupWooden, 1), new Object[]
                {
        			Item.woodenBucketWater, Block.pumpkin
                }, 0, 0);
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupSilver, 1), new Object[]
                {
                    Item.bucketWater, Block.pumpkin
                }, 0, 1);
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupSilver, 1), new Object[]
                {
        			Item.woodenBucketWater, Block.pumpkin
                }, 0, 1);
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupGolden, 1), new Object[]
                {
                    Item.bucketWater, Block.pumpkin
                }, 0, 2);
        addShapelessRecipe(new ItemStack(Item.pumpkinSoupGolden, 1), new Object[]
                {
        			Item.woodenBucketWater, Block.pumpkin
                }, 0, 2);
        
        // Pilzeintopf
        addShapelessRecipe(new ItemStack(Item.bowlSoup), new Object[]
                {
                    Block.mushroomBrown, Block.mushroomRed
                }, 0, 0);
        addShapelessRecipe(new ItemStack(Item.mushroomStewSilver), new Object[]
                {
                    Block.mushroomBrown, Block.mushroomRed
                }, 0, 1);
        addShapelessRecipe(new ItemStack(Item.mushroomStewGolden), new Object[]
                {
                    Block.mushroomBrown, Block.mushroomRed
                }, 0, 2);
        
        // Kekse
        addShapelessRecipe(new ItemStack(Item.cookie, 8), new Object[]
                {
                    new ItemStack(Item.dyePowder, 1, 3), Item.wheat
                }, -1, 0);
        
        Collections.sort(getRecipes(), new RecipeSorter(this));
        System.out.println((new StringBuilder()).append(getRecipes().size()).append(" cooking recipes").toString());
    }

    /**
     * Adds a recipe. See spreadsheet on first page for details.
     * @param subType 
     */
    void addRecipe(ItemStack par1ItemStack, Object par2ArrayOfObj[], int neededDish, int subType)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (par2ArrayOfObj[i] instanceof String[])
        {
            String as[] = (String[])par2ArrayOfObj[i++];

            for (int l = 0; l < as.length; l++)
            {
                String s2 = as[l];
                k++;
                j = s2.length();
                s = (new StringBuilder()).append(s).append(s2).toString();
            }
        }
        else
        {
            while (par2ArrayOfObj[i] instanceof String)
            {
                String s1 = (String)par2ArrayOfObj[i++];
                k++;
                j = s1.length();
                s = (new StringBuilder()).append(s).append(s1).toString();
            }
        }

        HashMap hashmap = new HashMap();

        for (; i < par2ArrayOfObj.length; i += 2)
        {
            Character character = (Character)par2ArrayOfObj[i];
            ItemStack itemstack = null;

            if (par2ArrayOfObj[i + 1] instanceof Item)
            {
                itemstack = new ItemStack((Item)par2ArrayOfObj[i + 1]);
            }
            else if (par2ArrayOfObj[i + 1] instanceof Block)
            {
                itemstack = new ItemStack((Block)par2ArrayOfObj[i + 1], 1, -1);
            }
            else if (par2ArrayOfObj[i + 1] instanceof ItemStack)
            {
                itemstack = (ItemStack)par2ArrayOfObj[i + 1];
            }

            hashmap.put(character, itemstack);
        }

        ItemStack aitemstack[] = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; i1++)
        {
            char c = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        getRecipes().add(new ShapedRecipes(j, k, aitemstack, par1ItemStack, neededDish, subType));
    }

    void addShapelessRecipe(ItemStack par1ItemStack, Object par2ArrayOfObj[], int neededDishes, int subType)
    {
        ArrayList arraylist = new ArrayList();
        Object aobj[] = par2ArrayOfObj;
        int i = aobj.length;

        for (int j = 0; j < i; j++)
        {
            Object obj = aobj[j];

            if (obj instanceof ItemStack)
            {
                arraylist.add(((ItemStack)obj).copy());
                continue;
            }

            if (obj instanceof Item)
            {
                arraylist.add(new ItemStack((Item)obj));
                continue;
            }

            if (obj instanceof Block)
            {
                arraylist.add(new ItemStack((Block)obj));
            }
            else
            {
                throw new RuntimeException("Invalid shapeless recipy!");
            }
        }

        getRecipes().add(new ShapelessRecipes(par1ItemStack, arraylist, neededDishes, subType));
    }

    public Object[] findMatchingRecipe(InventoryCooking par1InventoryCooking, InventoryDishes par2InventoryDishes, World par2World)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;

        for (int j = 0; j < par1InventoryCooking.getSizeInventory(); j++)
        {
            ItemStack itemstack2 = par1InventoryCooking.getStackInSlot(j);

            if (itemstack2 == null)
            {
                continue;
            }

            if (i == 0)
            {
                itemstack = itemstack2;
            }

            if (i == 1)
            {
                itemstack1 = itemstack2;
            }

            i++;
        }

        if (i == 2 && itemstack.itemID == itemstack1.itemID && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && Item.itemsList[itemstack.itemID].isDamageable())
        {
            Item item = Item.itemsList[itemstack.itemID];
            int l = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
            int i1 = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
            int j1 = l + i1 + (item.getMaxDamage() * 10) / 100;
            int k1 = item.getMaxDamage() - j1;

            if (k1 < 0)
            {
                k1 = 0;
            }

            return new Object[] {new ItemStack(itemstack.itemID, 1, k1), -1};
        }

        for (int k = 0; k < getRecipes().size(); k++)
        {
            IRecipe irecipe = (IRecipe)getRecipes().get(k);

            if (irecipe.matches(par1InventoryCooking, par2World))
            {
            	try
            	{
            		int dishType = 0;
                    if(par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.bowlEmpty ||
                       par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.woodenPlate ||
                       par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.glass) dishType = 0;

                    if(par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.silverBowl ||
                       par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.silverPlate) dishType = 1;
                    
                    if(par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.goldenBowl ||
                       par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).getItem() == Item.goldenPlate) dishType = 2;
                    		
            		if (par2InventoryDishes.getStackInSlot(irecipe.getNeededDishes()).stackSize > 0 && irecipe.getSubType() == dishType)
            		{
            			return new Object[] {irecipe.getCookingResult(par1InventoryCooking), irecipe.getNeededDishes()};
            		}
            	}
            	catch (ArrayIndexOutOfBoundsException e)
            	{
            		return new Object[] {irecipe.getCookingResult(par1InventoryCooking), irecipe.getNeededDishes()};
            	}
            	catch (NullPointerException e)
            	{
            		return null;
            	}
            }
        }

        return null;
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return getRecipes();
    }

	public List getRecipes() {
		return recipes;
	}

	public void setRecipes(List recipes) {
		this.recipes = recipes;
	}
}
