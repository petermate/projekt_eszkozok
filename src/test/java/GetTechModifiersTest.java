package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.TechnologyModifier;
import file_reader.file_reader.EU4FileReader;

public class GetTechModifiersTest {

  @Test
  public void test() {
    TechnologyModifier tm = new TechnologyModifier();
    tm.setInfantryFire(0.25);
    tm.setInfantryShock(0.2);
    tm.setCavalryShock(0.8);
    tm.setMorale(2.0);
    tm.setInfantryShock(0.1);
    tm.setInfantryFire(0.1);
    tm.setInfantryShock(0.2);
    tm.setCavalryShock(0.2);
    tm.setCombatWidth(5);
    EU4FileReader eu4 = new EU4FileReader();
    TechnologyModifier actual = eu4.getTechModifiers(2);
    assertEquals(tm, actual);
  }

}
