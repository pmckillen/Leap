# Java `hashCode()`: When to Override It and When to Leave It Alone

---

## 1. Running the examples

No build tool is needed. From the project root:

```bash
java src/Step01DefaultIdentity.java
java src/Step02EqualsWithoutHashCode.java
# ...and so on
```

Or compile everything at once:

```bash
javac -d out src/*.java src/exercises/*.java
java -cp out Step01DefaultIdentity
```

You can also open the folder in IntelliJ or VS Code and run each `main` method directly.

| File | Lesson |
|------|--------|
| `Step01DefaultIdentity.java` | What `Object` gives you for free |
| `Step02EqualsWithoutHashCode.java` | The classic bug: overriding `equals()` only |
| `Step03CorrectImplementation.java` | Doing it properly, plus hash collisions |
| `Step04MutableKeyTrap.java` | Why keys should not change |
| `Step05Records.java` | Letting the compiler write it for you |
| `Step06PoorHashPerformance.java` | Correct but slow hash codes |
| `Step07WhenDefaultIsRight.java` | When you should NOT override |
| `exercises/Exercise01Employee.java` | Your turn (see `EXERCISES.md`) |

---

## 2. What is a hash code?

`hashCode()` returns an `int` that summarises an object. Hash-based collections (`HashMap`, `HashSet`, `LinkedHashMap`, `ConcurrentHashMap`) use it to decide **which bucket** an object goes into.

A lookup like `map.get(key)` works in two stages:

1. **Find the bucket:** call `key.hashCode()` and jump straight to the matching bucket. This is what makes hash maps fast (roughly O(1)).
2. **Find the entry:** inside that bucket, call `equals()` on each entry until one matches.

So `hashCode()` gets you to the right neighbourhood, and `equals()` finds the right house. If either one is wrong, the lookup fails.

> Lists (`ArrayList`, `LinkedList`) never call `hashCode()`. They only use `equals()`. This is why the bug in Step 2 can hide for a long time: code that uses lists keeps working.

---

## 3. The contract

The rules are written in the Javadoc for `java.lang.Object`. In plain English:

1. **Equal objects must have equal hash codes.** If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must be true. *This is the rule people break.*
2. **Consistent:** calling `hashCode()` repeatedly on an unchanged object must return the same value.
3. **Unequal objects MAY share a hash code.** This is called a collision. It is allowed, but lots of collisions hurt performance.

Note what rule 3 means: **the same hash code does not prove two objects are equal.** `"Aa"` and `"BB"` both have hash code `2112` (see Step 3).

---

## 4. The default behaviour (Step 1)

If you do not override anything, your class inherits from `Object`:

- `equals()` means **identity**: `a.equals(b)` is true only if `a == b` (literally the same object in memory).
- `hashCode()` is an **identity hash**, typically different for each object. You can see it directly with `System.identityHashCode(obj)`.

The defaults are consistent with each other and perfectly valid. They just model "same object", not "same values".

---

## 5. When to override `hashCode()`

Override `equals()` and `hashCode()` **together** when your class is a **value object**: two instances with the same data should be treated as the same thing.

Typical examples:

- `Point`, `Money`, `DateRange`, `Isin`, `CurrencyPair`, `Address`
- Composite keys for a map, such as `(accountId, tradeDate)`
- Anything you put in a `HashSet` to remove duplicates by content
- DTOs you compare in unit tests with `assertEquals`

**The golden rule:** if you override `equals()`, you must override `hashCode()`. Step 2 shows what happens otherwise: a `HashSet` happily stores two "equal" objects, and `map.get()` returns `null` for a key that is clearly there.

---

## 6. When to keep the default (Step 7)

Leave `equals()` and `hashCode()` alone when **identity is what matters**, meaning two objects with the same fields are still two different things:

- Objects with a lifecycle: tasks, jobs, sessions, requests in flight
- Resources: connections, threads, sockets, file handles
- Services, controllers, repositories, and other "doer" classes
- UI components and event listeners
- Mutable objects whose state changes while they sit in a collection

Two downloads of the same URL are two separate jobs. Cancelling one must not cancel the other, so identity equality is exactly right.

**Rule of thumb:** ask *"if I had two of these with identical fields, would I consider them the same thing?"* If yes, override. If no, keep the default.

---

## 7. How to write a good `hashCode()` (Step 3)

### Option A: `Objects.hash` (simplest)

```java
@Override
public int hashCode() {
    return Objects.hash(x, y);
}
```

Readable and null-safe. It creates a small array on each call, which only matters in very hot code.

### Option B: The classic `31 *` pattern

```java
@Override
public int hashCode() {
    int result = Long.hashCode(amountInPence);
    result = 31 * result + currency.hashCode();
    return result;
}
```

This is what IDEs generate and what `String` does internally. 31 is an odd prime, which spreads values well and is cheap for the JVM to compute.

### Option C: Records (best for new code, Step 5)

```java
record Point(int x, int y) {}
```

The compiler generates `equals()`, `hashCode()` and `toString()` from all components. Records are also immutable, which avoids the trap in Step 4.

### Option D: Let the tools do it

- IntelliJ: **Code > Generate > equals() and hashCode()**
- Eclipse: **Source > Generate hashCode() and equals()**
- Lombok: `@EqualsAndHashCode` or `@Value`

### Checklist

- Use **exactly the same fields** in `equals()` and `hashCode()`. Using fewer in `hashCode()` is legal but increases collisions. Using a field in `hashCode()` that is not in `equals()` breaks the contract.
- Only use fields that **do not change** while the object is in a collection.
- Use `Arrays.hashCode(array)` for arrays, not `array.hashCode()`.
- Never return a constant. It is legal, but see Step 6.

---

## 8. Common pitfalls

### Mutable keys (Step 4)

If a field used in `hashCode()` changes after the object is added to a `HashSet` or used as a `HashMap` key, the object is stranded in its old bucket. `contains()` and `remove()` both fail, but the object is still in the collection: a quiet memory leak and a hard bug to trace.

**Fix:** make value objects immutable (`final` fields, no setters), or base equality on an unchanging ID.

### Arrays inside records (Step 5)

A record's generated `equals()` compares an array field by **identity**, not contents. Prefer `List.copyOf(...)` for collection components.

### Constant or weak hash codes (Step 6)

`return 42;` satisfies the contract but puts every object in one bucket. On a typical laptop, 20,000 lookups go from a few milliseconds to a few seconds. Since Java 8, a crowded bucket turns into a tree, which helps if the keys implement `Comparable`, but it is no excuse for a bad hash.

### `instanceof` vs `getClass()` in `equals()`

- `instanceof` (used here) lets subclasses compare equal to parents. Safe if the class is `final` or a record.
- `getClass() != o.getClass()` is stricter and avoids symmetry problems in class hierarchies.

For graduates, the simple advice is: **make value classes `final` (or use records) and use `instanceof`.**

### JPA / Hibernate entities (a heads-up)

Entities are tricky because the database ID is often `null` until the object is saved. Do not blindly generate `equals()`/`hashCode()` from all fields on an entity. Follow your team's convention; this is worth a separate session.

---

## 9. Quick reference

| Situation | What to do |
|-----------|-----------|
| Overriding `equals()` | Always override `hashCode()` too |
| Value object (same data means same thing) | Override both, or use a `record` |
| Object with identity or lifecycle | Keep the `Object` defaults |
| Used as a `HashMap` key or in a `HashSet` | Make it immutable |
| Only ever stored in a `List` | `hashCode()` not used, but still honour the contract |
| Array field | `Arrays.equals` / `Arrays.hashCode` |
| New code | Prefer records for value types |

---

## 10. Suggested session plan (for facilitators)

| Time | Activity |
|------|----------|
| 0 to 10 min | Section 2 and 3: how a HashMap bucket lookup works (whiteboard it) |
| 10 to 20 min | Run Step 1 and Step 2. Ask the group to predict output before running |
| 20 to 35 min | Step 3 and Step 5: writing it properly |
| 35 to 50 min | Step 4, Step 6 and Step 7: traps and when not to override |
| 50 to 80 min | `EXERCISES.md` in pairs |
| 80 to 90 min | Review answers and the quick-reference table |

A good prediction question for Step 2: *"`list.contains(b)` and `set.contains(b)`: which is true?"* Most people guess both.
