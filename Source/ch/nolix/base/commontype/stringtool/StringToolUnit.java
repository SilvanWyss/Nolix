/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.stringtool;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.stringtool.IStringTool;
import ch.nolix.baseapi.commontype.stringtool.RegularExpressionPatternCatalog;
import ch.nolix.baseapi.commontype.stringtool.StringCatalog;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;

/**
 * The {@link StringToolUnit} provides methods to handle {@link String}s.
 * 
 * @author Silvan Wyss
 */
public final class StringToolUnit implements IStringTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStringWithoutLastCharacters(final String string, final int n) {
    Validator.assertThat(string).thatIsNamed(String.class).isNotNull();
    Validator.assertThat(n).thatIsNamed("n").isBetween(0, string.length());

    return string.substring(0, string.length() - n);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createTabs(final int tabCount) {
    return StringCatalog.TABULATOR.repeat(tabCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInBraces(final Object object) {
    return StringCatalog.OPEN_BRACE + object + StringCatalog.CLOSED_BRACE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInParentheses(final Object... objects) {
    if (objects != null) {
      final var stringBuilder = new StringBuilder();
      var index = 1;

      for (final var o : objects) {
        if (index > 1) {
          stringBuilder.append(StringCatalog.COMMA);
        }

        stringBuilder.append(o);
        index++;
      }

      return StringCatalog.OPEN_BRACKET + stringBuilder + StringCatalog.CLOSED_BRACKET;
    }

    return StringCatalog.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInSingleQuotes(final Object object) {
    return StringCatalog.SINGLE_QUOTE + object + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean toBoolean(final String string) {
    return //
    switch (string) {
      case "0", "F", "FALSE", "False", "false" ->
        false;
      case "1", "T", "TRUE", "True", "true" ->
        true;
      default ->
        throw UnrepresentingArgumentException.forArgumentAndType(string, Boolean.TYPE);
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toCapitalSnakeCase(final String string) {
    return CapitalSnakeCaseTransformer.toCapitalSnakeCase(string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double toDouble(final String string) {
    if (!RegularExpressionPatternCatalog.DOUBLE_PATTERN.matcher(string).matches()) {
      throw UnrepresentingArgumentException.forArgumentAndType(string, Double.TYPE);
    }

    return Double.valueOf(string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toPascalCase(final String string) {
    return PascalCaseTransformer.toPascalCase(string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double toProportion(final String string) {
    if (string.endsWith(StringCatalog.PERCENTAGE)) {
      final var percentageStringLength = string.length() - 1;
      final var percentageString = string.substring(0, percentageStringLength);
      final var percentage = Double.valueOf(percentageString);

      return 0.01 * percentage;
    }

    return Double.valueOf(string);
  }
}
