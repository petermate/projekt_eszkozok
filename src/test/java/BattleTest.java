package test.java;

import classes.Army;
import classes.Battle;
import classes.InputModifier;
import classes.TechnologyModifier;
import classes.Terrain;
import classes.Unit;
import classes.UnitType;
import org.junit.Test;

public class BattleTest {
    Army attacker, defender;
    Terrain terrain;
    
    Unit infantry, cavalry, artillery;
    InputModifier im;
    TechnologyModifier tm, tm2;
    public BattleTest(){
        this.infantry = new Unit("nomad_group", UnitType.INFANTRY, 4, 1, 1, 0, 1, 1, 1);
        this.cavalry = new Unit("nomad_group", UnitType.CAVALRY, 2, 1, 2, 0, 1, 1, 1);
        this.artillery = new Unit("", UnitType.ARTILLERY, 0, 4, 0, 0, 2, 2, 2);
        this.im = new InputModifier();
        tm = new TechnologyModifier();
        tm.setInfantryFire(0.25);
        tm.setInfantryShock(0.2);
        tm.setArtilleryFire(1);
        tm.setCavalryShock(0.8);
        tm.setMorale(2.0);
        tm.setInfantryShock(0.1);
        tm.setInfantryFire(0.1);
        tm.setInfantryShock(0.2);
        tm.setCavalryShock(0.2);
        tm.setCombatWidth(5);
        tm2 = new TechnologyModifier();
        tm2.setInfantryFire(0.25);
        tm2.setInfantryShock(0.2);
        tm2.setCavalryShock(0.8);
        tm2.setArtilleryFire(1);
        tm2.setMorale(2.0);
        tm2.setInfantryShock(0.1);
        tm2.setInfantryFire(0.1);
        tm2.setInfantryShock(0.2);
        tm2.setCavalryShock(0.2);
        tm2.setCombatWidth(11);
        attacker = new Army(1, 1, 1, "nomad_group", infantry, cavalry, artillery,
                3, 3, 12, 3, 3, 3, im, tm);
        defender = new Army(1, 1, 1, "nomad_group", infantry, cavalry, artillery,
                12, 12, 12, 3, 3, 3, im, tm2);
    }
    @Test
    public void testBattle(){
        Battle battle = new Battle(attacker,defender, new Terrain(-1,-1,true));
        //battle.unitDeployment(defender);
        //battle.unitDeployment(attacker);
        battle.start();
    }
}
