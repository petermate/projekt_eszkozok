package classes;

public class Unit {

	private String militaryGroup; 	//földrajzi elhelyezkedés
	private UnitType type;			//tipus ARTILLERY, CAVALRY, INFANTRY
	private int offensiveMorale;
	private int defensiveMorale;
	private int offensiveFire;
	private int defensiveFire;
	private int offensiveShock;
	private int defensiveShock;
	
	public Unit(String militaryGroup, UnitType type, int offensiveMorale, int defensiveMorale, int offensiveFire,
			int defensiveFire, int offensiveShock, int defensiveShock) {
		this.militaryGroup = militaryGroup;
		this.type = type;
		this.offensiveMorale = offensiveMorale;
		this.defensiveMorale = defensiveMorale;
		this.offensiveFire = offensiveFire;
		this.defensiveFire = defensiveFire;
		this.offensiveShock = offensiveShock;
		this.defensiveShock = defensiveShock;
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
	
}
