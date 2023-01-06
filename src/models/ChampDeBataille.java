package models;
import java.util.*;

public class ChampDeBataille {
    private Zone[] zones = new Zone[5];

    public void initZones() {
        // initialisation du champ de bataille avec les 5 zones
        for (int i = 0; i < 5; i++) {
            zones[i] = new Zone(i);
        }
        return;
    }
    public Zone getZone(int i) {
        return zones[i];
    }
    public Zone[] getZones(){
        return zones;
    }
}
