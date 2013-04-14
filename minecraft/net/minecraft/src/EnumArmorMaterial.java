package net.minecraft.src;

public enum EnumArmorMaterial
{

    CLOTH(5, new int[] {
        1, 3, 2, 1
    }, 15,
    0,0,0,0,0,0, 0,0),
    CHAIN(15, new int[] {
        2, 5, 4, 1
    }, 12,
    0,0,0,0,0,0, 0,0),
    CIN(7, new int[] {
    	1, 4, 3, 1
    }, 8,
    0,0,0,0,0,0, 0,0),
    COPPER(10, new int[] {
        2, 4, 3, 2
    }, 10,
    0,0,0,0,0,0, 0,0),
    BRONZE(13, new int[] {
        2, 5, 4, 2
    }, 15,
    0,0,0,0,0,0, 0,0),
    IRON(15, new int[] {
        2, 6, 5, 2
    }, 9,
    0,0,0,0,0,0, 0,0),
    SILVER(25, new int[] {
        3, 7, 5, 3
    }, 15,
    0,0,0,0,0,0, 0,0),
    GOLD(7, new int[] {
        2, 5, 3, 1
    }, 25,
    0,0,0,0,0,0, 0,0),
    DIAMOND(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,0,0,5,0, 0,0),
    COBALT(35, new int[] {
        4, 8, 6, 4
    }, 15,
    0,0,0,0,0,0, 0,0),
    MITHRIL(42, new int[] {
        5, 9, 8, 5
    }, 30,
    1,1,1,1,1,1, 1,1),
    ADAMANTITE(50, new int[] {
        6, 9, 9, 6
    }, 15,
    2,2,2,2,2,2, 2,2),
    ORICHALCUM(65, new int[] {
        8, 11, 11, 7
    }, 25,
    3,3,3,3,3,3, 3,3),
    ELECTRUM(75, new int[] {
        8, 12, 12, 8
    }, 25,
    5,5,5,5,5,5, 5,5),
    
    EMERALD(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,-5,5,0,0, 0,0),
    RUBY(33, new int[] {
        3, 8, 6, 3
    }, 10,
    5,-5,0,0,0,0, 0,0),
    SAPHIR(33, new int[] {
        3, 8, 6, 3
    }, 10,
    -5,5,0,0,0,0, 0,0),
    TOPAS(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,5,-5,0,0, 0,0),
    ONYX(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,0,0,0,0, 5,-5),
    AMETHYST(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,0,0,-5,5, 0,0),
    ACHAT(33, new int[] {
        3, 8, 6, 3
    }, 10,
    0,0,0,0,0,0, -5,5);

    /**
     * Holds the maximum damage factor (each piece multiply this by it's own value) of the material, this is the item
     * damage (how much can absorb before breaks)
     */
    private int maxDamageFactor;

    /**
     * Holds the damage reduction (each 1 points is half a shield on gui) of each piece of armor (helmet, plate, legs
     * and boots)
     */
    private int[] damageReductionAmountArray;

    private int fire;
    private int water;
    private int earth;
    private int wind;
    private int light;
    private int dark;

    private int thunder;
    private int ice;

    /** Return the enchantability factor of the material */
    private int enchantability;

    private EnumArmorMaterial(int par3, int[] par4ArrayOfInteger, int par5, int fire, int water, int earth, int wind, int light, int dark, int thunder, int ice)
    {
        this.maxDamageFactor = par3;
        this.damageReductionAmountArray = par4ArrayOfInteger;
        this.enchantability = par5;

        this.fire = fire;
        this.water = water;
        this.earth = earth;
        this.wind = wind;
        this.light = light;
        this.dark = dark;
        
        this.thunder = thunder;
        this.ice = ice;
    }

    /**
     * Returns the durability for a armor slot of for this type.
     */
    public int getDurability(int par1)
    {
        return ItemArmor.getMaxDamageArray()[par1] * this.maxDamageFactor;
    }

    /**
     * Return the damage reduction (each 1 point is a half a shield on gui) of the piece index passed (0 = helmet, 1 =
     * plate, 2 = legs and 3 = boots)
     */
    public int getDamageReductionAmount(int par1)
    {
        return this.damageReductionAmountArray[par1];
    }

    /**
     * Return the enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return this.enchantability;
    }

    /**
     * Return the crafting material for this armor material, used to determine the item that can be used to repair an
     * armor piece with an anvil
     */
    public int getArmorCraftingMaterial()
    {
    		 if(this == CLOTH) 		return Item.leather.itemID;
    	else if(this == CHAIN) 		return Item.ingotIron.itemID;
    	else if(this == CIN) 		return Item.ingotCin.itemID;
    	else if(this == COPPER) 	return Item.ingotCopper.itemID;
    	else if(this == BRONZE) 	return Item.ingotBronze.itemID;
    	else if(this == IRON) 		return Item.ingotIron.itemID;
    	else if(this == SILVER) 	return Item.ingotSilver.itemID;
    	else if(this == GOLD) 		return Item.ingotGold.itemID;
    	else if(this == DIAMOND) 	return Item.diamond.itemID;
    	else if(this == COBALT) 	return Item.ingotCobalt.itemID;
    	else if(this == MITHRIL) 	return Item.ingotMithril.itemID;
    	else if(this == ADAMANTITE) return Item.ingotAdamantite.itemID;
    	else if(this == ORICHALCUM) return Item.ingotOrichalcum.itemID;
    	else if(this == ELECTRUM) 	return Item.ingotElectrum.itemID;
    	else if(this == EMERALD) 	return Item.emerald.itemID;
    	else if(this == RUBY) 		return Item.ruby.itemID;
    	else if(this == SAPHIR) 	return Item.saphir.itemID;
    	else if(this == TOPAS) 		return Item.topas.itemID;
    	else if(this == AMETHYST) 	return Item.amethyst.itemID;
    	else if(this == ONYX) 		return Item.onyx.itemID;
    	else if(this == ACHAT) 		return Item.achat.itemID;
        
    	return 0;
    }
    
    public int getFireResistance() {
    	return fire;
    }
    
    public int getWaterResistance() {
    	return water;
    }
    
    public int getEarthResistance() {
    	return earth;
    }
    
    public int getWindResistance() {
    	return wind;
    }
    
    public int getLightResistance() {
    	return light;
    }
    
    public int getDarkResistance() {
    	return dark;
    }

    
    public int getThunderResistance() {
    	return thunder;
    }
    
    public int getIceResistance() {
    	return ice;
    }
}
