package predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class VectorDemo {

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
        List<Integer> list = new ArrayList<>();
        list = Collections.synchronizedList(list);
        System.out.println(list);
    }

}
