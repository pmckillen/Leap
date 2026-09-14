package com.training.portfolio;

/**
 * Turns raw text lines from an asset feed (e.g. "A1001,EQUITY,15000.00")
 * into Asset objects, validating each one along the way.
 *
 * You'll need:
 *  - a method that parses a single line into an Asset, throwing
 *    MalformedAssetException for anything that doesn't look right -
 *    think about how many fields a line should have, which asset types
 *    are actually valid, and what makes a value invalid. Note that
 *    Double.parseDouble throws an *unchecked* exception of its own when
 *    the text isn't a number - you'll want to do something about that.
 *  - a method that parses a whole batch of lines, so that one bad line
 *    doesn't stop the rest of the batch being processed - think about
 *    how you'd report which lines failed, and why.
 *
 * ParseSummary (already provided) is one way to return both the
 * successfully-parsed assets and the errors from that batch method.
 */
public class AssetLineParser {

}
