import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Step06PoorHashPerformance {

    static final int COUNT = 20_000;

    static final class LazyKey {
        final int id;

        LazyKey(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof LazyKey other && id == other.id;
        }

        @Override
        public int hashCode() {
            return 42;
        }
    }

    static final class GoodKey {
        final int id;

        GoodKey(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof GoodKey other && id == other.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== A legal but terrible hashCode() ===");
        System.out.println("Inserting and looking up " + COUNT + " keys...");

        long start = System.nanoTime();
        Set<GoodKey> good = new HashSet<>();
        for (int i = 0; i < COUNT; i++) good.add(new GoodKey(i));
        for (int i = 0; i < COUNT; i++) good.contains(new GoodKey(i));
        long goodMs = (System.nanoTime() - start) / 1_000_000;

        start = System.nanoTime();
        Set<LazyKey> lazy = new HashSet<>();
        for (int i = 0; i < COUNT; i++) lazy.add(new LazyKey(i));
        for (int i = 0; i < COUNT; i++) lazy.contains(new LazyKey(i));
        long lazyMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("Good hashCode()     : " + goodMs + " ms");
        System.out.println("Constant hashCode() : " + lazyMs + " ms");
        System.out.println();
        System.out.println("Both are CORRECT (equal objects have equal hashes).");
        System.out.println("But every LazyKey lands in one bucket, so lookups degrade from O(1) towards O(n).");
    }
}
