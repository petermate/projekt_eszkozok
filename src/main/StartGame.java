package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Army;
import classes.CombatSequence;
import classes.CombatUnit;
import classes.InputModifier;
import classes.Leader;
import classes.TechnologyModifier;
import classes.Unit;
import file_reader.file_reader.EU4FileReader;

public class StartGame {

	private Leader leader1;
	private Leader leader2;
	private ArrayList<CombatUnit> userCombatUnits = new ArrayList<>();
	private ArrayList<CombatUnit> enemyCombatUnits = new ArrayList<>();
	private int tech;
	private int tech2;
	private int stance;  	//támadás vagy védekezés, lehet boolean is, ha kényelmesebb
	//private int regiments1;
	//private int regiments2;
	private int river;  	//folyó, lehet boolean is
	private int terrainMod;
	private Army army1;
	private Army army2;
	
	/*
	* elindítja a programot és bekéri a paramétereket a felhasználótól
	*/

	
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("EU4 csataszimulátor teszt");
		System.out.println("-----------------------------");	
				
		System.out.println("# Add meg a tabornokod parametereit! \n# Fire:");				
		int f;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    f = sc.nextInt();
		} while (f < 0 || f > 6);		
		
		System.out.println("# Shock:");
		int s;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    s = sc.nextInt();
		} while (s < 0 || s > 6);		
		
		System.out.println("# Maneuver:");
		int m;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    m = sc.nextInt();
		} while (m < 0 || m > 6);		
		
		leader1 = new Leader(f, s, m);
		System.out.println("# Az altalad valasztott tabornok: * Fire: " + f + " | Shock: " + s + " | Maneuver: " + m + " *");
				
		System.out.println("# Zsoldos katonakat szeretnel?");
		int mercenary;
		do {
			System.out.println("0 = nem | 1 = igen");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    mercenary = sc.nextInt();
		} while (mercenary < 0 || mercenary > 1);
		
		boolean isMercenary;
		if (mercenary == 0) {
			isMercenary = false;
		} else {
			isMercenary = true;
		}
		
		System.out.println("# Add meg a sereged military technology szintjet (0-32): ");
		do {
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    tech = sc.nextInt();
		} while (tech < 0 || tech > 32);
		System.out.println("> Az altalad valaszott szint: |" + tech + "| \n# Az alabbi egysegek kozul valaszthatsz: ");
		
		EU4FileReader fr = new EU4FileReader();
		List<String> list1 = fr.getUnlockedUnits(tech);		
		for (int i = 0; i < list1.size(); i++) {
			System.out.print(i+1 + ". " + list1.get(i) + " | ");		//csakhogy 1-től induljon a számozás
		}
			
		InputModifier im = new InputModifier(1,1,1,1,1,1,1,1,1,1,1); 	//dummy object for now
		TechnologyModifier tm = new TechnologyModifier();
		List<Unit> units1 = new ArrayList<>();	
		boolean end = false;
		
		System.out.println("\n# Ird be a valasztott egysegek sorszamat, majd ha vegeztel irj be barmilyen betut es enter!");
		
		while (sc.hasNext() && end == false) {
			if (sc.hasNextInt())
				units1.add(fr.getUnitWithAttributes(list1.get(sc.nextInt()-1)+".txt"));
			else
				end = true;
		}
		
		for (Unit un : units1) {
			userCombatUnits.add(new CombatUnit(un, tm, im, leader1, isMercenary));
		}
		
		/*
		System.out.println("Az altalad valaszott egysegek: ");  
		for (CombatUnit cu : userCombatUnits) {
			System.out.print(cu + " | " );
		}
		*/
		
		
				
		System.out.println("\n# Add meg, hogy tamadsz vagy vedekezel! ");
		do {
			System.out.println("0 = tamadsz | 1 = vedekezel");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    stance = sc.nextInt();
		} while (stance < 0 || stance > 1);
		
		/*
		System.out.println("Add meg az ezredek szamat! (1 ezred = 1000 ember) ");
		do {
			System.out.println("minimum 1 ezred");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    regiments1 = sc.nextInt();
		} while (regiments1 < 0);
		*/
		
		
		
		System.out.println("# Van folyo, amin a tamado seregnek at kell kelnie?");
		do {
			System.out.println("0 = nincs | 1 = van");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    river = sc.nextInt();
		} while (river < 0 || river > 1);
		
		System.out.println("# Add meg a terep tipusat!");
		do {
			System.out.println("0 = siksag | 1 = erdo | 2 = hegyek | 3 = dzsungel | 4 = dombok | 5 = mocsar | 6 = sivatag");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    terrainMod = sc.nextInt();
		} while (terrainMod < 0 || terrainMod > 6);
		
		//ellenség
		
		System.out.println("# Add meg az ellenseges tabornok parametereit! \n# Fire:");				
		int f2;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    f2 = sc.nextInt();
		} while (f2 < 0 || f2 > 6);		
		
		System.out.println("# Shock:");
		int s2;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    s2 = sc.nextInt();
		} while (s2 < 0 || s2 > 6);		
		
		System.out.println("# Maneuver:");
		int m2;
		do {
			System.out.println("0 es 6 kozotti szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    m2 = sc.nextInt();
		} while (m2 < 0 || m2 > 6);		
		
		leader2 = new Leader(f2, s2, m2);
		System.out.println("# Az altalad valasztott ellenseges tabornok: * Fire: " + f2 + " | Shock: " + s2 + " | Maneuver: " + m2 + " *");
				
		System.out.println("# Zsoldos katonakat szeretnel?");
		int mercenary2;
		do {
			System.out.println("0 = nem | 1 = igen");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    mercenary2 = sc.nextInt();
		} while (mercenary2 < 0 || mercenary2 > 1);
		
		boolean isMercenary2;
		if (mercenary2 == 0) {
			isMercenary2 = false;
		} else {
			isMercenary2 = true;
		}
				
		System.out.println("# Add meg az ellenseges sereg military technology szintjet (0-32): ");
		do {
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    tech2 = sc.nextInt();
		} while (tech2 < 0 || tech2 > 32);
		System.out.println("> Az altalad valaszott szint: |" + tech2 + "| \n# Az alabbi egysegek kozul valaszthatsz: ");
		
		List<String> list2 = fr.getUnlockedUnits(tech2);
		List<Unit> units2 = new ArrayList<>();	
		for (int i = 0; i < list2.size(); i++) {
			System.out.print(i+1 + ". " + list2.get(i) + " | ");		//csakhogy 1-től induljon a számozás
		}
		
		boolean end2 = false;
		
		System.out.println("\n# Ird be a valasztott egysegek sorszamat, majd ha vegeztel irj be barmilyen betut es enter!");
		
		while (sc.hasNext() && end2 == false) {
			if (sc.hasNextInt())
				units2.add(fr.getUnitWithAttributes(list2.get(sc.nextInt()-1)+".txt"));
			else
				end2 = true;
		}
		
		
		for (Unit un2 : units2) {
			enemyCombatUnits.add(new CombatUnit(un2, tm, im, leader2, isMercenary2));
		}
		
				
		army1 = new Army(leader1, 1, 1, userCombatUnits);  			// using dummy values for now
		army2 = new Army(leader2, 1, 1, enemyCombatUnits);
		
		CombatSequence combat = new CombatSequence(army1, army2, terrainMod);
		
		combat.battle();
				
	}
	
	public Leader getLeader1() {
		return leader1;
	}
	
	public Leader getLeader2() {
		return leader2;
	}
	
	public List<CombatUnit> getUserCombatUnits() {
		return userCombatUnits;
	}
	
	
	public List<CombatUnit> getEnemyCombatUnits() {
		return enemyCombatUnits;
	}
	
	public int getTech() {
		return tech;
	}
	
	public int getTech2() {
		return tech2;
	}
	
	public int getStance() {
		return stance;
	}
	
	/*
	public int getRegiments1() {
		return regiments1;
	}
	
	public int getRegiments2() {
		return regiments2;
	}
	*/
	
	public int getRiver() {
		return river;
	}
	
	public int getTerrainMod() {
		return terrainMod;
	}
	
	public Army getArmy1() {
		return army1;
	}
	
	public Army getArmy2() {
		return army2;
	}
}
