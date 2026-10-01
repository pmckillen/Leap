package com.neueda.refactoring;

public sealed interface CustomerTier {

    record Regular() implements CustomerTier {}
    record Silver() implements CustomerTier {}
    record Gold() implements CustomerTier {}
    record Staff() implements CustomerTier {}

    static CustomerTier fromCode(String code) {
        return switch (code) {
            case "REGULAR" -> new Regular();
            case "SILVER" -> new Silver();
            case "GOLD" -> new Gold();
            case "STAFF" -> new Staff();
            default -> throw new IllegalArgumentException("Unknown customer tier: " + code);
        };
    }
}
