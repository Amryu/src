package net.minecraft.src;

public enum EnumToolMaterial
{
	WOOD(0, 59, 2.0F, 0, 15, 		 0,0,0,0,0,0, 0,0),
	STONE(1, 131, 4F, 1, 5, 		 0,0,0,0,0,0, 0,0),
 	CIN(1, 176, 3F, 1, 8,            0,0,0,0,0,0, 0,0),
 	COPPER(1, 192, 3F, 1, 10, 		 0,0,0,0,0,0, 0,0),
 	BRONZE(2, 228, 3F, 2, 15, 		 0,0,0,0,0,0, 0,0),
	IRON(2, 250, 6F, 2, 14, 		 0,0,0,0,0,0, 0,0),
 	SILVER(2, 670, 3F, 3, 15, 		 0,0,0,0,0,0, 0,0),
	DIAMOND(3, 1561, 8F, 3, 10, 	 0,0,0,0,5,0, 0,0),
	GOLD(0, 32, 12F, 0, 22,          0,0,0,0,0,0, 0,0),
 	COBALT(4, 2100, 8F, 3, 15, 		 0,0,0,0,0,0, 0,0),
 	MITHRIL(4, 3400, 11F, 4, 30, 	 1,1,1,1,1,1, 1,1),
 	ADAMANTITE(5, 5000, 15F, 5, 15,  2,2,2,2,2,2, 2,2),
 	ORICHALCUM(6, 12000, 20F, 6, 25, 3,3,3,3,3,3, 3,3),
 	ELECTRUM(6, 16000, 20F, 6, 25, 	 5,5,5,5,5,5, 5,5),
 	
 	EMERALD(3, 1000, 8F, 3, 10,  0,0,0,10,0,0, 0,0),
 	RUBY(3, 1000, 8F, 3, 10, 	 10,0,0,0,0,0, 0,0),
 	SAPHIR(3, 1000, 8F, 3, 10, 	 0,10,0,0,0,0, 0,0),
 	TOPAS(3, 1000, 8F, 3, 10, 	 0,0,10,0,0,0, 0,0),
 	ONYX(3, 1000, 8F, 3, 10, 	 0,0,0,0,0,0, 10,0),
 	AMETHYST(3, 1000, 8F, 3, 10, 0,0,0,0,0,10, 0,0),
 	ACHAT(3, 1000, 8F, 3, 10, 	 0,0,0,0,0,0, 0,10);
 	
    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    private final int harvestLevel;

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    private final int maxUses;

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    private final float efficiencyOnProperMaterial;

    /** Damage versus entities. */
    private final int damageVsEntity;

    /** Defines the natural enchantability factor of the material. */
    private final int enchantability;
    
    private int fire;
    private int water;
    private int earth;
    private int wind;
    private int light;
    private int dark;

    private int thunder;
    private int ice;

    private EnumToolMaterial(int par3, int par4, float par5, int par6, int par7, int fire, int water, int earth, int wind, int light, int dark, int thunder, int ice)
    {
        harvestLevel = par3;
        maxUses = par4;
        efficiencyOnProperMaterial = par5;
        damageVsEntity = par6;
        enchantability = par7;
        
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
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    public int getMaxUses()
    {
        return this.maxUses;
    }

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    public float getEfficiencyOnProperMaterial()
    {
        return this.efficiencyOnProperMaterial;
    }

    /**
     * Damage versus entities.
     */
    public int getDamageVsEntity()
    {
        return this.damageVsEntity;
    }

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    /**
     * Return the natural enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return this.enchantability;
    }

    /**
     * Return the crafting material for this tool material, used to determine the item that can be used to repair a tool
     * with an anvil
     */
    public int getToolCraftingMaterial()
    {
             if(this == WOOD) 		return Block.planks.blockID;
        else if(this == STONE) 		return Item.ingotCopper.itemID;
        else if(this == CIN) 		return Item.ingotCin.itemID;
        else if(this == COPPER) 	return Item.ingotCopper.itemID;
        else if(this == BRONZE) 	return Item.ingotBronze.itemID;
        else if(this == IRON) 		return Item.ingotIron.itemID;
        else if(this == SILVER) 	return Item.ingotSilver.itemID;
        else if(this == DIAMOND) 	return Item.diamond.itemID;
        else if(this == GOLD) 		return Item.ingotGold.itemID;
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
    
    public int getFireAttack() {
    	return fire;
    }
    
    public int getWaterAttack() {
    	return water;
    }
    
    public int getEarthAttack() {
    	return earth;
    }
    
    public int getWindAttack() {
    	return wind;
    }
    
    public int getLightAttack() {
    	return light;
    }
    
    public int getDarkAttack() {
    	return dark;
    }

    
    public int getThunderAttack() {
    	return thunder;
    }
    
    public int getIceAttack() {
    	return ice;
    }
}
