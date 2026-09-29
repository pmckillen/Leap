import java.util.HashSet;
import java.util.Set;

public class Step01DefaultIdentity {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        Point c = a;

        System.out.println("=== Default equals() and hashCode() from Object ===");
        System.out.println("a == b        : " + (a == b));
        System.out.println("a.equals(b)   : " + a.equals(b));
        System.out.println("a.equals(c)   : " + a.equals(c));
        System.out.println();
        System.out.println("a.hashCode()  : " + a.hashCode());
        System.out.println("b.hashCode()  : " + b.hashCode());
        System.out.println("c.hashCode()  : " + c.hashCode());
        System.out.println("System.identityHashCode(a): " + System.identityHashCode(a));
        System.out.println();

        Set<Point> set = new HashSet<>();
        set.add(a);
        System.out.println("set.contains(a): " + set.contains(a));
        System.out.println("set.contains(b): " + set.contains(b) + "   <- same values, different object");
        set.add(b);
        System.out.println("set size after adding a and b: " + set.size());
        System.out.println();
        System.out.println("Takeaway: by default, equality means 'the very same object'.");
    }
}
