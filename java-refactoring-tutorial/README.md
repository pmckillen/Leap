# Java Refactoring Tutorial

**Duration:** about 30 minutes
**Level:** Graduate / early-career developers
**Tech:** Java SE 21, Maven, JUnit 5

## What is refactoring?

Refactoring means changing the *structure* of code without changing its *behaviour*. The goal is code that is easier to read, test and change. You make small, safe steps and run the tests after every one.

> **The golden rule:** the tests are green before you start, and green after every step. If they go red, undo your last change and try a smaller step.

## The scenario

`OrderProcessor` works out the total price of an online shop order. It applies customer discounts, a promo code, shipping and VAT. It works, and the tests prove it, but it is hard to read and painful to change.

Business rules (these must not change):

| Rule | Value |
|---|---|
| Customer discounts | Silver 10%, Gold 20%, Staff 30% |
| Promo code `WELCOME10` | 10.00 off the goods (never below zero) |
| Standard shipping | 4.99, free when goods are 50.00 or more |
| Express delivery | +9.99 |
| Delivery outside Ireland (`IE`) | +15.00 |
| VAT | 23%, applied to goods plus shipping |
| Bulk item | quantity of 10 or more |

## Getting started (3 mins)

```bash
cd starter
mvn test
```

You should see **15 tests, 0 failures**. Open `starter` in your IDE and read `OrderProcessor.java`. Before you change anything, note down everything that makes it hard to understand.

All of your work happens in `starter/src/main/java/com/neueda/refactoring/`. Only touch the test file where a challenge tells you to.

---

## Challenge 1: Rename and replace magic numbers (5 mins)

**Smell:** *Mysterious names* and *magic numbers*. What are `t`, `l`, `c`, `x`, `p`, `d`, `s` and `r`? What does `0.23` mean? What about `50`?

**Techniques:** Rename Variable, Rename Method, Replace Magic Number with Symbolic Constant

**Tasks**

1. Rename the parameters and local variables of `calc` so each name says what it holds.
2. Rename the methods: `calc` to `calculateTotal`, `count` to `totalQuantity`, `top` to `mostExpensiveItem`, `bulk` to `bulkItemNames`.
3. Replace every magic number and string with a `private static final` constant, for example `VAT_RATE`, `FREE_SHIPPING_THRESHOLD` and `HOME_COUNTRY`.
4. Run `mvn test`.

**Hint:** Use your IDE's rename refactoring (IntelliJ: `Shift+F6`, Eclipse: `Alt+Shift+R`) instead of editing by hand. It updates every usage, including the tests.

---

## Challenge 2: Extract method (5 mins)

**Smell:** *Long method*. `calculateTotal` does five jobs in one block, and you need the comments in your head to follow it.

**Technique:** Extract Method

**Tasks**

1. Pull each step into its own small, private, well-named method:
   - `subtotal(items)`
   - `applyTierDiscount(amount, tier)`
   - `applyPromoCode(amount, promoCode)`
   - `shippingCost(goods, ...)`
   - `addVat(amount)` and `roundToCents(amount)`
2. `calculateTotal` should now read almost like the business rules table above.
3. Run `mvn test`.

**Hint:** Highlight a block and use Extract Method (IntelliJ: `Ctrl+Alt+M` / `Cmd+Alt+M`). While you are in `applyPromoCode`, try `"WELCOME10".equals(promoCode)` and `Math.max(0, ...)` to remove the null check and the nested `if`.

---

## Challenge 3: Introduce parameter object (5 mins)

**Smell:** *Long parameter list*. `calculateTotal(String, List<Item>, String, boolean, String)` is easy to call with the arguments in the wrong order, and the compiler cannot help because two of them are `String`.

**Technique:** Introduce Parameter Object

**Tasks**

1. Create a record:
   ```java
   public record OrderRequest(String tier, List<Item> items, String countryCode,
                              boolean expressDelivery, String promoCode) { }
   ```
2. Change `calculateTotal` to take a single `OrderRequest`.
3. Update the `totalFor` helper in the test class. This is the **only** test change, and you change how it calls the code, never the expected values.
4. Run `mvn test`.

**Hint:** Records give you a constructor, accessors, `equals`, `hashCode` and `toString` for free. Add a compact constructor with `items = List.copyOf(items);` so nobody can change the basket after the order is created.

---

## Challenge 4: Replace type code with sealed types (5 mins)

**Smell:** *Primitive obsession* and *repeated conditionals*. The customer tier is a `String`, so a typo like `"GLOD"` compiles and silently gives no discount. The `if/else` chain has to be kept in step with every new tier.

**Techniques:** Replace Type Code with Subclasses, Replace Conditional with Polymorphism (using Java 21 sealed types and pattern matching)

**Tasks**

1. Create a sealed interface with one record per tier:
   ```java
   public sealed interface CustomerTier {
       record Regular() implements CustomerTier {}
       record Silver() implements CustomerTier {}
       record Gold() implements CustomerTier {}
       record Staff() implements CustomerTier {}
   }
   ```
2. Add a `static CustomerTier fromCode(String code)` factory that turns `"GOLD"` into `new Gold()`, and throws `IllegalArgumentException` for anything it doesn't recognise.
3. Change `OrderRequest.tier` from `String` to `CustomerTier`.
4. Replace the `if/else` chain with a `switch` expression that returns the discount rate:
   ```java
   return switch (tier) {
       case Regular r -> 0.0;
       case Silver s -> SILVER_DISCOUNT;
       // ...
   };
   ```
5. In the test helper, wrap the tier: `CustomerTier.fromCode(tier)`.
6. Run `mvn test`.

**Hint:** There is no `default` branch. Because the interface is sealed, the compiler knows every possible tier. Try deleting the `Staff` case and see what happens.

**Discussion point:** Throwing for an unknown code *is* a behaviour change (the old code silently charged full price). Is that a bug fix or a breaking change? Who should decide?

---

## Challenge 5: Replace loops with streams (5 mins)

**Smell:** *Duplicated code*. `subtotal`, `totalQuantity`, `mostExpensiveItem` and `bulkItemNames` all repeat the same loop-and-accumulate pattern.

**Technique:** Replace Loop with Pipeline

**Tasks**

Rewrite each loop as a Stream pipeline:

| Method | Stream operations to try |
|---|---|
| `subtotal` | `mapToDouble(...)`, `sum()` |
| `totalQuantity` | `mapToInt(Item::quantity)`, `sum()` |
| `mostExpensiveItem` | `max(Comparator.comparingDouble(Item::price))`, `orElse(null)` |
| `bulkItemNames` | `filter(...)`, `map(Item::name)`, `toList()` |

Run `mvn test`.

**Hint:** Don't forget the bulk quantity `10` should already be a constant from Challenge 1.

**Stretch:** Should `mostExpensiveItem` return `Optional<Item>` instead of `null`? What would you need to change?

---

## Wrap-up (3 mins)

Compare your code with `solution/`. Yours doesn't have to match exactly: if the tests pass and the code is clear, it's a good refactor.

**Things to take away**

- Refactor in small steps, with tests running after each one.
- Let the IDE do mechanical refactors (rename, extract, change signature).
- Names and small methods do most of the work.
- Use the type system (records, sealed types) so the compiler catches mistakes for you.
- Don't refactor without tests, and don't mix refactoring with new features in the same commit.

**Something to think about:** this code uses `double` for money. Why is that risky, and what would you use in production? (Look up `BigDecimal`.)

## Project layout

```
java-refactoring-tutorial/
├── README.md          this tutorial
├── starter/           the code you refactor (15 tests)
├── solution/          one possible refactored version (16 tests)
└── slides/            intro and wrap-up slides
```
