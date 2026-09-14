package com.training.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Turns raw text lines from an asset feed into Asset objects, validating
 * each one along the way.
 *
 * TODO: implement parseLine and parsePortfolio below. Run `mvn test` to
 * check your work as you go.
 */
public class AssetLineParser {

    private static final Set<String> VALID_ASSET_TYPES = Set.of("EQUITY", "BOND", "PROPERTY", "CASH");

    /**
     * Parses one line of the form "assetId,assetType,value",
     * e.g. "A1001,EQUITY,15000.00"
     *
     * @throws MalformedAssetException if:
     *      - the line does not split into exactly 3 fields, or
     *      - the assetType is not one of VALID_ASSET_TYPES, or
     *      - the value cannot be parsed as a number, or
     *      - the value is negative
     */
    public Asset parseLine(String line) throws MalformedAssetException {
        // TODO 1: split the line on "," (String.split). If the result isn't
        // exactly 3 fields, throw:
        //     new MalformedAssetException("expected 3 fields, got " + fields.length + ": " + line)

        // TODO 2: trim the assetType field and check it against VALID_ASSET_TYPES.
        // If it isn't in the set, throw:
        //     new MalformedAssetException("unknown asset type: " + assetType)

        // TODO 3: parse the value field with Double.parseDouble(...). That
        // method throws the UNCHECKED java.lang.NumberFormatException if the
        // text isn't a number - the compiler will not force you to catch it.
        // Catch it anyway, and re-throw it as a MalformedAssetException using
        // the (message, cause) constructor, so every caller of parseLine only
        // ever has to deal with one (checked) exception type:
        //
        // try {
        //     value = Double.parseDouble(rawValue);
        // } catch (NumberFormatException e) {
        //     throw new MalformedAssetException("not a number: " + rawValue, e);
        // }

        // TODO 4: if the parsed value is negative, throw:
        //     new MalformedAssetException("value cannot be negative: " + value)

        // TODO 5: return new Asset(assetId, assetType, value)

        return null;
    }

    /**
     * Parses every line in the batch. A line that fails validation is
     * skipped (its exception message is recorded in the summary's errors)
     * rather than stopping the whole batch.
     */
    public ParseSummary parsePortfolio(List<String> lines) {
        List<Asset> validAssets = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        // TODO: for each line in lines, try parseLine(line).
        //   - on success, add the returned Asset to validAssets
        //   - on MalformedAssetException, add e.getMessage() to errors and
        //     carry on with the next line (don't let one bad line stop the batch)

        return new ParseSummary(validAssets, errors);
    }
}
