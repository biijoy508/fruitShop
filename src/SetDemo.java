import java.util.HashMap;

public class SetDemo {

    public static void main(final String[] args) {

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        addValues(map1);
        addValues2(map2);


      boolean hashSet = map1.keySet().retainAll(map2.keySet());
      System.out.println(map1.keySet().toString());
      System.out.println(map2.keySet().toString());


      System.out.println("Expected True: " + hashSet);

    }


    public static void addValues(final HashMap<String, Integer> map){

        map.put("Hej1", 5);
        map.put("Hej2", 5);
        map.put("Hej3", 5);
    }

    public static void addValues2(final HashMap<String, Integer> map){

        map.put("Hej1", 5);
        map.put("alkdgfjhadgf", 5);
        map.put("Hej3", 5);
    }

}
