package classes;

/**
 * Any and all parameters from the UI.
 */
public class InputModifier {

    double morale, discipline, mercenaryDiscipline, flankingRange,
        offensiveBonusShock, offensiveBonusFire, defensiveBonusShock,
        defensiveBonusFire, infantryCombatAbility, cavalryCombatAbility,
        artilleryCombatAbility, bonusCavalryToInfRatio;
    
    public InputModifier(double morale, double discipline, double mercenaryDiscipline,
            double flankingRange, double offensiveBonusShock, double offensiveBonusFire,
            double defensiveBonusShock, double defensiveBonusFire,
            double infantryCombatAbility, double cavalryCombatAbility,
            double artilleryCombatAbility, double bonusCavalryToInfRatio) {
        this.morale = morale;
        this.discipline = discipline;
        this.mercenaryDiscipline = mercenaryDiscipline;
        this.flankingRange = flankingRange;
        this.offensiveBonusShock = offensiveBonusShock;
        this.offensiveBonusFire = offensiveBonusFire;
        this.defensiveBonusShock = defensiveBonusShock;
        this.defensiveBonusFire = defensiveBonusFire;
        this.infantryCombatAbility = infantryCombatAbility;
        this.cavalryCombatAbility = cavalryCombatAbility;
        this.artilleryCombatAbility = artilleryCombatAbility;
        this.bonusCavalryToInfRatio = bonusCavalryToInfRatio;
    }
    
    public InputModifier(){
        morale = 0;
        discipline = 0;
        mercenaryDiscipline = 0;
        flankingRange = 0;
        offensiveBonusShock = 0;
        offensiveBonusFire = 0;
        defensiveBonusShock = 0;
        defensiveBonusFire = 0;
        infantryCombatAbility = 0;
        cavalryCombatAbility = 0;
        artilleryCombatAbility = 0;
        bonusCavalryToInfRatio = 0;
    }
    
    public double getMorale() {
        return morale;
    }

    public void setMorale(double morale) {
        this.morale = morale;
    }

    public double getDiscipline() {
        return discipline;
    }

    public void setDiscipline(double discipline) {
        this.discipline = discipline;
    }

    public double getMercenaryDiscipline() {
        return mercenaryDiscipline;
    }

        public void setMercenaryDiscipline(double mercenaryDiscipline) {
        this.mercenaryDiscipline = mercenaryDiscipline;
    }

    public double getFlankingRange() {
        return flankingRange;
    }

    public void setFlankingRange(double flankingRange) {
        this.flankingRange = flankingRange;
    }

    public double getOffensiveBonusShock() {
        return offensiveBonusShock;
    }

    public void setOffensiveBonusShock(double offensiveBonusShock) {
        this.offensiveBonusShock = offensiveBonusShock;
    }

    public double getOffensiveBonusFire() {
        return offensiveBonusFire;
    }

    public void setOffensiveBonusFire(double offensiveBonusFire) {
        this.offensiveBonusFire = offensiveBonusFire;
    }

    public double getDefensiveBonusShock() {
        return defensiveBonusShock;
    }

    public void setDefensiveBonusShock(double defensiveBonusShock) {
        this.defensiveBonusShock = defensiveBonusShock;
    }

    public double getDefensiveBonusFire() {
        return defensiveBonusFire;
    }
    public double getBonusCavalryToInfRatio(){
        return bonusCavalryToInfRatio;
    }
    public void setDefensiveBonusFire(double defensiveBonusFire) {
        this.defensiveBonusFire = defensiveBonusFire;
    }

    public double getInfantryCombatAbility() {
        return infantryCombatAbility;
    }

    public void setInfantryCombatAbility(double infantryCombatAbility) {
        this.infantryCombatAbility = infantryCombatAbility;
    }

    public double getCavalryCombatAbility() {
        return cavalryCombatAbility;
    }

    public void setCavalryCombatAbility(double cavalryCombatAbility) {
        this.cavalryCombatAbility = cavalryCombatAbility;
    }

    public double getArtilleryCombatAbility() {
        return artilleryCombatAbility;
    }

    public void setArtilleryCombatAbility(double artilleryCombatAbility) {
        this.artilleryCombatAbility = artilleryCombatAbility;
    }
   
}
