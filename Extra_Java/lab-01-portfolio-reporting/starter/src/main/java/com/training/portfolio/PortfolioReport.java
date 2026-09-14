package com.training.portfolio;

import java.util.*;

/**
 * Holds a simple collection of assets for one client's portfolio and
 * produces basic reports over that collection.
 *
 * TODO: implement every method below. Nothing here needs inheritance -
 * just the List, Map and Set you already saw on the "Collections Framework"
 * slides. Run `mvn test` to check your work as you go.
 */
public class PortfolioReport {

    private final List<Asset> assets = new ArrayList<>();

    /**
     * Adds a single asset to the portfolio.
     */
    public void addAsset(Asset asset) {
        // TODO: add asset to the assets list
    }

    /**
     * @return an unmodifiable view of every asset currently in the portfolio,
     *         in the order they were added
     */
    public List<Asset> getAssets() {
        // TODO: return assets, wrapped with Collections.unmodifiableList(...)
        return null;
    }

    /**
     * @return the sum of the value of every asset in the portfolio (0.0 if empty)
     */
    public double totalValue() {
        // TODO: loop over assets and sum their getValue()
        return 0.0;
    }

    /**
     * @return the total asset value, grouped by assetType,
     *         e.g. {"EQUITY": 45000.0, "BOND": 12000.0}
     */
    public Map<String, Double> valueByAssetType() {
        // TODO: build a Map<String, Double>.
        // Hint: this is the same accumulation pattern from the "Map and Set"
        // slide - byType.getOrDefault(type, 0.0) + value, then put() it back.
        return null;
    }

    /**
     * @return the number of assets, grouped by assetType,
     *         e.g. {"EQUITY": 3, "BOND": 1}
     */
    public Map<String, Integer> countByAssetType() {
        // TODO: same accumulation pattern as valueByAssetType, but counting
        // assets instead of summing their value
        return null;
    }

    /**
     * @return every distinct asset type currently held, e.g. {"EQUITY", "BOND", "CASH"}
     */
    public Set<String> distinctAssetTypes() {
        // TODO: build a Set<String> from the assets' types
        return null;
    }

    /**
     * @return the single highest-value asset in the portfolio
     * @throws NoSuchElementException if the portfolio has no assets
     */
    public Asset highestValueAsset() {
        // TODO: find the asset with the largest getValue().
        // If assets is empty, throw: new NoSuchElementException("portfolio is empty")
        return null;
    }
}
