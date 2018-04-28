package classes;


public class Army {
	
	public int manpower;
	public int combatWidth;
	public int infantryToCavalryRatio; // százalékosan, 0 és 100 között
	public int cavalryPositionValueInOneSide; // X position to each side for the units of cavalry
	public int artilleryPositionValue;

	public int numberOfInfantry;
	public int numberOfCavalry;
	public int numberOfArtillery;
	public int numberOfSpareUnit;
	
	Unit[] firstRow;
	Unit[] secondRow;
	
	
	public Army( int combatWidth, int cavalryPositionValueInOneSide, int artilleryPositionValue) {
		this.manpower = 1000;
		this.combatWidth = combatWidth;
		this.infantryToCavalryRatio = 50;
		this.cavalryPositionValueInOneSide = cavalryPositionValueInOneSide;
		this.artilleryPositionValue = artilleryPositionValue;
				
		firstRow = new Unit[combatWidth];
		secondRow = new Unit[combatWidth];
		
		numberOfArtillery = artilleryPositionValue;		
		numberOfInfantry = ( manpower - numberOfArtillery ) * ( infantryToCavalryRatio / 100 ) ;
		numberOfCavalry = ( manpower - numberOfArtillery ) - numberOfInfantry  ;
		this.numberOfSpareUnit = 0;
		
		deployUnits();
		
	}
	 
	public Army() {
		
	}
	
	public void deployUnits() {
		
		// first row
		
		for( int indexFirstRow = 0; indexFirstRow < combatWidth; indexFirstRow++) {
			
			setRow( firstRow, indexFirstRow );
			
		}		
		
		// second row
		for( int indexSecondRow = 0; indexSecondRow < combatWidth; indexSecondRow++) {
			
			setRow( secondRow, indexSecondRow );
			
		}
		
		setNumberOfSpareUnit( ( numberOfCavalry + numberOfArtillery + numberOfInfantry) );
	}	
	
	public void setRow( Unit[] row, int index ) {
		
				
		int artilleryIndexBegin = ( combatWidth / 2 ) - ( numberOfArtillery / 2 ); 
		
	
		if( ( index < cavalryPositionValueInOneSide ) || ( index > combatWidth - cavalryPositionValueInOneSide) ) {
			
			if( numberOfCavalry != 0 ) {
				
				row[index] = new Unit();
				row[index].setType(UnitType.CAVALRY);
				setNumberOfCavalry( ( numberOfCavalry-1) );
			}
			else {
				
				if( numberOfInfantry != 0 ) {
					
					row[index] = new Unit();
					row[index].setType(UnitType.INFANTRY);
					setNumberOfInfantry( ( numberOfInfantry-1) );	
				}
								
			}
			
		}			
		else if( ( row.equals(secondRow) ) && ( ( index >  artilleryIndexBegin ) || ( index < ( artilleryIndexBegin + numberOfArtillery ) ) ) ) {
			
			if( numberOfArtillery != 0 ) {
				
				row[index] = new Unit();
				row[index].setType(UnitType.ARTILLERY);
				setNumberOfArtillery( ( numberOfArtillery-1) );
			}
			else {
				
				if( numberOfInfantry != 0 ) {
					
					row[index] = new Unit();
					row[index].setType(UnitType.INFANTRY);
					setNumberOfInfantry( ( numberOfInfantry-1) );	
				}
								
			}
			
		}
		else {
			
			if( numberOfInfantry != 0 ) {
				
				row[index] = new Unit();
				row[index].setType(UnitType.INFANTRY);
				setNumberOfInfantry( ( numberOfInfantry-1) );	
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
	
	public int getArtilleryPositionValue() {
		return artilleryPositionValue;
	}
	
	public int getNumberOfInfantry() {
		return numberOfInfantry;
	}
	
	public int getNumberOfCavalry() {
		return numberOfCavalry;
	}
	
	public int getNumberOfArtillery() {
		return numberOfArtillery;
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
	
	public void setArtilleryPositionValue( int artilleryPositionValue ) {
		this.artilleryPositionValue = artilleryPositionValue;
	}
	
	public void setNumberOfInfantry( int numberOfInfantry ) {
		this.numberOfInfantry = numberOfInfantry;
	}
	
	public void setNumberOfCavalry( int numberOfCavalry ) {
		this.numberOfCavalry = numberOfCavalry;
	}
	
	public void setNumberOfArtillery( int numberOfArtillery ) {
		this.numberOfArtillery = numberOfArtillery;
	}
	
	public void setNumberOfSpareUnit( int numberOfSpareUnit ) {
		this.numberOfSpareUnit = numberOfSpareUnit;
	}
	
}
