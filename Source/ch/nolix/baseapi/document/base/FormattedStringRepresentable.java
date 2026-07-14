/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.base;

/**
 * @author Silvan Wyss
 */
public interface FormattedStringRepresentable {
  /**
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable}
   */
  default String toFormattedString() {
    return toFormattedStringWithIndentationLevel(0);
  }

  /**
   * @param indentationLevel
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   * @throws RuntimeException if the given indentationLevel is negative
   */
  String toFormattedStringWithIndentationLevel(int indentationLevel);
}
