# Portfolio Collections Labs

Two hands-on labs for graduate IT trainees, following the Core Java Refresher
module. Both use the same running example: a simple insurance portfolio,
modelled as a plain `Asset` class held in a `List`.

Neither lab uses inheritance or multithreading - only what the refresher
module covers: types, control flow, the collections framework (`List`,
`Map`, `Set`), and exception handling.

| Lab | Focus | Approx. time |
|---|---|---|
| [lab-01-portfolio-reporting](lab-01-portfolio-reporting/README.md) | List/Map/Set management and reporting | 45 minutes |
| [lab-02-portfolio-validation](lab-02-portfolio-validation/README.md) | Validation, checked vs. unchecked exceptions | 45 minutes |

## Structure

Each lab has the same layout:

```
lab-0X-.../
  README.md        <- the task sheet
  starter/          <- skeleton code with TODOs, pre-written JUnit tests
  solution/         <- a complete, working reference solution
```

Each `starter/` and `solution/` folder is its own Maven project. Trainees
work only in `starter/`; `solution/` is for the trainer (or for trainees who
get stuck and want to compare).

## Prerequisites

- JDK 17 or later
- Maven (`mvn`)

## Running a lab

```
cd lab-01-portfolio-reporting/starter
mvn test
```

The pre-written tests define what "correct" means. A freshly-unpacked
starter project compiles but fails most tests - that's expected. The lab is
done when `mvn test` passes with no failures.
