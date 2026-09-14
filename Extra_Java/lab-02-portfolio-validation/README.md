# Lab 2: Portfolio Validation & Exception Handling

**Time:** ~45 minutes
**Builds on:** the "Two Categories of Exception", "Unchecked Exceptions",
and "Checked Exceptions" slides

## Scenario

Asset data arrives from an upstream feed as plain text lines, one holding
per line: `assetId,assetType,value` - for example `A1001,EQUITY,15000.00`.
Some of those lines are bad: missing fields, an unrecognised asset type, a
value that isn't a number, or a negative value. You need a parser that
turns good lines into `Asset` objects and rejects bad ones clearly, without
one bad line taking down the whole batch.

This is where the module's one genuinely new idea comes in: **checked
exceptions**. You'll define one, and use it alongside a built-in *unchecked*
exception in the same method.

## What you're given

Open `starter/`. There are four classes:

- **`Asset.java`** - the same finished class from Lab 1. Nothing to do here.
- **`ParseSummary.java`** - a finished, simple holder for the parse results
  (a list of valid assets, a list of error messages). Nothing to do here.
- **`MalformedAssetException.java`** - an empty checked exception, with a
  comment describing what it needs. It must `extend Exception` (not
  `RuntimeException`) - that's what makes it checked, and it's why
  `AssetLineParser` won't compile until it either catches it or declares
  `throws MalformedAssetException`.
- **`AssetLineParser.java`** - an empty class with a comment describing the
  two methods it needs. No method signatures are given - decide those
  yourself.

There's also an empty `AssetLineParserTest.java` under `src/test/java`.
Write your own JUnit tests for `AssetLineParser` as you build it.

## What to build

1. **`MalformedAssetException`** - add two constructors: one taking just a
   `String message`, and one taking a `String message` and a `Throwable
   cause`. Both just forward to the matching `super(...)` constructor.

2. **`AssetLineParser.parseLine(String line)`** - parses one line, throwing
   `MalformedAssetException` when:
   - the line doesn't split into exactly 3 fields,
   - the asset type isn't one of `EQUITY`, `BOND`, `PROPERTY`, `CASH`,
   - the value isn't a valid number - `Double.parseDouble` throws the
     **unchecked** `NumberFormatException` for this; catch it and re-throw
     it as a `MalformedAssetException` (using the `(message, cause)`
     constructor) so callers only ever have to deal with one exception type,
   - the value is negative.

3. **`AssetLineParser.parsePortfolio(List<String> lines)`** - parses every
   line, collecting successfully-parsed assets in one list and error
   messages in another. A bad line is skipped, not fatal - the batch keeps
   going.

## Running your tests

```
cd starter
mvn test
```

Freshly unpacked, this runs zero tests. Add tests alongside your
implementation and use `mvn test` to check your work as you go.

## If you get stuck

`solution/` has a complete, working version of the same project, tests
included.

## Stretch goal (optional, if you finish early)

Add validation that an asset's `assetId` must be non-blank, with a test to
match, before adding the check to `parseLine`.

## Discussion point for the group

Why does `NumberFormatException` not need a `throws` clause, but
`MalformedAssetException` does? What would happen if `MalformedAssetException`
extended `RuntimeException` instead - would `parsePortfolio` still need a
`try`/`catch` to work correctly?
