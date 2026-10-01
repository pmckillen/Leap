package com.neueda.refactoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    private static final List<Item> SMALL_BASKET = List.of(new Item("Pen", 2.00, 10));
    private static final List<Item> MEDIUM_BASKET = List.of(
            new Item("Notebook", 15.00, 2),
            new Item("Desk lamp", 30.00, 1));
    private static final List<Item> LARGE_BASKET = List.of(new Item("Keyboard", 50.00, 2));

    private double totalFor(String tier, List<Item> items, String country, boolean express, String promo) {
        OrderRequest order = new OrderRequest(CustomerTier.fromCode(tier), items, country, express, promo);
        return processor.calculateTotal(order);
    }

    @Test
    void regularCustomerOverThresholdGetsFreeShipping() {
        assertEquals(73.80, totalFor("REGULAR", MEDIUM_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void regularCustomerUnderThresholdPaysStandardShipping() {
        assertEquals(30.74, totalFor("REGULAR", SMALL_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void goldCustomerGetsTwentyPercentOff() {
        assertEquals(98.40, totalFor("GOLD", LARGE_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void silverCustomerGetsTenPercentOff() {
        assertEquals(110.70, totalFor("SILVER", LARGE_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void staffGetThirtyPercentOff() {
        assertEquals(86.10, totalFor("STAFF", LARGE_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void discountCanDropOrderBelowFreeShippingThreshold() {
        assertEquals(65.18, totalFor("GOLD", MEDIUM_BASKET, "IE", false, null), 0.001);
    }

    @Test
    void welcomePromoTakesTenOff() {
        assertEquals(110.70, totalFor("REGULAR", LARGE_BASKET, "IE", false, "WELCOME10"), 0.001);
    }

    @Test
    void promoNeverMakesGoodsNegative() {
        List<Item> cheap = List.of(new Item("Sticker", 1.00, 5));
        assertEquals(6.14, totalFor("REGULAR", cheap, "IE", false, "WELCOME10"), 0.001);
    }

    @Test
    void unknownPromoIsIgnored() {
        assertEquals(73.80, totalFor("REGULAR", MEDIUM_BASKET, "IE", false, "FREESTUFF"), 0.001);
    }

    @Test
    void expressDeliveryAddsSurcharge() {
        assertEquals(86.09, totalFor("REGULAR", MEDIUM_BASKET, "IE", true, null), 0.001);
    }

    @Test
    void internationalDeliveryAddsSurcharge() {
        assertEquals(92.25, totalFor("REGULAR", MEDIUM_BASKET, "GB", false, null), 0.001);
    }

    @Test
    void countsTotalQuantityOfItems() {
        assertEquals(3, processor.totalQuantity(MEDIUM_BASKET));
    }

    @Test
    void findsMostExpensiveItem() {
        assertEquals("Desk lamp", processor.mostExpensiveItem(MEDIUM_BASKET).name());
    }

    @Test
    void mostExpensiveItemOfEmptyBasketIsNull() {
        assertNull(processor.mostExpensiveItem(List.of()));
    }

    @Test
    void listsItemsBoughtInBulk() {
        List<Item> mixed = List.of(
                new Item("Pen", 2.00, 10),
                new Item("Stapler", 8.00, 1),
                new Item("Paper", 5.00, 20));
        assertEquals(List.of("Pen", "Paper"), processor.bulkItemNames(mixed));
    }

    @Test
    void unknownTierCodeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> CustomerTier.fromCode("GLOD"));
    }
}
