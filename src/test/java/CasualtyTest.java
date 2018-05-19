package test.java;

import classes.Casualty;
import classes.CombatUnit;
import classes.TechnologyModifier;
import classes.Unit;
import classes.InputModifier;
import classes.Leader;
import classes.UnitType;
import org.junit.Test;
import static org.junit.Assert.*;

public class CasualtyTest {

    TechnologyModifier tm;
    Unit u1, u2;
    InputModifier im;
    Leader l1, l2;
    CombatUnit a, d;
    int terrainMod = -1;

    public CasualtyTest() {
        u1 = new Unit("nomad_group", UnitType.INFANTRY, 4, 1, 1, 0, 1, 1, 1);
        u2 = new Unit("western", UnitType.CAVALRY, 1, 1, 1, 0, 0, 1, 1);
        im = new InputModifier(
                1.0, //morale
                1.1, //discipline
                0.0, //mercenary discipline
                1.0, //flanking range
                0.0, //offensiveBonusShock
                0.1, //offensiveBonusFire
                0.0, //defensiveBonusShock
                0.0, //defensiveBonusFire
                1.0, //infantryCombatAbility
                0.0, //cavalryCombatAbility
                0.0, //artilleryCombatAbility
                0.0 //
        );
        tm = new TechnologyModifier();
        tm.setInfantryFire(0.25);
        tm.setInfantryShock(0.2);
        tm.setCavalryShock(0.8);
        tm.setMorale(2.0);
        tm.setInfantryShock(0.1);
        tm.setInfantryFire(0.1);
        tm.setInfantryShock(0.2);
        tm.setCavalryShock(0.2);
        tm.setCombatWidth(5);
        l1 = new Leader(1, 2, 2);
        l2 = new Leader(0,6,0);
        a = new CombatUnit(u1, tm, im, l1, false);
        d = new CombatUnit(u1, tm, im, l2, false);
        
    }
    
    @Test
    public void testDiceRoll() {
        System.out.println("DiceRoll");
        int droll = 5;
        String phase = "fire";
        Casualty instance = new Casualty(a,d,terrainMod);
        int expResult = 4;
        int result = instance.diceRoll(droll, phase);
        assertEquals(expResult, result);
    }
    @Test
    public void testCasualties() {
        System.out.println("Casualties");
        int droll = 2;
        String phase = "shock";
        Casualty instance = new Casualty(a,d,terrainMod);
        int expResult = 50;
        int result = instance.casualties(droll, phase);
        assertEquals(expResult, result);
    }
    @Test
    public void testFirePhaseCasualties() {
        System.out.println("FirePhaseCasualties");
        int droll = 5;
        Casualty instance = new Casualty(a,d,terrainMod);
        int expResult = 54;
        int result = instance.firePhaseCasualties(droll);
        assertEquals(expResult, result);
    }

    @Test
    public void testShockPhaseCasualties() {
        System.out.println("ShockPhaseCasualties");
        int droll = 5;
        Casualty instance = new Casualty(a,d,terrainMod);
        int expResult = 30;
        int result = instance.shockPhaseCasualties(droll);
        assertEquals(expResult, result);
    }

    @Test
    public void testFirePhaseMoraleDamage() {
        System.out.println("FirePhaseMoraleDamage");
        int droll = 5;
        Casualty instance = new Casualty(a,d,terrainMod);;
        double expResult = 0.308;
        double result = instance.firePhaseMoraleDamage(droll);
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testShockPhaseMoraleDamage() {
        System.out.println("ShockPhaseMoraleDamage");
        int droll = 5;
        Casualty instance = new Casualty(a,d,terrainMod);
        double expResult = 0.15;
        double result = instance.shockPhaseMoraleDamage(droll);
        assertEquals(expResult, result, 0.01);;
    }

}
