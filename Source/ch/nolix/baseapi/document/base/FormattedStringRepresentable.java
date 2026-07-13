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
  String toFormattedString();
}
