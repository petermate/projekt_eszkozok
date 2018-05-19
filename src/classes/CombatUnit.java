package classes;

/**
 * The full representation of a combat unit, their daily update and one-time
 * initialization processes. Any instance of this class has to be fully ready
 * for in-combat calculations.
 */
public class CombatUnit {

    int strength;
    double morale, maxMorale, discipline, tactics, fireMod, shockMod, flankingRange,
            offensiveBonusShock, offensiveBonusFire, defensiveBonusShock,
            defensiveBonusFire, combatAbility;
    boolean isMercenary;
    UnitType type;
    String militaryGroup;
    Leader leader;
    int offensiveFire,
            defensiveFire,
            offensiveShock,
            defensiveShock,
            offensiveMorale,
            defensiveMorale,
            coveringFire,
            coveringShock,
            coveringMorale;

    /**
     * @param im any parameters from the user interface
     * @param isMercenary as specified in the UI as well; there can be both merc
     * and non-merc units of the same unit type
     * @param leader the General leading the Army containing this unit
     */
    public CombatUnit(Unit u, TechnologyModifier tm, InputModifier im, Leader leader,
            boolean isMercenary) {
        coveringFire = 0;
        coveringShock = 0;
        coveringMorale = 0;
        strength = 1000;
        morale = tm.getMorale() + im.getMorale();
        maxMorale = morale;
        militaryGroup = u.getUnitType();
        this.leader = leader;
        this.isMercenary = isMercenary;
        discipline = im.getDiscipline();
        if (isMercenary) {
            discipline += im.getMercenaryDiscipline();
        }
        tactics = tm.getTactics() * discipline;
        type = u.getType();
        if (type.equals(UnitType.INFANTRY)) {
            fireMod = tm.infantryFire;
            shockMod = tm.infantryShock;
            combatAbility = im.getInfantryCombatAbility();
        } else if (type.equals(UnitType.CAVALRY)) {
            fireMod = tm.cavalryFire;
            shockMod = tm.cavalryShock;
            combatAbility = im.getCavalryCombatAbility();
        } else if (type.equals(UnitType.ARTILLERY)) {
            fireMod = tm.artilleryFire;
            shockMod = tm.artilleryShock;
            combatAbility = im.getArtilleryCombatAbility();
            tactics *= 0.5; //artillery takes double damage
        }
        flankingRange = u.getManeuver() * (1 + tm.getFlankingRange() + im.getFlankingRange());
        offensiveBonusShock = im.getOffensiveBonusShock();
        offensiveBonusFire = im.getOffensiveBonusFire();
        defensiveBonusShock = im.getDefensiveBonusShock();
        defensiveBonusFire = im.getDefensiveBonusFire();
        offensiveFire = u.getOffensiveFire();
        offensiveShock = u.getOffensiveShock();
        defensiveFire = u.getDefensiveFire();
        defensiveShock = u.getDefensiveShock();
        offensiveMorale = u.getOffensiveMorale();
        defensiveMorale = u.getDefensiveMorale();
    }

    public void updateFlankingRange() {;
        if (strength < 250) {
            flankingRange *= 0.25;
        } else if (strength < 500) {
            flankingRange *= 0.5;
        } else if (strength < 750) {
            flankingRange *= 0.75;
        }
    }

    /**
     * Nomadic hordes get a bonus/malus to their shock damage based on whether
     * the battlefield is flat or not
     */
    public void initializeNomadShock(boolean flatTerrain) {
        if (militaryGroup.equals("nomad_group")) {
            if (flatTerrain) {
                offensiveBonusShock += 0.25;
            } else {
                offensiveBonusShock -= 0.25;
            }
        }
    }

    /**
     * Artillery automatically gives half of its defensive pips to a unit they
     * fire behind from (rounded down).
     */
    public void coveringFire(int artilleryDefensiveShock,
            int artilleryDefensiveFire, int artilleryDefensiveMorale) {
        defensiveShock -= coveringShock;
        defensiveFire -= coveringFire;
        defensiveMorale -= coveringMorale;

        coveringShock = (int) (artilleryDefensiveShock / 2);
        coveringFire = (int) (artilleryDefensiveFire / 2);
        coveringMorale = (int) (artilleryDefensiveFire / 2);
        
        defensiveShock += coveringShock;
        defensiveFire += coveringFire;
        defensiveMorale += coveringMorale;
    }

    //getters, setters
    public int getOffensiveFire() {
        return offensiveFire;
    }

    public int getDefensiveFire() {
        return defensiveFire;
    }

    public int getOffensiveShock() {
        return offensiveShock;
    }

    public int getDefensiveShock() {
        return defensiveShock;
    }

    public int getOffensiveMorale() {
        return offensiveMorale;
    }

    public int getDefensiveMorale() {
        return defensiveMorale;
    }

    public int getStrength() {
        return strength;
    }

    public String getMilitaryGroup() {
        return militaryGroup;
    }

    public double getOffensiveBonusFire() {
        return offensiveBonusFire;
    }

    public double getOffensiveBonusShock() {
        return offensiveBonusShock;
    }

    public double getDefensiveBonusFire() {
        return defensiveBonusFire;
    }

    public double getDefensiveBonusShock() {
        return defensiveBonusShock;
    }

    public double getFireMod() {
        return fireMod;
    }

    public double getShockMod() {
        return shockMod;
    }

    public double getCombatAbility() {
        return combatAbility;
    }

    public double getDiscipline() {
        return discipline;
    }

    public double getTactics() {
        return tactics;
    }

    public double getMaxMorale() {
        return maxMorale;
    }

    public double getMorale() {
        return morale;
    }

    public Leader getLeader() {
        return leader;
    }

    public boolean isIsMercenary() {
        return isMercenary;
    }

    public UnitType getType() {
        return type;
    }

    public int getFlankingRange() {
        return (int) Math.round(flankingRange);
    }

    public void setStrength(int strength) {
        this.strength = strength;
        if(this.strength > 1000) this.strength = 1000;
        if(this.strength < 0) this.strength = 0;
    }

    public void setFlankingRange(double flankingRange) {
        this.flankingRange = flankingRange;
    }
    
    public void setMorale(double morale) {
        this.morale = morale;
        if(this.morale<0.0) this.morale = 0.0;
        if(this.morale>maxMorale) this.morale = maxMorale;
    }

    public void setMaxMorale(double maxMorale) {
        this.maxMorale = maxMorale;
    }

    public void setDiscipline(double discipline) {
        this.discipline = discipline;
    }

    public void setTactics(double tactics) {
        this.tactics = tactics;
    }

    public void setFireMod(double fireMod) {
        this.fireMod = fireMod;
    }

    public void setShockMod(double shockMod) {
        this.shockMod = shockMod;
    }

    public void setOffensiveBonusShock(double offensiveBonusShock) {
        this.offensiveBonusShock = offensiveBonusShock;
    }

    public void setOffensiveBonusFire(double offensiveBonusFire) {
        this.offensiveBonusFire = offensiveBonusFire;
    }

    public void setDefensiveBonusShock(double defensiveBonusShock) {
        this.defensiveBonusShock = defensiveBonusShock;
    }

    public void setDefensiveBonusFire(double defensiveBonusFire) {
        this.defensiveBonusFire = defensiveBonusFire;
    }

    public void setCombatAbility(double combatAbility) {
        this.combatAbility = combatAbility;
    }

    public void setIsMercenary(boolean isMercenary) {
        this.isMercenary = isMercenary;
    }

    public void setType(UnitType type) {
        this.type = type;
    }

    public void setOffensiveFire(int offensiveFire) {
        this.offensiveFire = offensiveFire;
    }

    public void setDefensiveFire(int defensiveFire) {
        this.defensiveFire = defensiveFire;
    }

    public void setOffensiveShock(int offensiveShock) {
        this.offensiveShock = offensiveShock;
    }

    public void setDefensiveShock(int defensiveShock) {
        this.defensiveShock = defensiveShock;
    }

    public void setOffensiveMorale(int offensiveMorale) {
        this.offensiveMorale = offensiveMorale;
    }

    public void setDefensiveMorale(int defensiveMorale) {
        this.defensiveMorale = defensiveMorale;
    }

}
