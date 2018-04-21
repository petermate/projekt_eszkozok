package gameLogic;

import java.util.Random;

import classes.Unit;

public class CombatVariables {
	
	Unit attacker;
	Unit defender;
	
	public void setAttacker(Unit unit) {	//itt lehetne beállítani a unit tulajdonságait, amit kapunk valahonnan (fileból vagy felhasználótól a típust és úgy átkonvertálni?)
		this.attacker = unit;
	}
	
	public void setDefender(Unit unit) {
		this.defender = unit;
	}
	
	public int getDiceRoll() {
		int result = 0;
		Random rand = new Random();
		int roll = rand.nextInt(10); //0-9 
		int attackingLeaderSkill = 3; //bekérjük felhasználótól, vagy fileból beolvassuk, most csak random valami
		int defendingLeaderSkill = 2;	
		int unitAttack = attacker.getOffensiveShock() + attacker.getOffensiveFire(); //ha el akarjuk különíteni majd a phase-ket, akkor ezek is változhatnak
		int unitDef = defender.getDefensiveShock() + defender.getDefensiveFire(); 	
		int terrain = 0; //terep modifier, bekérni / fileból olvasni / skippelni?
		
		result = roll + attackingLeaderSkill + unitAttack - defendingLeaderSkill - unitDef - terrain;
		
		return result;
	}
	
	public int getCasulties() {
		int result = 0;
		int roll = getDiceRoll();
		int base = 15 + 5 * roll;
		int strength = 3; //ez nem tudom pontosan micsoda, de értéke arányosan csökken a katonák számával
		int modifier = 5; //unit type bónusz és techmodifierek összeadva, most csak random valami
		int ability = 3; //combat bonuses for the attacking unit
		int discipline = 1; //100%-ról indul, és csatán kívüli dolgok befolyásolják wiki alapján
		int tactics = 1; // a védekező unit tactics-a
		
		result = base * strength * modifier * (1 + ability) * discipline / tactics;
		
		return result;
	}
	
	public int getMoraleDamage() {
		int result = 0;		
		int casulties = getCasulties();
		int morale = 100; // nem tudom, hogy honnan jön és mennyi, de technology befolyásolja és komoly szerepe van a combatban
		
		result = casulties * morale / 600;
		
		return result;
	}
}
