package test.java;

import classes.Army;
import classes.InputModifier;
import classes.TechnologyModifier;
import classes.Unit;
import classes.UnitType;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArmyTest {
    Unit infantry, cavalry, artillery;
    InputModifier im;
    TechnologyModifier tm;

    public ArmyTest() {
        this.infantry = new Unit("nomad_group", UnitType.INFANTRY, 4, 1, 1, 0, 1, 1, 1);
        this.cavalry = new Unit("nomad_group", UnitType.CAVALRY, 2, 1, 2, 0, 1, 1, 1);
        this.artillery = new Unit("", UnitType.ARTILLERY, 0, 4, 0, 0, 2, 2, 2);
        this.im = new InputModifier();
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
    }

    @Test
    public void testArmy() {
        System.out.println("testArmy");
        Army army = new Army(1, 1, 1, "nomad_group", infantry, cavalry, artillery,
                3, 3, 3, 3, 3, 3, im, tm);
        for (int i = 0; i < army.getNumOfRegInfantry(); i++) {
            assertEquals(army.getInf()[i].getType().toString(), "INFANTRY");
        }
        assertEquals(army.getTm().getCombatWidth(), army.getFirstRow().length);
        assertEquals(army.getNumOfUnits(), 18);
        assertEquals(army.getUnits().length, 18);
        assertEquals(army.getMg(), army.getInf()[2].getMilitaryGroup());
        assertEquals(army.getCav()[2].getMilitaryGroup(), army.getInf()[2].getMilitaryGroup());
    }

}
