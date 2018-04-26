package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.Unit;
import classes.UnitType;
import file_reader.file_reader.EU4FileReader;

public class GetUnitWithAttributesTest {

	@Test
	public void test() {
		Unit exp = new Unit("nomad_group",UnitType.INFANTRY,1,1,1,0,0,1,1);
		EU4FileReader fr = new EU4FileReader();
		Unit unit = fr.getUnitWithAttributes("mongol_bow.txt");
		assertEquals(exp, unit);
	}

}
