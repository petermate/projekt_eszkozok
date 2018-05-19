package classes;

import java.util.Random;

public class Battle {

    Army attacker, defender;
    Terrain terrain;
    int day, terrainMod;
    int[] receivedCasualtiesAttacker;
    int[] receivedCasualtiesDefender;
    double[] receivedMoraleDamageAttacker;
    double[] receivedMoraleDamageDefender;
    int attackerCombatWidth;
    int defenderCombatWidth;

    public Battle(Army attacker, Army defender, Terrain terrain) {
        this.attacker = attacker;
        this.defender = defender;
        this.terrain = terrain;
        day = 0;
        attackerCombatWidth = attacker.getTm().getCombatWidth();
        defenderCombatWidth = defender.getTm().getCombatWidth();

        terrainMod = terrain.getTerrainPenalty();
        if (defender.getLeader().getManeuver() >= attacker.getLeader().getManeuver()) {
            terrainMod += terrain.getCrossingPenalty();
        }
    }

    public void start() {
        if (defender.getLeader().getManeuver() >= attacker.getLeader().getManeuver()) {
            terrainMod += terrain.getCrossingPenalty();
        }
        if (attacker.getMg().equals("nomad_group")) {
            for (CombatUnit cu : attacker.getUnits()) {
                cu.initializeNomadShock(terrain.isIsFlat());
            }
        }
        if (defender.getMg().equals("nomad_group")) {
            for (CombatUnit cu : defender.getUnits()) {
                cu.initializeNomadShock(terrain.isIsFlat());
            }
        }
        String phase = "fire";
        Random random = new Random();
        int attackerdice = random.nextInt(10);
        int defenderdice = random.nextInt(10);
        this.unitDeployment(attacker);
        this.unitDeployment(defender);
        while (day < 1000) {
            receivedCasualtiesAttacker = new int[attackerCombatWidth];
            receivedCasualtiesDefender = new int[defenderCombatWidth];
            receivedMoraleDamageAttacker = new double[attackerCombatWidth];
            receivedMoraleDamageDefender = new double[defenderCombatWidth];
            day++;
            this.insufficientSupport(attacker);
            this.insufficientSupport(defender);

            this.CoveringFire(attacker);
            this.CoveringFire(defender);

            this.Combat(attacker, defender, phase, attackerdice, 1,
                    receivedCasualtiesDefender, receivedMoraleDamageDefender, terrainMod);
            this.Combat(attacker, defender, phase, attackerdice, 2,
                    receivedCasualtiesDefender, receivedMoraleDamageDefender, terrainMod);

            this.Combat(defender, attacker, phase, defenderdice, 1, receivedCasualtiesAttacker, receivedMoraleDamageAttacker, 0);
            this.Combat(defender, attacker, phase, defenderdice, 2, receivedCasualtiesAttacker, receivedMoraleDamageAttacker, 0);

            this.dailyMoraleDamage(attacker);
            this.dailyMoraleDamage(defender);

            for (int i = 0; i < attackerCombatWidth; i++) {
                CombatUnit cu = attacker.getFirstRow()[i];
                if (cu != null) {
                    cu.setMorale(cu.getMorale() - receivedMoraleDamageAttacker[i]);
                    cu.setStrength(cu.getStrength() - receivedCasualtiesAttacker[i]);
                    if (cu.getMorale() == 0.0 || cu.getStrength() == 0) {
                        attacker.getRetreated().add(cu);
                        attacker.getFirstRow()[i] = null;
                    }
                }
                cu = attacker.getSecondRow()[i];
                if (cu != null) {
                    if (cu.getMorale() == 0.0) {
                        attacker.getRetreated().add(cu);
                        attacker.getSecondRow()[i] = null;
                    }
                }
            }

            for (int i = 0; i < defenderCombatWidth; i++) {
                CombatUnit cu = defender.getFirstRow()[i];
                if (cu != null) {
                    cu.setMorale(cu.getMorale() - receivedMoraleDamageDefender[i]);
                    cu.setStrength(cu.getStrength() - receivedCasualtiesDefender[i]);
                    if (cu.getMorale() == 0.0 || cu.getStrength() == 0) {
                        cu.setMorale(0);
                        defender.getRetreated().add(cu);
                        defender.getFirstRow()[i] = null;
                    }
                }
                cu = defender.getSecondRow()[i];
                if (cu != null) {
                    if (cu.getMorale() == 0.0) {
                        cu.setMorale(0);
                        defender.getRetreated().add(cu);
                        defender.getSecondRow()[i] = null;
                    }
                }

            }
            if (this.isDefeated(attacker)) {
                this.Results("defender");
                break;
            }
            if (this.isDefeated(defender)) {
                this.Results("attacker");
                break;
            }
            if (day == 999) {
                System.out.println("A szimulacio nem all le (999. nap)");
            }

            this.fill(attacker);
            this.fill(defender);

            if (day % 3 == 0) {
                if (phase.equals("fire")) {
                    phase = "shock";
                }
                if (phase.equals("shock")) {
                    phase = "fire";
                }
                attackerdice = random.nextInt(10);
                defenderdice = random.nextInt(10);
            }

            for (CombatUnit cu : attacker.getUnits()) {
                cu.updateFlankingRange();
            }
            for (CombatUnit cu : defender.getUnits()) {
                cu.updateFlankingRange();
            }

        }
    }

    void Results(String winner) {
        this.Losses(attacker);
        this.Losses(defender);

        //TODO: Sending data back to the UI
    }

    void Losses(Army army) {
        int deadinf = army.getNumOfRegInfantry() * 1000;
        int deadminf = army.getNumOfMercInfantry() * 1000;
        int deadcav = army.getNumOfRegCavalry() * 1000;
        int deadmcav = army.getNumOfMercCavalry() * 1000;
        int deadart = army.getNumOfRegArtillery() * 1000;
        int deadmart = army.getNumOfMercArtillery() * 1000;
        int manpowerloss = 0;
        for (CombatUnit cu : army.getUnits()) {
            if (cu.getType().equals(UnitType.INFANTRY) && !cu.isIsMercenary()) {
                deadinf -= cu.getStrength();
                manpowerloss += cu.getStrength();
            }
            if (cu.getType().equals(UnitType.INFANTRY) && cu.isIsMercenary()) {
                deadminf -= cu.getStrength();
            }
            if (cu.getType().equals(UnitType.CAVALRY) && !cu.isIsMercenary()) {
                deadcav -= cu.getStrength();
                manpowerloss += cu.getStrength();
            }
            if (cu.getType().equals(UnitType.CAVALRY) && cu.isIsMercenary()) {
                deadmcav -= cu.getStrength();
            }
            if (cu.getType().equals(UnitType.ARTILLERY) && !cu.isIsMercenary()) {
                deadart -= cu.getStrength();
                manpowerloss += cu.getStrength();
            }
            if (cu.getType().equals(UnitType.ARTILLERY) && cu.isIsMercenary()) {
                deadmart -= cu.getStrength();
            }
        }

        //TODO: Sending data back to the UI :
    }

    /**
     * Checks if all the units have 0 morale or not.
     *
     * @param army
     * @return
     */
    boolean isDefeated(Army army) {
        for (int i = 0; i < army.getNumOfUnits(); i++) {
            if (army.getUnits()[i].getMorale() > 0.0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reorganizes the army, considering that after retreating units there may
     * be gaps needing to be filled.
     *
     * @param army
     */
    void fill(Army army) {
        for (int i = 0; i < army.getTm().getCombatWidth(); i++) {
            if (army.getSecondRow()[i] != null && army.getFirstRow()[i] == null) {
                if (army.getSecondRow()[i].getType().equals(UnitType.ARTILLERY)) {
                    for (CombatUnit cu : army.getReserve()) {
                        if (!cu.getType().equals(UnitType.ARTILLERY)) {
                            army.getFirstRow()[i] = cu;
                            army.getReserve().remove(cu);
                            break;
                        }
                    }
                } else {
                    army.getFirstRow()[i] = army.getSecondRow()[i];
                    army.getSecondRow()[i] = null;
                }
            }
        }
        for (int i = 0; i < army.getTm().getCombatWidth(); i++) {
            if (army.getFirstRow()[i] == null) {
                int direction = 0;
                if (i < (army.getTm().getCombatWidth() - 1) / 2) {
                    direction = -1;
                } else {
                    direction = 1;
                }
                int j = i;
                while (i + direction >= 0 || i + direction <= army.getTm().getCombatWidth()) {
                    j += direction;
                    if (army.getFirstRow()[j] != null) {
                        army.getFirstRow()[i] = army.getFirstRow()[j];
                        break;
                    }
                }
            }
            if (army.getSecondRow()[i] == null) {
                for (CombatUnit cu : army.getReserve()) {
                    army.getSecondRow()[i] = cu;
                    army.getReserve().remove(cu);
                    break;
                }
                int direction = 0;
                if (i < (army.getTm().getCombatWidth() - 1) / 2) {
                    direction = -1;
                } else {
                    direction = 1;
                }
                int j = i;
                while (i + direction >= 0 || i + direction <= army.getTm().getCombatWidth()) {
                    j += direction;
                    if (army.getSecondRow()[j] != null) {
                        army.getSecondRow()[i] = army.getSecondRow()[j];
                        break;
                    }
                }
            }
        }

    }

    /**
     * Applies the daily morale damage to all units in an army.
     *
     * @param army
     */
    public void dailyMoraleDamage(Army army) {
        for (CombatUnit cu : army.getUnits()) {
            cu.setMorale(cu.getMorale() - 0.03);
        }
    }

    /**
     * Updates the defensive values of all the deployed units in the first row
     * in a given army, depending on whether they are being covered by an
     * artillery unit positioned directly behind them or not.
     *
     * @param army
     */
    public void CoveringFire(Army army) {
        for (int i = 0; i < army.getTm().getCombatWidth(); i++) {
            if (army.getFirstRow()[i] != null && army.getSecondRow()[i] != null
                    && army.getSecondRow()[i].getType().equals(UnitType.ARTILLERY)) {
                army.getFirstRow()[i].coveringFire(army.getSecondRow()[i].getDefensiveShock(),
                        army.getSecondRow()[i].getDefensiveFire(), army.getSecondRow()[i].getDefensiveMorale());
            }
        }
    }

    /**
     * Selects the target to attack for each unit in the attacker army, and
     * stores the data of casualties and morale damage for "simultaneous"
     * application at the end of the day.
     *
     * @param a the army currently dealing damage
     * @param d the army currently receiving damage
     * @param phase
     * @param droll
     * @param row 1 - first row, any other number - second row
     * @param rcd the array storing received casualties
     * @param rmd the array strong the received morale damage
     * @param terrainMod all terrain/crossing based penalties for the attacker,
     * otherwise 0
     */
    public void Combat(Army a, Army d, String phase, int droll, int row,
            int[] rcd, double[] rmd, int terrainMod) {
        int shift = (a.getTm().getCombatWidth() - d.getTm().getCombatWidth()) / 2;
        for (int i = 0; i < a.getTm().getCombatWidth(); i++) {
            Casualty c;
            CombatUnit attackingUnit;
            boolean isValidAttacker = false;
            if (row == 1) {
                attackingUnit = a.getFirstRow()[i];
                isValidAttacker = true;
            } else {
                attackingUnit = a.getSecondRow()[i];
                if (attackingUnit != null) {
                    if (attackingUnit.getType().equals(UnitType.ARTILLERY)) {
                        isValidAttacker = true;
                    }
                }
            }
            if (attackingUnit != null && isValidAttacker) {
                if (d.getFirstRow()[i - shift] != null) {
                    c = new Casualty(attackingUnit,
                            d.getFirstRow()[i - shift], terrainMod);
                    if (phase.equals("fire")) {
                        rcd[i - shift] += c.firePhaseCasualties(droll);
                        rmd[i - shift] += c.firePhaseMoraleDamage(droll);
                    } else if (phase.equals("shock")) {
                        rcd[i - shift] += c.shockPhaseCasualties(droll);
                        rmd[i - shift] += c.shockPhaseMoraleDamage(droll);
                    } else {
                        int j = 0;
                        boolean over = false;
                        while (!over) {
                            if (i <= ((a.getTm().getCombatWidth() - 1) / 2)) {
                                j++;
                            } else {
                                j--;
                            }
                            if (Math.abs(j) > attackingUnit.getFlankingRange()) {
                                break;
                            }
                            if (d.getFirstRow()[i - shift + j] != null) {
                                c = new Casualty(attackingUnit,
                                        d.getFirstRow()[i - shift + j], terrainMod);
                                if (phase.equals("fire")) {
                                    rcd[i - shift + j] += c.firePhaseCasualties(droll);
                                    rmd[i - shift + j] += c.firePhaseMoraleDamage(droll);
                                } else if (phase.equals("shock")) {
                                    rcd[i - shift + j] += c.shockPhaseCasualties(droll);
                                    rmd[i - shift + j] += c.shockPhaseMoraleDamage(droll);
                                }
                                over = true;
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * Decides whether the penalty for having too much cavalry should be applied
     * or not.
     *
     * @param army
     */
    public void insufficientSupport(Army army) {
        int sumOfInf = 0;
        int sumOfCav = 0;
        for (CombatUnit cu : army.getFirstRow()) {
            if (cu.getType().equals(UnitType.INFANTRY)) {
                sumOfInf += cu.getStrength();
            } else if (cu.getType().equals(UnitType.CAVALRY)) {
                sumOfCav += cu.getStrength();
            }
        }
        for (CombatUnit cu : army.getSecondRow()) {
            if (cu.getType().equals(UnitType.INFANTRY)) {
                sumOfInf += cu.getStrength();
            } else if (cu.getType().equals(UnitType.CAVALRY)) {
                sumOfCav += cu.getStrength();
            }
        }
        if (sumOfCav > (sumOfCav + sumOfInf) * (0.5 + army.getIm().getBonusCavalryToInfRatio())) {
            for (CombatUnit cu : army.getUnits()) {
                cu.setTactics(cu.getTactics() * 0.75);
            }
        }
    }

    /**
     * Initializes the starting position (first row, second row, reserves) of
     * each unit before the first day of battle in a given army.
     *
     * @param army
     */
    public void unitDeployment(Army army) {
        int cw = army.getTm().getCombatWidth();
        int center = (cw - 1) / 2;
        int frshift = 0;
        int srshift = 0;
        int infindex = 0;
        int cavindex = 0;
        int artindex = 0;
        int minfindex = 0;
        int mcavindex = 0;
        int martindex = 0;
        if (army.getNumOfInfantry() < cw) {
            for (int i = 0; i < army.numOfMercInfantry; i++) {
                frshift = Deploy(army.getMinf()[i], army.getFirstRow(), center, frshift);
                minfindex = i;
            }

            for (int i = 0; i < army.numOfRegInfantry; i++) {
                frshift = Deploy(army.getInf()[i], army.getFirstRow(), center, frshift);
                infindex = i;
            }

            for (int i = 0; i < army.numOfMercCavalry; i++) {
                if (frshift + center < 0 || frshift + center > (cw - 1)) {
                    break;
                }
                frshift = Deploy(army.getMcav()[i], army.getFirstRow(), center, frshift);
                mcavindex = i;
            }

            for (int i = 0; i < army.numOfRegCavalry; i++) {
                if (frshift + center < 0 || frshift + center > (cw - 1)) {
                    break;
                }
                frshift = Deploy(army.getCav()[i], army.getFirstRow(), center, frshift);
                cavindex = i;
            }
            for (int i = 0; i < army.numOfMercArtillery; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                if (army.getFirstRow()[center + srshift] != null) {
                    srshift = Deploy(army.getMart()[i], army.getSecondRow(), center, srshift);
                } else {
                    if (frshift + center < 0 || frshift + center > (cw - 1)) {
                        break;
                    }
                    frshift = Deploy(army.getMart()[i], army.getFirstRow(), center, frshift);
                }
                martindex = i;
            }
            for (int i = 0; i < army.numOfRegArtillery; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                if (army.getFirstRow()[center + srshift] != null) {
                    srshift = Deploy(army.getArt()[i], army.getSecondRow(), center, srshift);
                } else {
                    if (frshift + center < 0 || frshift + center > (cw - 1)) {
                        break;
                    }
                    frshift = Deploy(army.getArt()[i], army.getFirstRow(), center, frshift);
                }
                artindex = i;
            }

            for (int i = mcavindex; i < army.numOfMercCavalry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getMcav()[i], army.getSecondRow(), center, srshift);
                mcavindex = i;
            }
            for (int i = cavindex; i < army.numOfRegCavalry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getCav()[i], army.getSecondRow(), center, srshift);
                cavindex = i;
            }

        } else {
            int cavToTheSides = (int) Math.round(army.getTm().getCombatWidth()
                    * (0.5 + army.getIm().getBonusCavalryToInfRatio()));
            if (army.getNumOfCavalry() < cavToTheSides) {
                cavToTheSides = army.getNumOfCavalry();
            }
            int x = cavToTheSides;
            for (int i = 0; i < army.numOfMercInfantry; i++) {
                if (minfindex == cw - x - 1) {
                    break;
                }
                frshift = Deploy(army.getMinf()[i], army.getFirstRow(), center, frshift);
                minfindex = i;
            }

            for (int i = 0; i < army.numOfRegInfantry; i++) {
                if (minfindex + infindex == cw - x - 2) {
                    break;
                }
                frshift = Deploy(army.getInf()[i], army.getFirstRow(), center, frshift);
                infindex = i;
            }
            for (int i = 0; i < army.numOfMercCavalry; i++) {
                if (frshift + center < 0 || frshift + center > (cw - 1)) {
                    break;
                }
                frshift = Deploy(army.getMcav()[i], army.getFirstRow(), center, frshift);
                mcavindex = i;
            }

            for (int i = 0; i < army.numOfRegCavalry; i++) {
                if (frshift + center < 0 || frshift + center > (cw - 1)) {
                    break;
                }
                frshift = Deploy(army.getCav()[i], army.getFirstRow(), center, frshift);
                cavindex = i;
            }
            for (int i = 0; i < army.numOfMercArtillery; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getMart()[i], army.getSecondRow(), center, srshift);
                martindex = i;
            }
            for (int i = 0; i < army.numOfRegArtillery; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getArt()[i], army.getSecondRow(), center, srshift);
                artindex = i;
            }
            for (int i = minfindex; i < army.numOfMercInfantry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getMinf()[i], army.getSecondRow(), center, srshift);
                minfindex = i;
            }
            for (int i = infindex; i < army.numOfRegInfantry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getInf()[i], army.getSecondRow(), center, srshift);
                infindex = i;
            }

            for (int i = mcavindex; i < army.numOfMercCavalry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getMcav()[i], army.getSecondRow(), center, srshift);
                mcavindex = i;
            }
            for (int i = cavindex; i < army.numOfRegCavalry; i++) {
                if (srshift + center < 0 || srshift + center > (cw - 1)) {
                    break;
                }
                srshift = Deploy(army.getCav()[i], army.getSecondRow(), center, srshift);
                cavindex = i;
            }
        }
        for (int i = minfindex; i < army.getNumOfMercInfantry(); i++) {
            army.getReserve().add(army.getMinf()[i]);
        }
        for (int i = infindex; i < army.getNumOfRegInfantry(); i++) {
            army.getReserve().add(army.getInf()[i]);
        }
        for (int i = mcavindex; i < army.getNumOfMercCavalry(); i++) {
            army.getReserve().add(army.getCav()[i]);
        }
        for (int i = cavindex; i < army.getNumOfRegCavalry(); i++) {
            army.getReserve().add(army.getCav()[i]);
        }
        for (int i = martindex; i < army.getNumOfMercArtillery(); i++) {
            army.getReserve().add(army.getMart()[i]);
        }
        for (int i = artindex; i < army.getNumOfRegArtillery(); i++) {
            army.getReserve().add(army.getArt()[i]);
        }
    }

    public int Deploy(CombatUnit cu, CombatUnit[] row, int center, int shift) {
        row[center + shift] = cu;
        if (shift > 0) {
            return shift * -1;
        }
        return shift * -1 + 1;
    }

}
