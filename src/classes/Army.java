package classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Army {
	
	public Leader leader;
	public int manpower;
	public int combatWidth;
	public int infantryToCavalryRatio; // százalékosan, 0 és 100 között
	public int cavalryPositionValueInOneSide; // X position to each side for the units of cavalry
	public boolean isWonBattle;
	
	
	ArrayList<CombatUnit> units;
	ArrayList<CombatUnit> unitsOfInfantry;
	ArrayList<CombatUnit> unitsOfCavalry;
	ArrayList<CombatUnit> unitsOfArtillery;
	
	CombatUnit[] firstRow;
	CombatUnit[] secondRow;
	
	public int numberOfSpareUnit; //tartalékok száma
	public int numberOfDeadInfantryUnit;
	public int numberOfDeadCavalryUnit;
	public int numberOfDeadArtilleryUnit;
	public int numberOfRetreatingInfantryUnit;
	public int numberOfRetreatingCavalryUnit;
	public int numberOfRetreatingArtilleryUnit;
	
	
	public Army( Leader leader, int combatWidth, int cavalryPositionValueInOneSide, ArrayList<CombatUnit> units) {
		
		this.leader = leader;
		this.manpower = 1000;
		this.combatWidth = combatWidth;
		this.infantryToCavalryRatio = 50;
		this.cavalryPositionValueInOneSide = cavalryPositionValueInOneSide;
		
		this.numberOfSpareUnit = 0;
		this.numberOfDeadInfantryUnit = 0;
		this.numberOfDeadCavalryUnit = 0;
		this.numberOfDeadArtilleryUnit = 0;		
		this.numberOfRetreatingInfantryUnit = 0;
		this.numberOfRetreatingCavalryUnit = 0;
		this.numberOfRetreatingArtilleryUnit = 0;
		
		this.isWonBattle = false;
				
		firstRow = new CombatUnit[combatWidth];
		secondRow = new CombatUnit[combatWidth];
		
		
		this.units = new ArrayList<CombatUnit>();
		this.unitsOfArtillery = new ArrayList<>();
		this.unitsOfCavalry = new ArrayList<>();
		this.unitsOfInfantry = new ArrayList<>();
		
		this.units = units;
		
					
		try {
			
			checkMoral( this.units );
			splitUnits( this.units );		
			deployUnitsToStartingPosition();
			
        } catch (NullPointerException e) {
        	
            System.out.print("NullPointerException");
        }
		
		
		
	}
	 
	public Army() {
		
	}
	
	public void splitUnits( ArrayList<CombatUnit> units ) {
		
		
		for( CombatUnit unit : units) {
			
			if( unit.type.equals(UnitType.INFANTRY) ) {
				
				unitsOfInfantry.add(unit);
			}
			else if( unit.type.equals(UnitType.CAVALRY) ) {
				
				unitsOfCavalry.add(unit);
			}
			else if( unit.type.equals(UnitType.ARTILLERY) ) {
				
				unitsOfArtillery.add(unit);
			}
		}
		
	}
	
	public void checkMoral( ArrayList<CombatUnit> units )  {
		
		ArrayList<CombatUnit> unitsToRemove= new ArrayList<CombatUnit>();
		
		for( CombatUnit unit : units) {
			
			if( unit.getMorale() == 0) {
				
				unitsToRemove.add(unit);
				
				if( unit.type.equals(UnitType.INFANTRY) ) {
					
					numberOfRetreatingInfantryUnit++;
				}
				else if( unit.type.equals(UnitType.CAVALRY) ) {
					
					numberOfRetreatingCavalryUnit++;
				}
				else if( unit.type.equals(UnitType.ARTILLERY) ) {
					
					numberOfRetreatingArtilleryUnit++;
				}
				
			}
		}
		
		units.removeAll( unitsToRemove );
		
	}	
	
	
	
	public void deployUnitsToStartingPosition() {
		
		// first row
		
		for( int indexFirstRow = 0; indexFirstRow < combatWidth; indexFirstRow++) {
			
			setRow( firstRow, indexFirstRow );
			
		}		
		
		// second row
		for( int indexSecondRow = 0; indexSecondRow < combatWidth; indexSecondRow++) {
			
			setRow( secondRow, indexSecondRow );
			
		}
		
		setNumberOfSpareUnit( ( unitsOfCavalry.size() + unitsOfArtillery.size() + unitsOfInfantry.size() ) );
	}	
	
	
	public void setRow( CombatUnit[] row, int index ) {
		
				
		int artilleryIndexBegin = ( combatWidth / 2 ) - ( unitsOfArtillery.size() / 2 ); 
		
	
		if( ( index < cavalryPositionValueInOneSide ) || ( index > combatWidth - cavalryPositionValueInOneSide) ) {
			
			if( !unitsOfCavalry.isEmpty() ) {
								
				row[index] = unitsOfCavalry.get(0);
				unitsOfCavalry.remove(0);
			}
			else {
				
				if( !unitsOfInfantry.isEmpty() ) {
					
					row[index] = unitsOfInfantry.get(0);
					unitsOfInfantry.remove(0);
				}
								
			}
			
		}			
		else if( ( Arrays.equals(row, secondRow) ) && ( ( index >  artilleryIndexBegin ) || ( index < ( artilleryIndexBegin + unitsOfArtillery.size() ) ) ) ) {
			
			if( !unitsOfArtillery.isEmpty() ) {
				
				row[index] = unitsOfArtillery.get(0);
				unitsOfArtillery.remove(0);
			}
			else {
				
				if( !unitsOfInfantry.isEmpty() ) {
					
					row[index] = unitsOfInfantry.get(0);
					unitsOfInfantry.remove(0);
				}
								
			}
			
		}
		else {
			
			if( !unitsOfInfantry.isEmpty() ) {
				
				row[index] = unitsOfInfantry.get(0);
				unitsOfInfantry.remove(0);
			}
			
		}
		
		
	}
	
		
	public void getNewUnitForPosition( CombatUnit unit ) {
		
				
		if( numberOfSpareUnit != 0 ) {
			
			if( unit.getType().equals(UnitType.CAVALRY) ) {
				
				numberOfRetreatingCavalryUnit++;
				
				if( !unitsOfCavalry.isEmpty() ) {
					
					unit = unitsOfCavalry.get(0);
					unitsOfCavalry.remove(0);
				}
				else if( !unitsOfInfantry.isEmpty() ) {
					
					unit = unitsOfInfantry.get(0);
					unitsOfInfantry.remove(0);
				}
				else if( !unitsOfArtillery.isEmpty() ){
					
					unit = unitsOfArtillery.get(0);
					unitsOfArtillery.remove(0);
				}			
			
			}
			else if( unit.getType().equals(UnitType.INFANTRY) ) {
				
				numberOfRetreatingInfantryUnit++;
				
				if( !unitsOfInfantry.isEmpty() ) {
					
					unit = unitsOfInfantry.get(0);
					unitsOfInfantry.remove(0);
				}
				else if( !unitsOfCavalry.isEmpty() ) {
					
					unit = unitsOfCavalry.get(0);
					unitsOfCavalry.remove(0);
				}
				else if( !unitsOfArtillery.isEmpty() ){
					
					unit = unitsOfArtillery.get(0);
					unitsOfArtillery.remove(0);
				}
			
			
			}
			else if( unit.getType().equals(UnitType.ARTILLERY) ) {
				
				numberOfRetreatingArtilleryUnit++;
				
				if( !unitsOfInfantry.isEmpty() ) {
					
					unit = unitsOfInfantry.get(0);
					unitsOfInfantry.remove(0);
				}
				else if( !unitsOfCavalry.isEmpty() ) {
					
					unit = unitsOfCavalry.get(0);
					unitsOfCavalry.remove(0);
				}
				else if( !unitsOfArtillery.isEmpty() ){
					
					unit = unitsOfArtillery.get(0);
					unitsOfArtillery.remove(0);
				}
			
			
			}
			
		
		}
		
		setNumberOfSpareUnit( ( unitsOfCavalry.size() + unitsOfArtillery.size() + unitsOfInfantry.size() ) );
	}
		
	public void switchPositions() {
		
		for( int indexRow=0; indexRow < combatWidth; indexRow++) {
			
			if( firstRow[indexRow].getMorale() == 0 ) {
				
				getNewUnitForPosition( firstRow[indexRow] );
					
			}	
			
			if( secondRow[indexRow] != null && secondRow[indexRow].getMorale() == 0) {
				
				getNewUnitForPosition( secondRow[indexRow] );
			}
				
		}
					
		
	}
	
	
	
	
	// getter, setter
	
	public int getManpower() {
		return manpower;
	}
	
	public int getCombatWidth() {
		return combatWidth;
	}
	
	public double getInfantryToCavalryRatio() {
		return infantryToCavalryRatio;
	}
	
	public int getCavalryPositionValueInOneSide() {
		return cavalryPositionValueInOneSide;
	}
	
	public int getNumberOfSpareUnit() {
		return numberOfSpareUnit;
	}
	
	public void setManpower( int manpower ) {
		this.manpower = manpower;
	}
	
	public void setCombatWidth( int combatWidth ) {
		this.combatWidth = combatWidth;
	}
	
	public void setInfantryToCavalryRatio( int infantryToCavalryRatio ) {
		this.infantryToCavalryRatio = infantryToCavalryRatio;
	}
	
	public void setCavalryPositionValueInOneSide( int cavalryPositionValueInOneSide ) {
		this.cavalryPositionValueInOneSide = cavalryPositionValueInOneSide;
	}
	
	public void setNumberOfSpareUnit( int numberOfSpareUnit ) {
		this.numberOfSpareUnit = numberOfSpareUnit;
	}
	
	public int getNumberOfDeadInfantryUnit() {
		
		return numberOfDeadInfantryUnit;
	}
	
	public int getNumberOfDeadCavalryUnit() {
		
		return numberOfDeadCavalryUnit; 
	}
	
	public int getNumberOfDeadArtilleryUnit() {
		
		return numberOfDeadArtilleryUnit;
	}
	
	public int getNumberOfRetreatingInfantryUnit() {
		
		return numberOfRetreatingInfantryUnit;
	}
	
	public int getNumberOfRetreatingCavalryUnit() {
		
		return numberOfRetreatingCavalryUnit;
	}
	
	public int getNumberOfRetreatingArtilleryUnit() {
		
		return numberOfRetreatingArtilleryUnit;
	}
	
	public void setNumberOfDeadInfantryUnit( int NumberOfDeadInfantryUnit ) {
		
		this.numberOfDeadInfantryUnit += NumberOfDeadInfantryUnit;
	}
	
	public void setNumberOfDeadCavalryUnit( int NumberOfDeadCavalryUnit ) {
		
		this.numberOfDeadCavalryUnit += NumberOfDeadCavalryUnit; 
	}
	
	public void setNumberOfDeadArtilleryUnit( int NumberOfDeadArtilleryUnit ) {
		
		this.numberOfDeadArtilleryUnit += NumberOfDeadArtilleryUnit;
	}
	
	public void setNumberOfRetreatingInfantryUnit( int NumberOfRetreatingInfantryUnit ) {
		
		this.numberOfRetreatingInfantryUnit = NumberOfRetreatingInfantryUnit;
	}
	
	public void setNumberOfRetreatingCavalryUnit( int NumberOfRetreatingCavalryUnit ) {
		
		this.numberOfRetreatingCavalryUnit = NumberOfRetreatingCavalryUnit;
	}
	
	public void setNumberOfRetreatingArtilleryUnit( int NumberOfRetreatingArtilleryUnit ) {
		
		this.numberOfRetreatingArtilleryUnit = NumberOfRetreatingArtilleryUnit;
	}
	
	public boolean getIsWonBattle() {
		
		return isWonBattle;
	}
	
	public void setIsWonBattle( boolean isWonBattle ) {
		
		this.isWonBattle = isWonBattle;
	}
	
}
