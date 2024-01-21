//package declaration
package ch.nolix.core.commontypetool;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.RegularExpressionPatternCatalogue;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * The {@link GlobalStringHelper} provides methods to handle {@link String}s. Of
 * the {@link GlobalStringHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class GlobalStringHelper {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalStringHelper} can be created.
   */
  private GlobalStringHelper() {
  }

  //static method
  /**
   * @param string
   * @param length
   * @return a new {@link String} that is the given string extended to the given
   *         length with spaces.
   * @throws ArgumentIsNullException   if the given string is null.
   * @throws NegativeArgumentException if the given length is negative.
   * @throws InvalidArgumentException  if the given string is longer than the
   *                                   given length.
   */
  public static String createStringWithLength(final String string, final int length) {

    //Asserts that the given string is not null.
    GlobalValidator.assertThat(string).isNotNull();

    //Asserts that the given length is not negative.
    GlobalValidator.assertThat(length).thatIsNamed("length").isNotNegative();

    //Asserts that the given string is not longer than the given length.
    if (string.length() > length) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.STRING,
        string,
        "is longer than " + length + " with a length of " + string.length());
    }

    final var stringBuilder = new StringBuilder(string);

    GlobalSequencer
      .forCount(length - string.length())
      .run(() -> stringBuilder.append(CharacterCatalogue.SPACE));

    return stringBuilder.toString();
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
   * @param tabulatorCount
   * @return a new {@link String} consisting of as many tabulators as the given
   *         tabulatorCount says.
   * @throws NegativeArgumentException if the given tabulatorCount is negative.
   */
  public static String createTabulators(final int tabulatorCount) {

    //Asserts that the given tabulatorCount is not negative.
    GlobalValidator.assertThat(tabulatorCount).thatIsNamed("tabulator count").isNotNegative();

    final var stringBuilder = new StringBuilder();

    for (var i = 1; i <= tabulatorCount; i++) {
      stringBuilder.append(CharacterCatalogue.TABULATOR);
    }

    return stringBuilder.toString();
  }

  //static method
  //For a better performance, this implementation does not use all comfortable
  //methods.
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
  //For a better performance, this implementation does not use all comfortable
  //methods.
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
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * @param string
   * @return the given string in quotes
   * @throws ArgumentIsNullException if the given string is null.
   */
  public static String getInQuotes(final String string) {

    if (string == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.STRING);
    }

    return ("'" + string + "'");
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
        throw UnrepresentingArgumentException.forArgumentAndType(string, Boolean.class);
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
    return new PascalCaseCreator().toPascalCase(string);
  }

  //static method
  /**
   * @param string
   * @return a new {@link String} in capital snake case for the given string.
   */
  public static String toUpperSnakeCase(final String string) {
    return new CapitalSnakeCaseCreator().toCapitalSnakeCase(string);
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
