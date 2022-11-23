public class Joueur {
    private Armee armee;

    

    public Joueur() {
        armee = new Armee();
        armee.initDefaut();
    }

    public Armee getArmee() {
        return armee;
    }

    public void setArmee(Armee armee) {
        this.armee = armee;
    }

    @Override
    public String toString() {
        return "Joueur [armee=" + armee + "]";
    }
}
