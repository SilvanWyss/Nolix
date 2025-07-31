package ch.nolix.core.commontypetool.stringtool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.commontypetool.stringtool.IStringTool;
import ch.nolix.coreapi.programatom.stringcatalog.RegularExpressionPatternCatalog;
import ch.nolix.coreapi.programatom.variable.PluralLowerCaseVariableCatalog;

/**
 * The {@link StringToolUnit} provides methods to handle {@link String}s.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class StringToolUnit implements IStringTool {

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStringWithoutLastCharacters(final String string, final int n) {

    Validator.assertThat(n).thatIsNamed("n").isBetween(0, string.length());

    return string.substring(0, string.length() - n);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createTabs(final int tabCount) {

    //Asserts that the given tabulatorCount is not negative.
    Validator.assertThat(tabCount).thatIsNamed("tab count").isNotNegative();

    final var stringBuilder = new StringBuilder();

    for (var i = 1; i <= tabCount; i++) {
      stringBuilder.append(CharacterCatalog.TABULATOR);
    }

    return stringBuilder.toString();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String getInBraces(final Object object) {

    if (object == null) {
      throw ArgumentIsNullException.forArgumentType(Object.class);
    }

    return ("{" + object + "}");
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String getInParentheses(final Object object, final Object... objects) {

    if (object == null) {
      throw ArgumentIsNullException.forArgumentName("1th object");
    }

    if (objects == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.OBJECTS);
    }

    final var stringBuilder = new StringBuilder();
    var index = 2;

    stringBuilder.append(object);

    for (final var o : objects) {

      if (o == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th object");
      }

      stringBuilder.append(",");
      stringBuilder.append(o);
      index++;
    }

    return ("(" + stringBuilder.toString() + ")");
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String getInSingleQuotes(final Object object) {

    if (object == null) {
      throw ArgumentIsNullException.forArgumentType(Object.class);
    }

    return ("'" + object + "'");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean toBoolean(final String string) {

    //Enumerates the given string.
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
    return new PascalCaseTransformer().toPascalCase(string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toCapitalSnakeCase(final String string) {
    return new CapitalSnakeCaseTransformer().toCapitalSnakeCase(string);
  }
}
