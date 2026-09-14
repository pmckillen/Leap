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
- **`PortfolioReport.java`** - the class you'll implement. It wraps a
  `List<Asset>` and needs six methods filled in, each marked `// TODO`.

There's also `PortfolioReportTest.java` under `src/test/java` - ten
pre-written JUnit tests. Don't edit this file; it defines what "done" means.

## What to build

Implement each method in `PortfolioReport.java`:

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

## Running the tests

```
cd starter
mvn test
```

Freshly unpacked, most tests fail - the methods aren't implemented yet.
Work through the `TODO`s one at a time and re-run `mvn test` until all ten
pass.

## If you get stuck

`solution/` has a complete, working version of the same project. Try to
solve it yourself first - but if you're stuck for more than a few minutes on
one method, it's fine to look.

## Stretch goal (optional, if you finish early)

Add a method `topNByValue(int n)` that returns the `n` highest-value assets,
largest first. (Hint: `List.sort` with a `Comparator`, or copy the list and
sort it - don't mutate the original.)
