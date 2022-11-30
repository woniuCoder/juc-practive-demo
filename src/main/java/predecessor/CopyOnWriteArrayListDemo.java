package predecessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//演示CopyOnWriteArrayList 迭代过程中修改数据内容
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is :" + list);
            String next = iterator.next();
            System.out.println(next);
            if (next.equals("2")) {
                list.remove("5");
            }
        }
    }

    public static void clearList() {
        List<String> list = new ArrayList<>();
        list.add("wang");
        list.add("wang");
        list.add("zhan");
        list.add("qi");
        for (String s : list) {
            if (s.equals("wang")) {
                list.remove(s);
            }
        }
        System.out.println(list);

    }

}
