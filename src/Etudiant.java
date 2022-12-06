public class Etudiant {
    protected int credits, dexterite, force, resistance, consitution, initiative, joueur, id;
    private boolean reserviste;
    private Strategies strategie;

    public Etudiant(int joueur, int id) {
        this.credits = 30;
        this.joueur = joueur;
        this.id = id;
    }

    public int soigner(Etudiant etudiant) {
        int x = (int) Math.random() * 100;
        if (x < 20 + this.dexterite * 6) {// soin réussi
            int soin = (int) Math.floor(Math.random() * 0.6 * (10 + etudiant.consitution));
            // System.out.println("soin réussi : " + soin);
            etudiant.addCredits(soin);
            return soin;
        }
        return 0;
    }

    public int attaquer(Etudiant etudiant) {
        int x = (int) Math.random() * 100;
        int degatReference = 10;
        double coeffDegats = Math.max(0, Math.min(100, 10 * force - 5 * etudiant.resistance)) / 100;
        if (x < 40 + 3 * dexterite) {// attaque réussie
            int degats = (int) Math.floor(Math.random() * (1 + coeffDegats) * degatReference);
            // System.out.println("attaque reussie : " + degats);
            etudiant.addCredits(-degats);
            return degats;
        }
        return 0;
    }

    // ----------getters/setters-------------
    public int getCredits() {
        return credits;
    }

    public void addCredits(int credits) {
        if (this.credits + credits > 30 + this.consitution) {
            this.credits = 30 + this.consitution;
        } else if (this.credits + credits <= 0) {
            this.credits = 0;
            // TODO : mort
        } else {
            this.credits += credits;
        }
    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }

    public void incrDexterite() {
        this.dexterite++;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void incrForce() {
        this.force++;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void incrResistance() {
        this.resistance++;
    }

    public int getConsitution() {
        return consitution;
    }

    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }

    public void incrConstitution() {
        this.consitution++;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void incrInitiative() {
        this.initiative++;
    }

    public boolean isReserviste() {
        return reserviste;
    }

    public void setReserviste(boolean auCombat) {
        this.reserviste = auCombat;
    }

    public Strategies getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategies strategie) {
        this.strategie = strategie;
    }

    @Override
    public String toString() {
        return "Etudiant j" + joueur + "#" + id + " [credits=" + credits + ", dex=" + dexterite + ", for=" + force
                + ", res="
                + resistance + ", con=" + consitution + ", ini=" + initiative + ", reserviste=" + reserviste
                + ", strat=" + strategie + "]";
    }
}
