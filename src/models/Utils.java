package models;
import java.io.*;
import java.util.*;
import java.awt.*;
public class Utils {
    public static final Scanner sc = new Scanner(System.in);
    public static final Color bgColor = new Color(255, 128, 192);

    
    /** 
     * @return String
     */
    public static String input() {
        System.out.print("> ");
        return Utils.sc.nextLine();
    }


    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    
    /** 
     * @param array
     * @param elemnt
     * @return boolean
     */
    public static <T> boolean containsArray(T[] array, T elemnt) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemnt || array[i].equals(elemnt)) {
                return true;
            }
        }
        return false;
    }

    
    /** 
     * @param zone
     * @return String
     */
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

    
    /** 
     * @param t
     */
    public static void sleep(int t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            System.out.println("probleme durant thread.sleep");
        }
    }
}
