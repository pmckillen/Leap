package com.neueda.refactoring;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {

    public double calc(String t, List<Item> l, String c, boolean x, String p) {
        double d = 0;
        for (Item i : l) {
            d = d + i.price() * i.quantity();
        }

        if (t.equals("GOLD")) {
            d = d - d * 0.2;
        } else if (t.equals("SILVER")) {
            d = d - d * 0.1;
        } else if (t.equals("STAFF")) {
            d = d - d * 0.3;
        }

        if (p != null && p.equals("WELCOME10")) {
            d = d - 10;
            if (d < 0) {
                d = 0;
            }
        }

        double s = 0;
        if (d < 50) {
            s = 4.99;
        }
        if (x) {
            s = s + 9.99;
        }
        if (!c.equals("IE")) {
            s = s + 15;
        }

        double r = d + s;
        r = r + r * 0.23;
        return Math.round(r * 100) / 100.0;
    }

    public int count(List<Item> l) {
        int n = 0;
        for (Item i : l) {
            n = n + i.quantity();
        }
        return n;
    }

    public Item top(List<Item> l) {
        Item best = null;
        for (Item i : l) {
            if (best == null || i.price() > best.price()) {
                best = i;
            }
        }
        return best;
    }

    public List<String> bulk(List<Item> l) {
        List<String> out = new ArrayList<>();
        for (Item i : l) {
            if (i.quantity() >= 10) {
                out.add(i.name());
            }
        }
        return out;
    }
}
