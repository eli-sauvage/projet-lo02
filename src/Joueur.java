public class Joueur {
    private Armee armee;
    private int numeroJoueur;
    private String branche;
    public Joueur(int numero) {
        this.numeroJoueur = numero;
        System.out.println("init joueur");
        armee = new Armee(numeroJoueur);
        armee.initDefaut();
    }

    public Armee getArmee() {
        return armee;
    }

    public void setArmee(Armee armee) {
        this.armee = armee;
    }

    public void setBranche(String  branche) {
        this.branche = branche;
    }

    public int getNumero(){
        return this.numeroJoueur;
    }

    @Override
    public String toString() {
        return "Joueur [armee=" + armee + "]";
    }
}
