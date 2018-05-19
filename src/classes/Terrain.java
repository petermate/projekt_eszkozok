package classes;

class Terrain {
    int terrainPenalty;
    int crossingPenalty;
    boolean isFlat;

    public Terrain(int terrainPenalty, int crossingPenalty,boolean isFlat) {
        this.terrainPenalty = terrainPenalty;
        this.crossingPenalty = crossingPenalty;
        this.isFlat = isFlat;
    }

    public int getTerrainPenalty() {
        return terrainPenalty;
    }

    public int getCrossingPenalty() {
        return crossingPenalty;
    }

    public boolean isIsFlat() {
        return isFlat;
    }
    
}
