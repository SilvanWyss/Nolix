/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.base;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public interface FormattedStringRepresentable {
  int DEFAULT_IDENTATION_LEVEL = 0;

  String DEFAULT_IDENTTATION_SYMBOL = StringCatalog.DOUBLE_SPACE;

  /**
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable}
   */
  default String toFormattedString() {
    return //
    toFormattedStringWithIndentationLevelAndIndentationSymbol(DEFAULT_IDENTATION_LEVEL, DEFAULT_IDENTTATION_SYMBOL);
  }

  /**
   * @param indentationLevel
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   * @throws RuntimeException if the given indentationLevel is negative
   */
  default String toFormattedStringWithIndentationLevel(int indentationLevel) {
    return toFormattedStringWithIndentationLevelAndIndentationSymbol(indentationLevel, DEFAULT_IDENTTATION_SYMBOL);
  }

  /**
   * @param indentationLevel
   * @param indentationSymbol
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   *         and indentationSymbol
   * @throws RuntimeException if the given indentationLevel is negative
   * @throws RuntimeException if the given indentationSymbol is empty
   */
  String toFormattedStringWithIndentationLevelAndIndentationSymbol(int indentationLevel, String indentationSymbol);
}
