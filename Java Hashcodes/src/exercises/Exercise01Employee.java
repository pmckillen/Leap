import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Exercise01Employee {

    static class Employee {
        private final String employeeId;
        private String name;
        private String team;

        Employee(String employeeId, String name, String team) {
            this.employeeId = employeeId;
            this.name = name;
            this.team = team;
        }

        void moveTo(String newTeam) {
            this.team = newTeam;
        }

        // TODO 1: override equals() so two Employees with the same employeeId are equal.
        // TODO 2: override hashCode() consistently with equals().
        // Think: which fields should be included, and which must NOT be? Why?

        @Override
        public String toString() {
            return "Employee(" + employeeId + ", " + name + ", " + team + ")";
        }
    }

    public static void main(String[] args) {
        Employee fromHr = new Employee("E1001", "Sam", "Payments");
        Employee fromPayroll = new Employee("E1001", "Sam", "Payments");

        Set<Employee> staff = new HashSet<>();
        staff.add(fromHr);
        staff.add(fromPayroll);
        check("Same employee from two systems counts once", staff.size() == 1);

        Map<Employee, Integer> holidayDays = new HashMap<>();
        holidayDays.put(fromHr, 25);
        check("Lookup with an equal object works", Integer.valueOf(25).equals(holidayDays.get(fromPayroll)));

        fromHr.moveTo("Risk");
        check("Still findable after changing team", holidayDays.containsKey(fromHr));
    }

    static void check(String description, boolean passed) {
        System.out.println((passed ? "PASS  " : "FAIL  ") + description);
    }
}
