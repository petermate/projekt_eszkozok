package classes;

import java.util.ArrayList;
import java.util.List;

public class Army {

    Leader leader;
    Unit infantry, cavalry, artillery;
    int numOfRegInfantry, numOfRegCavalry, numOfRegArtillery, numOfMercInfantry,
            numOfMercCavalry, numOfMercArtillery, numOfUnits, numOfInfantry,
            numOfCavalry, numOfArtillery;
    InputModifier im;
    TechnologyModifier tm;
    String mg;
    CombatUnit[] inf, cav, art, minf, mcav, mart;
    CombatUnit[] units;
    CombatUnit[] firstRow, secondRow;
    List<CombatUnit> reserve, retreated;

    public Army(int fire, int shock, int maneuver, String mg, Unit infantry,
            Unit cavalry, Unit artillery, int numOfRegInfantry, int numOfRegCavalry,
            int numOfRegArtillery, int numOfMercInfantry, int numOfMercCavalry,
            int numOfMercArtillery, InputModifier im, TechnologyModifier tm) {
        leader = new Leader(fire, shock, maneuver);
        this.infantry = infantry;
        this.cavalry = cavalry;
        this.artillery = artillery;
        this.im = im;
        this.tm = tm;
        this.mg = mg;
        inf = new CombatUnit[numOfRegInfantry];
        cav = new CombatUnit[numOfRegCavalry];
        art = new CombatUnit[numOfRegArtillery];
        minf = new CombatUnit[numOfMercInfantry];
        mcav = new CombatUnit[numOfMercCavalry];
        mart = new CombatUnit[numOfMercArtillery];
        this.numOfMercInfantry = numOfMercInfantry;
        this.numOfMercCavalry = numOfMercCavalry;
        this.numOfMercArtillery = numOfMercArtillery;
        this.numOfRegInfantry = numOfRegInfantry;
        this.numOfRegCavalry = numOfRegCavalry;
        this.numOfRegArtillery = numOfRegArtillery;
        this.numOfUnits = numOfRegInfantry + numOfRegCavalry + numOfRegArtillery
                + numOfMercInfantry + numOfMercCavalry + numOfMercArtillery;
        numOfInfantry = numOfRegInfantry + numOfMercInfantry;
        numOfCavalry = numOfRegCavalry + numOfMercCavalry;
        numOfArtillery = numOfRegArtillery + numOfMercArtillery;
        units = new CombatUnit[numOfUnits];
        firstRow = new CombatUnit[tm.getCombatWidth()];
        secondRow = new CombatUnit[tm.getCombatWidth()];
        reserve = new ArrayList<>();
        retreated = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < numOfRegInfantry; i++, index++) {
            inf[i] = new CombatUnit(infantry, tm, im, leader, false);
            units[index] = inf[i];
        }

        for (int i = 0; i < numOfRegCavalry; i++, index++) {
            cav[i] = new CombatUnit(cavalry, tm, im, leader, false);
            units[index] = cav[i];
        }
        for (int i = 0; i < numOfRegArtillery; i++, index++) {
            art[i] = new CombatUnit(artillery, tm, im, leader, false);
            units[index] = art[i];
        }
        for (int i = 0; i < numOfMercInfantry; i++, index++) {
            minf[i] = new CombatUnit(infantry, tm, im, leader, true);
            units[index] = minf[i];
        }
        for (int i = 0; i < numOfMercCavalry; i++, index++) {
            mcav[i] = new CombatUnit(cavalry, tm, im, leader, true);
            units[index] = mcav[i];;
        }
        for (int i = 0; i < numOfMercArtillery; i++, index++) {
            mart[i] = new CombatUnit(artillery, tm, im, leader, true);
            units[index] = mart[i];
        }
    }

    public Leader getLeader() {
        return leader;
    }

    public Unit getInfantry() {
        return infantry;
    }

    public Unit getCavalry() {
        return cavalry;
    }

    public Unit getArtillery() {
        return artillery;
    }

    public int getNumOfRegInfantry() {
        return numOfRegInfantry;
    }

    public int getNumOfRegCavalry() {
        return numOfRegCavalry;
    }

    public int getNumOfRegArtillery() {
        return numOfRegArtillery;
    }

    public int getNumOfMercInfantry() {
        return numOfMercInfantry;
    }

    public int getNumOfMercCavalry() {
        return numOfMercCavalry;
    }

    public int getNumOfMercArtillery() {
        return numOfMercArtillery;
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public int getNumOfInfantry() {
        return numOfInfantry;
    }

    public int getNumOfCavalry() {
        return numOfCavalry;
    }

    public int getNumOfArtillery() {
        return numOfArtillery;
    }

    public InputModifier getIm() {
        return im;
    }

    public TechnologyModifier getTm() {
        return tm;
    }

    public String getMg() {
        return mg;
    }

    public CombatUnit[] getInf() {
        return inf;
    }

    public CombatUnit[] getCav() {
        return cav;
    }

    public CombatUnit[] getArt() {
        return art;
    }

    public CombatUnit[] getMinf() {
        return minf;
    }

    public CombatUnit[] getMcav() {
        return mcav;
    }

    public CombatUnit[] getMart() {
        return mart;
    }

    public CombatUnit[] getUnits() {
        return units;
    }

    public CombatUnit[] getFirstRow() {
        return firstRow;
    }

    public CombatUnit[] getSecondRow() {
        return secondRow;
    }

    public List<CombatUnit> getReserve() {
        return reserve;
    }

    public List<CombatUnit> getRetreated() {
        return retreated;
    }

}
