import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Step02EqualsWithoutHashCode {

    static class BrokenPoint {
        final int x;
        final int y;

        BrokenPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BrokenPoint other)) return false;
            return x == other.x && y == other.y;
        }

        // hashCode() deliberately NOT overridden: this breaks the contract.

        @Override
        public String toString() {
            return "BrokenPoint(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        BrokenPoint a = new BrokenPoint(1, 2);
        BrokenPoint b = new BrokenPoint(1, 2);

        System.out.println("=== equals() overridden, hashCode() NOT overridden ===");
        System.out.println("a.equals(b)  : " + a.equals(b));
        System.out.println("a.hashCode() : " + a.hashCode());
        System.out.println("b.hashCode() : " + b.hashCode());
        System.out.println("Equal objects, different hash codes: contract broken.");
        System.out.println();

        List<BrokenPoint> list = new ArrayList<>();
        list.add(a);
        System.out.println("ArrayList.contains(b): " + list.contains(b) + "   <- List only uses equals()");

        Set<BrokenPoint> set = new HashSet<>();
        set.add(a);
        System.out.println("HashSet.contains(b)  : " + set.contains(b) + "  <- HashSet looks in the wrong bucket");
        set.add(b);
        System.out.println("HashSet size after adding two 'equal' points: " + set.size());

        Map<BrokenPoint, String> map = new HashMap<>();
        map.put(a, "treasure");
        System.out.println("map.get(b)           : " + map.get(b));
        System.out.println();

        System.out.println("Takeaway: if you override equals(), you MUST override hashCode().");
        System.out.println("(Objects.equals is fine to use, but it will not save you here: "
                + Objects.equals(a, b) + ")");
    }
}
