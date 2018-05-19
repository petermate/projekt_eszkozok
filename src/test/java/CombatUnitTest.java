package test.java;

import classes.CombatUnit;
import classes.InputModifier;
import classes.Leader;
import classes.TechnologyModifier;
import classes.Unit;
import classes.UnitType;
import org.junit.Test;
import static org.junit.Assert.*;

public class CombatUnitTest {

    TechnologyModifier tm;
    Unit u1, u2;
    InputModifier im;
    Leader l;

    public CombatUnitTest() {
        u1 = new Unit("nomad_group", UnitType.INFANTRY, 4, 1, 1, 0, 0, 1, 1);
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
                0.0
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
        l = new Leader(1, 2, 2);
    }

    @Test
    public void testCombatUnit() {
        System.out.println("CombatUnit");
        CombatUnit instance = new CombatUnit(u1, tm, im, l, false);
        assertEquals(u1.getDefensiveFire(), instance.getDefensiveFire());
        assertEquals(u1.getDefensiveMorale(), instance.getDefensiveMorale());
        assertEquals(u1.getDefensiveShock(), instance.getDefensiveShock());
        assertEquals(u1.getOffensiveFire(), instance.getOffensiveFire());
        assertEquals(u1.getOffensiveMorale(), instance.getOffensiveMorale());
        assertEquals(u1.getOffensiveShock(), instance.getOffensiveShock());
        assertEquals(u1.getType(), instance.getType());
        assertEquals(u1.getUnitType(), instance.getMilitaryGroup());

        assertEquals(instance.getMaxMorale(), 3.0, 0.01);
        assertEquals(instance.getFireMod(),0.35,0.01);
        assertEquals(instance.getShockMod(),0.5,0.01);
        assertEquals(instance.getDiscipline(),1.1,0.01);
        assertEquals(instance.getTactics(),0.55,0.01);
        assertEquals(instance.getFlankingRange(),8.0,0.01);
    }

    @Test
    public void testUpdateFlankingRange() {
        System.out.println("updateFlankingRange");
        CombatUnit instance = new CombatUnit(u1, tm, im, l, false);
        double a = instance.getFlankingRange();
        instance.setStrength(749);
        instance.updateFlankingRange();
        int fr = instance.getFlankingRange();
        assertEquals(6, fr);
    }

    @Test
    public void testInitializeNomadShock() {
        System.out.println("initializeNomadShock");
        boolean flatTerrain = false;
        CombatUnit instance = new CombatUnit(u1, tm, im, l, false);
        double a = instance.getOffensiveBonusShock();
        instance.initializeNomadShock(flatTerrain);
        double b = instance.getOffensiveBonusShock();
        assertEquals(a, b + 0.25, 0.01);
        assertEquals(b, -0.25, 0.01);
    }

    @Test
    public void testCoveringFire() {
        System.out.println("coveringFire");
        int artilleryDefensiveShock = 2;
        int artilleryDefensiveFire = 1;
        int artilleryDefensiveMorale = 0;
        CombatUnit instance = new CombatUnit(u1, tm, im, l, false);
        instance.coveringFire(artilleryDefensiveShock, artilleryDefensiveFire, artilleryDefensiveMorale);
        int a = instance.getDefensiveFire();
        int b = instance.getDefensiveShock();
        int c = instance.getDefensiveMorale();
        assertEquals(a, 0);
        assertEquals(b, 2);
        assertEquals(c, 1);
    }

}
