package net.minecraft.src;

public enum EnumEquipType {
	;
	
	private int[] attack = {
			0,			// normal
			0,			// Feuer
			0,			// Wasser
			0,			// Erde
			0,			// Luft
			0,			// Licht
			0,			// Finsternis
			0,			// Blitz
			0,			// Eis
	};
	private int[] defense = {
			0,			// normal
			0,			// Feuer
			0,			// Wasser
			0,			// Erde
			0,			// Luft
			0,			// Licht
			0,			// Finsternis
			0,			// Blitz
			0,			// Eis
	};

    /** Return the enchantability factor of the material */
    private int enchantability;
    
    /** Wie sollen die Elementdaten verarbeitet werden: 0 = Ganzzahlen | 1 = Prozentwerte */
    private int mode;

    private  EnumEquipType(int enchant, int mode,
    		int attack_normal, int attack_fire, int attack_water, int attack_earth, int attack_wind, int attack_light, int attack_dark, int attack_thunder, int attack_ice,
    		int defense_normal, int defense_fire, int defense_water, int defense_earth, int defense_wind, int defense_light, int defense_dark, int defense_thunder, int defense_ice)
    {
        enchantability = enchant;
        this.setMode(mode);
        
        // Angriff
        attack[0] = attack_normal;

        attack[1] = attack_fire;
        attack[2] = attack_water;
        attack[3] = attack_earth;
        attack[4] = attack_wind;
        attack[5] = attack_light;
        attack[6] = attack_dark;

        attack[7] = attack_thunder;
        attack[8] = attack_ice;
        
        // Abwehr
        defense[0] = defense_normal;

        defense[1] = defense_fire;
        defense[2] = defense_water;
        defense[3] = defense_earth;
        defense[4] = defense_wind;
        defense[5] = defense_light;
        defense[6] = defense_dark;

        defense[7] = defense_thunder;
        defense[8] = defense_ice;
    }
    
	public int getDamageReductionAmount(int par4) {
		return 0;
	}

    /**
     * Return the enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return enchantability;
    }
    
    public int getNormalOffensive() {
    	return attack[0];
    }
    public int getFireOffensive() {
    	return attack[1];
    }
    public int getWaterOffensive() {
    	return attack[2];
    }
    public int getEarthOffensive() {
    	return attack[3];
    }
    public int getWindOffensive() {
    	return attack[4];
    }
    public int getLightOffensive() {
    	return attack[5];
    }
    public int getDarkOffensive() {
    	return attack[6];
    }
    public int getThunderOffensive() {
    	return attack[7];
    }
    public int getIceOffensive() {
    	return attack[8];
    }
    
    public int getNormalResistance() {
    	return defense[0];
    }
    public int getFireResistance() {
    	return defense[1];
    }
    public int getWaterResistance() {
    	return defense[2];
    }
    public int getEarthResistance() {
    	return defense[3];
    }
    public int getWindResistance() {
    	return defense[4];
    }
    public int getLightResistance() {
    	return defense[5];
    }
    public int getDarkResistance() {
    	return defense[6];
    }
    public int getThunderResistance() {
    	return defense[7];
    }
    public int getIceResistance() {
    	return defense[8];
    }

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
}
