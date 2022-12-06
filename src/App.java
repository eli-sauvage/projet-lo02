public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        System.out.println("Bienvenue dans C\'est du brutal!");
        menu();
        Utils.sc.close();
    }
    private static void menu(){
        System.out.println("1 - lancer une partie\n2 - voir les règles\n3 - quitter");
        switch (Utils.sc.nextLine()) {
            case "1":
                lancerPartie();
                break;
            case "2":
                printRegles();
                break;
            case "3":
                break;
            default:
                System.out.println("commande non reconnue");
                menu();
                break;
        }
    }
    private static void lancerPartie(){
        System.out.println("lancement de la partie");
        new Partie();
    }
    private static void printRegles(){
        System.out.println("regles : ...");
        menu();
    }
}
