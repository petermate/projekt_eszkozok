package classes;

import java.util.Objects;

public class Unit {

	private String militaryGroup; 	//földrajzi elhelyezkedés
	private UnitType type;			//típus ARTILLERY, CAVALRY, INFANTRY
	private int maneuver;
	private int offensiveMorale;
	private int defensiveMorale;
	private int offensiveFire;
	private int defensiveFire;
	private int offensiveShock;
	private int defensiveShock;
	
	public Unit(String militaryGroup, UnitType type, int maneuver, int offensiveMorale, int defensiveMorale, int offensiveFire,
			int defensiveFire, int offensiveShock, int defensiveShock) {
		this.militaryGroup = militaryGroup;
		this.type = type;
		this.maneuver = maneuver;
		this.offensiveMorale = offensiveMorale;
		this.defensiveMorale = defensiveMorale;
		this.offensiveFire = offensiveFire;
		this.defensiveFire = defensiveFire;
		this.offensiveShock = offensiveShock;
		this.defensiveShock = defensiveShock;
	}
	
	public Unit() {

	}

	public String getUnitType() {
		return militaryGroup;
	}

	public void setUnitType(String militaryGroup) {
		this.militaryGroup = militaryGroup;
	}

	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public int getOffensiveMorale() {
		return offensiveMorale;
	}

	public void setOffensiveMorale(int offensiveMorale) {
		this.offensiveMorale = offensiveMorale;
	}

	public int getDefensiveMorale() {
		return defensiveMorale;
	}

	public void setDefensiveMorale(int defensiveMorale) {
		this.defensiveMorale = defensiveMorale;
	}

	public int getOffensiveFire() {
		return offensiveFire;
	}

	public void setOffensiveFire(int offensiveFire) {
		this.offensiveFire = offensiveFire;
	}

	public int getDefensiveFire() {
		return defensiveFire;
	}

	public void setDefensiveFire(int defensiveFire) {
		this.defensiveFire = defensiveFire;
	}

	public int getOffensiveShock() {
		return offensiveShock;
	}

	public void setOffensiveShock(int offensiveShock) {
		this.offensiveShock = offensiveShock;
	}

	public int getDefensiveShock() {
		return defensiveShock;
	}

	public void setDefensiveShock(int defensiveShock) {
		this.defensiveShock = defensiveShock;
	}
	
	public int getManeuver() {
		return maneuver;
	}

	public void setManeuver(int maneuver) {
		this.maneuver = maneuver;
	}
	
	@Override
	public String toString() {
		return "" + this.militaryGroup + " " + this.type;
	}

	@Override
	public boolean equals(Object o) {
		// self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    Unit unit = (Unit) o;
	    // field comparison
	    return Objects.equals(maneuver, unit.maneuver)
	            && Objects.equals(type, unit.type)
	            && Objects.equals(militaryGroup, unit.militaryGroup)
	            && Objects.equals(offensiveMorale, unit.offensiveMorale)
	            && Objects.equals(defensiveMorale, unit.defensiveMorale)
	            && Objects.equals(offensiveFire, unit.offensiveFire)
	            && Objects.equals(defensiveFire, unit.defensiveFire)
	            && Objects.equals(offensiveShock, unit.offensiveShock)
	            && Objects.equals(defensiveShock, unit.defensiveShock);
	}
	
}
