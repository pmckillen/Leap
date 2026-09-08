# Snowflake examples: CLI, Java, Python, JavaScript

All 12 snippets below use the same two-table schema, so you can run them in order:

```
departments (department_id, department_name, budget)
employees   (employee_id, first_name, last_name, department_id, salary, hire_date)
```

Replace the placeholder account, user, password, warehouse, database and schema values with your own. For anything beyond a quick test, swap the hardcoded password for key-pair auth, `SNOWSQL_PWD`/env vars, or a secrets manager rather than committing credentials.

Tooling used: the current [Snowflake CLI](https://docs.snowflake.com/en/user-guide/snowsql-install-config) (`snow`), the [Snowflake JDBC driver](https://docs.snowflake.com/en/developer-guide/jdbc/jdbc) for Java, [`snowflake-connector-python`](https://docs.snowflake.com/en/developer-guide/python-connector/python-connector), and the [`snowflake-sdk`](https://docs.snowflake.com/en/developer-guide/node-js/nodejs-driver) Node.js driver.

---

## 1. Create a table

### CLI

```bash
snow sql -c myconn -q "
CREATE TABLE departments (
  department_id INT,
  department_name STRING,
  budget NUMBER(12,2)
);

CREATE TABLE employees (
  employee_id INT,
  first_name STRING,
  last_name STRING,
  department_id INT,
  salary NUMBER(10,2),
  hire_date DATE
);
"
```

### Java

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class CreateTable {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("user", "YOUR_USER");
        props.put("password", "YOUR_PASSWORD");
        props.put("warehouse", "COMPUTE_WH");
        props.put("db", "SALES_DB");
        props.put("schema", "PUBLIC");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:snowflake://YOUR_ACCOUNT.snowflakecomputing.com", props);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE departments (" +
                    "department_id INT, department_name STRING, budget NUMBER(12,2))");

            stmt.execute("CREATE TABLE employees (" +
                    "employee_id INT, first_name STRING, last_name STRING, " +
                    "department_id INT, salary NUMBER(10,2), hire_date DATE)");
        }
    }
}
```

Maven dependency: `net.snowflake:snowflake-jdbc`.

### Python

```python
import snowflake.connector

conn = snowflake.connector.connect(
    user="YOUR_USER",
    password="YOUR_PASSWORD",
    account="YOUR_ACCOUNT",
    warehouse="COMPUTE_WH",
    database="SALES_DB",
    schema="PUBLIC",
)

with conn.cursor() as cur:
    cur.execute("""
        CREATE TABLE departments (
            department_id INT,
            department_name STRING,
            budget NUMBER(12,2)
        )
    """)
    cur.execute("""
        CREATE TABLE employees (
            employee_id INT,
            first_name STRING,
            last_name STRING,
            department_id INT,
            salary NUMBER(10,2),
            hire_date DATE
        )
    """)

conn.close()
```

### JavaScript (Node.js)

```javascript
const snowflake = require('snowflake-sdk');

const connection = snowflake.createConnection({
  account: 'YOUR_ACCOUNT',
  username: 'YOUR_USER',
  password: 'YOUR_PASSWORD',
  warehouse: 'COMPUTE_WH',
  database: 'SALES_DB',
  schema: 'PUBLIC',
});

connection.connect((err) => {
  if (err) throw err;

  connection.execute({
    sqlText: `CREATE TABLE departments (
      department_id INT, department_name STRING, budget NUMBER(12,2)
    )`,
    complete: (err) => {
      if (err) throw err;

      connection.execute({
        sqlText: `CREATE TABLE employees (
          employee_id INT, first_name STRING, last_name STRING,
          department_id INT, salary NUMBER(10,2), hire_date DATE
        )`,
        complete: (err) => { if (err) throw err; },
      });
    },
  });
});
```

---

## 2. Select with a join across two tables

The query used in every example below:

```sql
SELECT
  e.first_name,
  e.last_name,
  d.department_name,
  e.salary
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE e.salary > 60000
ORDER BY e.salary DESC;
```

### CLI

```bash
snow sql -c myconn -q "
SELECT
  e.first_name,
  e.last_name,
  d.department_name,
  e.salary
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE e.salary > 60000
ORDER BY e.salary DESC;
"
```

### Java

```java
try (Connection conn = DriverManager.getConnection(
        "jdbc:snowflake://YOUR_ACCOUNT.snowflakecomputing.com", props);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(
        "SELECT e.first_name, e.last_name, d.department_name, e.salary " +
        "FROM employees e JOIN departments d ON e.department_id = d.department_id " +
        "WHERE e.salary > 60000 ORDER BY e.salary DESC")) {

    while (rs.next()) {
        System.out.printf("%s %s | %s | %.2f%n",
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("department_name"),
                rs.getDouble("salary"));
    }
}
```

### Python

```python
with conn.cursor() as cur:
    cur.execute("""
        SELECT e.first_name, e.last_name, d.department_name, e.salary
        FROM employees e
        JOIN departments d ON e.department_id = d.department_id
        WHERE e.salary > 60000
        ORDER BY e.salary DESC
    """)
    for first_name, last_name, department_name, salary in cur:
        print(first_name, last_name, department_name, salary)
```

### JavaScript (Node.js)

```javascript
connection.execute({
  sqlText: `
    SELECT e.first_name, e.last_name, d.department_name, e.salary
    FROM employees e
    JOIN departments d ON e.department_id = d.department_id
    WHERE e.salary > 60000
    ORDER BY e.salary DESC
  `,
  complete: (err, stmt, rows) => {
    if (err) throw err;
    rows.forEach((row) => {
      console.log(row.FIRST_NAME, row.LAST_NAME, row.DEPARTMENT_NAME, row.SALARY);
    });
  },
});
```

---

## 3. Group by with a having clause

The query used in every example below:

```sql
SELECT
  department_id,
  COUNT(*) AS employee_count,
  AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id
HAVING COUNT(*) > 5
ORDER BY avg_salary DESC;
```

### CLI

```bash
snow sql -c myconn -q "
SELECT
  department_id,
  COUNT(*) AS employee_count,
  AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id
HAVING COUNT(*) > 5
ORDER BY avg_salary DESC;
"
```

### Java

```java
try (Connection conn = DriverManager.getConnection(
        "jdbc:snowflake://YOUR_ACCOUNT.snowflakecomputing.com", props);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(
        "SELECT department_id, COUNT(*) AS employee_count, AVG(salary) AS avg_salary " +
        "FROM employees GROUP BY department_id HAVING COUNT(*) > 5 " +
        "ORDER BY avg_salary DESC")) {

    while (rs.next()) {
        System.out.printf("dept %d | count %d | avg %.2f%n",
                rs.getInt("department_id"),
                rs.getInt("employee_count"),
                rs.getDouble("avg_salary"));
    }
}
```

### Python

```python
with conn.cursor() as cur:
    cur.execute("""
        SELECT department_id, COUNT(*) AS employee_count, AVG(salary) AS avg_salary
        FROM employees
        GROUP BY department_id
        HAVING COUNT(*) > 5
        ORDER BY avg_salary DESC
    """)
    for department_id, employee_count, avg_salary in cur:
        print(department_id, employee_count, avg_salary)
```

### JavaScript (Node.js)

```javascript
connection.execute({
  sqlText: `
    SELECT department_id, COUNT(*) AS employee_count, AVG(salary) AS avg_salary
    FROM employees
    GROUP BY department_id
    HAVING COUNT(*) > 5
    ORDER BY avg_salary DESC
  `,
  complete: (err, stmt, rows) => {
    if (err) throw err;
    rows.forEach((row) => {
      console.log(row.DEPARTMENT_ID, row.EMPLOYEE_COUNT, row.AVG_SALARY);
    });
  },
});
```
