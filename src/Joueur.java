public class Joueur {
    private Armee armee;
    private int numeroJoueur;

    public Joueur(int numero) {
        this.numeroJoueur = numero;
        armee = new Armee(numeroJoueur);
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
