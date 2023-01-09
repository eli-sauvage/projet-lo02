package models;
public class Joueur {
    private Armee armee;
    private int numeroJoueur;
    public Joueur(int numero) {
        this.numeroJoueur = numero;
        System.out.println("init joueur");
        armee = new Armee(numeroJoueur);
        armee.initDefaut();
    }

    
    /** 
     * @return Armee
     */
    public Armee getArmee() {
        return armee;
    }

    
    /** 
     * @param armee
     */
    public void setArmee(Armee armee) {
        this.armee = armee;
    }


    
    /** 
     * retourne le numero du joueur
     * @return int le numero du joueur
     */
    public int getNumero(){
        return this.numeroJoueur;
    }

    
    /** 
     * @return String le joueur sous forme de texte
     */
    @Override
    public String toString() {
        return "Joueur [armee=" + armee + "]";
    }
}
