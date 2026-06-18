/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.doubletool;

/**
 * A {@link IDoubleTool} provides methods to handle doubles.
 * 
 * @author Silvan Wyss
 */
public interface IDoubleTool {
  /**
   * @param value
   * @return a {@link String} representation of the given value with a dot as
   *         separator symbol for the decimal places
   */
  String toString(double value);
}
