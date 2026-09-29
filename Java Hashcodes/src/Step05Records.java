import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Step05Records {

    record Point(int x, int y) {}

    record Tags(String[] values) {}

    record SafeTags(List<String> values) {
        SafeTags {
            values = List.copyOf(values);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Records generate equals(), hashCode() and toString() ===");
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        System.out.println(a + " equals " + b + ": " + a.equals(b));
        System.out.println("hash codes: " + a.hashCode() + " / " + b.hashCode());

        Set<Point> set = new HashSet<>(Set.of(a));
        System.out.println("set.contains(b): " + set.contains(b));
        System.out.println();

        System.out.println("=== Gotcha: arrays inside records use identity ===");
        Tags t1 = new Tags(new String[] {"java", "grad"});
        Tags t2 = new Tags(new String[] {"java", "grad"});
        System.out.println("Arrays.equals on contents: " + Arrays.equals(t1.values(), t2.values()));
        System.out.println("t1.equals(t2)            : " + t1.equals(t2));
        System.out.println();

        System.out.println("=== Fix: use an immutable List instead of an array ===");
        SafeTags s1 = new SafeTags(List.of("java", "grad"));
        SafeTags s2 = new SafeTags(List.of("java", "grad"));
        System.out.println("s1.equals(s2): " + s1.equals(s2) + ", hashes " + s1.hashCode() + " / " + s2.hashCode());
    }
}
