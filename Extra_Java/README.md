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
  starter/          <- the classes to build, each with a comment describing
                       what it needs to do - no method stubs, no pre-written
                       tests, just guidance
  solution/         <- a complete, working reference implementation, tests included
```

Each `starter/` and `solution/` folder is its own Maven project. Trainees
work only in `starter/`: they design the class themselves (fields, method
signatures, everything) from the comments provided, and write their own
JUnit tests for it. `solution/` is for the trainer, or for trainees who want
to compare their approach once they're done.

## Prerequisites

- JDK 17 or later
- Maven (`mvn`)

## Running a lab

```
cd lab-01-portfolio-reporting/starter
mvn test
```

A freshly-unpacked starter project compiles and runs (with zero tests,
until trainees add their own). The lab is done when a trainee is satisfied
their own tests exercise the brief in the README and pass against their
implementation.
