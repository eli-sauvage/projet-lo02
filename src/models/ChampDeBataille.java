package models;

public class ChampDeBataille {
    private Zone[] zones = new Zone[5];

    /**
     * initialise les zones avec de nouveaux objets Zone
     */
    public void initZones() {
        // initialisation du champ de bataille avec les 5 zones
        for (int i = 0; i < 5; i++) {
            zones[i] = new Zone(i);
        }
        return;
    }
    
    /** 
     * @param i l'indice de la zone
     * @return Zone la zone associee
     */
    public Zone getZone(int i) {
        return zones[i];
    }
    
    /** 
     * @return Zone[] la liste des zones
     */
    public Zone[] getZones(){
        return zones;
    }
}
