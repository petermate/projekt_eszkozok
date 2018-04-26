package classes;

import java.util.Objects;

public class TechnologyModifier {
  
  public double morale;
  public double tactics;
  public int combatWidth;
  public double flankingRange;
  public double infantryFire;
  public double infantryShock;
  public double cavalryFire;
  public double cavalryShock;
  public double artilleryFire;
  public double artilleryShock;
  
  public TechnologyModifier() {
    
    this.morale = 0;
    this.tactics = 0.5;
    this.combatWidth = 15;
    this.flankingRange = 0;
    this.infantryFire = 0;
    this.infantryShock = 0;
    this.cavalryFire = 0;
    this.cavalryShock = 0;
    this.artilleryFire = 0;
    this.artilleryShock = 0;
  }
  
  public double getMorale() {
    return morale;
  }
  public void setMorale(double morale) {
    this.morale += morale;
  }
  public double getTactics() {
    return tactics;
  }
  public void setTactics(double tactics) {
    this.tactics += tactics;
  }
  public int getCombatWidth() {
    return combatWidth;
  }
  public void setCombatWidth(int combatWidth) {
    this.combatWidth += combatWidth;
  }
  public double getFlankingRange() {
    return flankingRange;
  }
  public void setFlankingRange(double flankingRange) {
    this.flankingRange += flankingRange;
  }
  public double getInfantryFire() {
    return infantryFire;
  }
  public void setInfantryFire(double infantryFire) {
    this.infantryFire += infantryFire;
  }
  public double getInfantryShock() {
    return infantryShock;
  }
  public void setInfantryShock(double infantryShock) {
    this.infantryShock += infantryShock;
  }
  public double getCavalryFire() {
    return cavalryFire;
  }
  public void setCavalryFire(double cavalryFire) {
    this.cavalryFire += cavalryFire;
  }
  public double getCavalryShock() {
    return cavalryShock;
  }
  public void setCavalryShock(double cavalryShock) {
    this.cavalryShock += cavalryShock;
  }
  public double getArtilleryFire() {
    return artilleryFire;
  }
  public void setArtilleryFire(double artilleryFire) {
    this.artilleryFire += artilleryFire;
  }
  public double getArtilleryShock() {
    return artilleryShock;
  }
  public void setArtilleryShock(double artilleryShock) {
    this.artilleryShock += artilleryShock;
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
      TechnologyModifier tm = (TechnologyModifier) o;
      // field comparison
      return  Objects.equals(morale, tm.morale)
              && Objects.equals(tactics, tm.tactics)
              && Objects.equals(combatWidth, tm.combatWidth)
              && Objects.equals(flankingRange, tm.flankingRange)
              && Objects.equals(infantryFire, tm.infantryFire)
              && Objects.equals(infantryShock, tm.infantryShock)
              && Objects.equals(cavalryFire, tm.cavalryFire)
              && Objects.equals(cavalryShock, tm.cavalryShock)
              && Objects.equals(artilleryFire, tm.artilleryFire)
              && Objects.equals(artilleryShock, tm.artilleryShock);
  }

}
