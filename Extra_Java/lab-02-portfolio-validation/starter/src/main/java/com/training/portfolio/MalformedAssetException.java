package com.training.portfolio;

/**
 * A checked exception representing a line of asset data that could not be
 * turned into a valid Asset.
 *
 * It needs to be checked, not unchecked - so extend the right superclass -
 * and it should support being created either with just a message, or with
 * a message and a cause (for wrapping another exception without losing
 * the original error).
 */
public class MalformedAssetException extends Exception {

}
