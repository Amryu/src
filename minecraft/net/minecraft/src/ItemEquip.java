package net.minecraft.src;

public class ItemEquip extends Item
{
//    private static final int maxDamageArray[] =
//    {
//        11, 16, 15, 13
//    };

    /**
     * Stores the equip type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public final int equipType;

    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;

    /** The EnumArmorMaterial used for this ItemArmor */
    private final EnumEquipType material;

    public ItemEquip(int par1, EnumEquipType par2EnumEquip, int par3, int par4)
    {
        super(par1);
        material = par2EnumEquip;
        equipType = par4;
        renderIndex = par3;
        damageReduceAmount = par2EnumEquip.getDamageReductionAmount(par4);
//        setMaxDamage(par2EnumEquipType.getDurability(par4));
        maxStackSize = 1;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return material.getEnchantability();
    }

    /**
     * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
     * multiplied by armor material factor)
     */
//    static int[] getMaxDamageArray()
//    {
//        return maxDamageArray;
//    }
    
    public int getFireAttack() {
    	return material.getFireOffensive();
    }
    
    public int getWaterAttack() {
    	return material.getWaterOffensive();
    }
    
    public int getEarthAttack() {
    	return material.getEarthOffensive();
    }
    
    public int getWindAttack() {
    	return material.getWindOffensive();
    }
    
    public int getLightAttack() {
    	return material.getLightOffensive();
    }
    
    public int getDarkAttack() {
    	return material.getDarkOffensive();
    }

    
    public int getThunderAttack() {
    	return material.getThunderOffensive();
    }
    
    public int getIceAttack() {
    	return material.getIceOffensive();
    }
    
    ////////////////////////////////////////////////////////////
    
    public int getFireResistance() {
    	return material.getFireResistance();
    }
    
    public int getWaterResistance() {
    	return material.getWaterResistance();
    }
    
    public int getEarthResistance() {
    	return material.getEarthResistance();
    }
    
    public int getWindResistance() {
    	return material.getWindResistance();
    }
    
    public int getLightResistance() {
    	return material.getLightResistance();
    }
    
    public int getDarkResistance() {
    	return material.getDarkResistance();
    }

    
    public int getThunderResistance() {
    	return material.getThunderResistance();
    }
    
    public int getIceResistance() {
    	return material.getIceResistance();
    }
    
    ////////////////////////////////////////////////////////////
    
    public int getMode() {
    	return material.getMode();
    }
}
