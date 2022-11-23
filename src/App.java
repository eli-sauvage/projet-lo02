import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue dans C\'est du brutal!");
        menu();
    }
    private static void menu(){
        System.out.println("1 - lancer une partie\n2 - voir les règles\n3 - quitter");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());
        String message = "";
        if(sc.hasNextLine()){message = sc.nextLine();}
        System.out.println(message);
        switch (message) {
            case "1":
                lancerPartie();
                sc.close();
                break;
            case "2":
                printRegles();
                break;
            case "3":
                break;
            default:
                System.out.println("commande non reconnue");
                sc.next();
                menu();
                break;
        }
        sc.close();

    }
    private static void lancerPartie(){
        System.out.println("lancement de la partie");
        while(true){}
    }
    private static void printRegles(){
        System.out.println("regles : ...");
        menu();
    }
}
