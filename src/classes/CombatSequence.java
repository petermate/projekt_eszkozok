package classes;

import java.util.Random;

public class CombatSequence {

	public Army attackerArmy;
	public Army defenderArmy;
	public String phase;
	public int terrainMod;
	
	
	public int day;
	
	
	public CombatSequence( Army attackerArmy, Army defenderArmy, int terrainMod) {
		
		this.attackerArmy = attackerArmy;
		this.defenderArmy = defenderArmy;
		this.phase = "fire";
		this.terrainMod=terrainMod;
		
		this.day = 0;
		
		
		
	}
	
	
		
	public void changePhase() {
		
		if ( phase.equals("fire") ) {
			
			phase = "shock";
		}
		else if ( phase.equals("shock") ) {
			
			phase = "fire";
		}
	}
	
	public void battle() {
		
		while ( !isBattleFinished() ) {
			
			
			attack();
			
			attackerArmy.switchPositions();
			defenderArmy.switchPositions();
			
			
			
			
			day++;
			
			if( day % 3 == 2) {
				
				changePhase();
			}
		}
		
		
		
		result();
		
	}
	
	public void attack() {
		
		Casualty firstRowAttack;
		Casualty secondRowAttack;
		int diceRoll;
				
		for( int index=0; index < attackerArmy.combatWidth ; index ++) {
			
			Random r = new Random();
		    diceRoll = Math.abs(r.nextInt()) % 10;
		    
		    
			firstRowAttack = new Casualty( attackerArmy.firstRow[index], defenderArmy.firstRow[index], terrainMod );
			firstRowAttack.Casualties( firstRowAttack.DiceRoll( diceRoll, phase ), phase );
			
			firstRowAttack = new Casualty( defenderArmy.firstRow[index], attackerArmy.firstRow[index], terrainMod );
			firstRowAttack.Casualties( firstRowAttack.DiceRoll( diceRoll, phase ), phase );
					
						
			
			if ( attackerArmy.secondRow[index].type.equals(UnitType.ARTILLERY) ) {
				
				secondRowAttack = new Casualty( attackerArmy.secondRow[index], defenderArmy.firstRow[index], terrainMod );
				secondRowAttack.Casualties( secondRowAttack.DiceRoll( diceRoll, phase ), phase );
				
			}
			if ( defenderArmy.secondRow[index].type.equals(UnitType.ARTILLERY) ) {
				
				secondRowAttack = new Casualty( defenderArmy.secondRow[index], attackerArmy.firstRow[index], terrainMod );
				secondRowAttack.Casualties( secondRowAttack.DiceRoll( diceRoll, phase ), phase );
			}
			
		}
		
		
		
	}
	
	public boolean isBattleFinished() {
		
		int numberOfDeadUnitsInAttackerArmy = attackerArmy.NumberOfDeadArtilleryUnit + attackerArmy.NumberOfDeadInfantryUnit + attackerArmy.NumberOfDeadCavalryUnit;
		int numberOfRetreatingUnitsInAttackerArmy = attackerArmy.NumberOfRetreatingInfantryUnit + attackerArmy.NumberOfRetreatingCavalryUnit + attackerArmy.NumberOfRetreatingArtilleryUnit;
		
		int numberOfDeadUnitsInDefenderArmy = defenderArmy.NumberOfDeadArtilleryUnit + defenderArmy.NumberOfDeadInfantryUnit + defenderArmy.NumberOfDeadCavalryUnit;
		int numberOfRetreatingUnitsInDefenderArmy = defenderArmy.NumberOfRetreatingInfantryUnit + defenderArmy.NumberOfRetreatingCavalryUnit + defenderArmy.NumberOfRetreatingArtilleryUnit;
		
		if( numberOfDeadUnitsInAttackerArmy + numberOfRetreatingUnitsInAttackerArmy == attackerArmy.manpower ) {
						
			attackerArmy.setIsWonBattle(true);
			return true;			
		}
		else if( numberOfDeadUnitsInDefenderArmy + numberOfRetreatingUnitsInDefenderArmy == defenderArmy.manpower ) {
			
			defenderArmy.setIsWonBattle(true);
			return true;
		}
		
		
		return false;				
	}
	
	public void result() {
		
		System.out.println("Napok száma " + day );
		
		
		System.out.println("\n Támadó:");
		
		if(attackerArmy.isWonBattle) {
			System.out.println("Győztes:");
		}
		else {
			System.out.println("Vesztes");
		}
		
		System.out.println( "Halott gyalogság: " + attackerArmy.getNumberOfDeadInfantryUnit() );
		System.out.println( "Halott lovasság: " + attackerArmy.getNumberOfDeadCavalryUnit() );
		System.out.println( "Halott tüzérség: " + attackerArmy.getNumberOfDeadArtilleryUnit() );
		System.out.println( "Megfutamodott gyalogság: " + attackerArmy.getNumberOfRetreatingInfantryUnit() );
		System.out.println( "Megfutamodott lovasság: " + attackerArmy.getNumberOfRetreatingCavalryUnit() );
		System.out.println( "Megfutamodott tüzérség: " + attackerArmy.getNumberOfRetreatingArtilleryUnit() );
		
		
		
		System.out.println("\n Védekező");
		
		if(defenderArmy.isWonBattle) {
			System.out.println("Győztes:");
		}
		else {
			System.out.println("Vesztes");
		}
		
		System.out.println( "Halott gyalogság: " + defenderArmy.getNumberOfDeadInfantryUnit() );
		System.out.println( "Halott lovasság: " + defenderArmy.getNumberOfDeadCavalryUnit() );
		System.out.println( "Halott tüzérség: " + defenderArmy.getNumberOfDeadArtilleryUnit() );
		System.out.println( "Megfutamodott gyalogság: " + defenderArmy.getNumberOfRetreatingInfantryUnit() );
		System.out.println( "Megfutamodott lovasság: " + defenderArmy.getNumberOfRetreatingCavalryUnit() );
		System.out.println( "Megfutamodott tüzérség: " + defenderArmy.getNumberOfRetreatingArtilleryUnit() );
		
		
		
		
	}
	
	
	// getter, setter
	
	public Army getAttackerArmy() {
		
		return attackerArmy;
	}
	
	public Army getDefenderArmy() {
		
		return defenderArmy;
	}
	
	public String getPhase() {
		
		return phase;
	}
	
	public int getDay() {
		
		return day;
	}	
	
	public int getTerrainMod() {
		
		return terrainMod;
	}	
	
	public void setAttackerArmy( Army attackerArmy ) {
		
		this.attackerArmy = attackerArmy;
	}
	
	public void setDefenderArmy( Army defenderArmy ) {
		
		this.defenderArmy = defenderArmy;
	}
	
	public void setPhase( String phase ) {
		
		this.phase = phase;
	}
	
	public void setDay( int day ) {
		
		this.day = day;
	}
	
	public void setTerrainMod( int terrainMod ) {
		
		this.terrainMod = terrainMod;
	}
	
	
	
}
