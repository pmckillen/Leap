# Lab 1: Portfolio Reporting

**Time:** ~45 minutes
**Builds on:** the "Collections Framework" and "Map and Set" slides

## Scenario

Every client at the firm holds a portfolio of assets - a mix of equities,
bonds, property, and cash. You've been asked to build the reporting class
that a portfolio review screen will call into: given a simple list of
holdings, produce the totals an advisor needs to see.

## What you're given

Open `starter/` in your editor. There are two classes:

- **`Asset.java`** - a plain, finished class. One holding: an id, a type
  (`"EQUITY"`, `"BOND"`, `"PROPERTY"`, or `"CASH"` - just a `String`, not an
  enum or a subclass), and a value. Nothing to do here.
- **`PortfolioReport.java`** - an empty class with a comment describing what
  it needs to do. There are no method signatures to fill in - decide the
  fields and methods yourself, based on the brief below and the comment in
  the file.

There's also an empty `PortfolioReportTest.java` under `src/test/java`.
Write your own JUnit tests for `PortfolioReport` as you build it - that's
part of the exercise, not a separate step.

## What to build

`PortfolioReport` should wrap a collection of `Asset` objects and support:

1. `addAsset(Asset asset)` - add one asset to the collection.
2. `getAssets()` - return every asset, as a list the caller can't mutate.
3. `totalValue()` - the sum of every asset's value.
4. `valueByAssetType()` - total value grouped by asset type (a `Map`).
5. `countByAssetType()` - number of holdings grouped by asset type (a `Map`).
6. `distinctAssetTypes()` - every asset type currently held, with no
   duplicates (a `Set`).
7. `highestValueAsset()` - the single largest holding, or an exception if
   the portfolio is empty.

None of this needs anything beyond `List`, `Map`, `Set`, and a `for` loop -
the same accumulation pattern (`getOrDefault(key, 0.0)`, then `put` it back)
from the slides.

## Running your tests

```
cd starter
mvn test
```

Freshly unpacked, this runs zero tests - there's nothing there yet. As you
add methods to `PortfolioReport`, add tests for them alongside, and use
`mvn test` to check your work as you go.

## If you get stuck

`solution/` has a complete, working version of the same project, tests
included. Try to solve it yourself first - but if you're stuck for more
than a few minutes on one part, it's fine to look. There's more than one
reasonable way to design this class, so don't expect your solution to
match it exactly.

## Stretch goal (optional, if you finish early)

Add a method `topNByValue(int n)` that returns the `n` highest-value assets,
largest first. (Hint: `List.sort` with a `Comparator`, or copy the list and
sort it - don't mutate the original.)
