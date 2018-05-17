package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import file_reader.file_reader.EU4FileReader;

public class GetUnlockedUnitsTest {

	@Test
	public void test() {
		EU4FileReader fr = new EU4FileReader();
		List<String> militaryList = new ArrayList<>();
		militaryList.add("south_american_spearmen");
		militaryList.add("native_indian_archer");
		militaryList.add("native_clubmen");
		militaryList.add("african_spearmen");
		militaryList.add("african_clubmen");
		militaryList.add("indian_archers");
		militaryList.add("mesoamerican_spearmen");
		militaryList.add("halberd_infantry");
		militaryList.add("chevauchee");
		militaryList.add("western_medieval_knights");
		militaryList.add("bardiche_infantry");
		militaryList.add("muslim_cavalry_archers");
		militaryList.add("western_medieval_infantry");
		militaryList.add("eastern_medieval_infantry");
		militaryList.add("ottoman_yaya");
		militaryList.add("chinese_longspear");
		militaryList.add("japanese_archer");
		militaryList.add("persian_footsoldier");
		militaryList.add("east_asian_spearmen");
		militaryList.add("mongolian_bow");
		militaryList.add("indian_footsoldier");	
		militaryList.add("rajput_hill_fighters");
		militaryList.add("eastern_bow");	
		militaryList.add("persian_cavalry_charge");
		militaryList.add("african_mandelaku");
		militaryList.add("african_abyssinian_light_cavalry");
		militaryList.add("mongol_swarm");
		militaryList.add("mongol_steppe");	
		militaryList.add("mongol_bow");	
		militaryList.add("eastern_knights");
		militaryList.add("druzhina_cavalry");	
		militaryList.add("ottoman_musellem");	
		militaryList.add("mamluk_archer");	
		militaryList.add("mamluk_cavalry_charge");	
		militaryList.add("south_american_warfare");	
		militaryList.add("ha_xantican_warrior");
		List<String> result = fr.getUnlockedUnits(1);
		assertEquals(militaryList, result);
	}
	
	@Test
	public void test2() {
		EU4FileReader fr = new EU4FileReader();
		List<String> result = fr.getUnlockedUnits(-1);
		assertEquals(null, result);
	}

}
