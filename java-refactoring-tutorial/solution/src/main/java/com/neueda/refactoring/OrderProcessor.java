package com.neueda.refactoring;

import com.neueda.refactoring.CustomerTier.Gold;
import com.neueda.refactoring.CustomerTier.Regular;
import com.neueda.refactoring.CustomerTier.Silver;
import com.neueda.refactoring.CustomerTier.Staff;
import java.util.Comparator;
import java.util.List;

public class OrderProcessor {

    private static final double SILVER_DISCOUNT = 0.10;
    private static final double GOLD_DISCOUNT = 0.20;
    private static final double STAFF_DISCOUNT = 0.30;

    private static final String WELCOME_PROMO_CODE = "WELCOME10";
    private static final double WELCOME_PROMO_AMOUNT = 10.00;

    private static final double FREE_SHIPPING_THRESHOLD = 50.00;
    private static final double STANDARD_SHIPPING = 4.99;
    private static final double EXPRESS_SURCHARGE = 9.99;
    private static final double INTERNATIONAL_SURCHARGE = 15.00;
    private static final String HOME_COUNTRY = "IE";

    private static final double VAT_RATE = 0.23;
    private static final int BULK_QUANTITY = 10;

    public double calculateTotal(OrderRequest order) {
        double goods = subtotal(order.items());
        goods = applyTierDiscount(goods, order.tier());
        goods = applyPromoCode(goods, order.promoCode());

        double beforeVat = goods + shippingCost(goods, order);
        return roundToCents(addVat(beforeVat));
    }

    public int totalQuantity(List<Item> items) {
        return items.stream()
                .mapToInt(Item::quantity)
                .sum();
    }

    public Item mostExpensiveItem(List<Item> items) {
        return items.stream()
                .max(Comparator.comparingDouble(Item::price))
                .orElse(null);
    }

    public List<String> bulkItemNames(List<Item> items) {
        return items.stream()
                .filter(item -> item.quantity() >= BULK_QUANTITY)
                .map(Item::name)
                .toList();
    }

    private double subtotal(List<Item> items) {
        return items.stream()
                .mapToDouble(item -> item.price() * item.quantity())
                .sum();
    }

    private double applyTierDiscount(double amount, CustomerTier tier) {
        return amount * (1 - discountRate(tier));
    }

    private double discountRate(CustomerTier tier) {
        return switch (tier) {
            case Regular r -> 0.0;
            case Silver s -> SILVER_DISCOUNT;
            case Gold g -> GOLD_DISCOUNT;
            case Staff s -> STAFF_DISCOUNT;
        };
    }

    private double applyPromoCode(double amount, String promoCode) {
        if (WELCOME_PROMO_CODE.equals(promoCode)) {
            return Math.max(0, amount - WELCOME_PROMO_AMOUNT);
        }
        return amount;
    }

    private double shippingCost(double goods, OrderRequest order) {
        double shipping = goods < FREE_SHIPPING_THRESHOLD ? STANDARD_SHIPPING : 0;
        if (order.expressDelivery()) {
            shipping += EXPRESS_SURCHARGE;
        }
        if (!HOME_COUNTRY.equals(order.countryCode())) {
            shipping += INTERNATIONAL_SURCHARGE;
        }
        return shipping;
    }

    private double addVat(double amount) {
        return amount * (1 + VAT_RATE);
    }

    private double roundToCents(double amount) {
        return Math.round(amount * 100) / 100.0;
    }
}
