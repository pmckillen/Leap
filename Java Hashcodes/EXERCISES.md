# Exercises

Work in pairs. Try each one before opening the hint or answer.

---

## Exercise 1: Fix the Employee class (hands-on)

Open `src/exercises/Exercise01Employee.java` and run it:

```bash
java src/exercises/Exercise01Employee.java
```

Two checks fail. Complete the two TODOs so that all three checks print `PASS`.

Think about: an employee can change team (and even name), but their `employeeId` never changes.

<details>
<summary>Hint</summary>

Base both `equals()` and `hashCode()` on `employeeId` only. What happens to the third check if you include `team`?

</details>

<details>
<summary>Answer</summary>

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Employee other)) return false;
    return employeeId.equals(other.employeeId);
}

@Override
public int hashCode() {
    return employeeId.hashCode();
}
```

Including `team` makes the third check fail: after `moveTo("Risk")` the hash changes and the employee is stranded in the wrong bucket (the Step 4 trap).

</details>

---

## Exercise 2: Predict the output

Without running it, what does this print?

```java
class Card {
    final String suit;
    final int rank;
    Card(String suit, int rank) { this.suit = suit; this.rank = rank; }

    @Override
    public int hashCode() { return Objects.hash(suit, rank); }
}

Set<Card> hand = new HashSet<>();
hand.add(new Card("Hearts", 10));
hand.add(new Card("Hearts", 10));
System.out.println(hand.size());
```

<details>
<summary>Answer</summary>

`2`. Both cards land in the same bucket, but `equals()` was not overridden, so the default identity check says they are different objects. Overriding only `hashCode()` is just as broken as overriding only `equals()`.

</details>

---

## Exercise 3: Override or not?

For each class, decide: **override** `equals()`/`hashCode()`, **keep the default**, or **use a record**. Give a one-line reason.

1. `CurrencyPair` holding `"GBP"` and `"USD"`
2. `DatabaseConnection` wrapping a JDBC connection
3. `TradeKey` made of `tradeId` and `version`, used as a `HashMap` key
4. `OrderProcessor`, a service class with a `process(Order)` method
5. `ShoppingBasket` whose items change as the user shops
6. `DateRange` with a start and end `LocalDate`

<details>
<summary>Suggested answers</summary>

1. **Record.** Pure value, immutable.
2. **Default.** A connection is a resource with identity; two connections to the same DB are still two connections.
3. **Record** (or a final class with both overridden). A composite map key must be a value and must be immutable.
4. **Default.** A service has behaviour, not value.
5. **Default**, at least for use in hash collections. It is mutable and has identity (this user's basket). If you need to compare contents, write a separate method such as `hasSameItemsAs(other)`.
6. **Record.** Classic value object.

</details>

---

## Exercise 4: Spot the bug

```java
final class Account {
    private final String iban;
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        return o instanceof Account other && iban.equals(other.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance);
    }
}
```

<details>
<summary>Answer</summary>

`hashCode()` uses `balance`, but `equals()` does not. Two accounts with the same IBAN and different balances are equal but have different hash codes: rule 1 of the contract is broken. It also means the hash changes every time the balance changes. Fix: `return iban.hashCode();`

</details>

---

## Stretch challenge

In `Step06PoorHashPerformance.java`, make `LazyKey` implement `Comparable<LazyKey>` (compare by `id`) and re-run it. The constant hash is still there. Why does it get faster, and why is this still not a fix?

<details>
<summary>Answer</summary>

Since Java 8, when a single bucket gets crowded (8 or more entries, with a table of at least 64 buckets), `HashMap` converts it from a linked list into a balanced tree. If the keys are `Comparable`, the tree can be searched in O(log n) instead of O(n). It is still far slower than a well-distributed hash, and it only helps when keys happen to be `Comparable`.

</details>
