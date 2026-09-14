package com.training.portfolio;

/**
 * Thrown when a line of asset data cannot be turned into a valid Asset.
 *
 * This is a CHECKED exception (it extends Exception, not RuntimeException),
 * so - unlike an unchecked exception - the compiler will not let AssetLineParser
 * compile unless it either catches this or declares "throws MalformedAssetException".
 *
 * TODO: add the two constructors described below. Both should just forward
 * their arguments to the matching super(...) constructor on Exception.
 */
public class MalformedAssetException extends Exception {

    // TODO: constructor that takes a String message
    // public MalformedAssetException(String message) { ... }

    // TODO: constructor that takes a String message AND a Throwable cause
    // (used when this exception is wrapping another exception, e.g. a
    // NumberFormatException, so the original error isn't lost)
    // public MalformedAssetException(String message, Throwable cause) { ... }
}
