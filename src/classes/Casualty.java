package classes;

/**
 * Calculates the caused regular/morale damage for a given combat phase
 */
public class Casualty {
    CombatUnit a, d; //attacker, defender
    int droll, terrainMod; //diceroll, sum of all terrain based bonuses
    Leader la, ld; //general-attacking, general-defending
    public Casualty(CombatUnit a, CombatUnit d, int terrainMod){
        this.a=a;
        this.d=d;
        this.terrainMod=terrainMod;
        la=a.getLeader();
        ld=d.getLeader();
    }
    public int DiceRoll(int droll, String phase){
        int ret = 0;
        if (phase.equals("fire")) {
            ret = droll + la.getFire() + a.getOffensiveFire()
                 - ld.getFire() - d.getDefensiveFire() + terrainMod;
        }
        else if (phase.equals("shock")) {
            ret = droll + la.getShock() + a.getOffensiveShock()
                    -ld.getShock() - d.getDefensiveShock() + terrainMod;
        }
        else if (phase.equals("moralefire")) {
            ret = droll + la.getFire() + a.getOffensiveMorale()
                 - ld.getFire() - d.getDefensiveMorale() + terrainMod;
        }
        else if (phase.equals("moraleshock")) {
            ret = droll + la.getShock() + a.getOffensiveMorale()
                 - ld.getShock() - d.getDefensiveMorale() + terrainMod;
        }
        if (ret<0) return 0;
        return ret;
    }
    public int Casualties(int dresult, String phase){
        double ret = 0.0;
        if (phase.equals("fire")) {
            ret =  (15.0 + 5.0*dresult)*a.getStrength()*a.getFireMod()
                    *(a.getCombatAbility()+1.0)*a.getDiscipline()
                    *(1.0+a.getOffensiveBonusFire()-d.getDefensiveBonusFire())
                    /d.getTactics()/1000.0;
        }
        if (phase.equals("shock")){
            ret =  (15.0 + 5.0*dresult)*a.getStrength()*a.getShockMod()
                    *(a.getCombatAbility()+1.0)*a.getDiscipline()
                    *(1.0+a.getOffensiveBonusShock()-d.getDefensiveBonusShock())
                    /d.getTactics()/1000.0;
        }
        return (int) Math.round(ret);
    }
    public double MoraleDamage(int dresult, String phase){
        return Casualties(dresult, phase)*a.getMaxMorale()/600;
    }
    public int FirePhaseCasualties(int droll){
        return Casualties(DiceRoll(droll,"fire"),"fire");
    }
    public int ShockPhaseCasualties(int droll){
        return Casualties(DiceRoll(droll,"shock"),"shock");
    }
    public double FirePhaseMoraleDamage(int droll){
        return MoraleDamage(DiceRoll(droll,"moralefire"),"fire");
    }
    public double ShockPhaseMoraleDamage(int droll){
        return MoraleDamage(DiceRoll(droll,"moraleshock"),"shock");
    }
}


