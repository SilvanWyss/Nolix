//package declaration
package ch.nolix.core.commontypetool.stringtool;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.RegularExpressionPatternCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * The {@link GlobalStringTool} provides methods to handle {@link String}s. Of
 * the {@link GlobalStringTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class GlobalStringTool {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalStringTool} can be created.
   */
  private GlobalStringTool() {
  }

  //static method
  /**
   * @param string
   * @param n
   * @return a new {@link String} that is like the given string without the last n
   *         characters.
   */
  public static String createStringWithoutLastCharacters(final String string, final int n) {
    return string.substring(0, string.length() - n);
  }

  //static method
  /**
   * @param tabCount
   * @return a new {@link String} consisting of as many tabulators as the given
   *         tabCount says.
   * @throws NegativeArgumentException if the given tabCount is negative.
   */
  public static String createTabs(final int tabCount) {

    //Asserts that the given tabulatorCount is not negative.
    GlobalValidator.assertThat(tabCount).thatIsNamed("tab count").isNotNegative();

    final var stringBuilder = new StringBuilder();

    for (var i = 1; i <= tabCount; i++) {
      stringBuilder.append(CharacterCatalogue.TABULATOR);
    }

    return stringBuilder.toString();
  }

  //static method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param string
   * @return the given string in braces.
   * @throws ArgumentIsNullException if the given string is null.
   */
  public static String getInBraces(final String string) {

    if (string == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.STRING);
    }

    return ("{" + string + "}");
  }

  //static method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param string
   * @return the given string in parentheses.
   * @throws ArgumentIsNullException if the given string is null.
   */
  public static String getInParantheses(final String string) {

    if (string == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.STRING);
    }

    return ("(" + string + ")");
  }

  //static method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @param object
   * @return the {@link String} representation of the given object in quotes
   * @throws ArgumentIsNullException if the given object is null.
   */
  public static String getInSingleQuotes(final Object object) {

    if (object == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.OBJECT);
    }

    return ("'" + object + "'");
  }

  //static method
  /**
   * @param string
   * @return true if the given string is in lower case.
   */
  public static boolean isLowerCase(final String string) {

    //Handles the case that the given string is null.
    if (string == null) {
      return false;
    }

    //Handles the case that the given string is not null.
    return isLowerCaseWhenNotNull(string);
  }

  //static method
  /**
   * @param string
   * @return true if the given string is in pascal case.
   */
  public static boolean isPascalCase(final String string) {

    //Handles the case that the given string is null.
    if (string == null) {
      return false;
    }

    //Handles the case that the given string is not null.
    return isPascalCaseWhenNotNull(string);
  }

  //static method
  /**
   * @param string
   * @param prefix
   * @return true if the given string starts with the given prefix ignoring case.
   */
  public static boolean startsWithIgnoringCase(final String string, final String prefix) {
    return string.regionMatches(true, 0, prefix, 0, prefix.length());
  }

  //static method
  /**
   * @param string
   * @return the boolean the given string represents.
   * @throws InvalidArgumentException if the given string does not represent a
   *                                  boolean.
   */
  public static boolean toBoolean(final String string) {

    //Enumerates the given string.
    return switch (string) {
      case "0", "F", "FALSE", "False", "false" ->
        false;
      case "1", "T", "TRUE", "True", "true" ->
        true;
      default ->
        throw UnrepresentingArgumentException.forArgumentAndType(string, Boolean.TYPE);
    };
  }

  //static method
  /**
   * @param string
   * @return the double the given string represents.
   * @throws InvalidArgumentException if the given string does not represent a
   *                                  double.
   */
  public static double toDouble(final String string) {

    if (!RegularExpressionPatternCatalogue.DOUBLE_PATTERN.matcher(string).matches()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(string, "does not represent a double");
    }

    return Double.valueOf(string);
  }

  //static method
  /**
   * @param string
   * @return a new {@link String} in pascal case for the given string.
   */
  public static String toPascalCase(final String string) {
    return new PascalCaseTransformer().toPascalCase(string);
  }

  //static method
  /**
   * @param string
   * @return a new {@link String} in capital snake case for the given string.
   */
  public static String toCapitalSnakeCase(final String string) {
    return new CapitalSnakeCaseTransformer().toCapitalSnakeCase(string);
  }

  //static method
  /**
   * @param string
   * @return true if the given string is in lower case for the case that the given
   *         string is not null.
   */
  private static boolean isLowerCaseWhenNotNull(final String string) {
    return !string.contains(StringCatalogue.UNDERSCORE) && string.equals(string.toLowerCase(Locale.ENGLISH));
  }

  //static method
  /**
   * @param string
   * @return true if the given string is in pascal case for the case that the
   *         given string is not null.
   */
  private static boolean isPascalCaseWhenNotNull(final String string) {
    return string.equals(toPascalCase(string));
  }
}
