import java.util.*;
public class Utils{
    public static final Scanner sc = new Scanner(System.in);
    public static void clearConsole(){
        for(int i=0;i<50;i++)System.out.println();
    }
    public static <T> boolean containsArray(T[] array, T elemnt){
        for(int i=0; i<array.length; i++){
            if(array[i] == elemnt || array[i].equals(elemnt)){
                return true;
            }
        }
        return false;
    }
}
