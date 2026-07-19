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

  boolean DEFAULT_START_MULTILINER_WITH_IDENTATION_FLAG = true;

  /**
   * @return true if a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} will have multiple lines, false
   *         otherwise
   */
  boolean formattedStringWillHaveMultipleLines();

  /**
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable}
   */
  default String toFormattedString() {
    return //
    toFormattedStringWithIndentationLevelAndIndentationSymbol(
      DEFAULT_IDENTATION_LEVEL, DEFAULT_IDENTTATION_SYMBOL,
      DEFAULT_START_MULTILINER_WITH_IDENTATION_FLAG);
  }

  /**
   * @param indentationLevel
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   * @throws RuntimeException if the given indentationLevel is negative
   */
  default String toFormattedStringWithIndentationLevel(final int indentationLevel) {
    return //
    toFormattedStringWithIndentationLevelAndIndentationSymbol(
      indentationLevel,
      DEFAULT_IDENTTATION_SYMBOL,
      DEFAULT_START_MULTILINER_WITH_IDENTATION_FLAG);
  }

  /**
   * @param indentationLevel
   * @param indentationSymbol
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   *         and indentationSymbol
   * @throws RuntimeException if the given indentationLevel is negative
   * @throws RuntimeException if the given indentationSymbol is null
   */
  default String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol) {
    return //
    toFormattedStringWithIndentationLevelAndIndentationSymbol(
      indentationLevel,
      indentationSymbol,
      DEFAULT_START_MULTILINER_WITH_IDENTATION_FLAG);
  }

  /**
   * @param indentationLevel
   * @param indentationSymbol
   * @param startMultiLinerWithIndentation
   * @return a formatted {@link String} representation of the current
   *         {@link FormattedStringRepresentable} with the given indentationLevel
   *         and indentationSymbol
   * @throws RuntimeException if the given indentationLevel is negative
   * @throws RuntimeException if the given indentationSymbol is null
   */
  String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    int indentationLevel,
    String indentationSymbol,
    boolean startMultiLinerWithIndentation);
}
