package main;

import java.util.List;
import java.util.Scanner;

import classes.Leader;
import classes.Unit;
import file_reader.file_reader.EU4FileReader;

public class StartGame {

	private Leader leader1;
	private Leader leader2;
	//private CombatUnit combatUnit1;
	//private CombatUnit combatUnit2;
	private int tech;
	private int stance;  //támadás vagy védekezés, lehet boolean is, ha kényelmesebb
	private int regiments1;
	private int regiments2;
	private int river;  //folyó, lehet boolean is
	private int terrainMod;
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("EU4 csataszimulátor teszt");
		System.out.println("-----------------------------");	
				
		System.out.println("Add meg a tabornokod parametereit! \n Fire:");				
		int f;
		do {
			System.out.println("0 vagy nagyobb szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    f = sc.nextInt();
		} while (f < 0);		//itt le lehetne ellenőrizni, hogy a lehetséges értékeken belül legyen (nem tudom mennyi lehet a max., wikin sem találom
		
		System.out.println("Shock:");
		int s;
		do {
			System.out.println("0 vagy nagyobb szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    s = sc.nextInt();
		} while (s < 0);		//itt le lehetne ellenőrizni, hogy a lehetséges értékeken belül legyen (nem tudom mennyi lehet a max., wikin sem találom
		
		System.out.println("Maneuver:");
		int m;
		do {
			System.out.println("0 vagy nagyobb szamot adj meg!");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    m = sc.nextInt();
		} while (m < 0);		//itt le lehetne ellenőrizni, hogy a lehetséges értékeken belül legyen (nem tudom mennyi lehet a max., wikin sem találom
		
		leader1 = new Leader(f, s, m);
		System.out.println("Az altalad valasztott tabornok: * Fire: " + f + " | Shock: " + s + " | Maneuver: " + m + " *");
				
		System.out.println("Add meg a sereged military technology szintjet (0-32): ");
		do {
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    tech = sc.nextInt();
		} while (tech < 0 || tech > 32);
		System.out.println("Az altalad valaszott szint: |" + tech + "| \n Az alabbi egysegek kozul valaszthatsz: ");
		
		EU4FileReader fr = new EU4FileReader();
		List<String> list1 = fr.getUnlockedUnits(tech);		
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(i+1 + ". " + list1.get(i) + " | ");		//csakhogy 1-től induljon a számozás
		}
		
		int u1;
		do {
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    u1 = sc.nextInt();
		} while (u1 <= 0 || u1 > list1.size()+1);
		
		System.out.println("Az altalad valaszott egyseg: | " + list1.get(u1-1) + " |");
		Unit unit1 = fr.getUnitWithAttributes(list1.get(u1-1)+".txt");	//számozás miatt -1, ha nagyon zavaró kivehetjük az egészet és 0-tól indul
		
		/*System.out.println("Az altalad valaszott egyseg zsoldos? 0 = nem | 1 = igen");
		int mercenary = sc.nextInt();
		if (mercenary < 0 || mercenary > 1 ) {
			System.out.println("Rossz szamot adtal meg!");
		}
		boolean isMercenary;
		if (Integer.parseInt(args[0]) == 0) {
			isMercenary = false;
		} else {
			isMercenary = true;
		}
		 */
		//InputModifier im = new InputModifier();		
		//combatUnit1 = new CombatUnit(unit1, t, im, leader1, isMercenary);	
		// inputmodifier biztos, hogy minden UI-n jön? egyesével bekérjük a felhasználótól az összeset? azokat nem tech bónuszként kapjuk meg?
		
		//kövi a leírás szerint a miltiary group. Az is bemeneti paraméter? Az nem a unit txt-ben van minden egységhez?
		
				
		System.out.println("Add meg, hogy tamadsz vagy vedekezel! ");
		do {
			System.out.println("0 = tamadsz | 1 = vedekezel");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    stance = sc.nextInt();
		} while (stance < 0 || stance > 1);
		
		System.out.println("Add meg az ezredek szamat! (1 ezred = 1000 ember) ");
		do {
			System.out.println("minimum 1 ezred");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    regiments1 = sc.nextInt();
		} while (regiments1 < 0);
		
		//unit type = ez a választott egységtől függ, amit kiolvasunk a txt-ből nem?
		
		System.out.println("Van folyo, amin a tamado seregnek at kell kelnie?");
		do {
			System.out.println("0 = nincs | 1 = van");
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    river = sc.nextInt();
		} while (river < 0);
		
		System.out.println("Add meg a terep tipusat!");
		do {
			System.out.println("0 = siksag | 1 = erdo | 2 = hegyek"); //nem tudom hany fajta van
		    while (!sc.hasNextInt()) {
		        System.out.println("Szamot adj meg!");
		        sc.next(); 
		    }
		    terrainMod = sc.nextInt();
		} while (terrainMod < 0 || terrainMod > 2);
				
	}
	
	public Leader getLeader1() {
		return leader1;
	}
	
	public Leader getLeader2() {
		return leader2;
	}
	
	/*public CombatUnit getCombatUnit1() {
		return combatUnit1;
	}
	*/
	
	/*public CombatUnit getCombatUnit2() {
	return combatUnit2;
	}
	*/
	
	public int getTech() {
		return tech;
	}
	
	public int getStance() {
		return stance;
	}
	
	public int getRegiments1() {
		return regiments1;
	}
	
	public int getRegiments2() {
		return regiments2;
	}
	
	public int getRiver() {
		return river;
	}
	
	public int getTerrainMod() {
		return terrainMod;
	}
}
