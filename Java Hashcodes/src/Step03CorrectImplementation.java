import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Step03CorrectImplementation {

    static final class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point other)) return false;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    static final class Money {
        private final long amountInPence;
        private final String currency;

        Money(long amountInPence, String currency) {
            this.amountInPence = amountInPence;
            this.currency = Objects.requireNonNull(currency);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Money other)) return false;
            return amountInPence == other.amountInPence && currency.equals(other.currency);
        }

        @Override
        public int hashCode() {
            int result = Long.hashCode(amountInPence);
            result = 31 * result + currency.hashCode();
            return result;
        }
    }

    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);

        System.out.println("=== equals() AND hashCode() overridden ===");
        System.out.println("a.equals(b)  : " + a.equals(b));
        System.out.println("a.hashCode() : " + a.hashCode());
        System.out.println("b.hashCode() : " + b.hashCode());
        System.out.println();

        Set<Point> set = new HashSet<>();
        set.add(a);
        set.add(b);
        System.out.println("HashSet.contains(b): " + set.contains(b));
        System.out.println("HashSet size       : " + set.size());

        Map<Point, String> map = new HashMap<>();
        map.put(a, "treasure");
        System.out.println("map.get(b)         : " + map.get(b));
        System.out.println();

        Money m1 = new Money(1050, "GBP");
        Money m2 = new Money(1050, "GBP");
        System.out.println("Hand-written 31* style hashCode:");
        System.out.println("m1.equals(m2): " + m1.equals(m2) + ", hashes " + m1.hashCode() + " / " + m2.hashCode());
        System.out.println();

        System.out.println("=== Equal hash codes do NOT mean equal objects ===");
        System.out.println("\"Aa\".hashCode() = " + "Aa".hashCode());
        System.out.println("\"BB\".hashCode() = " + "BB".hashCode());
        System.out.println("\"Aa\".equals(\"BB\") = " + "Aa".equals("BB"));
        System.out.println("That is a collision. HashMap copes by calling equals() within the bucket.");
    }
}
