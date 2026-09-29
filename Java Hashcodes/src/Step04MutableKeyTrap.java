import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Step04MutableKeyTrap {

    static class MutablePoint {
        int x;
        int y;

        MutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MutablePoint other)) return false;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "MutablePoint(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        Set<MutablePoint> set = new HashSet<>();
        MutablePoint p = new MutablePoint(1, 2);
        set.add(p);

        System.out.println("=== Mutating an object after it is used as a key ===");
        System.out.println("Before: hashCode=" + p.hashCode() + ", set.contains(p)=" + set.contains(p));

        p.x = 99;

        System.out.println("After : hashCode=" + p.hashCode() + ", set.contains(p)=" + set.contains(p));
        System.out.println("set.size()   : " + set.size());
        System.out.println("set contents : " + set);
        System.out.println("set.remove(p): " + set.remove(p) + "   <- cannot even remove it");
        System.out.println();
        System.out.println("The object is still in the set, but stored in the bucket for its OLD hash.");
        System.out.println("Takeaway: only base hashCode() on fields that never change, or make the class immutable.");
    }
}
