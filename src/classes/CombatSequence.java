package classes;

import java.util.Random;

/**
 * A class to simulate a battle.
 */
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
	
	
	/**
	 * Change the phase between fire and shock	
	 */
	public void changePhase() {
		
		if ( phase.equals("fire") ) {
			
			phase = "shock";
		}
		else if ( phase.equals("shock") ) {
			
			phase = "fire";
		}
	}
	
	/**
	 * The battle itself
	 */
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
	
	/**
	 * Attack with an army
	 */
	public void attack() {
		
		Casualty firstRowAttack;
		Casualty secondRowAttack;
		int diceRoll;
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
				
		for( int index=0; index < attackerArmy.combatWidth ; index ++) {
		    
		    attackerArmy.firstRow[index].setFireMod(0.25);
		    attackerArmy.firstRow[index].setShockMod(0.25);
		    defenderArmy.firstRow[index].setFireMod(0.1);
		    defenderArmy.firstRow[index].setShockMod(0.1);
			
			Random r = new Random();
		    diceRoll = Math.abs(r.nextInt(Integer.MAX_VALUE)) % 10;
		    
		    
			firstRowAttack = new Casualty( attackerArmy.firstRow[index], defenderArmy.firstRow[index], terrainMod );
			int defCasualty = firstRowAttack.casualties( firstRowAttack.diceRoll( diceRoll, phase ), phase );
			this.defenderArmy.setNumberOfDeadInfantryUnit(defCasualty);
					
			firstRowAttack = new Casualty( defenderArmy.firstRow[index], attackerArmy.firstRow[index], terrainMod );
			int attackerCasualty = firstRowAttack.casualties( firstRowAttack.diceRoll( diceRoll, phase ), phase );			
			this.attackerArmy.setNumberOfDeadInfantryUnit(attackerCasualty);
						
			
			if (attackerArmy.secondRow[index]!= null && attackerArmy.secondRow[index].type.equals(UnitType.ARTILLERY) ) {
				
				secondRowAttack = new Casualty( attackerArmy.secondRow[index], defenderArmy.firstRow[index], terrainMod );
				int defCasualtySecondRow = secondRowAttack.casualties( secondRowAttack.diceRoll( diceRoll, phase ), phase );
				
			}
			if (defenderArmy.secondRow[index]!= null && defenderArmy.secondRow[index].type.equals(UnitType.ARTILLERY) ) {
				
				secondRowAttack = new Casualty( defenderArmy.secondRow[index], attackerArmy.firstRow[index], terrainMod );
				int attackerCasualtySecondRow = secondRowAttack.casualties( secondRowAttack.diceRoll( diceRoll, phase ), phase );
			}
			
		}
		
		
		
	}
	
	/**
	 * Checks if the battle is over
	 */
	public boolean isBattleFinished() {
		
		int numberOfDeadUnitsInAttackerArmy = attackerArmy.numberOfDeadArtilleryUnit + attackerArmy.numberOfDeadInfantryUnit + attackerArmy.numberOfDeadCavalryUnit;
		int numberOfRetreatingUnitsInAttackerArmy = attackerArmy.numberOfRetreatingInfantryUnit + attackerArmy.numberOfRetreatingCavalryUnit + attackerArmy.numberOfRetreatingArtilleryUnit;
		
		int numberOfDeadUnitsInDefenderArmy = defenderArmy.numberOfDeadArtilleryUnit + defenderArmy.numberOfDeadInfantryUnit + defenderArmy.numberOfDeadCavalryUnit;
		int numberOfRetreatingUnitsInDefenderArmy = defenderArmy.numberOfRetreatingInfantryUnit + defenderArmy.numberOfRetreatingCavalryUnit + defenderArmy.numberOfRetreatingArtilleryUnit;
		
		if( numberOfDeadUnitsInAttackerArmy + numberOfRetreatingUnitsInAttackerArmy >= attackerArmy.manpower ) {
						
		    defenderArmy.setIsWonBattle(true);
			return true;			
		}
		else if( numberOfDeadUnitsInDefenderArmy + numberOfRetreatingUnitsInDefenderArmy >= defenderArmy.manpower ) {
			
			attackerArmy.setIsWonBattle(true);
			return true;
		}
		
		
		return false;				
	}
	
	/**
	 * Writes the results to the console.
	 */
	public void result() {
		
		System.out.println("Napok száma " + day );
		
		
		System.out.println("\n Támadó:");
		
		if(attackerArmy.isWonBattle) {
			System.out.println("Győztes:");
		}
		else {
			System.out.println("Vesztes");
		}
		
		System.out.println( "Halott katonák: " + attackerArmy.getNumberOfDeadInfantryUnit() );
		/*System.out.println( "Halott lovasság: " + attackerArmy.getNumberOfDeadCavalryUnit() );
		System.out.println( "Halott tüzérség: " + attackerArmy.getNumberOfDeadArtilleryUnit() );
		System.out.println( "Megfutamodott gyalogság: " + attackerArmy.getNumberOfRetreatingInfantryUnit() );
		System.out.println( "Megfutamodott lovasság: " + attackerArmy.getNumberOfRetreatingCavalryUnit() );
		System.out.println( "Megfutamodott tüzérség: " + attackerArmy.getNumberOfRetreatingArtilleryUnit() );*/
		
		
		
		System.out.println("\n Védekező");
		
		if(defenderArmy.isWonBattle) {
			System.out.println("Győztes:");
		}
		else {
			System.out.println("Vesztes");
		}
		
		System.out.println( "Halott katonák: " + defenderArmy.getNumberOfDeadInfantryUnit() );
		/*System.out.println( "Halott lovasság: " + defenderArmy.getNumberOfDeadCavalryUnit() );
		System.out.println( "Halott tüzérség: " + defenderArmy.getNumberOfDeadArtilleryUnit() );
		System.out.println( "Megfutamodott gyalogság: " + defenderArmy.getNumberOfRetreatingInfantryUnit() );
		System.out.println( "Megfutamodott lovasság: " + defenderArmy.getNumberOfRetreatingCavalryUnit() );
		System.out.println( "Megfutamodott tüzérség: " + defenderArmy.getNumberOfRetreatingArtilleryUnit() );*/
		
		
		
		
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
