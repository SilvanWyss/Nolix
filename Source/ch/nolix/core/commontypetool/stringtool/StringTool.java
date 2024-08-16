//package declaration
package ch.nolix.core.commontypetool.stringtool;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.RegularExpressionPatternCatalogue;

//class
/**
 * The {@link StringTool} provides methods to handle {@link String}s.
 * 
 * Of the {@link StringTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class StringTool implements IStringTool {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStringWithoutLastCharacters(final String string, final int n) {

    GlobalValidator.assertThat(n).thatIsNamed("n").isBetween(0, string.length());

    return string.substring(0, string.length() - n);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String createTabs(final int tabCount) {

    //Asserts that the given tabulatorCount is not negative.
    GlobalValidator.assertThat(tabCount).thatIsNamed("tab count").isNotNegative();

    final var stringBuilder = new StringBuilder();

    for (var i = 1; i <= tabCount; i++) {
      stringBuilder.append(CharacterCatalogue.TABULATOR);
    }

    return stringBuilder.toString();
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
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

  //method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String getInParentheses(final Object object) {

    if (object == null) {
      throw ArgumentIsNullException.forArgumentType(Object.class);
    }

    return ("(" + object + ")");
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLowerCase(final String string) {
    return //
    string != null
    && string.equals(string.toLowerCase(Locale.ENGLISH));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPascalCase(final String string) {
    return //
    string != null
    && string.equals(toPascalCase(string));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean startsWithIgnoringCase(final String string, final String prefix) {
    return //
    string != null
    && prefix != null
    && string.regionMatches(true, 0, prefix, 0, prefix.length());
  }

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public double toDouble(final String string) {

    if (!RegularExpressionPatternCatalogue.DOUBLE_PATTERN.matcher(string).matches()) {
      throw UnrepresentingArgumentException.forArgumentAndType(string, Double.TYPE);
    }

    return Double.valueOf(string);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toPascalCase(final String string) {
    return new PascalCaseTransformer().toPascalCase(string);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toCapitalSnakeCase(final String string) {
    return new CapitalSnakeCaseTransformer().toCapitalSnakeCase(string);
  }
}
