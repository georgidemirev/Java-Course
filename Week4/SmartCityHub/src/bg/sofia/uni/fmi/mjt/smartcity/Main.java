package bg.sofia.uni.fmi.mjt.smartcity;

import java.util.Iterator;
import java.util.TreeSet;


public class Main {

    public static void main(String[] args) {
        TreeSet<MyObject> objects = new TreeSet<>();

        MyObject name1 = new MyObject(3, "name1");
        MyObject name2 = new MyObject(2, "name2");
        MyObject name3 = new MyObject(3, "name3");
        MyObject name4 = new MyObject(1, "name4");
        objects.add(name1);
        objects.add(name2);
        objects.add(name4);
        objects.add(name3);

        Iterator<MyObject> iterator = objects.descendingIterator();
        for (int i = 0; i < 3; i++) {
            System.out.println(iterator.next().name);
        }
    }

    public static class MyObject implements Comparable {

        public int number;
        public String name;

        public MyObject(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            if (number < ((MyObject) o).number) {
                return -1;
            }
            return 1;
        }
    }
}
