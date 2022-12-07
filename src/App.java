public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        System.out.println("Bienvenue dans C\'est du brutal!");
        menu();
        Utils.sc.close();
    }
    private static void menu(){
        System.out.println("1 - lancer une partie\n2 - lancer une partie avec les armées aléatoires\n3 - voir les règles\n4 - quitter");
        switch (Utils.sc.nextLine()) {
            case "1":
                lancerPartie(true);
                break;
            case "2":
                lancerPartie(false);
                break;
            case "3":
                printRegles();
                break;
            case "4":
                break;
            default:
                System.out.println("commande non reconnue");
                menu();
                break;
        }
    }
    private static void lancerPartie(boolean reparition){
        System.out.println("lancement de la partie");
        new Partie(reparition);
    }
    private static void printRegles(){
        System.out.println("regles : ...");
        menu();
    }
}
