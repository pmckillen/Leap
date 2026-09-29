import java.util.HashSet;
import java.util.Set;

public class Step07WhenDefaultIsRight {

    static class DownloadTask {
        final String url;
        int percentComplete;

        DownloadTask(String url) {
            this.url = url;
        }

        void progress(int percent) {
            percentComplete = percent;
        }

        @Override
        public String toString() {
            return "DownloadTask(" + url + ", " + percentComplete + "%)";
        }
    }

    public static void main(String[] args) {
        Set<DownloadTask> active = new HashSet<>();

        DownloadTask first = new DownloadTask("https://example.com/report.pdf");
        DownloadTask second = new DownloadTask("https://example.com/report.pdf");
        active.add(first);
        active.add(second);

        System.out.println("=== Identity equality is the correct model here ===");
        System.out.println("Two separate downloads of the same URL are two different jobs.");
        System.out.println("Active tasks: " + active.size());

        first.progress(60);
        second.progress(10);
        System.out.println("Progress changes (mutable state) do not break the set: contains(first)="
                + active.contains(first));

        active.remove(first);
        System.out.println("After cancelling the first: " + active);
        System.out.println();
        System.out.println("Takeaway: for objects with a lifecycle and identity (tasks, connections,");
        System.out.println("threads, services, UI widgets), keep Object's default equals()/hashCode().");
    }
}
