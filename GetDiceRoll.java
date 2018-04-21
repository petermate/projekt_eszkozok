package gameLogic;

import java.util.Random;

import classes.Unit;

public class GetDiceRoll {
	
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
}
