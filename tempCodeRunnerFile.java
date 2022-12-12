import java.util.*;
private class TestCollections {
 public static void main (String[] args) {
Integer un = new Integer(1);
Integer deux = new Integer(2);
Integer trois = new Integer(3);
// Test Elements non dupliqués
HashSet<Integer> hs = new HashSet<Integer>();
hs.add(un);
hs.add(deux);
hs.add(trois);
// Cet élément ne sera pas rajouté
hs.add(un);
for (Iterator it = hs.iterator(); it.hasNext(); ) {
 Integer itg = (Integer) it.next();
 System.out.println(itg);
}
ArrayList<Integer> al = new ArrayList<Integer>();
al.add(un);
al.add(deux);
al.add(trois);
al.add(un);
for (Iterator it = al.iterator(); it.hasNext(); ) {
 Integer itg = (Integer) it.next();
 System.out.println(itg);
}
String unStr = "Un";
String deuxStr = "Deux";
String troisStr = "Trois";
HashMap<String,Integer> hm = new HashMap<String,Integer>();
hm.put(unStr, un);
hm.put(deuxStr, deux);
hm.put(troisStr, trois);
for (Iterator<String> it = hm.keySet().iterator(); it.hasNext(); ) {
 String key = it.next();
 System.out.println(hm.get(key));
}
 }
}
