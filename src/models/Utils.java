package models;
import java.io.*;
import java.util.*;

public class Utils {
    public static final Scanner sc = new Scanner(System.in);

    public static String input() {
        System.out.print("> ");
        return Utils.sc.nextLine();
    }

    public static void attendreEntree() {
        System.out.print("[ENTREE pour continuer]");
        Utils.sc.nextLine();
    }

    public static void attendreEntree(String msg) {
        System.out.print("[ENTREE pour " + msg + "]");
        Utils.sc.nextLine();
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static <T> boolean containsArray(T[] array, T elemnt) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemnt || array[i].equals(elemnt)) {
                return true;
            }
        }
        return false;
    }

    public static String zoneIndexToString(int zone) {
        if (zone == 0)
            return "BU";
        else if (zone == 1)
            return "BDE";
        else if (zone == 2)
            return "Q. Admin";
        else if (zone == 3)
            return "Halle Indus";
        else if (zone == 4)
            return "Halle Sportive";
        else
            return "";
    }

    public static void removeLogfiles() {
        for (int i = 0; i < 5; i++) {
            File file = new File(Integer.toString(i) + ".log");
            file.delete();
        }
    }

    public static void sleep(int t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            System.out.println("probleme durant thread.sleep");
        }
    }
}
