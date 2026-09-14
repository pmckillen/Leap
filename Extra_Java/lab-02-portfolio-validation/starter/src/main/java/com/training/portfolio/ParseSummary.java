package com.training.portfolio;

import java.util.List;

/**
 * The result of parsing a batch of asset lines: the assets that parsed
 * successfully, plus an error message for every line that didn't.
 * Given to you complete - nothing to implement here.
 */
public class ParseSummary {

    private final List<Asset> validAssets;
    private final List<String> errors;

    public ParseSummary(List<Asset> validAssets, List<String> errors) {
        this.validAssets = validAssets;
        this.errors = errors;
    }

    public List<Asset> getValidAssets() {
        return validAssets;
    }

    public List<String> getErrors() {
        return errors;
    }
}
