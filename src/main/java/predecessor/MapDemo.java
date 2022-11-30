package predecessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("东哥", 38);
        map.put("西哥", 28);
        Set<String> strings = map.keySet();
        System.out.println(strings);
        System.out.println(map.get("东哥"));
        System.out.println(map.size());
    }

}
